<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RoomMaker login</title>
<link rel="stylesheet" href="../resources/css/member/login.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<style>
* {
	magin: 0 auto;
	padding: 0;
}

body {
	position: relative;
	background-image:url('../resources/images/bakcimg.jpg'); 
	background-size: cover;
	
}

a{
	text-decoration: none;
}

#login_wrap {
	position: absolute;
	width: 90%;
	height: 100%;
	left: 33%;
}

.login_logo{
	margin-top: 100px; 
	width: 400px;
	height: 100px;
	background-color: rgba( 255, 255, 255, 0 );
}

.login_content{
	border: 1px solid #ccc;
	border-radius: 15px;
	width: 500px;
	height: 400px;
	background: #ebf3f9;
}

.login_div{
	border: 1px solid #ccc;
	border-radius: 15px;
	margin-top: 50px;
	margin-left: 50px;
	width: 400px;
	height: 120px;
	display: block;
}

.login_div input{
	margin-left: 50px;
	width: 300px;
	height: 60px;
	font-size: 1.3em;
	border: none;
	outline: none;
}

.login_button{
	margin-top: 50px;
	margin-left: 50px;
	width: 400px;
	height: 60px;
	border: none;
	border-radius: 13px;
	background-color: skyblue;
	font-size: 1.3em;
	font-weight: bold;
	color: white;
}

.a_div{
	margin-top: 10px;
	margin-left: 50px;
	width: 400px;
	height: 30px;
	display: block;
	text-align: center;
}

.a_div a{
	color: black;
	margin-right: 5px;
}

.login_warn{
	color: red;
	font-weight: bold;
	width: 400px;
	margin-top: 30px;
	margin-left: 50px;
	text-align: center;
}

</style>
<body>
	<div id="login_wrap">
		<div class="login_logo">
			<a href="#"><img src="../resources/images/logo.png"></a>
		</div>
		<div class="login_content">
			<form id="login_form" method="post">
				<div class="login_div" style="background: white;">
					<input name="id" class="id" type="text" placeholder="아이디">
					<input name="pw" class="pw" type="password" placeholder="패스워드">
				</div>
					<input class="login_button" type="button" value="LOGIN">
				<div class="a_div">
					<a onclick="findIdWindow()">아이디 찾기</a>
					<a onclick="findPwWindow()">비밀번호 찾기</a>
					<a href="/member/join">회원가입</a>
				</div>
				<div>
					<input type="button" value="카카오" onclick="">
				</div>
				<c:if test = "${result == 0 }">
                <script type="text/javascript">alert("아이디 또는 비밀번호가 옳지 않습니다.");</script>
            	</c:if>
			</form>
		</div>

	</div>

	<script>
		$(".login_button").click(function() {
			var id = $('.id').val();
			var pw = $('.pw').val();
			
			if(id===""){
				alert("아이디를 입력해주세요");
				return false;
			}else if(pw===""){
				alert("비밀번호를 입력해주세요");
				return false;
			}
			
			//alert("로그인 버튼 작동");
			$("#login_form").attr("action", "/member/login");
			$("#login_form").submit();
		});
		
		function findIdWindow(){
			 var url = '/member/findIdWindow';
		       window.open(url,"_blank_1","toolbar=no , menubar=no, scrollbars=yes,resizeble=no, width=450,height=300");
		}
		
		function findPwWindow(){
			 var url = '/member/findPwWindow';
		       window.open(url,"_blank_1","toolbar=no , menubar=no, scrollbars=yes,resizeble=no, width=450,height=300");
		}
	
	</script>
</body>
</html>