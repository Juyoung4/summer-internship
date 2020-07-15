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
	
	public int insertEvent(DataMap paramMap) throws Exception {
		return paymentMapper.insertEvent(paramMap);
	}
}
