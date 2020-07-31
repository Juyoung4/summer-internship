package ETS.connect.service;

import ETS.connect.mapper.ConnectMapper;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//접속 로그 비즈니스 로직
@Service
public class ConnectService {

	@Autowired
	private ConnectMapper connectMapper;
	
	//접속 로그 리스트
	public List<CamelMap> getLogList(DataMap paramMap) throws Exception {
		return connectMapper.getLogList(paramMap);		
	}
	
	//접속 로그 리스트 카운트
	public int getLogListCount(DataMap paramMap) throws Exception {
		return connectMapper.getLogListCount(paramMap);
	}
}