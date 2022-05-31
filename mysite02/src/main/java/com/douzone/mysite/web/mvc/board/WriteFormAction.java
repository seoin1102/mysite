package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("no") != null) {
			String no2 = request.getParameter("no");
			long no= Long.parseLong(no2)+1L;
	
			List<BoardVo> list = new BoardRepository().findByNo(no);
			request.setAttribute("list",list);
		}

		if(request.getParameter("gNo") != null) {
			String gNo2 = request.getParameter("gNo");
			long gNo= Long.parseLong(gNo2);
	
		}
		WebUtil.forward(request, response,"board/write");


	}

}
