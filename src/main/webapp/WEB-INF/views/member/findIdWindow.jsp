<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RoomMaker 아이디 찾기</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" href="../resources/css/member/findIdWindow.css">
</head>
<body>
<div id="findIdWrap">
<img class="findLogo" src="../resources/images/logo.png">
<h2>아이디 찾기</h2>
<div class="findId_content">
<div class="name_wrap">
<input type="text" class="name" name="name" placeholder="이름을 입력해주세요">
</div>
<div class="select_way">
<label class="way_of_id_label"><input type="radio" value="email" name="way_of_id" checked>이메일</label>
<label><input type="radio" value="phone" name="way_of_id">전화번호</label>
</div>
<div class="input_wrap">
<div class="email_div">
<input type="text" class="email" name="email" placeholder="email@email.com">
</div>
<div class="phone_div">
<input type="text" class="phone" name="phone" placeholder="010-0000-0000">
</div>
</div>
<div class="result"></div>
</div>

<div class="button">
<input type="button" class="findId" onclick="findId()" value="찾기">
<input type="button" class="cancle" onclick="cancle()" value="닫기">
</div>
</div>
<script>
var ifway = "";

$(document).ready(function() {
    var way = $('input[name="way_of_id"]:checked').val();
    ifway =way;
	console.log("ifway="+ifway);
	
    if (way === "email") {
        $('.phone_div').css('z-index', 0);
    }
});

$("input[name='way_of_id']").change(function(){
	var way = $('input[name="way_of_id"]:checked').val();
	console.log("way="+way);
	ifway =way;
	console.log("ifway="+ifway);
    if (way === "email") {
        if ($('.phone_div').length) {
            $('.phone_div').css('z-index', 0);
        }
    } else if (way === "phone") {
        if ($('.phone_div').length) {
            $('.phone_div').css('z-index', 2);
        }
    }


});


function cancle(){
	window.close();
}

function findId(){
	var name = $('.name').val();
	var email = $('.email').val();
	var phone = $('.phone').val();
	
	
	if(name===""||name===null){
		alert("이름을 입력해주세요.");
		return false;
	}
	
	if(ifway==="email"){
		if(email===""||email===null){
			alert("이메일을 입력해주세요.");
			return false;
		}
	}
	
	if(ifway==="phone"){
		if(phone===""||phone===null){
			alert("전화번호를 입력해주세요.");
			return false;
		}
	}
		
		$.ajax({
	        type: 'POST',
	        url: '/member/findIdWindow?',
	        data: {name : name, email : email, phone: phone, ifway : ifway},
	        success: function (data) {
	        	
	        	if(data===""||data===null){
	        		var str = "<span>해당 정보로 찾은 아이디가 없습니다.</span>"
	        		$('.result').html(str);
	        	}else{
	        		var str="<span>해당 정보로 찾은 아이디는 "+data+" 입니다.</span>";
	                $('.result').html(str);
	        	}
	        },
	        error: function (xhr, status, error) {
	            console.error("에러 발생:", error);
	        }
	    });
	
}
</script>
</body>
</html>