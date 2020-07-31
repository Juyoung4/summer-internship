package ETS.connect.mapper;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

//접속 로그 인터페이스
public interface ConnectMapper {
	
	//접속 로그 리스트
	List<CamelMap> getLogList(DataMap paramMap) throws SQLException;
	
	//접속 로그 리스트 카운트
	int getLogListCount(DataMap paramMap) throws SQLException;	
}