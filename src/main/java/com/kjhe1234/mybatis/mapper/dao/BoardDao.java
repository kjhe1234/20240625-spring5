package com.kjhe1234.mybatis.mapper.dao;

import java.util.ArrayList;

import com.kjhe1234.mybatis.mapper.dto.BoardDto;
import com.kjhe1234.mybatis.mapper.dto.MemberDto;

public interface BoardDao {
	
	public void writeDao(String bid, String bname, String btitle, String bcfontent);  // 글쓰기
	
	public ArrayList<BoardDto> boardListDao(); // 글 목록
	
	public BoardDto content_viewDao(String bnum); // 글 번호로 내용 조회

	public void modifyDao(String btitle, String bcontent,String bnum); // 글 수정
	
}
