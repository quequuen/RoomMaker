<%@page import="com.rm.service.RoomService"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>room메인</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<style type="text/css">
   #result_card img{
      max-width: 100%;
       height: auto;
       display: block;
       padding: 5px;
       margin-top: 10px;
       margin: auto;   
   }
</style>

<script>
   function selChange() {
      var sel = document.getElementById('cntPerPage').value;
      location.href="boardList?nowPage=${paging.nowPage}&cntPerPage="+sel;
   }
</script>

</head>
	
   <%@ include file="header.jsp" %>
    <section>
        <div id="main_room" style="background: #f7fdfe">
            <div class="main_room_top">
                <button class="room_button" onclick="location.href='room/makeRoom'"><span>방만들기</span></button>
                <button class="room_button" style="margin-left: 400px;" onclick="location.href='/main?type='+'joinroom'+'&id='+'${member.id}'"><span>참여중인 방</span></button>
                <button class="room_button" onclick="location.href='/main?type='+'makeroom'+'&id='+'${member.id}'"><span>내가 만든 방</span></button>
            </div>
            <div class="main_room_section" style="background: #ebf9f9">
            
            <c:forEach items="${roomList}" var="roomList" varStatus="status">
               
                <div class="many_room" style="background: #f5f5dc;">
               		<a href="#" onclick="inRoomDetail('${roomList.roomcode}','${roomList.join_member_cnt}','${roomList.member_cnt}')">
                    <div class="room_info">
                    
                        <div class="room_icon">

                     
                        <div class="image_wrap" style="background: white; 100%; height:100%;" data-bookid="${roomList.imageList[0].roomcode}" data-path="${roomList.imageList[0].uploadPath}" data-uuid="${roomList.imageList[0].uuid}" data-filename="${roomList.imageList[0].fileName}">
                           <img style="width:100%; height:100%; object-fit:cover; border-radius: 10px">
                        </div>                  
          
                        </div>
                        
                        <div class="room_title" style="background: #fdfdf6;">
                            
                            <div class="room_number"style="background:white;">
                                <div style="margin-left: -10px; ">
                                 	${roomList.title}
                                </div>
                       
                            </div>
                          	<!-- 아이디를 넘겨주기 위한 input -->
                          	<input type="hidden" name="roomListid" value="${roomList.id}">
                          	
                          	
                            <div class="room_sub_title">
                                <div style="width: 40%;">방장 : ${roomList.id}</div>
                                <div style="display: flex;">
                                <div>
                                    참여자 ${roomList.join_member_cnt}/${roomList.member_cnt}
                                </div>
                                <input type="hidden" name="join_member_cnt" value="${roomList.join_member_cnt}">
                                <input type="hidden" name="member_cnt" value="${roomList.member_cnt}">
                                <div class="people_icon">
                                    
                                </div>
                            </div>

                            </div>
                        </div>
                        
                    </div>
                    </a>
                </div>
            
                </c:forEach>
            </div>
        </div>
         <input type="hidden" name="id" value="${member.id}">     
         <input type="hidden" name="keyword" value="${paging.keyword }">  
       <div style="display: block; text-align: center; margin-top:20px" class="pagemove">      
       <c:choose>
       <c:when test="${type eq 'all'}">
       
      <c:if test="${paging.startPage != 1 }">
         <a href="/main?keyword=${paging.keyword}&nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}" class="before">◀이전</a>
      </c:if>
      <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
         <c:choose>
            <c:when test="${p == paging.nowPage }">
               <b style="color:skyblue;">${p }</b>
            </c:when>
            <c:when test="${p != paging.nowPage }">
               <a href="/main?keyword=${paging.keyword}&nowPage=${p }&cntPerPage=${paging.cntPerPage}">다음▶</a>
            </c:when>
         </c:choose>
      </c:forEach>
      <c:if test="${paging.endPage != paging.lastPage}">
         <a href="/main?keyword=${paging.keyword}&nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}"  class="next">다음▶</a>
      </c:if>
      </c:when>
      
      <c:when test="${type eq 'joinroom'}">
      
      	<c:if test="${paging.startPage != 1 }">
         <a href="/main?type=joinroom&id=${member.id}&keyword=${paging.keyword}&nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}" class="before">◀이전</a>
      </c:if>
      <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
         <c:choose>
            <c:when test="${p == paging.nowPage }">
               <b style="color:skyblue;">${p }</b>
            </c:when>
            <c:when test="${p != paging.nowPage }">
               <a href="/main?type=joinroom&id=${member.id}&keyword=${paging.keyword}&nowPage=${p }&cntPerPage=${paging.cntPerPage}">다음▶</a>
            </c:when>
         </c:choose>
      </c:forEach>
      <c:if test="${paging.endPage != paging.lastPage}">
         <a href="/main?type=joinroom&id=${member.id}&keyword=${paging.keyword}&nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}"  class="next">다음▶</a>
      </c:if>
      </c:when>
      
      <c:when test="${type eq 'makeroom'}">
      
      	<c:if test="${paging.startPage != 1 }">
         <a href="/main?type=makeroom&id=${member.id}&keyword=${paging.keyword}&nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}" class="before">◀이전</a>
      </c:if>
      <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
         <c:choose>
            <c:when test="${p == paging.nowPage }">
               <b style="color:skyblue;">${p }</b>
            </c:when>
            <c:when test="${p != paging.nowPage }">
               <a href="/main?type=makeroom&id=${member.id}&keyword=${paging.keyword}&nowPage=${p }&cntPerPage=${paging.cntPerPage}">다음▶</a>
            </c:when>
         </c:choose>
      </c:forEach>
      <c:if test="${paging.endPage != paging.lastPage}">
         <a href="/main?type=makeroom&id=${member.id}&keyword=${paging.keyword}&nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}"  class="next">다음▶</a>
      </c:if>
      </c:when>
      
      </c:choose>
   </div> 
    </section>
    
    <script>
    
    	
       $(document).ready(function(){
          /* 이미지 삽입 */
          $(".image_wrap").each(function(i, obj){
             const bobj = $(obj);
             
             const uploadPath = bobj.data("path");
             const uuid = bobj.data("uuid");
             const fileName = bobj.data("filename");
             
             const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
             
             $(this).find("img").attr('src', '/room/display?fileName=' + fileCallPath);
          });
          
                    
       });
       
      
       
       
       function inRoomDetail(roomcode,join_member_cnt,member_cnt)
       {  	          	   
    	   var id = document.getElementsByName("id")[0].value;
    	  	   
    	   var senddata = "id="+id+"&roomcode="+roomcode;
                                   
   		    $.ajax({
   		        url:'/room/roomMemberCheck'
   		        , method : 'GET'
   		        , data : senddata
   		        , success :  function(result){
   		        		if(result == 's')
   		        		{
   		        		 	var url = '/room/roomPwCheck?roomcode='+roomcode;
   		              		window.open(url,"_blank_1","toolbar=no , menubar=no, scrollbars=yes,resizeble=no, width=450,height=300");
   		        			
   		        		}else{
   		        			if(parseInt(join_member_cnt) < parseInt(member_cnt))
		        			{
   		        				var url = '/room/roomPwCheck?roomcode='+roomcode;
   		                     	window.open(url,"_blank_1","toolbar=no , menubar=no, scrollbars=yes,resizeble=no, width=450,height=300");
 		        			}
   		        			else{
   		        				alert("방 인원이 전부 찼습니다.");
   		        			}
   		        			
   		        		}
   		        }
   		    });
   	          
       }
       
       
    </script>

<%@ include file="room/roomFooter.jsp" %>