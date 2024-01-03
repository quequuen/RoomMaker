<%@page import="com.rm.service.RoomService"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
  <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
        }

        #header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            border-bottom: 1px solid #000; /* Add a dividing line */
            padding-bottom: 10px; /* Optional: Add some space between the header and the line */
        }

        #header img {
            max-height: 60px;
            margin: auto; 
        }

    </style>
</head>


<body>

 	<div id="header">
        <img src="../resources/images/logo.png" alt="웹 사이트 로고" >
		<a href="#" onclick="logout()">로그아웃</a>
    </div>
	

    <h2>회원 관리</h2>
    <table id="memberTable">
        <thead>
            <tr>
                <th>회원 ID</th>
                <th>회원 이름</th>
                <th>회원 이메일</th>
                <th>회원 전화번호</th>
                <th>성별</th>
                <th>탈퇴</th>
            </tr>
        </thead>
        <tbody>
           	<c:forEach items="${adminMemberList}" var="adminMList" varStatus="status">
           		<tr>
           			<td>${adminMList.id}</td>
           			<td>${adminMList.name}</td>
           			<td>${adminMList.email}</td>
					<td>${adminMList.phonenum}</td>
					<c:if test="${adminMList.gender == 1}">           		
           				<td>여</td>
           			</c:if>
           			<c:if test="${adminMList.gender == 0}">           		
           				<td>남</td>
           			</c:if>
         			<td><input type="button" onclick="adminMemberDeleteBtn('${adminMList.id}')" value="탈퇴" style="background: red; color:white;"></td>
           		</tr>
           	</c:forEach>
        </tbody>
    </table>

    <h2>방 관리</h2>
    <table id="roomTable">
        <thead>
            <tr>
                <th>방 ID</th>
                <th>방 이름</th>
                <th>방 종류</th>
                <th>참여 인원</th>
                <th>수용 인원</th>
                <th>방장</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${adminRoomList}" var="adminRList" varStatus="status">
            	<tr>
            		<td>${adminRList.roomcode}</td>
            		<td>${adminRList.title}</td>
            		<c:if test="${adminRList.type == 0}">           		
           				<td>교육기관</td>
           			</c:if>
           			<c:if test="${adminRList.type == 1}">           		
           				<td>프로젝트</td>
           			</c:if>
            		<td>${adminRList.join_member_cnt}</td>
            		<td>${adminRList.member_cnt}</td>
            		<td>${adminRList.id}</td>
            		<td><input type="button" onclick="adminRoomDetail('${adminRList.roomcode}')" value="삭제" style="background: red; color:white;"></td>
            	</tr>
            
            </c:forEach>
        </tbody>
    </table>

    <script>
       function logout()
       {
    	   location.href = "/member/Logout";
       }
      
       function adminRoomDetail(roomcode)
       {
    	   var url = '/admin/adminRoomDetail?roomcode='+roomcode;
           window.open(url,"_blank_1","toolbar=no , menubar=no, scrollbars=yes,resizeble=no, width=900,height=1000"); 
       }
       
       function adminMemberDeleteBtn(id)
       {
    	   var url = '/admin/deleteMember?id='+id;
           window.open(url,"_blank_1","toolbar=no , menubar=no, scrollbars=yes,resizeble=no, width=450,height=350");
       }
    </script>

</body>
</html>
