<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath}" />

	<c:if test="${numSess !=null}">
		<c:set var="numSess" value="${numSess}" scope="session" />
		<c:set var="permissionSess" value="${permissionSess}" scope="session" />
		<c:set var="platformSess" value="${platformSess}" scope="session" />
		<c:set var="nicknameSess" value="${nicknameSess}" scope="session" />
		
		<c:if test="${photoNameSess !=null}">
			<c:set var="photoAddressSess" value="${root}/file/member/${photoNameSess}" scope="session" />
		</c:if>
		<c:if test="${photoNameSess ==null}">
			<c:set var="photoAddressSess" value="${root}/images/profile/profile_default.png" scope="session" />
		</c:if>
		
		<script type="text/javascript">
			location.href = "/index.do";
		</script>
	</c:if>

	<c:if test="${numSess == null}">
		<script type="text/javascript">
			alert("입력하신 정보가 존재하지 않습니다.");
			location.href = "${root}/member/login.do";
		</script>
	</c:if>
</body>
</html>