package com.kjhe1234.mybatis.mapper.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kjhe1234.mybatis.mapper.dao.MemberDao;
import com.kjhe1234.mybatis.mapper.dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired // 의존 자동 주입 DI
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}

	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		MemberDao memberdao = sqlSession.getMapper(MemberDao.class);
		memberdao.joinMemberDao(mid, mpw, mname, memail);
		
		model.addAttribute("mid", mid);
		model.addAttribute("mname", mname);
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, HttpSession session, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		MemberDao memberdao = sqlSession.getMapper(MemberDao.class);
		int loginFlag = memberdao.loginDao(mid, mpw);
		if (loginFlag ==1) {  // 참이면 로그인 성공
			session.setAttribute("sessionid", mid);
			model.addAttribute("loginid", mid);
			return "loginOk";
		} else { // 로그인 실패
			model.addAttribute("loginFail", "아이디 또는 비밀번호가 틀립니다.");
			return "login";
		}
		
	}
	
	@RequestMapping(value = "/search")
	public String search() {
		return "search";
	}
	
	@RequestMapping(value = "/searchOk")
	public String searchOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		
		MemberDao memberdao = sqlSession.getMapper(MemberDao.class);
		MemberDto memberdto = memberdao.searchDao(mid); 
				
		if(memberdto != null) {
			model.addAttribute("memberdto", memberdto);
		} else {
			model.addAttribute("searchFail", "조회하신 아이디는 존재하지 않습니다.");
		}

		return "searchOk";
	}
	
	@RequestMapping(value = "/memberlist")
	public String memberlist(HttpServletRequest request, Model model) {
		
		MemberDao memberdao = sqlSession.getMapper(MemberDao.class);
		ArrayList<MemberDto> memberdtos = memberdao.memberlistDao(); 
		
		int totalmember = memberdao.countDao(); // 총 회원수 반환
		
		model.addAttribute("memberList", memberdtos);
		model.addAttribute("total", totalmember);

		
		return "memberlist";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
