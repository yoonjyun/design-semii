<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FASTakeout :: 판교지역 선주문 서비스 </title>

    <!--  부트스트랩 CDN + 제이쿼리 CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
<!-- HEADER -->
    <section class="header">
    	<div class="row">
		  <div class="col-md-2">
		  	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=intro">서비스소개</a> &nbsp; 
		  	<a href="${pageContext.request.contextPath}/DispatcherServlet?command=getStoreList&nowPage=1">메뉴보기</a>
		  	<!-- <a href="template/home.jsp">메뉴보기</a> -->
		  </div>
		  <div class="col-md-8 center">
			  
		  </div>
		  <div class="col-md-2">
			
		  </div>
		</div>
	</section>



<!-- MAIN -->
	<section class="main">
		<div class="row">
			  <div class="col-md-2"></div>
			  <div class="col-md-8 center">
			 
				  <c:choose>
				  	<c:when test="${requestScope.url != null}">		<%-- url이 있으면 서비스 소개 페이지로 이동 --%>
				  		<jsp:include page="intro.jsp"></jsp:include>
				  	</c:when>
				  	<c:otherwise>									<%-- url이 없으면  welcome + login 기능을 하는 login 페이지로 이동 --%>
				  		<jsp:include page="member/login.jsp"></jsp:include>
				  	</c:otherwise>
				  </c:choose>
			  </div>
			  <div class="col-md-2"></div>
		</div>
    </section>

</body>
</html>


