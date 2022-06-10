package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.mysite.service.UserService;



@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpSession session,  Model model) {
		List<BoardVo> list = boardService.getIndex();
		model.addAttribute("list",list);
		if(session == null) {
			return "board/index";
		}
		UserVo authUser= (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			return "board/index";
		}

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		return "board/index";
	}
	
	@RequestMapping(value = "/page/{end}", method = RequestMethod.GET)
	public String index(@PathVariable("end") int end, Model model) {
		List<BoardVo> list = boardService.getIndex();
		model.addAttribute("end",end);
		model.addAttribute("list",list);
		return "board/index";
	}
	
	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(HttpSession session, @PathVariable("no") long no, Model model) {
		BoardVo vo = boardService.getView(no);
		model.addAttribute("no",no);
		model.addAttribute("vo",vo);
		if(session == null) {
			return "board/view";
		}
		UserVo authUser= (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "board/view";
		}
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		
		return "board/view";
	}
	
	@RequestMapping(value = "/delete/{gNo}/{oNo}/{userNo}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") long no, Model model) {
		BoardVo vo = boardService.getView(no);
		model.addAttribute("no",no);
		model.addAttribute("vo",vo);
		
	 
		
		return "board/view";
	}

	
}
