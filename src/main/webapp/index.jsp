<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<%@include file="navi.jsp" %>
 	<script>
		location.href = "./bbs";
	</script>
	<jsp:include page="bbs.jsp" />
	<%@include file="footer.jsp" %>
</body>
</html>