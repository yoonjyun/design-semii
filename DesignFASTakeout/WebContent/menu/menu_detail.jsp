<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
<script type="text/javascript">
	 $(document).ready(function(){
		
		$("#cart").click(function() {
			if(confirm("장바구니에 담으시겠습니까?")==true){
				$.ajax({
					type:"post",
					url:"DispatcherServlet",
					data: $("#addCartMenu").serialize(),
					success:function(data){ //data로 서버의 응답정보가 할당 
						alert(data);
						var moveToCart=confirm("장바구니로 이동하시겠습니까?");
						if(moveToCart==true){
							location.href="${pageContext.request.contextPath}/DispatcherServlet?command=getCartList&nowPage=1";	
						} else{
							location.href="${pageContext.request.contextPath}/DispatcherServlet?command=getStoreList&nowPage=1";
						}
					}
				}); //success
			}else{
				return false;
			}// if~else문
		}); //ajax
	}); //ready  
function calculate(){
	var mdNo = $("#menuOption").val();
	var priceText=$("#menuOption option:selected").text();
	var price = priceText.split('-')[2].replace("원","");
	var quantity = $("#quantity").val();
	var totalprice = price*quantity;
	$("#totalPrice").html(totalprice);
}
</script>


<c:set value="${requestScope.mdList}" var="menuDetailList"/>
<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-5">
		 <img alt="" src="${pageContext.request.contextPath}/upload/coffee.jpg" width="300" height="300">
	</div>
	<div class="col-md-5">
		<form action="" id="addCartMenu">
			<input type="hidden" name="command" value="cartAdd">
			<!-- orderPrice는 컨트롤러에서 계산  -->
			<input type="hidden" name="menuDetailNo" id="menuDetailNo">
			<input type="hidden" name="detailPrice" id="detailPrice">
			<input type="hidden" name="orderPrice" id = "orderPrice">
			
				${menuDetailList[0].menuName} <br>
				${menuDetailList[0].menuInfo} <br>
				 	
				<select id="menuOption" onchange="calculate()">
					<option value="">--옵션선택--</option>
					<c:forEach items="${menuDetailList}" var="mdList">
						<c:set value="${mdList.menuDetailVO}" var="md"/>
						<option value="${md.menuDetailNo}" >
						${md.hotIce}-${md.size}-${md.menuPrice}원
						</option>	
					</c:forEach>
				</select> <br><br>
					<span id="optionPrice" ></span> <br><br>
					수량  <input type="number" name="quantity" id="quantity" required="required" min="1" onchange="calculate()" value="1"> 개 <br><br>
					<span id="totalPrice"></span> <br><br> 
			  		<input type="button" id="cart" value="장바구니 담기">
			  		<br><br>
	  	</form>
	</div>
	<div class="col-md-1"></div>
</div>





