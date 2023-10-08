<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
* {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>
	<div id="navi">
		<div id="title">
			<div id="logo">
				<a href="./main">miniproject1012</a>
			</div>
		</div>
		<div class="topnav">
			<!-- new ano=1 쿼리스트링 추가함 -->
			<a href="./bbs">전체글게시판</a>
			<a href="./anoBbs?ano=1">공지사항</a>
			<a href="./freeBbs?free=1">자유게시판</a>
			 <a href="#">TODOLIST</a>
			<div class="topnav-right">
				<c:choose>
					<c:when test="${not empty sessionScope.id}">
						<a href="./logout">로그아웃</a>
					</c:when>
					<c:otherwise>
						<a href="./login">로그인</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<div id="text">
		<c:if test="${not empty sessionScope.name}">
			<span id="name">${sessionScope.name}</span>님 환영합니다^^
		</c:if>
	</div>
</body>
</html>