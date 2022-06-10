package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public boolean delete(Long gNo, Long oNo, Long userNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("gNo", gNo);
		map.put("oNo", oNo);
		map.put("userNo", userNo);
		return sqlSession.delete("board.delete",map)==1;
	}

	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}

	public BoardVo findByNo(long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

}
