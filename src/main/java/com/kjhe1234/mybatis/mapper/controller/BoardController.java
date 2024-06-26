package com.kjhe1234.mybatis.mapper.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kjhe1234.mybatis.mapper.dao.BoardDao;
import com.kjhe1234.mybatis.mapper.dao.MemberDao;
import com.kjhe1234.mybatis.mapper.dto.BoardDto;
import com.kjhe1234.mybatis.mapper.dto.MemberDto;

@Controller
public class BoardController {

	@Autowired // 의존 자동 주입 DI
	private SqlSession sqlSession;
	
	@RequestMapping(value ="/write")
	public String write(HttpSession session, Model model) {
		
		// 세션에서 sessionid 값 가져오기(세션 매개변수 선어 필수)
		String sessionid = (String) session.getAttribute("sessionid");
		
		if(sessionid == null) {  // 로그인 안된 상태
			model.addAttribute("loginFail", "글쓰기는 로그인 상태에서만 가능 합니다.");
			return "login";
		} else {  //로그인 된 상태
			
			MemberDao memberdao = sqlSession.getMapper(MemberDao.class);
			MemberDto memberdto =  memberdao.searchDao(sessionid);
			
			model.addAttribute("mid", sessionid);
			model.addAttribute("mname", memberdto.getMname());
			
			
			return "write_form";	
		}
		
		
	}
	
	@RequestMapping(value = "/writeOk")
	public String writeOk(HttpServletRequest request, Model model) {
		
		BoardDao boarddao = sqlSession.getMapper(BoardDao.class);
		boarddao.writeDao(request.getParameter("bid"), request.getParameter("bname"), request.getParameter("btitle"), request.getParameter("bcontent"));
		
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		
		BoardDao boarddao = sqlSession.getMapper(BoardDao.class);
		ArrayList<BoardDto> boarddtos = boarddao.boardListDao();
		
		model.addAttribute("boardList", boarddtos );
		
		return "boardlist";
	}
	
	@RequestMapping(value = "/contentView")
	public String contentView(HttpServletRequest request, Model model,HttpSession session) {
			
		BoardDao boarddao = sqlSession.getMapper(BoardDao.class);
		boarddao.uphitDao(request.getParameter("bnum")); // 조회수 1증가(bhit=bhit+1)
		
		BoardDto boarddto = boarddao.content_viewDao(request.getParameter("bnum"));
		
		String sessionid = (String) session.getAttribute("sessionid"); // 세션 아이디 가져오기

		int idCheck = 0;  //flag 값
		
		if((sessionid != null)  && (boarddto.getBid().equals(sessionid))) {  //현재 로그인한 아이디와 글쓴 아이디가 동일
			idCheck = 1;
		}
		
		model.addAttribute("idCheck", idCheck);
		// 현재 로그인한 아이디와 글쓴이 아이디가 동일하면 idChecㅏ=1이 됨 아니면 0		
		model.addAttribute("boarddto", boarddto);
		
		return "content_view";
	}
	
	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		BoardDao boarddao = sqlSession.getMapper(BoardDao.class);
		BoardDto boarddto = boarddao.content_viewDao(request.getParameter("bnum"));
				
		model.addAttribute("boarddto", boarddto);
		
		return "modify_form";
	}
	
	@RequestMapping(value = "/modifyOk")
	public String modifyOk(HttpServletRequest request, Model model,HttpSession session) {
		
		BoardDao boarddao = sqlSession.getMapper(BoardDao.class);
		boarddao.modifyDao(request.getParameter("btitle"), request.getParameter("bcontent"), request.getParameter("bnum"));
		
		BoardDto boarddto = boarddao.content_viewDao(request.getParameter("bnum"));
		
		String sessionid = (String) session.getAttribute("sessionid"); // 세션 아이디 가져오기

		int idCheck = 0;  //flag 값
		
		if((sessionid != null)  && (boarddto.getBid().equals(sessionid))) {  //현재 로그인한 아이디와 글쓴 아이디가 동일
			idCheck = 1;
		}
		
		
		model.addAttribute("boarddto", boarddto);
		
		//return "redirect:list";
		return "content_view";
	}
	
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		BoardDao boarddao = sqlSession.getMapper(BoardDao.class);
		boarddao.deleteDao(request.getParameter("bnum"));
		
		model.addAttribute("boarddao", boarddao);
		
		return "redirect:list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
