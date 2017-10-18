<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script src="//code.jquery.com/jquery.min.js"></script>
<style>
      .menu {
        width: 200px;
        height: 200px;
        float: left;
        margin: 10px;
        text-align: center;
      }
</style>
<title>전체 주문 내역</title>
<h3>전체 주문 내역</h3>
<c:choose>
	<c:when test="${sessionScope.mvo != null}">
<div class="col-md-1"></div>
<div class="col-md-10">
	<c:set value="${requestScope.lvo}" var="lvo"/>
	<c:set value="${lvo.pagingBean}" var="pb"/>
	 	 <div class="row" >
	 	 <table class="table table-bordered">
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
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lvo.list}" var="i">
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
					 </tr>
			  	 </c:forEach>
				<tr>
					<td>656</td>
					<td>맘모스커피</td>
					<td>아메리카노</td>
					<td>좋은원두로 갈은 커피</td>
					<td>2017-10-14</td>
					<td>HOT</td>
					<td>Large</td>
					<td>2</td>
					<td>4000</td>
					<td>조리중</td>
				</tr>
		 	 	<tr>
					<td>611</td>
					<td>한솥도시락</td>
					<td>칠리포크</td>
					<td>국내산 돼지고기로 만든 도시락</td>
					<td>2017-10-10</td>
					<td>-</td>
					<td>-</td>
					<td>3</td>
					<td>14,400</td>
					<td>조리완료</td>
				</tr>
		 	 	<tr>
					<td>514</td>
					<td>스타벅스</td>
					<td>그린티 프라푸치노</td>
					<td>자바칩을 갈아넣은 </td>
					<td>2017-10-14</td>
					<td>ICE</td>
					<td>SMALL</td>
					<td>2</td>
					<td>8000</td>
					<td>조리완료</td>
				</tr>
		 	 	<tr>
					<td>439</td>
					<td>맘모스커피</td>
					<td>아메리카노</td>
					<td>좋은원두로 갈은 커피</td>
					<td>2017-10-14</td>
					<td>HOT</td>
					<td>Large</td>
					<td>2</td>
					<td>4000</td>
					<td>조리완료</td>
				</tr>
		 	 	<tr>
					<td>263</td>
					<td>맘모스커피</td>
					<td>아메리카노</td>
					<td>좋은원두로 갈은 커피</td>
					<td>2017-10-14</td>
					<td>HOT</td>
					<td>Large</td>
					<td>2</td>
					<td>4000</td>
					<td>조리완료</td>
				</tr>
		 	 	<tr>
					<td>115</td>
					<td>맘모스커피</td>
					<td>아메리카노</td>
					<td>좋은원두로 갈은 커피</td>
					<td>2017-10-14</td>
					<td>HOT</td>
					<td>Large</td>
					<td>2</td>
					<td>4000</td>
					<td>조리완료</td>
				</tr>
		 	 
			</tbody>
		</table>
	  	</div> <br><br><br><br>


			
		<BR><HR><BR>
		<a href = "${pageContext.request.contextPath}/DispatcherServlet?command=customerCurrentOrderList&id=${sessionScope.mvo.memberId}">현재 주문 내역</a>
		<BR><BR><BR>
		
		<div class="row center-block" align="center">
		  <ul class="pagination">
			<c:if test="${pb.previousPageGroup==true}">
			    <li><a href="DispatcherServlet?command=customerAllOrderList&nowPage=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
			</c:if>
	
		  	<c:forEach var="pageNum"  begin="${pb.startPageOfPageGroup}"  end="${pb.endPageOfPageGroup}">
		  		<c:choose>
		  			<c:when test="${pageNum==pb.nowPage}">
				    	<li class="active"><a href="DispatcherServlet?command=customerAllOrderList&nowPage=${pageNum}">${pageNum}</a></li>
		  			</c:when>
		  			<c:otherwise>
				    	<li><a href="DispatcherServlet?command=customerAllOrderList&nowPage=${pageNum}">${pageNum}</a></li>
		  			</c:otherwise>
		  		</c:choose>
		  	</c:forEach>
	
			<c:if test="${pb.nextPageGroup==true}">
			    <li><a href="DispatcherServlet?command=customerAllOrderList&nowPage=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
			</c:if>
		  </ul>
		</div>
</div>
<div class="col-md-1"></div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			location.href="${pageContext.request.contextPath}/member/notlogin.jsp";
		</script>
	</c:otherwise>
</c:choose>
