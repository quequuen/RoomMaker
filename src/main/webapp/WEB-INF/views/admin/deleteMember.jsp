<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
th, td{
	 border: 1px solid #ddd;
     padding: 8px;
     text-align: left;
}

p{
	font-size: 20px;
	font-style: bold;
}

input{
	  background: skyblue;
      color: white;
      padding: 10px 15px;
      border: none;
      cursor: pointer;
}
</style>
</head>
<body>
<table style="margin: 0 auto;">
	<tr>
		<th>ID</th>
		<td>${deleteMember.id}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${deleteMember.name}</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${deleteMember.email}</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${deleteMember.phonenum}</td>
	</tr>
	<tr>
		<th>성별</th>
		<c:if test="${deleteMember.gender == 1}">           		
			<td>여</td>
		</c:if>
		<c:if test="${deleteMember.gender == 0}">           		
			<td>남</td>
		</c:if>
	</tr>
</table>
<br>
<div style="text-align: center;">
<p>${deleteMember.name} 회원을 탈퇴처리 하시겠습니까?<p>
<br>
<input type="button" onclick="deleteMember('${deleteMember.id}')" value="탈퇴"/>
<input type="button" onclick="cancel()" value="취소"/>
</div>

<script>
function deleteMember(id) {
    opener.location.href='/admin/deleteMemberDo?id=' + id;
    window.close();
}

function cancel() {
    opener.location.href='/admin/admin_main';
    window.close();
}

</script>
</body>
</html>