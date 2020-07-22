package ETS.eventmanage.mapper;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

public interface EventmanageMapper {
	List<CamelMap> geteventList(DataMap paramMap) throws SQLException;
	
	int geteventListCount(DataMap paramMap) throws SQLException;
	
	List<CamelMap> getExchangeList() throws SQLException;
	
	int SaveEvent(DataMap paramMap) throws SQLException;
}
