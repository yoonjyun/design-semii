<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FASTakeout :: 판교지역 선주문 서비스 </title>

    <!--  부트스트랩 CDN + 제이쿼리 CDN -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css">
<%-- 	<script src="${pageContext.request.contextPath }/css/css.css" type="text/css"></script> --%>
	<!-- <style type="text/css">
		.header{
			min-height: 100px;
			background-color: white;
		}
		.main{
			min-height: 70%;
		}
		.center{
			border-left: 1px solid Gainsboro;
		}
	</style> -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#command").change(function() {
				var command = document.getElementById("command").value;
				if(command=="customer"){
					location.href="${pageContext.request.contextPath}/DispatcherServlet?command=customerCurrentOrderList";
				}else if(command=="owner"){				
					location.href="${pageContext.request.contextPath}/DispatcherServlet?command=ownerCurrentOrderList&nowPage=1";
				}else if(command=="chargeForm"){
					location.href="${pageContext.request.contextPath}/DispatcherServlet?id=${sessionScope.mvo.memberId}&command="+command;
				}else if(command=="managementStore"){
					location.href="${pageContext.request.contextPath}/DispatcherServlet?nowPage=1&command="+command;				
				}else{
					location.href="${pageContext.request.contextPath}/DispatcherServlet?command="+command;
				}
			})
		});
	</script>
  </head>
  <body>
    <!-- HEADER -->
   <section class="header">
    <div class="row">
		 <div class="col-md-3"></div>
		 <div class="col-md-6 center">
		  	<p align="center">
			  <br>
			  	 <a href="${pageContext.request.contextPath}/welcome.jsp">
			  	 	<img alt="로고" src="${pageContext.request.contextPath}/upload/logooo.png" width="500px">
			  	 </a>		  
			 </p>
		</div>
		 <div class="mainlink">
		 	 <div class="col-md-3">
		  	<p align="left">
		  		<a href="${pageContext.request.contextPath}/DispatcherServlet?command=intro">서비스소개</a> 
		  	  	&nbsp;
		  		<a href="template/home.jsp">메뉴보기</a>		 	
		  	  	&nbsp;
			<c:if test="${sessionScope.mvo != null }"> <%-- session 검사 --%>
		  		<c:choose>
		  			<c:when test="${sessionScope.mvo.auth=='customer' }">
						<select id="command" class="btn btn-default">
					        <option value="">${mvo.name}님</option>
					        <option value="${sessionScope.mvo.auth}">주문내역보기</option>
					        <option value="viewMypage">마이페이지</option>
					        <option value="chargeForm">결제카드 충전</option>
					        <option value="logout">로그아웃</option>
					   </select>
					   &nbsp; <a href="#">장바구니</a> 
		  			</c:when>
		  			<c:otherwise>
		  				<select id="command" class="btn btn-default">
					        <option value="">${mvo.name}님</option>
					        <option value="${sessionScope.mvo.auth}">주문현황보기</option>
					        <option value="viewMypage">마이페이지</option>
					        <option value="managementStore">업체관리</option>
					        <option value="logout">로그아웃</option>
					   </select>
		  			</c:otherwise>
		  		</c:choose>
		   	     </c:if>
		   	    </p>
		   	  </div>   
			</div>
			</div>

	</section>


 	<!-- MAIN -->
	<section class="main">
		<div class="row">
		  <div class="col-md-2">
		  
		  </div>
		  <div class="col-md-8 center">
		  	 <jsp:include page="${requestScope.url}"></jsp:include>
		  </div>
		  <div class="col-md-2">
		  
		  </div>
		</div>
    </section>



	<!--  FOOTER  -->
   <section class="footer">
      <div class="container">
       	<jsp:include page="footer.jsp"></jsp:include>
      </div>
   </section>

  </body>

</html>
