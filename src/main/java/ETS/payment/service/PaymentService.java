package ETS.payment.service;

import ETS.payment.mapper.PaymentMapper;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	@Autowired
	private PaymentMapper paymentMapper;

	public List<CamelMap> getuseTicketList(DataMap paramMap) throws Exception {
		return paymentMapper.getuseTicketList(paramMap);		
	}
	public int getuseTicketListCount(DataMap paramMap) throws Exception {
		return paymentMapper.getuseTicketListCount(paramMap);
	}
	
	public List<CamelMap> geteventList() throws Exception {
		return paymentMapper.geteventList();		
	}
	
	public List<CamelMap> getExchangeList() throws Exception {
		return paymentMapper.getExchangeList();		
	}
	
	public int registerEvent(DataMap paramMap) throws Exception {
		return paymentMapper.registerEvent(paramMap);
	}
	
	public int registeruseTicket(DataMap paramMap) throws Exception {
		return paymentMapper.registeruseTicket(paramMap);
	}
	
	public boolean deleteuseTicket(DataMap paramMap) throws Exception{
		int rst = 0;
		rst = this.paymentMapper.deleteuseTicket(paramMap);
		
		return rst > 0 ? true : false;
	}

	public int releaseEvent(DataMap paramMap) throws Exception {
		return paymentMapper.releaseEvent(paramMap);
	}
}
