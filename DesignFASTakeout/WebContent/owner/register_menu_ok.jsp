<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">
	alert("메뉴가 등록되었습니다.");
	location.href="${pageContext.request.contextPath}/DispatcherServlet?command=getStoreList&nowPage=1";
</script>