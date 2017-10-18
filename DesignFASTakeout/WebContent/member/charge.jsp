<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:choose>
	<c:when test="${sessionScope.mvo.memberId != null}">
		<h3>카드 충전</h3>
		<form action="${pageContext.request.contextPath}/DispatcherServlet">
			<input type="hidden" name="command" value="charge">
			<input type="hidden" name="id" value="${sessionScope.mvo.memberId}">
			<table class="table table-bordered">
				<tr>
					<th>카드 금액</th>
					<td><input type="text" name="balance" readonly="readonly" value="${requestScope.balance }"></td>
				</tr>
				<tr>
					<th>충전 금액</th>
					<td><input type="text" name="money" required="required"></td>
				</tr>
				<tr>
					<th>결제 비밀 번호</th>
					<td><input type="password" name="payPassword" required="required"></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" value="충전"></td>
				</tr>
		</table>
		</form>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			location.href="/member/notlogin.jsp";
		</script>
	</c:otherwise>
</c:choose>