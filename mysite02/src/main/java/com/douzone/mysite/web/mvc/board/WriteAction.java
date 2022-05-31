package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

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
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String title= request.getParameter("title");
		String contents = request.getParameter("content");
		Long hit = 0L;
		String regDate = now.format(formatter).toString();
		BoardVo vo = new BoardVo();
		UserVo userVo = new UserVo();
		System.out.println("no?"+request.getParameter("no"));
		if(request.getParameter("no").length()>0) {
			System.out.println("no???"+request.getParameter("no"));

			String no2 = request.getParameter("no");
			long no= Long.parseLong(no2);
	
			List<BoardVo> list = new BoardRepository().findByNo(no);
			
	
			Long gNo = list.get(0).getgNo();
			Long oNo = list.get(0).getoNo()+1;
			int depth = list.get(0).getDepth()+1;
	
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);
	
			vo.setUserNo(authUser.getNo());
	
			new BoardRepository().update(no);
			new BoardRepository().insert(vo);
			
			}
		if(request.getParameter("gNo").length()>0)  {
			String gNo2 = request.getParameter("gNo");
			
			System.out.println("writeaction-"+request.getParameter("gNo"));

			Long gNo= Long.parseLong(gNo2);
	
			Long oNo = 1L;
			int depth = 1;
			
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);
			vo.setUserNo(authUser.getNo());
			
			new BoardRepository().insert(vo);
				
			}
		
		response.sendRedirect(request.getContextPath() + "/board");	
		
		//WebUtil.forward(request, response,"board/");


	}

}
