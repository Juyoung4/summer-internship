package ETS.payment.mapper;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

public interface PaymentMapper {
	List<CamelMap> getuseTicketList(DataMap paramMap) throws SQLException;
	
	int getuseTicketListCount(DataMap paramMap) throws SQLException;
	
	int insertEvent(DataMap paramMap) throws SQLException;
}
