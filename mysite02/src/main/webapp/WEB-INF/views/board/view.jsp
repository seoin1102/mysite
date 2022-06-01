<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@page import="java.util.List"%>
<%@page import="com.douzone.mysite.repository.BoardRepository"%>
<%@page import="com.douzone.mysite.vo.BoardVo"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<c:forEach items='${list}' var='vo' varStatus='status'>	
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${vo.contents}<br>
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
				<c:choose>
					<c:when test="${empty authUser }">
						<a href="${pageContext.request.contextPath }/board?end=5">글목록</a>
						<a href="${pageContext.request.contextPath }/user?a=loginform" id="new-book">글수정</a>
						<a href="${pageContext.request.contextPath }/user?a=loginform" id="new-book">답글달기</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/board?end=5">글목록</a>
						<c:if test = '${vo.userNo == userVo.no}'>
							<a href="${pageContext.request.contextPath }/board?a=modifyForm&no=${param.no}">글수정</a>
						</c:if>
						<a href="${pageContext.request.contextPath }/board?a=writeForm&no=${param.no}">답글달기</a>
					
					</c:otherwise>
				</c:choose>
				</div>
				</c:forEach>		
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>