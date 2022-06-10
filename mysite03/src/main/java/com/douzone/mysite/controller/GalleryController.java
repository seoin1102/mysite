package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImages();
		model.addAttribute("list",list);
		return "gallery/index";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
		@RequestParam(value="comments", required=true, defaultValue="") String comments,
		@RequestParam("file") MultipartFile multipartFile,
		Model model) {
		System.out.println("comments:" + comments);

		String url = fileUploadService.restore(multipartFile);
		
		galleryService.insert(url, comments);

		model.addAttribute("url", url);
		return "gallery/index";
	}
}
