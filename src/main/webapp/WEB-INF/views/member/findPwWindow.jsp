<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RoomMaker 비밀번호 찾기</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" href="../resources/css/member/findPwWindow.css">
</head>
<body>
<div id="findPwWrap">
<img class="findLogo" src="../resources/images/logo.png">
<h2>비밀번호 찾기</h2>
<div class="findPw_content">
<div class="name_wrap">
<input type="text" class="name" name="name" placeholder="이름을 입력해주세요">
</div>
<div class="select_way">
<label class="way_of_Pw_label"><input type="radio" value="email" name="way_of_Pw" checked>이메일</label>
<label><input type="radio" value="phone" name="way_of_Pw">전화번호</label>
</div>
<div class="input_wrap">
<div class="email_div">
<input type="text" class="email" name="email" placeholder="email@email.com"> 
<input type="button" class="sendNum" value="인증번호 발송" onclick="sendNum()" style="font-size: 0.7em;">
</div>
<div class="phone_div">
<input type="text" class="phone" name="phone" placeholder="010-0000-0000">
<input type="button" class="sendNum" value="인증번호 발송" onclick="sendNum()" style="font-size: 0.7em;">
</div>
</div>
<div class="number">
<input type="hidden" class="number_input" placeholder="인증번호를 입력해주세요.">
<input type="hidden" class="number_check" onclick="mailCheck()" value="확인">
</div>
</div>


</div>
<div class="button">
<input type="button" class="cancle" onclick="cancle()" value="닫기">
</div>
<script>
var ifway = "";
var code = "";

$(document).ready(function() {
    var way = $('input[name="way_of_Pw"]:checked').val();
    ifway =way;
	console.log("ifway="+ifway);
	
    if (way === "email") {
        $('.phone_div').css('z-index', 0);
    }
});

$("input[name='way_of_Pw']").change(function(){
	var way = $('input[name="way_of_Pw"]:checked').val();
	console.log("way="+way);
	ifway =way;
	console.log("ifway="+ifway);
	
	$('.number_input').prop('type', "hidden");
	$('.number_check').prop('type', "hidden");
	$('.email').attr("value","");
	$('.phone').attr("value","");
	$('.sendNum').attr("value","인증번호 발송");
	$('.sendNum').css("font-size","0.7em");
	
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



function sendNum(){
	
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
			}else {
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
				if(!cor){
					alert("이메일 형식이 올바르지 않습니다.");
				}else{
					$.ajax({
				        type: 'POST',
				        url: '/member/findIdWindow?',
				        data: {name : name, email : email, phone: phone, ifway: ifway},
				        success: function (data) {
				        	
				        	if(data===""||data===null){
				        		alert("해당 정보와 일치하는 계정이 존재하지 않습니다.")
				        	}else{
				        		$('.sendNum').attr("value","재전송");
				        		$('.sendNum').css("font-size","0.8em");
				        		$('.number_input').prop('type', "text");
				        		$('.number_check').prop('type', "button");
				        		
				        		$.ajax({     //인증번호 발송
				        			type:"GET",
				        			url:"/member/mailCheckFromFindPw?email="+email,
				        			success:function(data){
				        				code=data;
				        				console.log("code="+code);
				        			}
				        		});
				        	};
				        },
				        error: function (xhr, status, error) {
				            console.error("에러 발생:", error);
				        }
				    });
					}
				}
				
			}
			
			
	if(ifway==="phone"){
			if(phone===""||phone===null){
				alert("전화번호를 입력해주세요.");
				return false;
			}
			
			$.ajax({
		        type: 'POST',
		        url: '/member/findIdWindow?',
		        data: {name : name, email : email, phone: phone, ifway: ifway},
		        success: function (data) {
		        	
		        	if(data===""||data===null){
		        		alert("해당 정보와 일치하는 계정이 존재하지 않습니다.")
		        	}else{
		        		$('.sendNum').attr("value","재전송");
		        		$('.sendNum').css("font-size","0.8em");
		        		$('.number_input').prop('type', "text");
		        		$('.number_check').prop('type', "button");
		        	}
		        },
		        error: function (xhr, status, error) {
		            console.error("에러 발생:", error);
		        }
		    });
		}
}

function mailCheck(){
	var num = $('.number_input').val();
	
	var name = $('.name').val();
	var email = $('.email').val();
	var phone = $('.phone').val();
	
	if(num!==code){
		alert("인증번호가 일치하지 않습니다.");
	}else{
		console.log("인증번호 확인 완료");
		$.ajax({     //비밀번호 재설정
			type:"GET",
			url:"/member/resetPwWindow",
			data: {name : name, email : email, phone: phone},
			success:function(data){
				window.location.href="/member/resetPwWindow?name="+name+"&email="+email+"&phone="+phone;;
			}
		});
	}
}


</script>
</body>
</html>