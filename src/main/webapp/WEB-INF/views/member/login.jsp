<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Mochiy+Pop+P+One&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${root}/css/login/loginpage.css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인페이지</title>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script type="text/javascript" src="${root}/javascript/xhr/xhr.js"></script>
  
    <script type="text/javascript">
        function loginFormWithKakao(kakaoJavascript) {
         	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
    		Kakao.init(kakaoJavascript);
         
            Kakao.Auth
                .loginForm({
                    success: function (authObj) {
                        //alert(JSON.stringify(authObj));
                        var params = "token=" + JSON.stringify(authObj);
                        sendRequest(
                            "POST",
                            "http://localhost:8181/novelreader/member/kakaologin.do",
                            params, fromServer);

                        function fromServer() {
                            if (xhr.readyState == 4 && xhr.status == 200) {
                                //alert(xhr.responseText);
                            }
                        }
                    },
                    fail: function (err) {
                        console.log(JSON.stringify(err));
                    },
                })
        }

        function naverlogin(naverClientId,naverRedirectUri) {
            window.open('https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' + naverClientId + '&redirect_uri='+ naverRedirectUri +'&state=dc1bf16a5614dbb206e9b9629154c1ef','naver','width=490,height=500,status=no,toolbar=no,resizable=yes,scrollbars=no')
        }
    </script>
</head>
<body>
<!-- properties 내용 로드 -->
	<spring:eval var="kakaoJavascript" expression="@properties['kakao.javascript']"/>
	<spring:eval var="naverClientId" expression="@properties['naver.client_id']"/>
	<spring:eval var="naverRedirectUri" expression="@properties['naver.redirect_uri']"/>
	
	<c:if test="${numSess !=null}">
    	<script type="text/javascript">
        	location.href = "/index.do";
    	</script>
	</c:if>
	
	<c:if test="${numSess == null}">
    <div class="loginbox">
        <form action="${root}/member/loginOk.do" method="post">
            <h1 style="margin-top : 10px; color : #0096fa; cursor: pointer" onclick="location.href = '/index.do'">NOVELREADER</h1>
            <h3 style="color : #757c80; font-size : 14px; font-weight : bold;">소설의 모든것</h3>
            <h2 style="color : rgb(104, 109, 112); font-size:18px; font-weight : bold;
            margin-top : 30px; margin-bottom : -5px;">로그인</h2>
            <div class="inputbox">
                <input type="text" name="m_id" placeholder="ID를 입력하세요" required>
                <input type="password" name="m_pw" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="social">
                <h3>다음으로 로그인</h3>
                <a id="reauthenticate-popup-btn"
                   href="javascript:loginFormWithKakao('${kakaoJavascript}')"> <img
                        src="${root}/images/login/kakao.png"
                        alt="카카오 로그인 버튼"/>
                </a>
                <a href="javascript:naverlogin('${naverClientId}','${naverRedirectUri}')"> <img
                        src="${root}/images/login/naver.jpg"/>
                </a>
                <img src="${root}/images/login/google.jpg" />
            </div>
            <input class="registerbutton" type="submit" value="로그인"/>
            <input class="registerbutton" type="button" value="회원가입" onclick="location.href='${root}/member/register.do'" style="margin-top:10px;/">
        </form>
        <h3 class="footer">Copyright ⓒ 2022 by GTEC Kmove JP-A All Pictures cannot be copied without permission.</h3>
    </div>
	</c:if>
	
</body>
</html>