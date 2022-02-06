<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="${root}/css/novelhome/listStyle.css">
<script type="text/javascript"
	src="${root}/javascript/novelpost/list.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>소설로고 리스트</title>
</head>
<body>
	<c:if test="${nNumSess !=null}">
		<c:set var="nNumSess" value="${nNumSess}" scope="session" />
	</c:if>
		<div class="wrap" style="margin-top:100px;">
			<!-- 표지 -->
			<div class="upbox">
				<div class="leftbox">
					<!-- 이미지불러오기 -->
					<div class="image_container">
						<img src="${root}/file/${novelHomeDto.n_image_name}" style ="border-radius : 15px;"/>
					</div>
					<button style="margin-left: 0px; color: rgb(209, 206, 206);"
						onclick="">첫화보기</button>
				</div>
			
				<!-- 소설 -->
				<div class="rightbox">
					 <div class="right_up">
	                    <h1 style ="margin-top : 10px;">${novelHomeDto.n_title}</h1>
	                    <img src="https://img2.quasarzone.co.kr/img/data/img/editor/1906/1906___1791183465.jpg">
	                    <span class = "right_up_span">전체 삭제</span>
	                </div>
					<div class="right_up2">
						<h3 onclick="javascript:goProfile('${root}','${nickname}')"
							style="cursor: pointer">
							작가 : <span style="color: rgb(148, 21, 127); font-weight: bold;">${nickname}</span>
						</h3>
					</div>
					<div class="right_middle">
						<img src="https://novelpia.com/img/new/icon/count_view.png"
							style="margin-top: 2px; margin-right: 2px;">
						<h3>11111 명</h3>
						<img src="https://novelpia.com/img/new/icon/count_book.png"
							style="margin-top: 2px; margin-right: 2px;">
						<h3>11111 회차</h3>
					</div>
					<div class=right_bottom>
						<p style="font-size: 15px;">소설 줄거리:${novelHomeDto.n_summary}</p>
					</div>
					<div class="right_bottom2">
						<c:forEach var="categoryDto" items="${categoryList}" varStatus="status">
								<div class="container" style="position: absolute; margin-left: ${(status.index % 4)*50}px; margin-top:${Math.floor(status.index / 4)*100}px">
					                <div class="front card" style="width:50px;">
					                    <label for="${categoryDto.c_category_id}">${categoryDto.c_category_name}</label>
					                    <input type="radio" id="${categoryDto.c_category_id}" name="c_category_id" value="${categoryDto.c_category_id}" style="display: inline-block;">  
					                    <c:if test="${categoryDto.c_category_id == c_category_id}">
					                    	<script type="text/javascript">
					                    		document.getElementById("${categoryDto.c_category_id}").checked = true;
					                    	</script>
					                    </c:if>
					                </div>
					        	</div>
						</c:forEach>
						
				</div>
			</div>
		</div>
		<div class="bottombox">
			<div class="bottom_up">
				<h3>회차리스트</h3>
				<span onClick="location.href='${root}/novelpost/register.do'">등록</span>
			</div>
			<!-- 회차리스트 -->
			<c:if test="${novelPostList.size() > 0 }">
				<c:forEach var="novelPostDto" items="${novelPostList}"
					varStatus="status">
					<div class="bottom_down">
						<div class="bottom_down1">
							<h3 onclick="javascript:readCheck('${novelPostDto.n_POST_NUM}', '${root}')" style="cursor: pointer; font-weight:bold; font-size:17px;">
								Ep.${status.count} ${novelPostDto.n_POST_TITLE}</h3> 
							<span onclick="javascript:updateCheck('${novelPostDto.n_POST_NUM}', '${root}','${novelPostDto.n_num}')" >수정</span>
							<span onclick="javascript:deleteCheck('${novelPostDto.n_POST_NUM}', '${root}','${novelPostDto.n_num}')" >삭제</span>
						</div>
						<div class="bottom_down2">
							<h3><fmt:formatDate value="${novelPostDto.n_POST_TIME}" pattern="yyyy-MM-DD HH:mm:ss" /></h3>
							<h3 style="margin-left : 10px;"><img src="${root}/images/novelpost/people.svg" style="width:18px; height:18px; margin-top:-3px;">1111</h3>
		                  	<h3 style="margin-left : 10px;"><img src="https://novelpia.com/img/new/icon/count_good.png" style="margin-top:-5px;">1111</h3>
		                  	<h3 style="margin-left : 10px;"><img src="https://t1.daumcdn.net/cfile/blog/241BC2475465ABB42F" style="width:10px; height:10px; margin-top:-4px;">1111</h3>
		                  	<h3 style="margin-left : 10px;">조회수 : ${novelPostDto.n_POST_VIEWCOUNT}</h3>
                		</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div align="center">
			<c:if test="${count>0}">
				<%-- 총  페이지 수  --%>
				<fmt:parseNumber var="pageCount"
					value="${count/boardSize +  (count%boardSize == 0 ? 0:1)}"
					integerOnly="true" />
			
				<%-- 페이지 블럭 --%>
				<c:set var="pageBlock" value="${2}" />
				<%-- 블럭의 시작번호, 끝번호 --%>
				<fmt:parseNumber var="rs" value="${(currentPage - 1)/pageBlock}"
					integerOnly="true" />
				<c:set var="startPage" value="${rs * pageBlock + 1}" />
				<c:set var="endPage" value="${startPage + pageBlock - 1}" />
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}" />
				</c:if>
				<c:if test="${startPage > pageBlock}">
					<a href="${root}/novelhome/list.do?pageNumber=${startPage-pageBlock}&n_num=${n_num}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="${root}/novelhome/list.do?pageNumber=${i}&n_num=${n_num}">[${i}]</a>
				</c:forEach>
				<c:if test="${endPage < pageCount}">
					<a href="${root}/novelhome/list.do?pageNumber=${startPage+pageBlock}&n_num=${n_num}">[다음]</a>
				</c:if>
				<div>pageCount : ${pageCount} startPage : ${startPage} endPage :
					${endPage}
				</div>
			</c:if>
		</div>

</div>
</body>
</html>