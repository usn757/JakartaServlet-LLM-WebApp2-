# jaKarta_WEPAPP 

**Gemini API 연동 웹 애플리케이션**

## 프로젝트 개요

본 프로젝트는 Google의 Gemini API를 활용하여 텍스트 생성 기능을 제공하는 웹 애플리케이션입니다.  사용자는 웹 페이지에서 프롬프트를 입력하고, Gemini API를 통해 생성된 텍스트 응답을 확인할 수 있습니다.

**주요 기능:**

* **프롬프트 입력 폼:** 웹 페이지에서 텍스트 프롬프트를 입력할 수 있는 폼 제공
* **Gemini API 연동:** 입력된 프롬프트를 Gemini API에 전달하여 텍스트 생성 요청
* **API 응답 결과 표시:** Gemini API로부터 받은 JSON 응답에서 텍스트 내용을 추출하여 웹 페이지에 표시

## 웹 애플리케이션 소개

**jaKarta\_WEPAPP** 웹 애플리케이션은 Gemini API를 직접 체험하고 텍스트 생성 기능을 간편하게 테스트해볼 수 있도록 제작되었습니다.  별도의 API 개발 없이 웹 브라우저를 통해 Gemini API의 기능을 바로 확인할 수 있습니다.

**주요 화면 구성:**

1.  **프롬프트 입력 영역:**  웹 페이지 상단에 텍스트 프롬프트를 입력할 수 있는 텍스트 박스가 제공됩니다.  여기에 원하는 질문이나 텍스트 생성 요청을 자유롭게 입력할 수 있습니다.
2.  **API 요청 버튼:** "Gemini API 요청" 버튼을 클릭하면 입력된 프롬프트가 Gemini API로 전송되고, 텍스트 생성 요청이 시작됩니다.
3.  **응답 결과 표시 영역:**  Gemini API로부터 응답받은 텍스트 결과가 웹 페이지 하단에 깔끔하게 표시됩니다.  JSON 구조 없이 텍스트 내용만 추출하여 보여주므로, Gemini API의 텍스트 생성 결과를 직관적으로 확인할 수 있습니다.

**사용 시나리오:**

* Gemini API 텍스트 생성 기능 데모 확인
* 다양한 프롬프트 입력 및 Gemini API 응답 테스트
* Gemini API 연동 웹 애플리케이션 개발 학습

**UI 예시:**

```
+-----------------------------------------------------+
|  [ 텍스트 프롬프트 입력 영역 (Textarea)             ]  |
+-----------------------------------------------------+
|                      [ Gemini API 요청 ] 버튼         |
+-----------------------------------------------------+

-------------------- 응답 결과 --------------------
[ Gemini API 응답 텍스트 표시 영역 (pre 태그) ]
-----------------------------------------------------
```

**본 웹 애플리케이션은 Jakarta EE 와 Maven 기반으로 IntelliJ IDEA를 사용하여 쉽고 빠르게 개발되었습니다. Gemini API를 처음 접하거나, 간단하게 Gemini API 연동 기능을 테스트하고 싶은 개발자, 또는 Gemini API의 텍스트 생성 능력을 직접 확인하고 싶은 사용자에게 유용합니다.**

## 프로젝트 생성 with IntelliJ IDEA

본 프로젝트는 IntelliJ IDEA의 Jakarta EE Maven 프로젝트 템플릿을 사용하여 간편하게 생성되었습니다.

**프로젝트 생성 단계:**

1.  **IntelliJ IDEA 실행:** IntelliJ IDEA를 실행합니다.
2.  **새 프로젝트 생성:** `File` -> `New` -> `Project...` 메뉴를 선택합니다.
3.  **프로젝트 유형 선택:**  왼쪽 메뉴에서 `Maven` 을 선택하고, `Jakarta EE` 를 선택합니다.
4.  **프로젝트 설정:** 템플릿-웹어플리케이션, jdk-temurin17, 종속성-servlet
5.  **생성 완료:** `Create` 버튼을 클릭하여 프로젝트를 생성합니다.

IntelliJ IDEA가 제공하는 Jakarta EE Maven 템플릿을 사용하면 기본적인 웹 애플리케이션 구조가 자동으로 생성되어 Jakarta EE 개발을 빠르게 시작할 수 있습니다.

