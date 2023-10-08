<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1><a href="./main">miniproject1012</a></h1>
	<h1>[회원가입]</h1>
	<form action="./register" method="post" onsubmit="return formCheck(this)">
		<div id="msg" class="msg"></div>
		id : <input type="text" name="id" placeholder="아이디 입력" autofocus>
		<button type="button">중복 췍</button><br>
		pw : <input type="password" name="password" placeholder="비밀번호 입력"><br>
		name : <input type="text" name="name" placeholder="이름 입력"><br>
		<button>회원가입</button>
	</form>
	<script>
	  if (${ param.regError != null }) document.querySelector("#msg").innerText = "아이디 중복입니다"
	
	  function formCheck(frm) {
	    if (frm.id.value.length == 0) {
	      setMessage("아이디를 입력해주세요", frm.id);
	      return false;
	    }
	    if (frm.password.value.length == 0) {
	      setMessage("비밀번호를 입력해주세요", frm.pwd);
	      return false;
	    }
	    if (frm.id.value.length == 0) {
	      setMessage("이름을 입력해주세요", frm.name);
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