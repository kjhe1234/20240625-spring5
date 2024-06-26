package com.kjhe1234.mybatis.mapper.dao;

import java.util.ArrayList;

import com.kjhe1234.mybatis.mapper.dto.MemberDto;

public interface MemberDao {
	
	public void joinMemberDao(String mid, String mpw, String mname, String memail); // 회원 가입 메소드

	public int loginDao(String mid, String mpw); //로그인 체크 메소드
	
	public MemberDto searchDao(String mid); //회원 아이디로 정보 조회 메소드
	
	public ArrayList<MemberDto> memberlistDao(); //회원 리스트 조회 메소드
	
	public int countDao(); // 총 회원수 조회 메소드
	
	public int deleteMemberDao(String mid); // 회원 탈퇴(회원 삭제) 메소드
	
	public int checkIdDao(String mid); // 아이디 존재여부 체크 메소드
	
}
