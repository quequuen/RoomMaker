<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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

        .deleteBtn,select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

       .bottomBtn{
       	  margin-top: 50px;
		  background: skyblue;
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
            border-bottom: 1px solid #000; 
            padding-bottom: 10px;
        }

        #header img {
            max-height: 60px;
            margin: auto; 
        }

    </style>
</head>

<body>
<h2>방 정보 관리</h2>
    <table id="memberTable">
        <thead>
            <tr>
                <th>방 ID</th>
                <th>질문 ID</th>
                <th>답변</th>
                <th>작성일</th>
                <th>작성자</th>
                <th>삭제</th>
         
            </tr>
        </thead>
        <tbody>
           	<c:forEach items="${answerList}" var="answerList" varStatus="status">
           		<tr>
           			<td>${answerList.roomcode}</td>
           			<td>${answerList.questioncode}</td>  		
          			<td>${answerList.answer }</td>
	                <td><fmt:formatDate pattern="yyyy/MM/dd" value="${answerList.answerdate}"/></td>					
					<td>${answerList.answermember}</td>
					<td><input type="button" class="deleteBtn" onclick="deleteAnswerList('${answerList.roomcode}','${answerList.answer}','${answerList.questioncode}')" value="삭제" style="background: red; color:white;"></td>
           		</tr>
           	</c:forEach>
        </tbody>
    </table>
</body>

<script>
	function deleteAnswerList(roomcode,answer,questioncode)
	{
		alert("답변 : "+answer+"가 삭제되었습니다.");
		location.href = "/admin/deleteAnswerList?roomcode="+roomcode+"&answer="+answer+"&questioncode="+questioncode;
	}
</script>
</html>