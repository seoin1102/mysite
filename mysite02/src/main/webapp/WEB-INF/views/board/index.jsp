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
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?end=5" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
					
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
					<c:set var='count' value='${fn:length(list)}'/> 
					
					<c:choose>
					<c: if test = '${param.end >0}'>
					<c:when test = '${fn:length(param.kwd)>0}'>
						<c:forEach items='${list}' begin='0' end='${count}' var='vo' varStatus='status'>	
						<c:if test='${fn:contains(vo.title,param.kwd)}'>
							<tr>
							<td>${count-status.index}</td>
							<c:if test = '${vo.depth != 1}'>
								<td style="text-align:left; padding-left:${(vo.depth-1)*5}0px"><img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' /><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title}</a></td>
							</c:if>
							<c:if test = '${vo.depth == 1}'>
								<td style="text-align:left; padding-left:${(vo.depth-1)*10}0px"><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title}</a></td>
							</c:if>
							<td>${vo.userName}</td>
							<td>${vo.hit}</td>
							<td>${vo.regDate}</td>
							<c:if test = '${vo.userNo == userVo.no}'>
							<td><a href="${pageContext.request.contextPath }/board?a=delete&gNo=${vo.gNo}&oNo=${vo.oNo}&userNo=${userVo.no}" class="del">삭제</a></td>
							</c:if>
						</tr>
						</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach items='${list}' begin='${param.end-5}' end='${param.end-1}' var='vo' varStatus='status'>	
					
						<tr>
						<td>${count-status.index}</td>
						<c:if test = '${vo.depth != 1}'>
							<td style="text-align:left; padding-left:${(vo.depth-1)*5}0px"><img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' /><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title}</a></td>
						</c:if>
						<c:if test = '${vo.depth == 1}'>
							<td style="text-align:left; padding-left:${(vo.depth-1)*10}0px"><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title}</a></td>
						</c:if>
						<td>${vo.userName}</td>
						<td>${vo.hit}</td>
						<td>${vo.regDate}</td>
						<c:if test = '${vo.userNo == userVo.no}'>
						<td><a href="${pageContext.request.contextPath }/board?a=delete&gNo=${vo.gNo}&oNo=${vo.oNo}&userNo=${userVo.no}" class="del">삭제</a></td>
						</c:if>
					</tr>
						</c:forEach>	
					</c:otherwise>
					</c:>
					</c:choose>
						
					<br>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test= '${param.end-5>0}'>
							<li><a href="${pageContext.request.contextPath }/board?end=${param.end-5}">◀</a></li>
						</c:if>
						<c:forEach begin='1' end='${count/5+1}' step='1' var ='i'>
						
						<c:choose>
							<c:when test='${param.end/5==i}'>
								<li class="selected"><a href="${pageContext.request.contextPath }/board?end=${i*5}">${i}</a></li>
					
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }/board?end=${i*5}">${i}</a></li>
							</c:otherwise>
							
						</c:choose>
						</c:forEach>
						<c:if test= '${param.end<count}'>
							<li><a href="${pageContext.request.contextPath }/board?end=${param.end+5}">▶</a></li>
						</c:if>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
				
				<c:choose>
					<c:when test="${empty authUser }">
						<a href="${pageContext.request.contextPath }/user?a=loginform" id="new-book">글쓰기</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/board?a=writeForm&gNo=${count+1}" id="new-book">글쓰기</a>
						
					</c:otherwise>
				</c:choose>
			
				
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>