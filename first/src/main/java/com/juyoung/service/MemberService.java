package com.juyoung.service;

import java.util.List;
import java.util.Map;

import com.juyoung.dto.MemberVO;

public interface MemberService {
	public List<MemberVO> selectMember() throws Exception;
	//회원 가입을 위한 service
	public int memberjoin(MemberVO memberVO);
	//로그인을 위한 service
	public MemberVO memberlogin(Map<String, String>map);
	
}
