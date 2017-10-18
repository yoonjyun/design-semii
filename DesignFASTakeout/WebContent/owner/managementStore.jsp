<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//code.jquery.com/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#registerSBtn").click(function(){
			location.href="DispatcherServlet?command=registerStore";
		}); // registerSBtn click
		$("#registerMBtn").click(function(){
			location.href="DispatcherServlet?command=registerMenuForm";
		}); // registerMBtn click		
	}); //ready
</script>
<section class="ARTICLE ">
<br>
<div class="row">
	<h4>${sessionScope.mvo.name}님의 업체관리</h4>
</div>
<c:choose>
	<c:when test="${requestScope.svo==null }">
		<div class="row">
		<div class="col-md-10"></div>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/DispatcherServlet?command=registerStoreForm">
				<button type="button" class="btn btn-warning" id="registerSBtn">+업체등록</button></a>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row">
			<div class="col-md-10"></div>
				<div class="col-md-2">
					<button type="button" class="btn btn-warning" id="registerMBtn">+메뉴등록</button>
				</div>
		</div>
		<div class="row">
		<div style="color:#663300; font-size:20px; font-weight: bold">
			${requestScope.svo.storename }<br>
		</div>
		<div class="col-md-4">
			<br><br>
			<img alt="" src="${pageContext.request.contextPath}${requestScope.svo.storeImgUrl}" width="200" height="150">	
		</div>
  		<div class="col-md-8">
			<table class="table table-bordered">
				<tr>
					<th>가맹점주 ID</th><td>${requestScope.svo.id }</td>
				</tr>
				<tr>
					<th>가맹점 등록 번호</th><td>${requestScope.svo.storeNo }</td>
				</tr>
				<tr>
					<th>가맹점 주소</th><td>${requestScope.svo.address }</td>
				</tr>
				<tr>
					<th>가맹점 전화번호</th><td>${requestScope.svo.tel }</td>
				</tr>
				<tr>
					<th>가맹점 영업시간</th><td>${requestScope.svo.openDay }</td>
				</tr>
				<tr>
					<th>가맹점 업종</th>
					<td><c:forEach items="${requestScope.svo.category }" var="category">${category }<br></c:forEach></td>
				</tr>
			</table>
		</div>
		</div>
	</c:otherwise>
</c:choose>		
</section>
<hr>
<section class="article">
<c:if test="${requestScope.menuUrl!=null }">
	<jsp:include page="/owner/managementMenu.jsp">
		<jsp:param value="${requestScope.svo.storeNo }" name="storeNo"/>
	</jsp:include>
</c:if>
</section>









