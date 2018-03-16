<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Table</h1>
	<table border="1px" cellspacing="0" cellpadding="5px">
	<c:forEach begin="1" end="${param.r}" step="1" var="i">
		<tr>
			<td>cell(0, ${i })</td>
			<td>cell(1, ${i })</td>
			<td>cell(2, ${i })</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>