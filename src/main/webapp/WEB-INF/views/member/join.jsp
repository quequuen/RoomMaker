<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RoomMaker join</title>
<link rel="stylesheet" href="../resources/css/member/join.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<style>
body{
	background-image:url('../resources/images/bakcimg.jpg'); 
	background-size: cover;
}
</style>
<body>
	<div id="join_wrap">
		<div class="join_logo">
			<a href="#"><img src="../resources/images/logo.png"></a>
		</div>
		<div class="join_content" style="background: #ebf3f9" >
			<form id="join_form" name="join_form" method="post">
				<div class="join_div" style="background: white;">
				<input type="text" class="joinId" name="id" placeholder="아이디">
				<span class="joinIdChk"></span>
				<input type="password" class="joinPw input" name="pw" placeholder="패스워드">
				<input type="password" class="joinPwCheck input" name="pwChk" placeholder="패스워드 확인">
				</div>
				<div class="private_div" style="background: white;">
				<input type="text" class="joinName input" name="name" placeholder="이름">
				<input type="text" class="joinPhone input" name="phonenum" placeholder="010-0000-0000">
				<div class="gender_button">
				<input type="radio" class="joinGender" name="gender" value="0">
				<span class="joinGenderSpan">남</span>
				<input type="radio" class="joinGender sec" name="gender" value="1">
				<span class="joinGenderSpan">여</span>
				</div>
				<input type="text" class="joinEmail" name="email" placeholder="이메일">
				<input type="button" class="emailNum" value="인증번호 발송">
				<input type="text" class="joinEmailNum input" name="emailNum" placeholder="인증번호" disabled>
				<input type="hidden" name="memberlevel" value="0">
				</div>
				<input type="button" class="join_button" value="JOIN">
			</form>
		</div>
	</div>
	<script>
	 
	$(document).ready(function(){
		var code = 0;
	    $(".join_button").click(function() {

	        var id = $('.joinId').val();                 // id 입력란
	        var pw = $('.joinPw').val();                // 비밀번호 입력란
	        var pwck = $('.joinPwCheck').val();            // 비밀번호 확인 입력란
	        var phone = $('.joinPhone').val();            // 전화번호 입력란
	        var name = $('.joinName').val();            // 이름 입력란
	        var mail = $('.joinEmail').val();            // 이메일 입력란
	        var gender = $('input[name="gender"]').is(":checked");           // 성별 선택
	        var mailNum = $('.joinEmailNum').val();

	        // 회원가입 유효성 검사
	        if(id == ""){
	            alert("아이디를 입력해주세요.");
	            join_form.id.focus();
	            return false;
	        } else if(pw == ""){
	            alert("패스워드를 입력해주세요.");
	            join_form.pw.focus();
	            return false;
	        } else if(pwck == ""){
	            alert("패스워드 확인을 입력해주세요.");
	            join_form.pwChk.focus();
	            return false;
	        } else if(pw != pwck){
	            alert("패스워드 확인이 일치하지 않습니다.");
	            join_form.pwChk.focus();
	            return false;
	        } else if(name == ""){
	            alert("이름을 입력해주세요.");
	            join_form.name.focus();
	            return false;
	        } else if(phone == ""){
	            alert("전화번호를 입력해주세요.");
	            join_form.phonenum.focus();
	            return false;
	        } else if(gender == ""){
	            alert("성별을 선택해주세요.");
	            return false;
	        } else if(mail == ""){
	            alert("이메일을 입력해주세요.");
	            join_form.email.focus();
	            return false;
	        } else if(mailNum == ""){
	            alert("인증번호를 입력해주세요.");
	            join_form.emailNum.focus();
	            return false;
	        } else if(code !== mailNum){
	        	alert("인증번호가 옳지 않습니다");
	        	join_form.mailNum.focus();
	        	return false;
	        }
	        $("#join_form").attr("action", "/member/join");
	        $("#join_form").submit();
	    });

	    $(".emailNum").click(function() {
			
	        var email = $(".joinEmail").val(); // 입력한 이메일

	        if(email == ""){                // 이메일 공란 검사
	            alert("이메일을 입력해주세요.");
	            join_form.email.focus();
	            return false;
	        } else {
	            var cor = CheckEmail(email);
	            console.log(cor);

	            function CheckEmail(email)    // 이메일 형식 검사
	            {                                        
	                var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	                if(!reg_email.test(email)) {                                     
	                    return false;         
	                }                       
	                else {return true;}                                      
	            }  
	            
	            if(cor){ // 형식이 맞을 때
	                $.ajax({  // 인증번호 발송

	                    type:"GET",
	                    url:"mailCheck?email=" + email,
	                    success:function(data){
	                        $('.joinEmailNum').attr("disabled",false);
	                        console.log(data);
	                        code = data;
	                    }

	                });
	            } else if(cor == false){    // 형식이 맞지 않을 때
	                alert("이메일 형식이 올바르지 않습니다.");
	            }   
	        }       
	    });

	});

	// 아이디 중복검사
	$('.joinId').on("propertychange change keyup paste input", function(){

	    var id = $('.joinId').val();            // .joinId에 입력되는 값
	    var data = {id : id};               // '컨트롤에 넘길 데이터 이름' : '데이터(.joinId에 입력되는 값)'

	    $.ajax({
	        type : "post",
	        url : "/member/joinIdCheck",
	        data : data,
	        success : function(result){
	            console.log("성공 여부" + result);
	            if(result != 'fail'){
	                $('.joinIdChk').css({
	                    "color":"#4Ab34A"
	                }).text("사용가능");

	            } else {
	                $('.joinIdChk').css({
	                    "color":"red"
	                }).text("사용불가");
	            }
	        } // success 종료
	    }); // ajax 종료    

	});
	</script>
	
</body>
</html>