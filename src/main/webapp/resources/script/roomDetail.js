
var roomcode = document.getElementsByName("roomcode")[0].value;
var sock = new SockJS("http://192.168.0.4:8000/chatting");


$(document).ready(function () {
	
    $(".room_function").click(function () {
        var buttonValue = $(this).text().trim();
        $.ajax({
            type: 'GET',
            url: '/room/loadDynamicJSP',
            data: {buttonValue: buttonValue , roomcode:roomcode},
            success: function (data) {
                $('#dynamicContent').html(data);
            },
            error: function (xhr, status, error) {
                console.error("에러 발생:", error);
            }
        });
    });
    
    $(".master_update_btn").click(function () {
        var buttonValue = $(this).text().trim();
        $.ajax({
            type: 'GET',
            url: '/room/loadDynamicJSP',
            data: {buttonValue: buttonValue , roomcode:roomcode},
            success: function (data) {
                $('#dynamicContent').html(data);
            },
            error: function (xhr, status, error) {
                console.error("에러 발생:", error);
            }
        });
    });
    
    
});



        
function fileUploadAction(){
        	
            var roomcode = $('.roomcode').val();
            var filetitle = $('.filetitle').val();
            var filemember = $('.filemember').val();
            var content = $('.content').val();
            var uploadPath = $('.uploadPath').val();
            let formData = new FormData($("#fileUpload_form")[0]);
            var uploadFile = $('.uploadFile').val();
            console.log("uploadFile="+uploadFile);
            
            if(filetitle===""){
            alert("게시물의 제목을 입력해주세요");
            return false;
            }
           	if(uploadFile===""){
           	alert("공유할 자료를 선택해주세요");
            return false;
           	}
            

            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: '/room/loadDynamicFileJSP',
                data: formData,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    $('#dynamicContent').html(data);
                },
                error: function (xhr, status, error) {
                    console.error("에러 발생:", error);
                }
            });

           
        };
        
        function resetAction(){
        	var buttonValue = "취소";
        	console.log(buttonValue);
        	//var buttonValue = $(this).val().trim();
        	$.ajax({
                type: 'GET',
                url: '/room/loadDynamicJSP',
                data: {buttonValue: buttonValue , roomcode:roomcode},
                success: function (data) {
                    $('#dynamicContent').html(data);
                },
                error: function (xhr, status, error) {
                    console.error("에러 발생:", error);
                }
            });
        };
        
        function resetActionFromFileMod(){
        	var buttonValue=""
        
        
        }
        
        function beforeAction(){
        	var nowPage= $('.startPage').val();
        	var cntPerPage= $('.cntPerPage').val();
        	nowPage = parseInt(nowPage, 10);
            cntPerPage = parseInt(cntPerPage, 10);
        	console.log(nowPage);
        	console.log(cntPerPage);
        	
        	nowPage = nowPage-1;
        	
        	console.log(nowPage);
        	console.log(cntPerPage);
        	
        	$.ajax({
                type: 'POST',
                url: '/room/fileListPaging',
                data: {nowPage: nowPage,cntPerPage: cntPerPage, roomcode:roomcode},
                success: function (data) {
                    $('#dynamicContent').html(data);
                },
                error: function (xhr, status, error) {
                    console.error("에러 발생:", error);
                }
            });
        }
        
        function afterAction(){
        	var nowPage= $('.endPage').val();
        	var cntPerPage= $('.cntPerPage').val();
        	nowPage = parseInt(nowPage, 10);
            cntPerPage = parseInt(cntPerPage, 10);
        	console.log(nowPage);
        	console.log(cntPerPage);
        	
        	nowPage = nowPage+1;
        	
        	console.log(nowPage);
        	console.log(cntPerPage);
        	
        	$.ajax({
                type: 'POST',
                url: '/room/fileListPaging',
                data: {nowPage: nowPage,cntPerPage: cntPerPage, roomcode:roomcode},
                success: function (data) {
                    $('#dynamicContent').html(data);
                },
                error: function (xhr, status, error) {
                    console.error("에러 발생:", error);
                }
            });
        }
        
        function goFileDetail(filecode){
        	$.ajax({
        		type: 'GET',
        		url: '/room/fileDetail',
                data: {filecode: filecode, roomcode:roomcode},
                success: function (data) {
                    $('#dynamicContent').html(data);
                },
                error: function (xhr, status, error) {
                    console.error("에러 발생:", error);
                }
        	});
        }
        
