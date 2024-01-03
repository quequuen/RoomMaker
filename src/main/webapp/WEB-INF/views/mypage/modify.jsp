<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="dynamicContent">
 <div id="my_main" style="background: #f7fdfe">
 	<div class="my_main_content" style="background: #ebf9f9">
 	<div class="my_inform_content" style="background: #f5f5dc;">
 		<div class="my_informs">
 		<div class="my_ modify_id"><label style="width:300px;">ID:</label> ${member.id }</div>
 		<div class="my_ modify_pw">
 		<label>PASSWORD:</label> <input type="password" class="pw" name="pw" value="${member.pw }">
 		</div>
 		<div class="my_ modify_repw">
 		<label>PASSWORD CHECK:</label> <input type="password" name="repw" class="repw">
 		</div>
 		<div class="my_ modify_name"><label>NAME:</label> <input type="text" class="name" name="name" value="${member.name }"></div>
 		<div class="my_ modify_email"><label>EMAIL:</label> <input type="text" class="email" name="email" value="${member.email }"></div>
 		<div class="my_ modify_phone"><label>PHONE:</label> <input type="text" class="phonenum" name="phonenum" value="${member.phonenum }" placeholder="010-0000-0000"></div>
 		</div>
 	</div>
 	<div class="buttons">
 	<input class="myResetButton" onclick="resetActionFromMypageModify()" type="button" value="취소">
 	<input class="myLeaveButton" onclick="myLeaveAction('${member.id}')" type="button" value="회원탈퇴">
 	<input class="myModifyButton" onclick="modifyMypageAction('${member.id}')" type="button" value="수정하기">
 	</div>
 </div>
</section>