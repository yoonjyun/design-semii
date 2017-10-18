<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script src="//code.jquery.com/jquery.min.js"></script>
<title>카드 충전</title> 
<script type="text/javascript">
	alert("결제 비밀번호가 일치 하지 않습니다.");
	location.href = "${pageContext.request.contextPath}/DispatcherServlet?command=chargeForm&id=${sessionScope.mvo.memberId}";
</script>