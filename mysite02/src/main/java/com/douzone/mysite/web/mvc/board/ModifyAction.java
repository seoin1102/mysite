package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		if(session == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
			}
		
	
		//request.setCharacterEncoding("utf-8");

		String title= request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		
		String no2 = request.getParameter("no");

		long no= Long.parseLong(no2);
		List<BoardVo> list = new BoardRepository().findByNo(no);

		vo.setTitle(title);
		vo.setContents(contents);
		vo.setNo(no);
		System.out.println(list.get(0).getHit());
		vo.setHit(list.get(0).getHit());
		
		vo.setUserNo(authUser.getNo());

		new BoardRepository().modify(vo);
	
		response.sendRedirect(request.getContextPath() + "/board?a=view&no="+no);	


	}

}
