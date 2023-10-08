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
  	* {
  		text-decoration: none;
  		color: black;
  	}
    tr {
      height: 35px;
    }
   	.check{
			color: red;
			border:1px solid #e5e7ea;
		}
  </style>
</head>

<body>
  <%@include file="navi.jsp" %>
  <h2>[공지사항]</h2>
  <!-- 검색폼 -->
  <!-- searchField값, searchWord값을 list 컨트롤러로 보내는 역할 -->
  <!-- new action bbs -> anoBbs -->
  <form action="anoBbs" method="get" onsubmit="return searchCheck(this)">
  	<input type="hidden" name="ano" value="1">
  	<!-- new -->
    <table class="border-none" id="tsearch">
      <tr class="border-none">
        <td class="border-none">
          <select name="searchField">
            <option value="title" ${param.searchField eq "title" ? "selected" : "" }>제목</option>
            <option value="content" ${param.searchField eq "content" ? "selected" : "" }>내용</option>
          </select>
          <input type="text" name="searchWord" id="search"
            value='${ empty param.searchWord ? "" : param.searchWord }'>
          <button id="sBtn">검색</button>
        </td>
      </tr>
    </table>
  </form>

  <table style="border: 1px solid black; border-collapse: collapse;">
    <thead>
      <tr>
        <th style="width: 50px;">번호</th>
        <th style="width: 250px;">제목</th>
        <th style="width: 50px;">작성자</th>
        <th style="width: 50px;">조회수</th>
        <th style="width: 50px;"><i class="fa-solid fa-thumbs-up fa-lg"></i></th>
        <th style="width: 80px;">작성일</th>
      </tr>
    </thead>
    <tbody>
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
<%-- <c:if test="${pageNumber != 1 }"><a href="bbs?pageNumber=${pageNumber - 1 }">이전</a></c:if>
  <c:if test="${dao.nextPage(pageNumber + 1) }"><a href="bbs?pageNumber=${pageNumber + 1 }">다음</a></c:if> --%>
  
  <button type="button" onclick="location.href='write?back=anoBbs';">글쓰기</button>
  <c:if test="${ph.showPrev }">
  	<!-- new 쿼리스트링 &ano=1 추가, anoBbs로 변경 -->
    <a href="<c:url value='/anoBbs${ph.sc.getQueryString(ph.beginPage-1)}&ano=1'/>">&laquo;</a>
  </c:if>
  <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
  	<!-- new 쿼리스트링 &ano=1 추가, anoBbs로 변경 -->
    <a class="${ph.sc.page==i? 'check':'' }" href="<c:url value='/anoBbs${ph.sc.getQueryString(i)}&ano=1'/>">${i }</a>
  </c:forEach>
  <c:if test="${ph.showNext }">
    <a href="<c:url value='/anoBbs${ph.sc.getQueryString(ph.endPage+1)}&ano=1'/>">&raquo;</a>
  </c:if>
  
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













