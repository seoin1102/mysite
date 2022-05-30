package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no2 = request.getParameter("no");
		System.out.println(no2);
		long no= Long.parseLong(no2);
		System.out.println(no);

		List<BoardVo> list = new BoardRepository().findByNo(no);
		request.setAttribute("list",list);
		System.out.println(list);
		WebUtil.forward(request, response, "board/view");

	}

}
