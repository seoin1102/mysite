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
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
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
	
	@RequestMapping("")
	public String index(@AuthUser UserVo authUser,  Model model) {
		List<BoardVo> list = boardService.getIndex();
		model.addAttribute("list",list);
		
		return "board/index";
	}
	
	@RequestMapping(value = "/page/{end}", method = RequestMethod.GET)
	public String index(@AuthUser UserVo authUser,@PathVariable("end") int end, Model model) {
		List<BoardVo> list = boardService.getIndex();
		model.addAttribute("end",end);
		model.addAttribute("list",list);

		return "board/index";
	}
	
	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(UserVo authUser, @PathVariable("no") long no, Model model) {
		BoardVo vo = boardService.getView(no);
		vo.setHit(vo.getHit()+1);
		System.out.println("HIT!!!"+vo.getHit());
		boardService.updateHit(vo);
		model.addAttribute("no",no);
		model.addAttribute("vo",vo);
		

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/delete/{gNo}/{oNo}/{userNo}", method = RequestMethod.GET)
	public String delete(@AuthUser UserVo authUser, @PathVariable("gNo") long gNo, @PathVariable("oNo") long oNo, @PathVariable("userNo") long userNo) {
	
		boardService.deleteMessage(gNo, oNo, authUser.getNo());
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/write/{gNo}", method= RequestMethod.GET)
	public String write(@PathVariable("gNo") long gNo,  Model model) {
		return "board/write";
	}
	
	@RequestMapping(value="/write/{gNo}/{oNo}/{depth}", method= RequestMethod.GET)
	public String write( @PathVariable("gNo") long gNo, @PathVariable("oNo") long oNo,@PathVariable("depth") int depth, BoardVo vo) {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write/{gNo}", method= RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, @PathVariable("gNo") long gNo, BoardVo vo, Model model) {
		long oNo = 1L;
		int depth = 1;
		vo.setgNo(gNo+1L);
		vo.setoNo(oNo);
		vo.setDepth(depth);
		
		UserVo userVo = userService.getUser(authUser.getNo());
		
		vo.setUserNo(userVo.getNo());
		boardService.write(vo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/write/{gNo}/{oNo}/{depth}", method= RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, @PathVariable("gNo") long gNo, @PathVariable("oNo") long oNo,@PathVariable("depth") int depth, BoardVo vo) {
		vo.setgNo(gNo);
		vo.setoNo(oNo+1L);
		vo.setDepth(depth+1);
		
		UserVo userVo = userService.getUser(authUser.getNo());
		
		vo.setUserNo(userVo.getNo());
		boardService.write(vo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method= RequestMethod.GET)
	public String updateform(@AuthUser UserVo authUser, @PathVariable("no") long no) {
		
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method= RequestMethod.POST)
	public String update( @PathVariable("no") long no,
							@RequestParam(value="title", required=false, defaultValue="") String title, 
							@RequestParam(value="content", required=false, defaultValue="")String content, 
							BoardVo vo, Model model) {
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(content);
		boardService.update(vo);
	
		model.addAttribute("vo", vo);
		return "redirect:/board";
	}
	

	
}
