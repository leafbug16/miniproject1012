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
	#bbsTitle {
		margin-top: 20px;
	}
	th {
		background-color: lightgray !important;
		text-align: center !important;
	}
	td {
		text-align: center !important;
	}
	.td-content {
		text-align: left !important;
	}
</style>
</head>
<body>
	<%@include file="navi.jsp"%>
	<main class="container-xxl spacing">
		<h3 id="bbsTitle">view</h3>
		<table class="table table-bordered border-dark">
			<tr>
				<th>카테고리</th>
				<th>번호</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
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
				<fmt:formatDate value="${dto.getPostDate() }" type="date" pattern="yyyy-MM-dd HH시 mm분" var="postDate" />
	      <fmt:formatDate value="${dto.getPostDate() }" type="time" pattern="오늘 HH시 mm분" var="postTime" />
	      <fmt:formatDate value="<%=new java.util.Date()%>" type="date" pattern="yyyy-MM-dd" var="today" />
	      <c:choose>
	      	<c:when test="${postDate eq today }">
          	<td>${postTime }</td>
          </c:when>
          <c:otherwise>
          	<td>${postDate }</td>
          </c:otherwise>
        </c:choose>
				<%-- <td>${dto.getPostDate() }</td> --%>
			</tr>
	
			<tr>
				<td colspan="5" class="h4">${dto.getTitle() }</td>
			</tr>
			<tr>
				<th colspan="5" style="height:4px;"></th>
			</tr>
			<tr>
				 <td colspan="5">
    			<div class="td-content" style="min-height: 200px;">${dto.getContent() }</div>
  			</td>
			</tr>
			<tr>
				<td colspan="5">
					<c:if test="${not empty sessionScope.id and sessionScope.id == dto.getId() }">
						<button class="btn btn-secondary btn-sm" type="button" onclick="location.href='update?num=${dto.getNum() }'">수정</button>
						<button class="btn btn-secondary btn-sm" type="button" onclick="location.href='delete?num=${dto.getNum() }'">삭제</button>
					</c:if>
					<c:choose>
						<c:when test="${not empty back }">
							<button class="btn btn-secondary btn-sm" type="button" onclick="location.href='${back}';">목록</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-secondary btn-sm" type="button" onclick="location.href='./bbs';">목록</button>						
						</c:otherwise>
					</c:choose>
					<!-- 좋아요 기능 추가 -->
					<c:if test="${res==1 }">
						<button class="btn btn-light-sm" id='likeNum' type='button' onclick='unlike(bno);' data-cnt ='1' data-check='true'><i class="fa-solid fa-thumbs-up fa-lg"></i></button>
					</c:if>
					<c:if test='${res!=1 }'>
						<button class="btn btn-light-sm" id='likeNum' type='button' onclick='like(bno);' data-cnt ='0' data-check='false'><i class="fa-regular fa-thumbs-up fa-lg"></i></button>
					</c:if>
					[${dto.getLikeNum() }]
				</td>
			</tr>
		</table>
		<%@include file="comment.jsp" %>
	</main>
	
 	<script>
		bno = ${param.num };
		function like(bno) {
			location.href = "./like?bno="+bno+"&mode=like";
		}
		function unlike(bno) {
			location.href = "./like?bno="+bno+"&mode=unlike";
		}
	</script>
</body>
</html>

















