<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">
	alert("삭제 되었습니다.");
	location.href="${pageContext.request.contextPath}/DispatcherServlet?command=getCartList&nowPage=1";
</script>