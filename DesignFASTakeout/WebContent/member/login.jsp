<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css">
	<%-- 메인이미지 --%>	<br><br><br><br>
	<p align="center">
	<br>
			<a href="${pageContext.request.contextPath}/DispatcherServlet?command=getStoreList&nowPage=1">
			<img alt="로고" src="${pageContext.request.contextPath}/upload/logooo.png" width="600px" align="middle">
		</a>
	</p>
	 	<br><br><br><br>
	 	<%-- 로그인폼 --%>
	<div id="loginForm">
		<form action="${pageContext.request.contextPath}/DispatcherServlet" method="post">
			<input type="hidden" name="command" value="login">
		  <div class="form-group">
		    <input type="text" class="form-control" name="userId" placeholder="고객아이디를 입력하세요">
		  </div>
		  <div class="form-group">
		    <input type="password" class="form-control" name="userPwd" placeholder="암호를 입력하세요">
		  </div>
		  <div class="row center">
		<p align="center">
		&nbsp;&nbsp;&nbsp;&nbsp;
		  <button type="submit" class="btn btn-default">로그인</button>&nbsp;
		  <a class="btn btn-default" href="${pageContext.request.contextPath}/DispatcherServlet?command=registerIndex" role="button">회원가입</a>
		</p>
		  </div>
		</form>
		<br><br><br><br>
	</div>