package ETS.eventmanage;

import ETS.eventmanage.service.EventmanageService;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.common.util.HttpUtil;
import ETS.common.util.Paging;
import ETS.payment.PaymentController;
import ETS.payment.service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("eventmanage")
public class EventmanageController {
	private static final Logger logger = LoggerFactory.getLogger(EventmanageController.class);
	
	@Autowired
	private EventmanageService eventmanageService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/eventMg", method = {RequestMethod.GET, RequestMethod.POST})
	public String getEventList(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		List<CamelMap> resultExchangeList = new ArrayList<CamelMap>();
		List<CamelMap> eventList = new ArrayList<CamelMap>();
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
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
			rowMax = eventmanageService.geteventListCount(paramMap);
		} catch (Exception e) {
			logger.error("이용권 리스트 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			eventList = eventmanageService.geteventList(paramMap);
			resultExchangeList = eventmanageService.getExchangeList();
		} catch (Exception e) {
			logger.error("게시물 조회 오류 : {}", e);
		}
		
		model.addAttribute("eventList", eventList);
		model.addAttribute("resultExchangeList", resultExchangeList);
		model.addAttribute("pageInfo", pageMap);
		logger.info("model 테스트 : {}", model);
		
		HttpUtil.getParams(paramMap, model);
		return "/eventmanage/eventMg";
	}
	
	//이벤트 저장
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/ajaxeventSave" }, method = {RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Model ajaxeventSave(HttpServletRequest request, Model model) {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		boolean sw = false;
		int result = 0;
		logger.info("paramMap 테스트 : {}", paramMap);
		try {
			result = this.eventmanageService.SaveEvent(paramMap);
			sw = (result==1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("sw", sw);
		return model;
	}
	
}