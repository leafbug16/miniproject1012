<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1><a href="./main">miniproject1012</a></h1>
	<h1>[로그인]</h1>
	<form action="./login" method="post" onsubmit="return formCheck(this)">
		<input type="hidden" name="back" value="${back }">
		<div id="msg"></div>
		id : <input type="text" name="id" placeholder="아이디 입력" autofocus value="${cookie.id.value }"><br>
		pw : <input type="password" name="password" placeholder="비밀번호 입력"><br>
		<button>뢰괴인</button><br>
		<input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked" }> 아이디 기억
		<a href="./register">회원가입</a>
	</form>
	
	<script>
	  if (${ param.logError != null }) document.querySelector("#msg").innerText = "아이디와 비밀번호를 확인해주세요"
	
	  function formCheck(frm) {
	    if (frm.id.value.length == 0) {
	      setMessage("아이디를 입력해주세요", frm.id);
	      return false;
	    }
	    if (frm.password.value.length == 0) {
	      setMessage("비밀번호를 입력해주세요", frm.pwd);
	      return false;
	    }
	    return true;
	  }
	
	  function setMessage(msg, element) {
	    document.getElementById("msg").innerHTML = msg;
	    if (element) {
	      element.select();
	    }
	  }
	</script>
</body>
</html>