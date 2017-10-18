<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%-- 주문처리상태변경 script -ajax로 --%>

<h3>가맹점주 현재 주문 내역</h3>
<c:choose>
	<c:when test="${sessionScope.mvo!=null }">
		<c:set value="${requestScope.lvo}" var="lvo"/>
		<c:set value="${lvo.pagingBean}" var="pb"/>
		<table class="table" id = "testTable">
			<thead>
				<tr>
					<th>주문번호</th><th>주문자아이디<th>메뉴명</th>
					<th>HOT/ICE</th><th>사이즈</th><th>수량</th><th>총금액</th>
					<th>주문날짜</th><th>주문상태</th><th>주문상태변경</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lvo.list }" var="i" >
				<tr>
					<td >${i.orderNo }</td><td>${i.memberId }</td><td>${i.menuVO.menuName }</td>
					<td>${i.menuVO.menuDetailVO.hotIce }</td><td>${i.menuVO.menuDetailVO.size }</td>
					<td>${i.quantity }</td><td>${i.orderPrice }</td>
					<td>${i.orderDate }</td><td>${i.orderStatus }</td>
					<td><span id="spanChangeBtn">
					<a href="${pageContext.request.contextPath }/DispatcherServlet?command=changeOrder&orderNo=${i.orderNo }">
					 	<button type="button" class="btn btn-danger">주문 완료</button>
					 </a></span>
					 </td>
					 
				</tr>
				</c:forEach>
			</tbody>
		</table> 
		
		<BR><HR><BR>
		<a href="${pageContext.request.contextPath }/DispatcherServlet?command=ownerAllOrderList&nowPage=1">전체 주문 내역</a>
		<BR><BR><BR>
		
		<ul class="pagination">
			<c:if test="${pb.previousPageGroup==true }">
				<li><a href="DispatcherServlet?command=ownerCurrentOrderList&nowPage=${pb.startPageOfPageGroup-1 }">&laquo;</a></li>
			</c:if>
			<c:forEach var="pageNum" begin="${pb.startPageOfPageGroup }" end="${pb.endPageOfPageGroup }">
				<c:choose>
					<c:when test="${pageNum==pb.nowPage }">
						<li class="active"><a href="DispatcherServlet?command=ownerCurrentOrderList&nowPage=${pageNum}">${pageNum}</a></li> 
					</c:when>
					<c:otherwise>
						<li><a href="DispatcherServlet?command=ownerCurrentOrderList&nowPage=${pageNum}">${pageNum}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pb.nextPageGroup==true}">
			    <li><a href="DispatcherServlet?command=ownerCurrentOrderList&nowPage=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
			</c:if>
		</ul>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			location.href="${pageContext.request.contextPath}/member/notlogin.jsp";
		</script>
	</c:otherwise>
</c:choose>