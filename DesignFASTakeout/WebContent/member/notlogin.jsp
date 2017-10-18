<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">
	alert("로그인 후 이용 바랍니다.");
	location.href="${pageContext.request.contextPath}/welcome.jsp";
</script>