function fileDownload(filecode){
	$.ajax({
		type: 'POST',
		url: '/room/fileDownload',
        data: {filecode: filecode, roomcode:roomcode},
        success: function (data) {
        	alert("파일 다운로드 완료");
        },
        error: function (xhr, status, error) {
            console.error("에러 발생:", error);
        }
	});
}

    function deleteAction(filecode){
   		var loginId = $('.loginId').val();  //로그인 되어 있는 유저 Id
   		var roomId=$('.roomId').val();		//방장 Id
   		var uploadId= $('.uploadId').val();		//파일을 업로드한 유저 Id
   
    
    	if(loginId===uploadId||loginId===roomId){
    		console.log("일치");
    		$.ajax({
    		type: 'POST',
    		url: '/room/deleteUploadedFile',
            data: {filecode: filecode, roomcode:roomcode},
            success: function (data) {
            	alert("파일 삭제 완료");
            	 $('#dynamicContent').html(data);
            	 },
	        error: function (xhr, status, error) {
	            console.error("에러 발생:", error);
        		}
            	 
    		});
    	}
    	else{
    		alert("삭제할 수 있는 권한이 없습니다.");
    		return false;
   		}
  	
    }
        
function goFileModify(filecode){
   		var loginId = $('.loginId').val();  //로그인 되어 있는 유저 Id
   		var roomId=$('.roomId').val();		//방장 Id
   		var uploadId= $('.uploadId').val();		//파일을 업로드한 유저 Id
   
    	if(loginId===uploadId||loginId===roomId){
    		console.log("일치");
    		$.ajax({
    		type: 'GET',
    		url: '/room/goFileModify',
            data: {filecode: filecode, roomcode:roomcode},
            success: function (data) {
            	 $('#dynamicContent').html(data);

            },
            error: function (xhr, status, error) {
                console.error("에러 발생:", error);
            }
    	});
    		
    	}
    	else{
    		alert("수정할 수 있는 권한이 없습니다.");
    		return false;
   		}
  	
    }
        
function modifyAction(filecode){
	var filetitle = $('.fileModify_title_sec').val();
	var content = $('.modifycontent').val();
	
	console.log(filetitle, content);
	
	if(filetitle===""){
	alert("수정하실 게시물 제목을 입력해주세요");
	return false;
	}
	$.ajax({
		type: 'POST',
		url: '/room/modifyUploadedFile',
        data: {filecode: filecode, roomcode:roomcode, filetitle: filetitle, content: content},
        success: function (data) {
        	 $('#dynamicContent').html(data);
        },
        error: function (xhr, status, error) {
            console.error("에러 발생:", error);
        }

	});
	
}
        
function resetActionFromFileMod(filecode){
	console.log(filecode)
	$.ajax({
		type: 'GET',
		url: '/room/fileDetail',
        data: {filecode: filecode, roomcode:roomcode},
        success: function (data) {
        	 $('#dynamicContent').html(data);
        },
        error: function (xhr, status, error) {
            console.error("에러 발생:", error);
        }
	});

}

function deleteAnswer(answer,questioncode,roomcode)
{
   	 $.ajax({
   		type: 'POST',
   		url: '/room/deleteAnswer',
           data: {questioncode: questioncode, roomcode:roomcode, answer:answer},
           success: function (data) {
        	   alert("답변 삭제 완료");
        	   questionDetail(questioncode); 
           },
           error: function (xhr, status, error) {
               console.error("에러 발생:", error);
               //alert("답변 삭제 완료");
        	   //questionDetail(questioncode); 
           }
   	});  
   
}
       
