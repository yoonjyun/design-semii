<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<br><br><br><br><br>
<div class="col-md-3"></div>
<div class="row">
<div class="col-md-3">
<p align="center">
	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=registerMemberForm&auth=customer">
		<img alt="로고" src="${pageContext.request.contextPath}/upload/personn.png" width="300px">	
	</a> 
</p>
</div>
<div class="col-md-3">
<p align="center">
	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=registerMemberForm&auth=owner">
		<img alt="로고" src="${pageContext.request.contextPath}/upload/personn.png" width="300px">
	</a>
</p>
</div>
</div>
<br>
<div class="row">
<div class="col-md-4"></div>
<div class="col-md-2">
<p align="center">
	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=registerMemberForm&auth=customer">
		고객 회원가입
	</a>
	</p>
</div>
<div class="col-md-2">
<p align="center">
	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=registerMemberForm&auth=owner">
		가맹점주 회원가입
	</a>
	</p>
</div>
<div class="col-md-4"></div>
</div>
<div class="col-md-3"></div>