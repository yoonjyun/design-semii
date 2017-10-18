<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<style type="text/css">
   .dynamic{
      display: none;
   }
</style>
<script type="text/javascript">
   $(document).ready(function(){
      $("#tb1").css("display","block");
      $("#regForm").submit(function(){
         if($("#tb1").css("display")=="block"){
            $("#hiddentb").val("tb1");
         }else if($("#tb2").css("display")=="block"){
            $("#hiddentb").val("tb2");
         }else if($("#tb3").css("display")=="block"){
            $("#hiddentb").val("tb3");
         }else{
            $("#hiddentb").val("tb4");
         }
      });
   });
   
function showTable(){
   $(".dynamic").css("display","none");
    if($("#Sized").is(":checked")){
       if($("#Temped").is(":checked")){       // 사이즈 O / 온도 O
          $(".six").css("display","block");
       }else{                            // 사이즈 O / 온도 X
          $(".three").css("display","block");
       }
    }else{                               // 사이즈 X / 온도 O
         if($("#Temped").is(":checked")){
             $(".two").css("display","block");
       }else{                            // 사이즈 X / 온도 X
          $(".one").css("display","block");
       }
    }
}
</script>

<form action="${pageContext.request.contextPath}/DispatcherServlet" method="post" id="regForm" enctype="multipart/form-data">
   <input type="hidden" name="tbodyNumber" id="hiddentb">
   <input type = "hidden" name = "command" value = "menuRegister">
   <table class="table table-bordered" >
      <tr>
         <td>메뉴명</td>
         <td><input type="text" name="menuId"></td>
      </tr>
      <tr>
         <td><input type="checkbox" id="Sized" onchange="showTable()">사이즈있음</td>
         <td><input type="checkbox" id="Temped" onchange="showTable()">Hot/Ice있음</td>
      </tr>
       <tbody class="dynamic one" id="tb1">
         <tr>
            <td>가격</td>
            <td><input type="number" name="price12"></td>
         </tr>
      </tbody>
      <tbody class="dynamic two" id="tb2">
         <tr>
            <td>HOT 가격</td>
            <td><input type="number" name="price11"></td>
         </tr>
         <tr>
            <td>ICE 가격</td>
            <td><input type="number" name="price10"></td>
         </tr>
      </tbody>
      
      <tbody class="dynamic three" id="tb3">
         <tr>
            <td>사이즈  </td><td>SMALL</td><td>MEDIUM</td><td>LARGE</td>
         </tr>
         <tr>
            <td>가격</td>
            <td><input type="number" name="price7"></td><td><input type="number" name="price8"></td><td><input type="number" name="price9"></td>
         </tr>
      </tbody>
      
      <tbody class="dynamic six" id="tb4">
         <tr>
            <td>사이즈  </td><td>SMALL</td><td>MEDIUM</td><td>LARGE</td>
         </tr>
         <tr>
            <td>HOT 가격</td>
            <td><input type="number" name="price1"></td><td><input type="number" name="price2"></td><td><input type="number" name="price3"></td>
         </tr>
         <tr>
            <td>ICE 가격</td><td><input type="number" name="price4"></td><td><input type="number" name="price5"></td><td><input type="number" name="price6"></td>
         </tr>
      </tbody>
      <tbody>
         <tr><td>메뉴설명</td><td><textarea rows="5" cols="50" placeholder="메뉴설명을 입력하세요" name="menuInfo"></textarea></td></tr>
         <tr><td>메뉴이미지</td><td><input type="file" name = "picture"></td></tr>
      </tbody>
   </table>
  <button type="submit" class="btn btn-default">등록하기</button>
</form>