package com.juyoung.service;

import java.util.List;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.juyoung.dto.MemberVO;
import com.juyoung.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
    private MemberDAO dao;
    
    @Override
    public List<MemberVO> selectMember() throws Exception {
        return dao.selectMember();
    }
    
    //회원가입 부분 dao의 join함수의 실행값을 반환시켜줌
    public int memberjoin(MemberVO memberVO) {
    	return dao.join(memberVO);
    }
}
