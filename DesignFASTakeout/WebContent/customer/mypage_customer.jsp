<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<title>[mypage_customer]</title>
<c:choose>
	<c:when test="${sessionScope.mvo!=null }">
		<table class="table table-bordered">
		   	<tr>
				<td>ID</td><td>${requestScope.memberInfo.memberId}</td>
			</tr>
			<tr>
				<td>이름</td><td>${requestScope.memberInfo.name}</td>
			</tr>
			<tr>
				<td>전화번호</td><td>${requestScope.memberInfo.phone}</td>
			</tr>
			<tr>
				<td>가입일자</td><td>${requestScope.memberInfo.regDate}</td>
			</tr>
			<tr>
				<td>잔액</td><td>${requestScope.memberInfo.balance}</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("로그인하세요");
			location.href = "template/home.jsp";
		</script>
	</c:otherwise>
</c:choose>
