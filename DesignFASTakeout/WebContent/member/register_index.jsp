<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<br><br><br><br><br>
<p align="center">
	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=registerMemberForm&auth=customer">고객 회원가입</a> 
	&nbsp;
	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=registerMemberForm&auth=owner">가맹점주 회원가입</a>
</p>