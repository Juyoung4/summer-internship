package ETS.payment;


import ETS.payment.service.PaymentService;
//import ETS.common.service.CommonService;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.common.util.HttpUtil;
import ETS.common.util.Paging;

import java.io.IOException;
import java.sql.SQLException;
//import java.io.IOException;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("payment")
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/useTicketMg", method = {RequestMethod.GET, RequestMethod.POST})
	public String getuseTicketMg(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		List<CamelMap> resultList = new ArrayList<CamelMap>();
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		logger.info("paraMap 테스트 : {}", paramMap);
		logger.info("텍스트 타입 테스트: {}", paramMap.getString("searchType"));
		logger.info("넘버  테스트: {}", paramMap.getString("useTicketSeq"));
		int rowMax = 0;
		int pageCount = 10;
		int rowCount = 10;
		
		String nowPage = paramMap.getString("nowPage");
		
		if (StringUtils.isEmpty(nowPage)) {
			nowPage = "1";
		}

		paramMap.put("nowPage", nowPage);
		//rowMax
		try {
			rowMax = paymentService.getuseTicketListCount(paramMap);
		} catch (Exception e) {
			logger.error("이용권 리스트 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			resultList = paymentService.getuseTicketList(paramMap);
			
		} catch (Exception e) {
			logger.error("게시물 조회 오류 : {}", e);
		}
		logger.info("resultList 테스트 : {}", resultList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("pageInfo", pageMap);
        
		HttpUtil.getParams(paramMap, model);
		
		return "/payment/useTicketMg";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/ajaxEventInsert" }, method = {RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Model ajaxEventInsert(HttpServletRequest request, Model model) {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		boolean sw = false;
		int result = 0;
		
		try {
			result = this.paymentService.insertEvent(paramMap);
			
			sw = (result==1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("sw", sw);
		return model;
	}
	
}
