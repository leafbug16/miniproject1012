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
	min-height: calc(100vh - 100px);
	border: 1px solid cyan;
}

.navbar {
	height: 50px;
	flex-shrink: 0;
	border: 1px solid red;
}

#spacing{
 flex: 1 0 auto;
 border: 1px solid black;
}

.footer {
	height: 50px;
	flex-shrink: 0;
	bottom: 0;
	border: 1px solid blue;
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