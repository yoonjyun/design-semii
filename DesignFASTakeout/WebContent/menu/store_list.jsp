<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css">

<div class="col-lg-1"></div>
<div class="col-lg-10 container-fluid">
<c:set value="${requestScope.lvo}" var="lvo"/>
<c:set value="${lvo.pagingBean}" var="pb"/>
	
<div class="row container-fluid">
  <div class="row storeList container-fluid">
    <c:forEach items="${lvo.list}" var="store" varStatus="count">
		<c:if test="${count.index<3}">
		<!-- 리스트 첫 번째 줄 -->
			<div class="col-lg-4 store container-fluid">
				<a href="${pageContext.request.contextPath}/DispatcherServlet?command=getMenuList&storeNo=${store.storeNo}&nowPage=1">
				 	 <img alt="사진" src="${pageContext.request.contextPath}${store.storeImgUrl}" id="storeImg">	
				 </a><br><br>	
			  	${store.storename} <br><br>
			</div>
		</c:if>
	</c:forEach>
  </div>
  <div class="row storeList container-fluid">
	 	<c:forEach items="${lvo.list}" var="store" varStatus="count">
			<c:if test="${count.index>=3}">
			<!-- 리스트 두 번째 줄 -->
			<div class="col-lg-4 store">
				<a href="${pageContext.request.contextPath}/DispatcherServlet?command=getMenuList&storeNo=${store.storeNo}&nowPage=1">
				 	 <img alt="사진" src="${pageContext.request.contextPath}${store.storeImgUrl}" >	
				 </a><br>	
			  	${store.storename} <br><br>
			</div>
			</c:if>
		</c:forEach>
  </div>
</div>
	
	
	
	
	
  <div class="row center-block pagination"  align="center">
	  <ul class="pagination">
		<c:if test="${pb.previousPageGroup==true}">
		    <li><a href="DispatcherServlet?command=getStoreList&nowPage=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
		</c:if>

	  	<c:forEach var="pageNum"  begin="${pb.startPageOfPageGroup}"  end="${pb.endPageOfPageGroup}">
	  		<c:choose>
	  			<c:when test="${pageNum==pb.nowPage}">
			    	<li class="active"><a href="DispatcherServlet?command=getStoreList&nowPage=${pageNum}">${pageNum}</a></li>
	  			</c:when>
	  			<c:otherwise>
			    	<li><a href="DispatcherServlet?command=getStoreList&nowPage=${pageNum}">${pageNum}</a></li>
	  			</c:otherwise>
	  		</c:choose>
	  	</c:forEach>

		<c:if test="${pb.nextPageGroup==true}">
		    <li><a href="DispatcherServlet?command=getStoreList&nowPage=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
		</c:if>
	  </ul>
	</div>
</div>  
  
<div class="col-md-1"></div>


