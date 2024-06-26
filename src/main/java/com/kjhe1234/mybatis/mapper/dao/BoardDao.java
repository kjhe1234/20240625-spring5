package com.kjhe1234.mybatis.mapper.dao;

import java.util.ArrayList;

import com.kjhe1234.mybatis.mapper.dto.BoardDto;

public interface BoardDao {
	
	public void writeDao(String bid, String bname, String btitle, String bcfontent);
	
	public ArrayList<BoardDto> boardListDao();
	
}
