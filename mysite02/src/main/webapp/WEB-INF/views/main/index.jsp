<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" style="width:300px" src="<%=request.getContextPath()%>/assets/images/profile.png">
					<h2>안녕하세요. 김서인의  mysite에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는  웹 프로그램밍 실습과제 예제 사이트입니다.<br>
						치킨 사준다고 한 사람 010-3459-4713으로 연락주세요.<br>
						기다리고 있겠읍니다.<br>
						<a href="<%=request.getContextPath()%>/guestbook?a=">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>

		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>

	</div>
</body>
</html>