<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gemini LLM Test</title>
</head>
<body>
<h1>Gemini LLM 테스트 페이지</h1>

<form action="${pageContext.request.contextPath}/gemini" method="get">
    <label for="prompt">프롬프트 입력:</label>
    <input type="text" id="prompt" name="prompt" size="100" value="부자 개발자가 되는 법"><br><br>
    <input type="submit" value="Gemini API 요청">
</form>

<br>

<h2>Gemini API 응답:</h2>
<div id="apiResponse">
    <%-- GeminiController에서 전달된 응답 결과가 여기에 표시됩니다. --%>
    <%
        String apiResponse = (String) request.getAttribute("apiResponse");
        if (apiResponse != null && !apiResponse.isEmpty()) {
            out.println("<pre>" + apiResponse + "</pre>");
        } else {
            out.println("응답 결과가 없습니다.");
        }
    %>
</div>

</body>
</html>