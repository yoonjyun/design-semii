<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=0.75">

    <title>FASTakeout :: 판교지역 선주문 서비스 </title>

    <!--  부트스트랩 CDN + 제이쿼리 CDN -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
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
				}else if(command=="getCartList"){
					location.href="${pageContext.request.contextPath}/DispatcherServlet?nowPage=1&command="+command;				
				}else{
					location.href="${pageContext.request.contextPath}/DispatcherServlet?command="+command;
				}
			})
		});
	</script>
	<style type="text/css">
		.header{
			min-height: 180px;
			background-image: url("${pageContext.request.contextPath}/upload/6.png");
		}
	</style>
</head>
  
<body>
    <!-- HEADER -->
	<section class="header">
		<div class="row">
			<div class="col-md-4">
				<p align="right"><br><br><br><br><br><br><br>
			  		<a href="${pageContext.request.contextPath}/DispatcherServlet?command=intro" style="color: white; font-weight: bold">서비스소개</a> &nbsp;&nbsp;
			  		<a href="template/home.jsp" style="color: white; font-weight: bold; font-size: 15px">메뉴보기</a>	&nbsp;&nbsp;
				</p>
			</div>
			<div class="col-md-4 center">
			  	<br><br>
			  	<%-- <p style="font-size:50px; text-shadow: 0 0 1px #006600 , 0 0 10px #ff0000 ; ">
					<a href = "${pageContext.request.contextPath }/frontController?command=allpostlist">
					FASTakeOut
					</a>
				</p> --%>
		  		<p align="center">
				  	 <a href="${pageContext.request.contextPath}/DispatcherServlet?command=getStoreList&nowPage=1">
				  	 	<img alt="로고" src="${pageContext.request.contextPath}/upload/logo3.png" width="600px">
				  	 </a>		  
				 </p>
			</div>
			<div class="col-md-4">
				<div class="mainlink"><br><br><br>
			  	  	<%-- session 검사 --%>
					<c:if test="${sessionScope.mvo != null }"> <%-- session 검사 --%>
			  			<c:choose>
			  				<c:when test="${sessionScope.mvo.auth=='customer' }">
								<select id="command" class="btn btn-default">
							        <option value="">${mvo.name}님</option>
							        <option value="${sessionScope.mvo.auth}">주문내역보기</option>
							        <option value="getCartList">장바구니</option>
							        <option value="viewMypage">마이페이지</option>
							        <option value="chargeForm">결제카드 충전</option>
							        <option value="logout">로그아웃</option>
						   		</select> 
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
				</div>
			</div>
		</div>
	<div class="row"></div>
	</section>
<br><br>

 	<!-- MAIN -->
	<section class="main">
		<div class="row">
		  	<div class="col-md-1"></div>
		 	<div class="col-md-10 center">
		  	 	<jsp:include page="${requestScope.url}"></jsp:include>
		  	</div>
		  	<div class="col-md-1"></div>
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
