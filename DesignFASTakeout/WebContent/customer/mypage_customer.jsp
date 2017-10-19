<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<title>[mypage_customer]</title>
<c:choose>
	<c:when test="${sessionScope.mvo!=null }">
	<div class="table">
		<table class="table table-striped">
		   	<tr>
				<th>ID</th><td>${requestScope.memberInfo.memberId}</td>
			</tr>
			<tr>
				<th>이름</th><td>${requestScope.memberInfo.name}</td>
			</tr>
			<tr>
				<th>전화번호</th><td>${requestScope.memberInfo.phone}</td>
			</tr>
			<tr>
				<th>가입일자</th><td>${requestScope.memberInfo.regDate}</td>
			</tr>
			<tr>
				<th>잔액</th><td>${requestScope.memberInfo.balance}</td>
			</tr>
		</table>
	</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("로그인하세요");
			location.href = "${pageContext.request.contextPath}/welcome.jsp";
		</script>
	</c:otherwise>
</c:choose>
