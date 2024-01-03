<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
*{
	margin :0;
	padding:0;
}

</style>
<body id="main_backround" style="background-image:url('../resources/images/sky2.jpeg'); background-size: cover;">
<header style="display: flex;">
        <div class="make_room" style="width: 60%;">
        	<img class="logo" src="../resources/images/logo.png" onclick="location.href='/main'">
            <input type="text" class="find_room" placeholder="방 제목을 입력해주세요" id="keyword">
            <button class="find_room_click" style="background-color: rgba( 255, 255, 255, 0 );"><img src="../resources/images/search.png"></button>
            <a onclick="inMypage()" class="mypage" data-text="마이페이지" style="margin-left: 750px;">마이페이지</a>       
        </div>
		<div class="make_room_right" style="margin-left:300px;margin-top:80px; width: 10%;">
			<span style="font-size: 24px;">${member.name}님</span>
		</div>
		<div class="make_room_logout" style="width: 10%; background-color: rgba( 255, 255, 255, 0 );">
			<button class="logout" onclick="logout()" style="background-color: rgba( 255, 255, 255, 0 );">logout</button>
		</div>

</header>
<link rel="stylesheet" href="../resources/css/main.css?v=1" />
<link rel="stylesheet" href="../resources/css/header.css?v=1" />
<link rel="stylesheet" href="../resources/css/footer.css?v=1" />
<script>


$(".find_room_click").on("click", function(e){
	
	var keyword = document.getElementById('keyword').value;
	location.href = "/main?keyword="+keyword;
});

function logout()
{
	location.href="/member/Logout";
}

function inMypage()
{
      var url = '/mypage/memberPwCheck';
       window.open(url,"_blank_1","toolbar=no , menubar=no, scrollbars=yes,resizeble=no, width=450,height=300");
}

</script>

