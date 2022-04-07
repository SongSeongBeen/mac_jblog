<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="cateName" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	$(document).ready(function(){
		fetchList();
	});
	
	
	$("#cateList").on("click",".btnCateDel", function(){
		
		var cateNo = $(this).data("cno");
		var postNum = $("#tr"+cateNo).data("pnum");
		if(postNum > 0){
			alert("삭제할 수 없습니다.");
			return false;
		} 
		
		$.ajax({
			url: "${pageContext.request.contextPath}/category/remove",
			type : "post",
			data : {cateNo: cateNo},
			dataType: "json",
			success : function(result){
				//console.log(cateNo);
				//console.log(result);
				if(result == 'success'){
					$("#tr"+cateNo).remove();
				}
				else{ // fail
					alert('삭제 실패');
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
	});
	
	$("#btnAddCate").on("click", function(){
		var id = '<c:out value="${blogUser.id}"/>';
		var cateName = $('input[name="cateName"]').val();
		var description = $('input[name="description"]').val();
		
		var cateVo = {
			id:id,
			cateName:cateName,
			description:description
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/category/add",
			type : "post",
			data : cateVo,
			dataType: "json",
			success : function(categoryVo){
				$('input[name="cateName"]').val("");
				$('input[name="description"]').val("");
				render(categoryVo, "prepend");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	
	
	function fetchList(){
		var id = '<c:out value="${blogUser.id}"/>';
		
		$.ajax({
			url: "${pageContext.request.contextPath}/${blogUser.id}/getCateList",
			type : "post",
			data : {id: id},
			dataType: "json",
			success : function(categoryList){
				console.log(categoryList);
				for(var vo of categoryList){
					render(vo);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function render(vo, direction = "append"){
		str  = '';
		str += '<tr id="tr'+vo.cateNo+'" data-pnum="'+vo.postNum+'">';
		str += '	<td>'+vo.cateNo+'</td>';
		str += '	<td>'+vo.cateName+'</td>';
		str += '	<td>'+vo.postNum+'</td>';
		str += '	<td>'+vo.description+'</td>';
		str += '	<td class="text-center">';
		str += '		<img class="btnCateDel" data-cno="'+vo.cateNo+'" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	</td>';
		str += '</tr>';
		
		if(direction == "append"){
			$("#cateList").append(str);
		}
		else{
			$("#cateList").prepend(str);
		}
	}
</script>


</html>