package ETS.eventmanage.service;

import ETS.eventmanage.mapper.EventmanageMapper;
import ETS.payment.mapper.PaymentMapper;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventmanageService {
	@Autowired
	private EventmanageMapper eventmanageMapper;

	public List<CamelMap> geteventList(DataMap paramMap) throws Exception {
		return eventmanageMapper.geteventList(paramMap);		
	}
	
	public int geteventListCount(DataMap paramMap) throws Exception {
		return eventmanageMapper.geteventListCount(paramMap);
	}
	
	public List<CamelMap> getExchangeList() throws Exception {
		return eventmanageMapper.getExchangeList();		
	}
	
	public int SaveEvent(DataMap paramMap) throws Exception {
		return eventmanageMapper.SaveEvent(paramMap);
	}
	
}
