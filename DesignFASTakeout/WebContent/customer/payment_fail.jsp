<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>잔액이 부족하여 결제를 실패하였습니다!</h1>
 
<a href="${pageContext.request.contextPath}/DispatcherServlet?command=getCartList&nowPage=1">장바구니로</a> 