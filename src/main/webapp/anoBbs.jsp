<%@page import="dto.Bbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>공지사항</title>
  <script src="https://kit.fontawesome.com/77d5171cb8.js" crossorigin="anonymous"></script>
  <style>
  	.con {
  		display: flex;
			flex-direction: column;
			min-height: calc(100vh - 150px);
  	}
  	
  	.spacing {
  		flex: 1 0 auto;
  	}
  	
  	#searchForm{
  		margin-left: 950px;
  		margin-bottom: 15px;
  	}
  	#bbsTitle {
  		margin-top: 20px;
  	}
  	.pageMove {
  		padding: 5px;
  		border: 1px solid lightgray;
  		color: black;
  		text-decoration: none;
  	}
  	.check {
  		color: red !important;
  	}
  	#pageNum {
  		padding: 5px;
  		border: 1px solid lightgray;
  		color: black;
  		text-decoration: none;
  	}
  	#boardBottom {
  		margin-left: 580px;
  		margin-top: 30px;
  	}
  	#writeBtn {
  		margin-left: 550px;
  	}
  	#formControll {
  		min-height: 350px;
  	}
  </style>
</head>

<body>
  <%@include file="navi.jsp" %>
<div class="container-xxl con">
  <div class="container-xxl spacing">
  	<div id="formControll">
		  <h3 id="bbsTitle">공지사항</h3>
		  <!-- 검색폼 -->
		  <!-- searchField값, searchWord값을 list 컨트롤러로 보내는 역할 -->
		  <!-- new action bbs -> anoBbs -->
		  <form action="anoBbs" method="get" onsubmit="return searchCheck(this)" id="searchForm">
		  	<input type="hidden" name="ano" value="1">
		    <table id="tsearch">
		      <tr>
		        <td>
		          <select class="form-select form-select-sm" aria-label="Small select example" name="searchField" style="width: 100px; display: inline-block">
		            <option value="title" ${param.searchField eq "title" ? "selected" : "" }>제목</option>
		            <option value="content" ${param.searchField eq "content" ? "selected" : "" }>내용</option>
		          </select>
		          <input class="form-control form-control-sm" type="text" name="searchWord" id="search"
		              value='${ empty param.searchWord ? "" : param.searchWord }' style="width: 150px; display: inline-block">
		          <button type="button" class="btn btn-secondary btn-sm">검색</button>
		        </td>
		      </tr>
		    </table>
		  </form>
		
		  <table class="table table-hover text-center">
		    <thead>
		      <tr>
			      <th>번호</th>
			      <th>제목</th>
			      <th>작성자</th>
			      <th>조회수</th>
			      <th><i class="fa-solid fa-thumbs-up fa-lg"></i></th>
			      <th>작성일</th>
		      </tr>
		    </thead>
		    <tbody class="table-group-divider">
		      <c:forEach var="list" items="${boardLists }">
		        <tr style="text-align: center; border-top: 1px solid lightgray;">
		          <td>${list.getNum() }</td>
		          <td>
		  					<a href="view?num=${list.getNum() }&back=anoBbs">${list.getTitle() }
		    				<c:if test="${list.getCommentCnt() > 0}">[${list.getCommentCnt() }]</c:if>
		  					</a>
							</td>
		          <td>${list.getId() }</td>
		          <td>${list.getViewCnt() }</td>
		          <td>${list.getLikeNum() }</td>
		            <fmt:formatDate value="${list.getPostDate() }" type="date" pattern="yyyy-MM-dd" var="postDate" />
		            <fmt:formatDate value="${list.getPostDate() }" type="time" pattern="HH:mm" var="postTime" />
		            <fmt:formatDate value="<%=new java.util.Date()%>" type="date" pattern="yyyy-MM-dd" var="today" />
		            <c:choose>
		              <c:when test="${postDate eq today }">
		          <td>${postTime }</td>
		          </c:when>
		          <c:otherwise>
		            <td>${postDate }</td>
		          </c:otherwise>
		          </c:choose>
		        </tr>
		      </c:forEach>
		    </tbody>
		  </table>
		</div>
	  <div id="boardBottom">
	  <c:if test="${ph.showPrev }">
	  	<!-- new 쿼리스트링 &ano=1 추가, anoBbs로 변경 -->
	    <a href="<c:url value='/anoBbs${ph.sc.getQueryString(ph.beginPage-1)}&ano=1'/>" class="pageMove">&laquo;</a>
	  </c:if>
	  <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
	  	<!-- new 쿼리스트링 &ano=1 추가, anoBbs로 변경 -->
	    <a class="${ph.sc.page==i? 'check':'' }" href="<c:url value='/anoBbs${ph.sc.getQueryString(i)}&ano=1'/>" id="pageNum">${i }</a>
	  </c:forEach>
	  <c:if test="${ph.showNext }">
	    <a href="<c:url value='/anoBbs${ph.sc.getQueryString(ph.endPage+1)}&ano=1'/>" class="pageMove">&raquo;</a>
	  </c:if>
	  <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='write?back=anoBbs';" id="writeBtn">글쓰기</button>
	  </div>
  </div>
</div>
  <%@include file="footer.jsp" %>
  <script>
    function searchCheck(frm) {
      if (frm.searchWord.value.trim() == "") {
        alert("검색어를 입력해주세요");
        frm.searchWord.focus();
        return false;
      }
    }
  </script>
</body>

</html>













