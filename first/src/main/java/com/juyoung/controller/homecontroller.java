package com.juyoung.controller;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.juyoung.dto.MemberVO;
import com.juyoung.service.MemberService;

@Controller
public class homecontroller {
	private static final Logger logger = LoggerFactory.getLogger(homecontroller.class);
	@Autowired
	private MemberService service;
		
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		List<MemberVO> memberList = service.selectMember();
	
		model.addAttribute("memberList", memberList);
	
		return "home";
	}
	
	/*
	 * 회원가입 메소드 구현 후 로그인 페이지(/member/login)로 보내기	
	 */
	@RequestMapping(value = "/users/signup", method = RequestMethod.GET)
	public String memberJoin() {
		return "users/signup";
	}
	
	@RequestMapping(value = "/users/signup", method = RequestMethod.POST)
	public String memberJoin(MemberVO memberVO) {
		int cnt = 0;
		cnt = service.memberjoin(memberVO);
		return cnt==1 ? "users/login" : "users/signup";
	}
	
	
	/*
	 * 로그인
	 */
	@RequestMapping(value = "/users/login", method = RequestMethod.GET)
	public String memberLogin() {
		return "users/login";
	}
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public String memberLogin(@RequestParam Map<String,String> map, HttpSession session) {
		MemberVO memberVO = service.memberlogin(map);
		
		if(memberVO != null) {
			session.setAttribute("loginMember", memberVO);
			return "redirect:/";
		} else {
			return "redirect:/users/login";
		}
	}
		
}
