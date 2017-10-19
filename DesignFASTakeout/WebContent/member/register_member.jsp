<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/css.css">
<br><br><br>
<style>
	table{
		text-align: center;
	}
	td,th{
		padding: 15px;  /*내부여백*/
		font-size: 15px;
		vertical-align: center;

/* 		font-weight: bold; */
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#idCheckBtn").click(function(){
		$.ajax({
				type:"post",
				url:"IdDupleCheckServlet",
				data:"input_id="+$("#input_id").val(),
				success:function(data){
					if(data == 'true'){
						$("#userId").val($("#input_id").val());
						alert("사용할 수 있는 아이디");
					}else{
						$("#input_id").val("");
						$("#userId").val("");
						alert("사용할 수 없는 아이디");
					}
				}//success
			}); //ajax
	});//click
	
	$("#regForm").submit(function(){
		// 회원가입 폼 확인
		if($("#input_id").val() == ""){
			alert("아이디를 입력해주세요");
			return false;
		}else if($("#userId").val() != $("#input_id").val()){
			alert("아이디 중복확인을 해주세요");
			return false;
		}else if($("#userPwd1").val() != $("#userPwd2").val()){
			alert("비밀번호가 서로 일치하지 않습니다.");
			return false;
		}else if($("#payPwd1").val() != $("#payPwd2").val()){
			alert("결제비밀번호가 서로 일치하지 않습니다.");
			return false;
		}
		return true;
	});//submit
	
}); //ready
</script>
    	<div class="row">
		  <div class="col-md-2">
		  
		  </div>
		
		  <div class="col-md-8">
			  <form action="${pageContext.request.contextPath}/DispatcherServlet?command=registerMember" method="post" id="regForm">
					<input type="hidden" name="userId" id="userId" value="">
					<table class="table">
						<tr><th>가입구분 </th><td colspan="2"><input type="text" value="${param.auth}" name="auth" readonly="readonly"></td><td></td></tr>
						<tr><th>아이디 </th><td colspan="2"><input type="text" id="input_id"></td><td><input class="btn btn-default" type="button" id="idCheckBtn" value="중복확인"></td></tr>
						<tr><th>비밀번호 </th><td colspan="2"><input type="password"  name="userPwd" id="userPwd1"></td><td></td></tr>
						<tr><th>비밀번호 확인 </th><td colspan="2"><input type="password" id="userPwd2"></td><td></td></tr>
						<tr><th>이름 </th><td colspan="2"><input type="text"  name="userName"></td><td></td></tr>
						<tr><th>휴대폰번호 </th><td colspan="2"><input type="text"  name="userPhone"></td><td></td></tr>
						<tr><th>결제비밀번호 </th><td colspan="2"><input type="password" name="userPayPwd" id="payPwd1"></td><td></td></tr>
						<tr><th>결제비밀번호 확인 </th><td colspan="2"><input type="password" id="payPwd2"></td><td></td></tr>
						<tr><td colspan="4"><input class="btn btn-default" type="submit" value="회원가입"></td></tr>
					</table>
			   </form>
		  </div>
		  <div class="col-md-2">
			
		  </div>
		</div>

	
