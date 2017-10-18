<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script type="text/javascript">
	$(document).ready(function() {
		var storeCategory=[];
		$("#registerStore").submit(function() {
			$("#registerStore :checkbox[name=category]:checked").each(function () {
                storeCategory.push($(this).val());
            }); //업종 check
            
			if($("#img").val()==""){
				alert("이미지를 등록하세요!");
				return false;
			} else if(storeCategory.length ==0){
				alert("업종을 체크하세요!");
				return false;
			}
            alert("submit전");
         }); //submit
	}); // ready 
</script>

<form action="${pageContext.request.contextPath}/DispatcherServlet" id="registerStore" method="post" enctype="multipart/form-data">
<input type="hidden" name="command" id="command" value="registerStore">
	<table class="table table-bordered">
		<tr><td>가맹점명</td><td><input type="text" name="storeName"></td></tr>
		<tr><td>가맹점주소</td><td><input type="text" name="storeAddress"></td></tr>
		<tr><td>가맹점전화번호</td><td><input type="text" name="tel"></td></tr>
		<tr><td>영업일</td><td><input type="text" name="openday"></td></tr>
		<tr>
			<td>업종</td>
			<td>
				
				<c:forEach items="${requestScope.categoryList}" var="i">
					<input type="checkbox" name="category" value="${i}">${i}
				</c:forEach>
			</td>
		</tr>
		
		<tr><td>가맹점이미지</td><td><input type="file" name="img" id="img"></td></tr>
	</table>
	<!-- <button type="submit"></button> -->
  <input type="submit" id="register" class="btn btn-default"  value="등록하기">
  
	<span id="checkTest"></span>
</form>