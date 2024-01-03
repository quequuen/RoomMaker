<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../resources/css/mypage/header.css" />
<link rel="stylesheet" href="../resources/css/footer.css?v=1" />
   
<body id="main_backround" style="background-image:url('../resources/images/sky2.jpeg'); background-size: cover;">
<header style="display: flex;">
        <div class="make_room" style="width: 60%;">
        	<img class="logo" src="../resources/images/logo.png" onclick="location.href='/main'">
        	<span class="mypage_span">${member.id}님 마이페이지</span>
            <a href="/main" class="mainpage" data-text="메인페이지" style="margin-left: 750px;">메인페이지</a>       
        </div>
		<div class="make_room_right" style="margin-left:300px;margin-top:80px; width: 10%;">
			<span style="font-size: 24px;">${member.name}님</span>
		</div>
		<div class="make_room_logout" style="width: 10%;">
			<button class="logout" onclick="logout()" style="background-color: rgba( 255, 255, 255, 0 );">logout</button>
		</div>

</header>
<script>
function logout()
{
	location.href="/member/Logout";
}
</script>