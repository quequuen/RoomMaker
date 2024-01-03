<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RoomMaker 비밀번호 재설정</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" href="../resources/css/member/resetPwWindow.css">
</head>
<body>
<div id="resetPwWrap">
<img class="resetLogo" src="../resources/images/logo.png">
<h2>비밀번호 재설정</h2>
<input type="hidden" name="name" class="name" value="${member.name}">
<input type="hidden" name="email" class="email" value="${member.email}">
<input type="hidden" name="phone" class="phone" value="${member.phonenum}">
<div class="resetPw_content">
<input type="password" class="pw_input" name="pw" placeholder="재설정할 비밀번호 입력">
<input type="password" class="pw_input_check" name="pw" placeholder="재설정할 비밀번호 확인">
</div>
</div>
<div class="buttons">
<input type="button" class="resetPw_button" onclick="reset()" value="재설정">
<input type="button" class="cancle" onclick="cancle()" value="닫기">
</div>
</body>
<script type="text/javascript">
function cancle(){
	window.close();
}

function reset(){
	
	var name = $('.name').val();
	var email = $('.email').val();
	var phone  = $('.phone').val();
	
	var pw = $('.pw_input').val();
	var pw_re = $('.pw_input_check').val();
	if(pw===""){
		alert("재설정할 비밀번호를 입력하세요.");
		return false;
	}else if(pw_re===""){
		alert("비밀번호 확인을 입력하세요.");
		return false;
	}else if(pw!==pw_re){
		alert("비밀번호 확인이 일치하지 않습니다.");
		return false;
	}else{
		
		$.ajax({     //비밀번호 재설정
			type:"POST",
			url:"/member/resetPwWindowAction",
			data: {name : name, email : email, phone: phone, pw: pw},
			success:function(data){
				if(data==="success"){
					alert("비밀번호 설정이 완료되었습니다.");
					window.close();
				}else if(data==="aleady"){
					alert("이미 사용중인 비밀번호입니다.");
					
				}else{
				
					alert("비밀번호 설정을 다시 시도해주세요.");
					window.close();
				}
					
			}
		});
	}
}
</script>
</html>