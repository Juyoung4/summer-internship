package ETS.refferal.service;

import ETS.refferal.mapper.RefferalMapper;
import ETS.board.mapper.BoardMapper;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefferalService {
	//레퍼럴 관리 비즈니스 로직
	@Autowired
	private RefferalMapper refferalMapper;
	
	
	//레퍼럴 리스트
	public List<CamelMap> getManageList(DataMap paramMap) throws Exception {
		return refferalMapper.getManageList(paramMap);	
	}
	
	//레퍼럴 리스트 카운트
	public int getManageListCount(DataMap paramMap) throws Exception {
		return refferalMapper.getManageListCount(paramMap);
	}
	
	//레퍼럴 등록
	public int insertManage(DataMap paramMap) throws Exception {
		return refferalMapper.insertManage(paramMap);
	}
	
	

}
