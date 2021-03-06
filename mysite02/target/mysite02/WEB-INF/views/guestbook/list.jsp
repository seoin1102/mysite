<%@page import="com.douzone.mysite.vo.GuestbookVo"%>
<%@page import="com.douzone.mysite.repository.GuestbookRepository"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<GuestbookVo> list = new GuestbookRepository().findAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<li><a href="">로그인</a><li>
				<li><a href="">회원가입</a><li>
				<li><a href="">회원정보수정</a><li>
				<li><a href="">로그아웃</a><li>
				<li>님 안녕하세요 ^^;</li>
			</ul>
		</div>
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath() %>/guestbook?a=add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<br>
		
	<%
	int i = 1;
		for(GuestbookVo vo:list) {
			
	%>	
		<table width=510 border=1>
			<tr> 
				<td><%= i%></td>
				
				<td><%=vo.getName() %></td>
				<td><%=vo.getDatetime() %></td>

				
				<td><a href="<%=request.getContextPath() %>/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan=4><%=vo.getMessage() %></td>
			</tr>
		</table>
		<br/>
	<%

		i++;
		}
	%>
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>

	<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>