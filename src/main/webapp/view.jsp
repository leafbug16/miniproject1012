<%@page import="dto.Bbs"%>
<%@page import="dao.BbsDAO"%>
<%@page import="util.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view</title>
<script src="https://kit.fontawesome.com/77d5171cb8.js" crossorigin="anonymous"></script>
<style>
table, tr, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<%@include file="navi.jsp"%>
	<h1>[view]</h1>
	<table style="border: 1px solid black">
		<tr>
			<td colspan="5">게시판 글 보기</td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td>번호</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>작성일</td>
		</tr>
		<tr>
			<td>
      	<c:choose>
       		<c:when test="${dto.getAno() == 1 }">[공지사항]</c:when>
        	<c:otherwise>[자유게시판]</c:otherwise>
        </c:choose>
      </td>
			<td>${dto.getNum() }</td>
			<td>${dto.getId() }</td>
			<td>${dto.getViewCnt() }</td>
			<td>${dto.getPostDate() }</td>
		</tr>

		<tr>
			<td>제목</td>
			<td colspan="4">${dto.getTitle() }</td>
		</tr>
		<tr>
			<td colspan="5">내용</td>
		</tr>
		<tr>
			<td colspan="5">${dto.getContent() }</td>
		</tr>
		<tr>
			<td colspan="5">
				<c:if test="${not empty sessionScope.id } && ${sessionScope.id == dto.getId() }">
					<button type="button" onclick="location.href='update?num=${dto.getNum() }';">수정</button>
					<button type="button" onclick="location.href='delete?num=${dto.getNum() }'">삭제</button>
				</c:if>
				<c:choose>
					<c:when test="${not empty back }">
						<button type="button" onclick="location.href='${back}';">목록보기</button>
					</c:when>
					<c:otherwise>
						<button type="button" onclick="location.href='./bbs';">목록보기</button>						
					</c:otherwise>
				</c:choose>
				<!-- 좋아요 기능 추가 -->
				<c:if test="${res==1 }">
					<button id='likeNum' type='button' onclick='unlike(bno);' data-cnt ='1' data-check='true'><i class="fa-solid fa-thumbs-up fa-lg"></i></button>
				</c:if>
				<c:if test='${res!=1 }'>
					<button id='likeNum' type='button' onclick='like(bno);' data-cnt ='0' data-check='false'><i class="fa-regular fa-thumbs-up fa-lg"></i></button>
				</c:if>
				[${dto.getLikeNum() }]
			</td>
		</tr>
	</table>
	<%@include file="comment.jsp" %>
	<%@include file="footer.jsp" %>
	
 	<script>
		bno = ${param.num };
		function like(bno) {
			location.href = "./like?bno="+bno+"&mode=like";
		}
		function unlike(bno) {
			location.href = "./like?bno="+bno+"&mode=unlike";
		}
	</script>
	
<!-- 	<script>
		function deletePost(){
			let confirmed = confirm("정말로 삭제하겠습니까?");
			if(confirmed){
				let form = document.writeFrm; // form name 속성 writeFrm
				form.method="post";
				form.action="delete";
				form.submit();
			}
		}
	</script> -->
</body>
</html>

















