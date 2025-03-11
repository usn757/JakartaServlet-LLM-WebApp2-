package org.example.servletpreview.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/gemini")
public class GeminiController extends Controller {

    @Override
    void initLogger() {
        logger = Logger.getLogger(GeminiController.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");


        String prompt = req.getParameter("prompt");
        if (prompt == null || prompt.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("프롬프트가 입력되지 않았습니다.");
            return;
        }

        var url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> bodyMap = new HashMap<>();

        bodyMap.put("contents", mapper.readValue("""
                [{
                    "parts":[{"text": "%s"}]
                }]
                """.formatted(prompt).strip(), JsonNode.class)); // JsonNode로 파싱

        String bodyString = mapper.writeValueAsString(bodyMap);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?key=%s".formatted(dotenv.get("GEMINI_KEY"))))
                .POST(HttpRequest.BodyPublishers.ofString(
                        bodyString,
                        StandardCharsets.UTF_8)) // 명시적으로 UTF-8 설정
                .header("Content-Type", "application/json")
                .build();

        String apiResponseResult; // 응답 결과를 저장할 변수 초기화


        try {
            HttpResponse<String> apiResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("API 응답: " + apiResponse);
            if (apiResponse.statusCode() == 200) {
                String responseBody = apiResponse.body();
                logger.info("API 응답 성공: " + responseBody);

                JsonNode rootNode = mapper.readTree(responseBody);
                JsonNode candidatesNode = rootNode.get("candidates");
                if (candidatesNode != null && candidatesNode.isArray() && !candidatesNode.isEmpty()) {
                    JsonNode contentNode = candidatesNode.get(0).get("content");
                    if (contentNode != null) {
                        JsonNode partsNode = contentNode.get("parts"); // "parts" 배열 노드 가져오기
                        if (partsNode != null && partsNode.isArray()) {
                            StringBuilder textContent = new StringBuilder();
                            for (JsonNode partNode : partsNode) { // "parts" 배열 순회
                                JsonNode textNode = partNode.get("text"); // 각 part에서 "text" 노드 가져오기
                                if (textNode != null) {
                                    textContent.append(textNode.asText()); // 텍스트 내용 추출 및 StringBuilder에 추가
                                }
                            }
                            apiResponseResult = textContent.toString(); // 추출된 텍스트 내용을 String으로 변환하여 저장
                        } else {
                            apiResponseResult = "API 응답 구조 변경: 'parts' 배열을 찾을 수 없습니다.";
                            logger.warning(apiResponseResult);
                        }


                    } else {
                        apiResponseResult = "API 응답에서 content를 추출 실패";
                        logger.warning(apiResponseResult);
                    }
                } else {
                    apiResponseResult = "API 응답에서 candidates를 추출 실패";
                    logger.warning(apiResponseResult);
                }

            } else {
                apiResponseResult = "Gemini API 요청 실패, 상태 코드: " + apiResponse.statusCode() + ", 응답 본문: " + apiResponse.body();
                logger.warning(apiResponseResult);
                resp.setStatus(apiResponse.statusCode());
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("apiResponse", apiResponseResult); // 응답 결과를 request 속성에 저장
        req.getRequestDispatcher("/index.jsp").forward(req, resp); // index.jsp로 forward
    }
}
