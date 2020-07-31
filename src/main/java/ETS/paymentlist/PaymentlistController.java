package ETS.paymentlist;

import ETS.paymentlist.service.PaymentlistSerivce;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.common.util.HttpUtil;
import ETS.common.util.Paging;
import ETS.payment.PaymentController;
import ETS.payment.service.PaymentService;

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
@RequestMapping("paymentlist")
public class PaymentlistController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentlistSerivce paymentlistSerivce;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/payment", method = {RequestMethod.GET, RequestMethod.POST})
	public String getuseTicketMg(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		List<CamelMap> resultUseTicketList = new ArrayList<CamelMap>();
		List<CamelMap> resultList = new ArrayList<CamelMap>();
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		logger.info("paramMap 테스트 : {}", paramMap);
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
			rowMax = paymentlistSerivce.getpaymentListCount(paramMap);
		} catch (Exception e) {
			logger.error("이용권 리스트 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			resultUseTicketList = paymentlistSerivce.getuseTicketList();
			resultList=paymentlistSerivce.getpaymentList(paramMap);
		} catch (Exception e) {
			logger.error("게시물 조회 오류 : {}", e);
		}
		
		model.addAttribute("resultUseTicketList", resultUseTicketList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("pageInfo", pageMap);
		logger.info("model 테스트 : {}", model);
		HttpUtil.getParams(paramMap, model);
		
		return "/paymentlist/payment";
	}
}
