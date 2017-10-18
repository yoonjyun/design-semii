<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<style>
      .menu {
        width: 200px;
        height: 160px;
        float: left;
        margin-left: 25px;
        margin-right:25px;
        text-align: center;
      }
</style>
<c:set value="${requestScope.lvo}" var="lvo"/>
<c:set value="${requestScope.lvo.pagingBean}" var="pb"/>
<div class="row">
	<c:forEach items="${lvo.list }" var="menu">
			<div class="menu">
			<a href="${pageContext.request.contextPath}/DispatcherServlet?command=getMenuDetail">
			<%-- <a href="${pageContext.request.contextPath}/DispatcherServlet?command=getMenuDetail&menuNo=${lvo.list.menuNo}"> --%>
				 <img alt="" src="${pageContext.request.contextPath}${menu.menuImgUrl}" width="200" height="200">
			</a>
			 ${menu.menuName} 
			</div>
	</c:forEach>
</div>
<br><br><br>
  <div class="row  center-block" align="center">
		  <ul class="pagination">
			<c:if test="${pb.previousPageGroup==true}">
			    <li><a href="DispatcherServlet?command=managementStore&nowPage=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
			</c:if>
	
		  	<c:forEach var="pageNum"  begin="${pb.startPageOfPageGroup}"  end="${pb.endPageOfPageGroup}">
		  		<c:choose>
		  			<c:when test="${pageNum==pb.nowPage}">
				    	<li class="active"><a href="DispatcherServlet?command=managementStore&nowPage=${pageNum}">${pageNum}</a></li>
		  			</c:when>
		  			<c:otherwise>
				    	<li><a href="DispatcherServlet?command=managementStore&nowPage=${pageNum}">${pageNum}</a></li>
		  			</c:otherwise>
		  		</c:choose>
		  	</c:forEach>
	
			<c:if test="${pb.nextPageGroup==true}">
			    <li><a href="DispatcherServlet?command=managementStore&nowPage=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
			</c:if>
		  </ul>
</div>
