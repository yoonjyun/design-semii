<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	function deleteForm(){
		return confirm("선택하신 품목을 삭제 하시겠습니까?")
	}
	
	function payOrder(){
		return confirm("장바구니에 담은 품목을 결제 하시겠습니까?");
	}
</script>
<c:choose>
	<c:when test="${sessionScope.mvo != null}">
	<h3>장바구니</h3>
	<table>
			<thead>
				<tr>
					<th>주문번호</th>
					<th>가맹점</th>
					<th>메뉴명</th>
					<th>메뉴설명</th>
					<th>주문날짜</th>
					<th>HOT/ICE</th>
					<th>사이즈</th>
					<th>수량</th>
					<th>총금액</th>
					<th>주문상태</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
			<c:set value="${requestScope.lvo.list}" var="list"/>
			<c:forEach items="${list}" var="i">
				<tr>
					<td>${i.orderNo }</td>
					<td>${i.menuVO.storeName }</td>
					<td>${i.menuVO.menuName }</td>
					<td>${i.menuVO.menuInfo }</td>
					<td>${i.orderDate }</td>
					<td>${i.menuVO.menuDetailVO.hotIce }</td>
					<td>${i.menuVO.menuDetailVO.size }</td>
					<td>${i.quantity }</td>
					<td>${i.orderPrice }</td>
					<td>${i.orderStatus }</td>
					<td>
					<form action="${pageContext.request.contextPath}/DispatcherServlet">
						<input type = "hidden" name = "command" value = "delete">
						<input type = "hidden" name = "orderNo" value = "${i.orderNo}">
						<input type = "submit" value = "삭제" onsubmit ="return deleteForm()">
					</form>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<tr><th>
			<form action="${pageContext.request.contextPath}/DispatcherServlet">
				<input type = "hidden" name = "command" value = "checkPayPasswordForm">
				<input type = "submit" value = "주문 결제 하기" onsubmit = "return payOrder()">
			</form>
			</th></tr>
		</table>
		<br><hr><br>
		<div class="row center-block" align="center">
		  <ul class="pagination">
			<c:if test="${pb.previousPageGroup==true}">
			    <li><a href="DispatcherServlet?command=getCartList&nowPage=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
			</c:if>
	
		  	<c:forEach var="pageNum"  begin="${pb.startPageOfPageGroup}"  end="${pb.endPageOfPageGroup}">
		  		<c:choose>
		  			<c:when test="${pageNum==pb.nowPage}">
				    	<li class="active"><a href="DispatcherServlet?command=getCartList&nowPage=${pageNum}">${pageNum}</a></li>
		  			</c:when>
		  			<c:otherwise>
				    	<li><a href="DispatcherServlet?command=getCartList&nowPage=${pageNum}">${pageNum}</a></li>
		  			</c:otherwise>
		  		</c:choose>
		  	</c:forEach>
	
			<c:if test="${pb.nextPageGroup==true}">
			    <li><a href="DispatcherServlet?command=getCartList&nowPage=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
			</c:if>
		  </ul>
		</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			location.href="${pageContext.request.contextPath}/member/notlogin.jsp";
		</script>
	</c:otherwise>
</c:choose>

