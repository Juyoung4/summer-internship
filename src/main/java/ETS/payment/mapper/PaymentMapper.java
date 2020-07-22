package ETS.payment.mapper;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

public interface PaymentMapper {
	List<CamelMap> getuseTicketList(DataMap paramMap) throws SQLException;
	
	int getuseTicketListCount(DataMap paramMap) throws SQLException;
	
	List<CamelMap> geteventList() throws SQLException;
	
	List<CamelMap> getExchangeList() throws SQLException;
	
	int registerEvent(DataMap paramMap) throws SQLException;
	
	int registeruseTicket(DataMap paramMap) throws SQLException;
	
	int deleteuseTicket(DataMap paramMap) throws SQLException;
}
