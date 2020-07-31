package ETS.connect;

import ETS.connect.service.ConnectService;
import ETS.common.service.CommonService;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.common.util.HttpUtil;
import ETS.common.util.Paging;

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

//접속 로그 페이지 컨트롤러
@Controller
@RequestMapping("connect")
public class ConnectController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectController.class);
	
	@Autowired
	private ConnectService connectService;
	
	//접속 로그 관리
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/logList", method = {RequestMethod.GET, RequestMethod.POST})
	public String getConnectLogList(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		
		List<CamelMap> resultList = new ArrayList<CamelMap>();
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		String searchText = paramMap.getString("searchText");
		String searchType = paramMap.getString("searchType");
		String startDt = paramMap.getString("startDt");
		String endDt = paramMap.getString("endDt");
		
		
		int rowMax = 0;
		int pageCount = 10;
		int rowCount = 8;
		
		String nowPage = paramMap.getString("nowPage");
		
		if (StringUtils.isEmpty(nowPage)) {
			nowPage = "1";
		}
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("nowPage", nowPage);

		//rowMax
		try {
			rowMax = connectService.getLogListCount(paramMap);
		} catch (Exception e) {
			logger.error("게시물 갯수 조회 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			resultList = connectService.getLogList(paramMap);
			
		} catch (Exception e) {
			logger.error("게시물 조회 오류 : {}", e);
		}
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("pageInfo", pageMap);
		model.addAttribute("searchText", searchText);
		model.addAttribute("searchType", searchType);
		model.addAttribute("startDt", startDt);
		model.addAttribute("endDt", endDt);
		
		logger.info("검색어 테스트 : {}", searchText);
		logger.info("startDt 테스트 : {}", startDt);
		logger.info("endDt 테스트 : {}", endDt);
        
		HttpUtil.getParams(paramMap, model);
		
		return "/connect/logList";
	}
}