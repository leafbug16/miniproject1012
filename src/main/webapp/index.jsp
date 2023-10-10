<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
#con {
	display: flex;
	flex-direction: column;
	height: calc(100vh-50px);
}

#header {
	height: 50px;
	flex-shrink: 0;
}

#footer {
	height: 50px;
	flex-shrink: 0;
}

#spacing{
 flex: 1 0 auto;
}
</style>
</head>
<body>
	<div id="con">
		<%@include file="navi.jsp"%>
		<div class="container-xxl" id="spacing"></div>
	</div>
		<%@include file="footer.jsp"%>
</body>
</html>