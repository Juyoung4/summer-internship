package ETS.paymentlist.service;

import ETS.paymentlist.mapper.PaymentlistMapper;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.payment.mapper.PaymentMapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentlistSerivce {
	@Autowired
	private PaymentlistMapper paymentlistMapper;
	
	public List<CamelMap> getpaymentList(DataMap paramMap) throws Exception {
		return paymentlistMapper.getpaymentList(paramMap);		
	}
	public int getpaymentListCount(DataMap paramMap) throws Exception {
		return paymentlistMapper.getpaymentListCount(paramMap);
	}
	
	public List<CamelMap> getuseTicketList() throws Exception {
		return paymentlistMapper.getuseTicketList();		
	}
}
