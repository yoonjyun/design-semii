<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<title>카드 충전</title>
<script type="text/javascript">
	alert("충전 되었습니다.");
	location.href="${pageContext.request.contextPath}/DispatcherServlet?command=getStoreList&nowPage=1";
</script>