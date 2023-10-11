<%@page import="util.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.writeTitle {
		margin-top: 20px;
		margin-left: 200px;
	}
	select {
		display:inline-block;
	}
	.title {
		width: 700px;
	}
	.content {
		width: 810px;
		margin-left: 200px;
	}
	#boardType {
		margin-left: 200px;
	}
	#button {
		margin-left: 200px;
	}
</style>
</head>
<body>
	<%@include file="navi.jsp" %>
	<div class="container-xxl">
		<h3 class="writeTitle">글쓰기</h3>
		<form action="write" method="post">
			<select id="boardType" name="boardType">
				<option value="ano">공지사항</option>
				<option value="free" selected>자유게시판</option>
			</select>
			<input class="title" type="text" name="title" autofocus placeholder="제목"><br><br>
			<textarea class="content" name="content" style="height: 300px" placeholder="내용"></textarea><br><br>
			<button class="btn btn-primary btn-sm" id="button">작성</button>
			<button class="btn btn-secondary btn-sm" type="button" onclick="location.href='bbs';">목록</button>
		</form>
	</div>
</body>
</html>