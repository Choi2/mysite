<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="word" name="word" value="">
					<input type="submit" value="찾기">
					<input type="hidden" name="page" value="${page}"/>
					<input type="hidden" name="a" value="searchword"/>
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list}" var="vo" varStatus="status">				
						<tr>
							<td>${vo.no}</td>
	
							<td style="text-align:left; padding-left:${(vo.depth) * 20}px">
							<c:if test="${vo.depth > 0}">
								<img src="/mysite/assets/images/reply.png"/>
							</c:if>
								<c:choose>
									<c:when test="${vo.boardDelete == 1}">
										<span style="font-weight:bold;">지워진 글입니다.</span>
									</c:when>
									<c:otherwise>
										<a href="/mysite/board?a=view&no=${vo.no}">${vo.title}</a>
									</c:otherwise>
								</c:choose>
							</td>

							<td>${vo.name}</td>
							<td>${vo.readCount}</td>
							<td><fmt:formatDate value="${vo.regDate}" pattern="yyyy-MM-dd"/></td> 

							<td>
								<c:if test="${(sessionScope.authUser.no eq vo.userNo) and vo.boardDelete == 0}">
									<a href="/mysite/board?a=delete&no=${vo.no}" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:set var="count" value="${fn:length(list)}"/>
				<div class="pager">
					<ul>
						<c:if test="${pager.startGroupPage > 0}">
							<li><a href="/mysite/board?page=${pager.startPage - 2}&word=${word}">◀</a></li>
						</c:if>
						<c:forEach begin="${pager.startPage}" end="${pager.endPage}" varStatus="status">
				
						<li class="selected">
						<a href="/mysite/board?page=${status.index - 1}&word=${word}">${status.index}</a></li>
						</c:forEach>
						
						<c:forEach begin ="${pager.endPage + 1}" end = '5'  varStatus="status">
							<li style="color:gray;">${status.index}</li>
						</c:forEach>
						
						<c:if test="${boardSize > 25}">
							<li><a href="/mysite/board?page=${pager.endPage}&word=${word}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<c:if test="${sessionScope.authUser ne null}">
				<div class="bottom">
					<a href="/mysite/board?a=writeform&userno=${userNo}" id="new-book">글쓰기</a>
				</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>