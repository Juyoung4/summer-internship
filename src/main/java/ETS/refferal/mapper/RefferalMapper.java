package ETS.refferal.mapper;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

public interface RefferalMapper {
	//getManageList, 레퍼럴 리스트
	List<CamelMap> getManageList(DataMap paramMap) throws SQLException;
	
	//getManageListCount, 레퍼럴 리스트 카운드
	int getManageListCount(DataMap paramMap) throws SQLException;
	
	
	//insertManage, 레퍼럴 정보 등록
	int insertManage(DataMap paramMap) throws SQLException;
}
