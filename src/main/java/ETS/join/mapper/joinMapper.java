package ETS.join.mapper;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

public interface joinMapper {
	
	List<CamelMap> getUserList(DataMap paramMap) throws SQLException;
	
	List<CamelMap> getDUserList(DataMap paramMap) throws SQLException;
	
	int getUserListCount(DataMap paramMap) throws SQLException;
	
	int getDUserListCount(DataMap paramMap) throws SQLException;
	
	int userApprove(DataMap paramMap) throws SQLException;
	
	int userDelay(DataMap paramMap) throws SQLException;
	
	int DuserApprove(DataMap paramMap) throws SQLException;
}
