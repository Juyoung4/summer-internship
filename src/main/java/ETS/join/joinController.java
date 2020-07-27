package ETS.join;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.common.util.HttpUtil;
import ETS.common.util.Paging;
import ETS.join.service.*;

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
@RequestMapping("join")
public class joinController {
	private static final Logger logger = LoggerFactory.getLogger(joinController.class);
	
	@Autowired
	private joinService joinService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
	public String getUserList(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		
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
			rowMax = joinService.getUserListCount(paramMap);
		} catch (Exception e) {
			logger.error("조회 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			resultList = joinService.getUserList(paramMap);
			
		} catch (Exception e) {
			logger.error("조회 오류 : {}", e);
		}
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("pageInfo", pageMap);
		
		HttpUtil.getParams(paramMap, model);
		
		return "/join/admin";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delay", method = {RequestMethod.GET, RequestMethod.POST})
	public String getDUserList(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		
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
			rowMax = joinService.getDUserListCount(paramMap);
		} catch (Exception e) {
			logger.error("조회 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, rowCount, Integer.parseInt(nowPage));
		
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			resultList = joinService.getDUserList(paramMap);
			
		} catch (Exception e) {
			logger.error("조회 오류 : {}", e);
		}
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("pageInfo", pageMap);
		
		HttpUtil.getParams(paramMap, model);
		
		return "/join/delay";
	}
	
	@RequestMapping(value = {"/ajaxUserDelay"}, method = RequestMethod.POST)
	@ResponseBody
    public Model ajaxUserDelay(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		boolean sw = false;
		
		try {
			sw = this.joinService.userDelay(paramMap);
		} catch (Exception e) {
			logger.error("사용자 반려 오류 : {}", e);
		}
		
		model.addAttribute("sw", sw);
		
		return model;
	}
	
	@RequestMapping(value = {"/ajaxDUserApprove"}, method = RequestMethod.POST)
	@ResponseBody
    public Model ajaxDUserApprove(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		boolean sw = false;
		
		try {
			sw = this.joinService.DuserApprove(paramMap);
		} catch (Exception e) {
			logger.error("사용자 승인 오류 : {}", e);
		}
		
		model.addAttribute("sw", sw);
		
		return model;
	}
	
	@RequestMapping(value = {"/ajaxUserApprove"}, method = RequestMethod.POST)
	@ResponseBody
    public Model ajaxUserApprove(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		boolean sw = false;
		
		try {
			sw = this.joinService.userApprove(paramMap);
		} catch (Exception e) {
			logger.error("사용자 승인 오류 : {}", e);
		}
		
		model.addAttribute("sw", sw);
		
		return model;
	}
	
}
