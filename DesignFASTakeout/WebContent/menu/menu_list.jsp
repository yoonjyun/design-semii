<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>

<style>
      .menu {
	    width: 200px;
        height: 200px;
        margin: 20px;
        text-align: center;
      	display: inline-block;
      }
      .menuList{
      	width: 100%;
      	text-align: center;
      	padding-top: 90px;
      }
      .storeInfo{
      	padding-top: 10px;
      }
      .storeInfoTable{
      	text-align: center;
      }
</style>
	<%-- 가맹점 정보  --%>
  
<div class="col-md-1"></div>
<div class="col-md-10 menuList">
	<div class="row storeInfo">
  	<table class="table table-bordered storeInfoTable">
  		<tr>
  			<td colspan="4"><h2>가맹점 정보</h2></td>
  		</tr>
  		<tr>
  			<td>${svo.storename}</td>
  			<td>${svo.address}</td>
  			<td>${svo.tel}</td>
  			<td>${svo.openDay}</td>
  		</tr>  	
  	</table>
  </div>
	<div class="row">
	<c:set value="${requestScope.lvo}" var="lvo"/>
	<c:set value="${lvo.pagingBean}" var="pb"/>
		  <div class="row" >
			  <c:forEach items="${lvo.list}" var="menu">
					 <div class="menu">
						 <a href="${pageContext.request.contextPath}/DispatcherServlet?command=getMenuDetail&menuNo=${menu.menuNo}">
						 	 <img alt="" src="${pageContext.request.contextPath}/upload/coffee.jpg" width="200" height="200">	
						 </a>	
					  	 ${menu.menuName} <br><br>
					</div> 
			  </c:forEach>
		  </div> <br><br><br><br>
		  <div class="row center-block" align="center">
			  <ul class="pagination">
				<c:if test="${pb.previousPageGroup==true}">
				    <li><a href="DispatcherServlet?command=getMenuList&nowPage=${pb.startPageOfPageGroup-1}&storeNo=${lvo.list[0].storeNo}">&laquo;</a></li>
				</c:if>
		
			  	<c:forEach var="pageNum"  begin="${pb.startPageOfPageGroup}"  end="${pb.endPageOfPageGroup}">
			  		<c:choose>
			  			<c:when test="${pageNum==pb.nowPage}">
					    	<li class="active"><a href="DispatcherServlet?command=getMenuList&nowPage=${pageNum}&storeNo=${lvo.list[0].storeNo}">${pageNum}</a></li>
			  			</c:when>
			  			<c:otherwise>
					    	<li><a href="DispatcherServlet?command=getMenuList&nowPage=${pageNum}&storeNo=${lvo.list[0].storeNo}">${pageNum}</a></li>
			  			</c:otherwise>
			  		</c:choose>
			  	</c:forEach>
		
				<c:if test="${pb.nextPageGroup==true}">
				    <li><a href="DispatcherServlet?command=getMenuList&nowPage=${pb.endPageOfPageGroup+1}&storeNo=${lvo.list[0].storeNo}">&raquo;</a></li>
				</c:if>
			  </ul>
		</div>
	</div>
</div>
<div class="col-md-1"></div>
 