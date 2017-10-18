<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>[checkPayPasswordForm]</title>
<c:if test="${requestScope.message=='incorrectPassword' }">
	<script>
		alert("비밀번호가 틀렸습니다. 다시 입력해주세요.");
	</script>
</c:if>
<c:choose>
	<c:when test="${sessionScope.mvo!=null }">
	<form action="DispatcherServlet">
	<input type="hidden" name="command" value="checkPayPassword">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th colspan="2"> 결제하기 </th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>결제아이디</td><td>${sessionScope.mvo.memberId }</td>
				</tr>
				<tr>
					<td>결제비밀번호</td>
					<td><input type="password" name="payPassword" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
							<a class="btn btn-default" href="template/home.jsp" role="button">No</a>
							<button class="btn btn-default" type="submit" id="payYes">Yes</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</c:when>
	<c:otherwise>
		<script>
			alert("로그인하세요!");
			location.href="template/home.jsp";
		</script>
	</c:otherwise>
</c:choose>