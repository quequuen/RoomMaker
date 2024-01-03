<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage메인</title>
<script>
 
 
 function goModifyMypage() {
	    $.ajax({
	      url: '/mypage/modify',
	      type: 'GET',
	      success: function(data) {
	        $('#dynamicContent').html(data);
	      },
	      error: function() {
	        console.error('Failed to load content from /mypage/modify.jsp');
	      }
	    });
	  }
 
 function resetActionFromMypageModify(){
	 location.reload();
 }
 
 function myLeaveAction(idvalue){
	 var id =idvalue;
	 var pw = $('.pw').val();
	 
	 if (!confirm("정말 떠나실건가요?")) {
	        location.reload();
	    } else {
	    	$.ajax({
	  	      url: '/mypage/leave',
	  	      type: 'GET',
	  	      data: {id:id,pw:pw},
	  	      success: function(data) {
	  	    	  alert("탈퇴되었습니다");
	  	    	  location.href = "/member/login";
	  	      },
	  	      error: function() {
	  	        console.error('Failed to load content from /mypage/modify.jsp');
	  	      }
	  	    });
	    }
	 
 }
 
 function modifyMypageAction(idvalue){
	 var id = idvalue;
	 var pw = $('.pw').val();
	 var repw = $('.repw').val();
	 var name = $('.name').val();
	 var email = $('.email').val();
	 var phonenum = $('.phonenum').val();
	 
	 console.log(pw);
	 console.log(repw);
	 console.log(name);
	 console.log(email);
	 console.log(phonenum);
	 
	 if(repw===""){
		 alert("PASSWORD CHECK를 입력해주세요");
		 return false;
	 }else if(pw!==repw){
		 alert("PASSWORD와 PASSWORD CHECK가 동일하지 않습니다");
		 return false;
	 }
	 
	 $.ajax({
	      url: '/mypage/modify',
	      type: 'POST',
	      data: {id:id,pw:pw,name:name,email:email,phonenum:phonenum},
	      success: function(data) {
	    	  alert("수정되었습니다");
	    	 location.reload();
	      },
	      error: function() {
	        console.error('Failed to load content from /mypage/modify.jsp');
	      }
	    });
	 
 }
 
 </script>
<link rel="stylesheet" href="../resources/css/mypage/main.css" />
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
 <%@ include file="header.jsp" %>
<section id="dynamicContent">
 <div id="my_main" style="background: #f7fdfe">
 	<div class="my_main_content" style="background: #ebf9f9">
 	<div class="my_inform_content" style="background: #f5f5dc;">
 		<div class="my_informs" >
 		<div class="my_id">${member.id }<span class="my_name">${member.name }</span></div>
 		<div class="my_email">EMAIL. ${member.email }</div>
 		<div class="my_phone">PHONE. ${member.phonenum }</div>
 		</div>
 	</div>
 	<input class="myMainModifyButton" type="button" onclick="goModifyMypage()" value="수정하기">
 	</div>
 </div>
</section>
 <%@ include file="../room/roomFooter.jsp" %>
 