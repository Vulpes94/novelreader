<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<link rel="stylesheet" href="${root}/css/home/index.css">
<title>Home</title>
</head>
<body>
	<div id=novelleader_main>
		<div class=follow_novels>
			<p style="text-align: left; font-size: 1rem; padding: 15px;">조회수 순</p>
			<div id=follower_novels>
			
				<c:if test="${count==0}">
					<div align="center">소설이 없습니다.</div>
				</c:if>

				<c:if test="${count>0}">
					<c:forEach var="novelhomeDto" items="${novelViewCountList}" varStatus="status">
						<div class=main_views style="width: 33.2%; float: left;" onclick="location.href='${root}/novelhome/list.do?n_num=${novelhomeDto.n_num}'">
							<div class=search_image>
								<c:if test="${novelhomeDto.n_image_name!=null}">
									<img alt="이미지 불러오는중"
										src="${root}/file/${novelhomeDto.n_image_name}"
										onerror="this.onerror=null; this.src='${root}/images/novelLabel/defaultImage.png';"
										class="novel_image">
								</c:if>
							</div>
							<div class=search_infor>
								<p align="left">
									제목 : ${novelhomeDto.n_title}<br> <br>
									작가 : ${novelhomeDto.m_nickname}<br> <br> <br>
									장르 : ${novelhomeDto.c_category_name}<br>
								</p>
							</div>
						</div>
					</c:forEach>
				</c:if> 
			</div>
		</div>
	</div>

	<!-- 페이지 번호 -->
	<%-- 1. 총페이지 수 = 전체레코드 / 한페이지당 게시물 수.. 200/10=10page, 201/10=21page 
	        2. 페이지 블럭 정하기 : 10
	           2-1) 블럭 strat 번호, end 번호 구하기
	           2-2) 계산방법
	                 - 요청페이지 : 5page,    총페이지수 : 200/10=20page,   페이지블럭:10
	                 - int startPage=(int) ((currentPage-1)/pageBlock) * pageBlock +1
	                                               ((5-1=4)/10=0.4)=0*10=0+1=1page
	                                               ((25-1=24)/10=2.4)=2*10=20+1=21page
	                 - int endPage=startPage+pageBlock-1         
	                                                1+10-1=10, 21+10=31-1=30                                              
	       3. 이전, 다음  
	       		3-1) 이전 : startPage 11  >  pageBlock 10  / startPage 11 - pageBlock 10 = 1page
	       		3-2) 다음 : endPage 10 < pageCount 200  /  startPage+pageBlock = 11 
	 	--%>

	<div align="center" class=novel_list>
		<%-- 총페이지 수 --%>
		<fmt:parseNumber var="pageCount" value="${count/boardSize+(count%boardSize==0? 0:1)}" integerOnly="true" />
		<%-- 페이지 블럭 --%>
		<c:set var="pageBlock" value="${10}" />

		<%-- 블럭의 시작번호, 끝번호 --%>
		<fmt:parseNumber var="rs" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
		<c:set var="startPage" value="${rs * pageBlock + 1}" />
		<c:set var="endPage" value="${startPage+ pageBlock - 1}" />

		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>

		<c:if test="${startPage > pageBlock}">
			<a href="${root}/novel/top10.do?pageNumber=${startPage - pageBlock}" style="color:#83aaff; font-weight:600;">[이전]</a>
		</c:if>
				
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:if test="${i == currentPage}">
				<a
					href="${root}/novel/top10.do?pageNumber=${i}"
					style="cursor: pointer; color: #001076; font-weight: 800; font-size: 18px;">[${i}]</a>
			</c:if>
			<c:if test="${i != currentPage}">
				<a
					href="${root}/novel/top10.do?pageNumber=${i}"
					style="cursor: pointer; color: #83aaff; font-weight: 600; font-size: 18px;">[${i}]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
			<a href="${root}/novel/top10.do?pageNumber=${startPage + pageBlock}" style="color:#83aaff; font-weight:600;">[다음]</a>
		</c:if>
	</div>
</body>
</html>