## 시작하기

### 로컬 환경에서 실행 (개발 환경)

1.  **JDK 및 Maven 설치:** Java Development Kit (JDK) 17 이상 버전과 Maven 빌드 도구를 설치합니다.
2.  **IntelliJ IDEA 프로젝트 열기:** IntelliJ IDEA에서 `File` -> `Open...` 메뉴를 선택하고, 생성한 프로젝트 디렉토리를 선택하여 프로젝트를 엽니다.
3.  **환경 변수 설정:** 프로젝트 루트 디렉토리에 `.env` 파일을 생성하고 Gemini API 키를 설정합니다.
    ```
    GEMINI_KEY=YOUR_GEMINI_API_KEY
    ```
4.  **Maven 빌드:** IntelliJ IDEA Maven Tool 창에서 `package` Lifecycle을 실행하거나, 터미널에서 Maven 빌드 명령어를 실행합니다.
    ```bash
    mvn clean package -DskipTests
    ```
5.  **Tomcat 서버 실행:**  빌드된 WAR 파일을 Tomcat 웹 서버의 `webapps` 디렉토리에 배포하고 Tomcat 서버를 실행합니다.  IntelliJ IDEA의 Tomcat Run/Debug Configuration을 사용하여 Tomcat 서버를 실행할 수도 있습니다.
6.  **웹 브라우저 접속:** 웹 브라우저에서 `http://localhost:8080/[Context Path]/index.jsp` (Context Path는 프로젝트 설정에 따라 다를 수 있습니다) 또는 `http://localhost:8080` (ROOT Context 배포 시) 로 접속합니다.

### Koyeb 환경에서 실행 (배포 환경)

본 프로젝트는 Docker 컨테이너로 빌드되어 [Koyeb](https://www.google.com/search?q=https://www.google.com/url%3Fsa%3DE%26source%3Dgmail%26q%3Dhttps://www.google.com/url%3Fsa%3DE%252526source%3Dgmail%252526q%3Dhttps://www.koyeb.com/) 클라우드 플랫폼에 배포되었습니다.

* **배포 URL:** [Koyeb 배포 URL (실제 Koyeb 앱 URL로 변경)] (예: `https://your-koyeb-app.koyeb.app:8000` 또는 포트 80/443 사용 시 `https://your-koyeb-app.koyeb.app`)

## 사용 방법

1.  웹 애플리케이션에 접속합니다. (로컬 또는 Koyeb 배포 URL)
2.  `index.jsp` 페이지에서 텍스트 입력 폼에 Gemini API에 요청할 프롬프트를 입력합니다.
3.  "Gemini API 요청" 버튼을 클릭합니다.
4.  Gemini API 응답 결과가 웹 페이지 하단에 표시됩니다.

## Dockerfile

프로젝트는 Docker를 사용하여 컨테이너화되어 있습니다.  `Dockerfile` 은 다음과 같이 구성되어 있습니다.

```dockerfile
# Maven 빌드 스테이지
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Tomcat 배포 스테이지
FROM tomcat:10-jdk17-temurin

EXPOSE 8000  # 또는 EXPOSE 8080 (Koyeb 설정과 일치하도록 수정)
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]
```

* **Maven 빌드 스테이지:** Maven 이미지를 사용하여 프로젝트를 빌드하고 WAR 파일을 생성합니다.
* **Tomcat 배포 스테이지:** Tomcat 이미지를 기반으로 WAR 파일을 배포하고 웹 애플리케이션을 실행합니다.

## Koyeb 배포

본 프로젝트는 [Koyeb](https://www.google.com/search?q=https://www.google.com/url%3Fsa%3DE%26source%3Dgmail%26q%3Dhttps://www.google.com/url%3Fsa%3DE%252526source%3Dgmail%252526q%3Dhttps://www.koyeb.com/) 클라우드 플랫폼을 사용하여 배포되었습니다. Koyeb은 Docker 컨테이너 기반의 PaaS (Platform-as-a-Service) 로, 간편하게 웹 애플리케이션을 배포하고 관리할 수 있도록 지원합니다.

