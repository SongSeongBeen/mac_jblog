<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		
		<!--메인 해더 자리 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		
		<form id="search-form" action="${pageContext.request.contextPath}/search" method="get">
			<fieldset>
				<input type="text" name="keyword" >
				<button id="btnSearch" type="submit" >검색</button>
			</fieldset>
			
			<fieldset>
				<label for="rdo-title">블로그 제목</label> 
				<input id="rdo-title" type="radio" name="kwdOpt" value="optTitle" > 
				
				<label for="rdo-userName">블로거 이름</label> 
				<input id="rdo-userName" type="radio" name="kwdOpt" value="optName" > 
			</fieldset>
		</form>
		
		
		<div id="searchArea">
			<div id="resultList">
				<c:forEach items="${pMap.searchList}" var="vo">
					<table class="" border="1">
						<colgroup>
							<col style="width:10%">
							<col style="width:50%">
							<col style="width:20%">
							<col style="width:20%">
						</colgroup>
						<tr>
							<td><img src="${pageContext.request.contextPath}/upload/${vo.logoFile}"></td>
							<td><a href="${pageContext.request.contextPath}/${vo.id}">${vo.blogTitle}</a></td>
							<td>${vo.userName}(${vo.id})</td>
							<td>${vo.joinDate}</td>
						<tr>
					</table>
				</c:forEach>
			</div>
			
			
			<div id="searchPaging">
				<ul>
					<c:if test="${!empty pMap}">
						<c:if test="${pMap.prev}">
							<li><a href="${pageContext.request.contextPath}/search?keyword=${param.keyword}&crtPage=${pMap.startPageBtnNo-1}">◀</a></li>
						</c:if>
						<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
							<c:choose>
								<c:when test="${param.crtPage == page}">
									<li class="active">
										<a href="${pageContext.request.contextPath}/search?keyword=${param.keyword}&crtPage=${page}">
											${page}
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="${pageContext.request.contextPath}/search?keyword=${param.keyword}&crtPage=${page}">
											${page}
										</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${pMap.next}">
							<li><a href="${pageContext.request.contextPath}/search?keyword=${param.keyword}&crtPage=${pMap.endPageBtnNo+1}">▶</a></li>
						</c:if>
					</c:if>
				</ul>
			</div>
		
		</div>
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
	
	</div>
	<!-- //center-content -->
</body>
</html>