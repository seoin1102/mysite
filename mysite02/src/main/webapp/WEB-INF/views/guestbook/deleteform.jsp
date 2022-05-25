
<%@page import="com.douzone.mysite.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String no2 = request.getParameter("no");
	long no= Long.parseLong(no2);
	String password = request.getParameter("password");
	
	GuestbookVo vo = new GuestbookVo();
	vo.setNo(no);
	vo.setPassword(password);


%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="<%=request.getContextPath() %>/guestbook?a=delete&no=<%=vo.getNo()%>">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인">
				</form>
				<a href="<%=request.getContextPath()%>/guestbook?a=">방명록 리스트</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>