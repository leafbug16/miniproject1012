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
</head>
<body>
	<%@include file="navi.jsp" %>
	<h1>[게시글 수정]</h1>
	<form action="update" method="post">
		<input type="hidden" name="num" value="${dto.getNum() }">
		제목<br>
		<input type="text" name="title" value="${dto.getTitle() }"><br>
		내용<br>
		<textarea name="content" style="height: 350px;">${dto.getContent() }</textarea>
		<button type="submit">수정완료</button>
		<button type="reset">다시입력</button>
		<button type="reset" onclick="location.href='bbs'">목록보기</button>
	</form>
	<%@include file="footer.jsp" %>
</body>
</html>