<%@page import="dto.Bbs"%>
<%@page import="dao.BbsDAO"%>
<%@page import="util.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.updateTitle {
		margin-top: 20px;
		margin-left: 200px;
	}
	.title {
		width: 810px;
		margin-left: 200px;
	}
	.content {
		width: 810px;
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
		<h3 class="updateTitle">글수정</h3>
		<form action="update" method="post">
			<input type="hidden" name="num" value="${dto.getNum() }">
			<input class="title" type="text" name="title" value="${dto.getTitle() }"><br><br>
			<textarea class="content" name="content" style="height: 350px;">${dto.getContent() }</textarea><br><br>
			<button class="btn btn-primary btn-sm" id="button" type="submit">수정</button>
			<button class="btn btn-secondary btn-sm" type="reset">복구</button>
			<button class="btn btn-secondary btn-sm" type="reset" onclick="location.href='bbs'">목록</button>
		</form>
	</div>
</body>
</html>