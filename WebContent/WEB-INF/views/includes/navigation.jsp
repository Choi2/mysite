<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<div id="navigation">
			<ul>
				<c:choose>
				<c:when test='${param.menu eq "main"}'>
					<li class="selected"><a href="/mysite/main">최성진</a></li>
					<li><a href="/mysite/guestbook">방명록</a></li>
					<li><a href="/mysite/board">게시판</a></li>
				</c:when>
				
				<c:when test='${param.menu eq "guestbook"}'>
					<li><a href="/mysite/main">최성진</a></li>
					<li class="selected"><a href="/mysite/guestbook">방명록</a></li>
					<li><a href="/mysite/board">게시판</a></li>
				</c:when>
				
				<c:when test='${param.menu eq "board"}'>
					<li><a href="/mysite/main">최성진</a></li>
					<li><a href="/mysite/guestbook">방명록</a></li>
					<li class="selected"><a href="/mysite/board">게시판</a></li>
				</c:when>
				
				<c:otherwise>
					<li><a href="/mysite/main">최성진</a></li>
					<li><a href="/mysite/guestbook">방명록</a></li>
					<li><a href="/mysite/board">게시판</a></li>
				</c:otherwise>
				</c:choose>

			</ul>
		</div>