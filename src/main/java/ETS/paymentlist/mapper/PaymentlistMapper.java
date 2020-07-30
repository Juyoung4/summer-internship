package ETS.paymentlist.mapper;

import java.sql.SQLException;
import java.util.List;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

public interface PaymentlistMapper {
	List<CamelMap> getpaymentList(DataMap paramMap) throws SQLException;
	
	int getpaymentListCount(DataMap paramMap) throws SQLException;
	
	List<CamelMap> getuseTicketList() throws SQLException;
}
