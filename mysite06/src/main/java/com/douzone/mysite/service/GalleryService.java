package com.douzone.mysite.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryRepository galleryRepository;
	public List<GalleryVo> getImages(){
		return galleryRepository.findAll();
	}
	
	public Boolean removeImages(Long no) {
		return galleryRepository.delete(no);
	}
	
	public Boolean insert(String url, String comments) {
		return galleryRepository.insert(url,comments);
	}


}
