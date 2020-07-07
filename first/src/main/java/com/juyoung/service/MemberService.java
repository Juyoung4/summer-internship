package com.juyoung.service;

import java.util.List;
import com.juyoung.dto.MemberVO;

public interface MemberService {
	public List<MemberVO> selectMember() throws Exception;
	//회원 가입을 위한 service
	public int memberjoin(MemberVO memberVO);
}
