package ETS.refferal;

import ETS.refferal.service.RefferalService;
import ETS.refferal.RefferalController;
import ETS.refferal.service.RefferalService;
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

@Controller
@RequestMapping("refferal")
public class RefferalController {

private static final Logger logger = LoggerFactory.getLogger(RefferalController.class);
	
	@Autowired
	private RefferalService refferalService;
	
	@Autowired
	private CommonService commonService;
	
	//refferal 관리
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/manage", method = {RequestMethod.GET, RequestMethod.POST})
	public String getRefferalManage(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		
		List<CamelMap> resultList = new ArrayList<CamelMap>();
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
			rowMax = refferalService.getManageListCount(paramMap);
		} catch (Exception e) {
			logger.error("게시물 갯수 조회 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			resultList = refferalService.getManageList(paramMap);
			
		} catch (Exception e) {
			logger.error("게시물 조회 오류 : {}", e);
		}
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("pageInfo", pageMap);
		
		HttpUtil.getParams(paramMap, model);
		
		return "/refferal/manage";
	}
	
	
	//refferal 등록
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/ajaxRefferalSubmit" }, method = {RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Model ajaxmanageSubmit(HttpServletRequest request, Model model) {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		logger.info("paramMap : {}", paramMap);
		boolean sw = false;
		int result = 0;
		
		try {
			result = this.refferalService.insertManage(paramMap);
			sw = (result==1);
		} catch (Exception e1) {
			logger.debug("레퍼럴 저장 오류", e1);
		}
		model.addAttribute("sw", sw);
		return model;
	}
}
