<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>

<style>
       .store {
	    width: 300px;
        height: 300px;
        margin: 20px;
        text-align: center;
      	display: inline-block;
      }
      .storeList{
      	width: 100%;
      	text-align: center;
      }
</style>


<div class="col-md-1"></div>
<div class="col-md-10 storeList">
<c:set value="${requestScope.lvo}" var="lvo"/>
<c:set value="${lvo.pagingBean}" var="pb"/>
	<div class="row">
	  <c:forEach items="${lvo.list}" var="store">
	  	<div class="store">
				 <a href="${pageContext.request.contextPath}/DispatcherServlet?command=getMenuList&storeNo=${store.storeNo}&nowPage=1">
				 	 <img alt="사진" src="${pageContext.request.contextPath}${store.storeImgUrl}" width="200" height="200">	
				 </a><br>	
			  	${store.storename} <br><br>
	    </div>
	  </c:forEach>
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


