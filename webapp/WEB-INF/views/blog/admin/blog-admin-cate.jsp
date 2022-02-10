<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${id}/admin/writeForm">글작성</a></li>
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
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
		      	
		      	<input type="hidden" id="cateId" value="${id}">
			
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

	$(document).ready(function() {
	
		//리스트 그리기
		fetchList();
	
	});
	
	//카테고리 추가 버튼 눌렀을때
	$("#btnAddCate").on("click", function(){
		
		var cateName = $("[name='name']").val();
		var desc = $("[name='desc']").val();
		var id = $("#cateId").val();
		
		var categoryVo = {
			cateName: cateName, 
			description: desc,
			id: id
		};
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/category/add",		
			type : "post",
			//contentType : "application/json",
			data : categoryVo,

			//dataType : "json",
			success : function(categoryVo){
				/*성공시 처리해야될 코드 작성*/
				render(categoryVo, "down");
				
				var cateName = $("[name='name']").val("");
				var desc = $("[name='desc']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
	});
	
	//삭제버튼 눌렀을때
	$("#cateList").on("click", ".btnCateDel", function(){
		
		var cateNo = $(this).data("no");
		
		$.ajax({

			url : "${pageContext.request.contextPath}/category/delete",
			type : "post",
			//contentType : "application/json",
			data : {cateNo: cateNo},

			dataType : "json",
			success : function(result) {
				console.log(result);
				
				if(result === 's'){
					/*성공시 처리해야될 코드 작성*/

					//  해당 테이블 html 삭제
					$("#cate"+cateNo).remove();	 
					
				}else {
					alert("삭제할 수 없습니다.");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});

	//리스트 출력
	function fetchList() {
		
		$.ajax({
			url : "${pageContext.request.contextPath}/category/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(cateList) {
				/*성공시 처리해야될 코드 작성*/

				for (var i = 0; i < cateList.length; i++) {
					render(cateList[i], "down"); //방명록리스트 그리기
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	//리스트 그리기
	function render(categoryVo, updown) {
		
		var str = '';
		str += '<tr id="cate'+categoryVo.cateNo+'">';
		str += '	<td>'+categoryVo.cateNo+'</td>';
		str += '	<td>'+categoryVo.cateName+'</td>';
		str += '	<td>'+categoryVo.postCnt+'</td>';
		str += '	<td>'+categoryVo.description+'</td>';
		str += '	<td class=' + 'text-center'+ '> ';
		str += '	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-no="'+categoryVo.cateNo+'" >';
		str += '	</td>';
		str += '</tr>';
 
		if(updown == 'down'){
			$("#cateList").prepend(str);
		} else {
			$("#cateList").append(str);
		}
	
	}

</script>















</html>
