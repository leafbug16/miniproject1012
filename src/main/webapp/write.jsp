<%@page import="util.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="navi.jsp" %>
	<h3>글쓰기 화면</h3>
	<form action="write" method="post">
		제목 : <input type="text" name="title" autofocus><br><br>
		내용 : <textarea name="content" style="height: 300px"></textarea><br>
		<select name="boardType">
			<option value="ano">공지사항</option>
			<option value="free" selected>자유게시판</option>
		</select><br>
		<button>작성</button>
		<button type="reset">리셋</button>
		<button type="button" onclick="location.href='bbs';">목록보기</button>
	</form>
</body>
</html>