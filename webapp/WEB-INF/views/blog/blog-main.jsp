<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">
					
					<div id="nick">${blogUser.userName}(${blogUser.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${categoryList}" var="cateVo">
							<li class="cateListClass" data-cno="${cateVo.cateNo}"><a href="${pageContext.request.contextPath}/${blogUser.id}?cateNo=${cateVo.cateNo}">${cateVo.cateName}</a></li>
						</c:forEach>
					</ul> 
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
				<div id="postBox" class="clearfix">
						<div id="postTitle" class="text-left"><strong>08.페이징</strong></div>
						<div id="postDate" class="text-left"><strong>2020/07/23</strong></div>
						<div id="postNick">${blogUser.userName}(${blogUser.id})님</div>
				</div>
				<!-- //postBox -->
			
				<div id="post" >
					
				</div>
				<!-- //post -->
				
				<div id="commentsArea">
					<table border="1">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 80%;">
							<col style="width: 10%;">
						</colgroup>
						<tbody id="cmtInput">
						
						</tbody>
					</table>
					
					<table>
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 65%;">
							<col style="width: 20%;">
							<col style="width: 5%;">
						</colgroup>
						<tbody id="cmtList">
						
						</tbody>
					</table>
					
				</div>
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<tbody id="postList">
							<c:forEach items="${pMap.postList}" var="post">
								<tr>
									<td class="text-left"> <a href="" data-pno="${post.postNo}">${post.postTitle}</a></td>
									<td class="text-right">${post.regDate}</td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
					<div id="paging">
							<ul>
								<c:if test="${pMap.prev}">
									<li><a id="prevPage" href="">◀</a></li>
								</c:if>
								<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
									<c:choose>
										<c:when test="${param.crtPage == page}">
											<li class="active">
												<a class="pageGo" href="">
													${page}
												</a>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a class="pageGo" href="">
													${page}
												</a>
											</li>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
								
								<c:if test="${pMap.next}">
									<li><a id="nextPage" href="">▶</a></li>
								</c:if>
								
							</ul>
					</div>
					
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
		</div>	
		<!-- //content -->
		<div class=></div>
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	$(document).ready(function(){
		var postNo = $("a[data-pno]").first().data("pno");
		
		if(postNo != undefined) {
			renderView(postNo);
			
		}
		else{
			$("#postTitle").html("<strong>등록된 글이 없습니다.</strong>");
			$("#postTitle").attr("data-pno","0");
			$("#postDate").html("");
			$("#post").html("");
			$("#postNick").html("");
			$("#cmtInput").html("");
		}
	});
	
	$("#postList").on("click", "a" ,function(){
		var postNo = $(this).data("pno");
		renderView(postNo);
		return false;
	});
	/*
	attr(data-) : 변경된 값 적용 O
	data  : 변경 x
	*/
	$("#cmtInput").on("click", "#cmtAddBtn" ,function(){
		var postNo = $("#postTitle").attr("data-pno");
		var userNo = '<c:out value="${authUser.userNo}"/>';
		var cmtContent = $("#cmtContent").val();
		console.log(postNo);
		
		var commentsVo = {
			postNo : postNo,
			userNo : userNo,
			cmtContent : cmtContent
		};
		
		$.ajax({
			url: "${pageContext.request.contextPath}/comments/add",
			type : "post",
			data : commentsVo,
			dataType: "json",
			success : function(comments){
				console.log(comments);
				renderCmt(comments,"prepend");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	$("#cmtList").on("click", ".cmtDel" ,function(){
		var cmtNo = $(this).data("cmtno");
		console.log("삭제 "+cmtNo);
		
		$.ajax({
			url: "${pageContext.request.contextPath}/comments/remove",
			type : "post",
			data : {cmtNo : cmtNo},
			dataType: "json",
			success : function(result){
				console.log(result);
				if(result = "success"){
					$("#cmt"+cmtNo).remove();	
				}
				else{
					alert("삭제 실패");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	$(".pageGo").on("click",function(){
		var page = $(this).text();
		
		const URLSearch = new URLSearchParams(location.search);
		var cateNo = URLSearch.get('cateNo');
		if(cateNo == null)
			cateNo = $(".cateListClass").first().data("cno");
		
		location.href = '${pageContext.request.contextPath}/${blogUser.id}?cateNo='+cateNo+'&crtPage='+page;
		return false;
	});
	
	$("#prevPage").on("click",function(){
		const URLSearch = new URLSearchParams(location.search);
		var cateNo = URLSearch.get('cateNo');
		if(cateNo == null)
			cateNo = $(".cateListClass").first().data("cno");
		var nextPageNo = '<c:out value="${blogUser.id}"/>';
		
		location.href = '${pageContext.request.contextPath}/${blogUser.id}?cateNo='+cateNo+'&crtPage=${pMap.startPageBtnNo-1}';
		return false;
	});
	
	$("#nextPage").on("click",function(){
		const URLSearch = new URLSearchParams(location.search);
		var cateNo = URLSearch.get('cateNo');
		if(cateNo == null)
			cateNo = $(".cateListClass").first().data("cno");
		var nextPageNo = '<c:out value="${blogUser.id}"/>';
		
		location.href = '${pageContext.request.contextPath}/${blogUser.id}?cateNo='+cateNo+'&crtPage=${pMap.endPageBtnNo+1}';
		return false;
	});
	
	
	function renderView(postNo){
		console.log("view "+postNo);
		$.ajax({
			url: "${pageContext.request.contextPath}/post/read",
			type : "post",
			data : {postNo: postNo},
			dataType: "json",
			success : function(post){
				$("#postTitle").html("<strong>"+post.postTitle+"</strong>");
				$("#postTitle").attr("data-pno",postNo);
				$("#postDate").html("<strong>"+post.regDate+"</strong>");
				$("#post").html(post.postContent);
				fetchCmt(postNo);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function fetchCmt(postNo){
		
		var str  = '';
		 	str += '<c:if test="${!empty authUser}">';
		 	str += '	<tr>';
		 	str += '		<td>${authUser.userName}</td>';
		 	str += '		<td><input  id="cmtContent" type="text" name="cmtContent"></td>';
			str += '		<td><button id="cmtAddBtn" type="button">저장</button></td>';
			str += '	</tr>';
			str += '</c:if>';
		$("#cmtInput").html("");
		$("#cmtInput").append(str);
		
		$.ajax({
			url: "${pageContext.request.contextPath}/comments/list",
			type : "post",
			data : {postNo: postNo},
			dataType: "json",
			success : function(commentsList){
				$("#cmtList").html("");
				for(var cmt of commentsList){
					renderCmt(cmt);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function renderCmt(cmt, direction = "append"){
		
		var userNo = '<c:out value="${authUser.userNo}"/>';
		var str  = '';
			str += '<tr id="cmt'+cmt.cmtNo+'">';
			str += '	<td>'+cmt.userName+'</td>';
			str += '	<td>'+cmt.cmtContent+'</td>';
			str += '	<td>'+cmt.regDate+'</td>';
			if(userNo == cmt.userNo){
				str += '<td><img class="cmtDel" data-cmtno="'+cmt.cmtNo+'" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>';
			}
			str += '</tr>';
		
		if(direction == "append")
			$("#cmtList").append(str);
		else
			$("#cmtList").prepend(str);
	}
	
</script>

</html>