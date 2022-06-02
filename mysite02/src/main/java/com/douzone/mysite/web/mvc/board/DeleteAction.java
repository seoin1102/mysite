package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();
		
		String gNo2= request.getParameter("gNo");
		String oNo2= request.getParameter("oNo");
		String userNo2= request.getParameter("userNo");
		
		long gNo = Long.parseLong(gNo2);
		long oNo = Long.parseLong(oNo2);
		long userNo = Long.parseLong(userNo2);

		vo.setgNo(gNo);
		vo.setoNo(oNo);
		vo.setUserNo(userNo);
                                             
		
		new BoardRepository().delete(vo);
		response.sendRedirect(request.getContextPath() + "/board?end=5");	

	}

}
