package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public List<GalleryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
	}

	public boolean delete(Long no) {
		return sqlSession.delete("gallery.delete",no)==1;
	}
	
	public boolean insert(String url, String comments) {
		Map<String, Object> map = new HashMap<>();
		map.put("url", url);
		map.put("comments", comments);
		//System.out.println("갤러리 인서트"+vo);
		boolean result = sqlSession.insert("gallery.insert",map)==1;
		//System.out.println("갤러리 인서트"+vo);
		return result;
	}


	



}