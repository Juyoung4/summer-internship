package ETS.textNotice;

import ETS.textNotice.service.textNoticeService;
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
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**************************************************
* @FileName   : textNoticeController.java
* @Description: 게시판 관리 페이지 컨트롤러
* @Author     : Seung-Jun. Kim
* @Version    : 2020. 1. 29.
* @Copyright  : ⓒADUP. All Right Reserved
**************************************************/
@Controller
@RequestMapping("textNotice")
public class textNoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(textNoticeController.class);
	
	@Autowired
	private textNoticeService textNoticeService;
	
	@Autowired
	private CommonService commonService;
	
	/**************************************************
	* @MethodName : getBoardNotice
	* @Description: 공지사항 관리
	* @param request
	* @param model
	* @param response
	* @return String
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 29.
	**************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/textNotice", method = {RequestMethod.GET, RequestMethod.POST})
	public String gettextNotice(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {

		List<CamelMap> resultList = new ArrayList<CamelMap>();
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		//search
		String searchText = paramMap.getString("searchText");
		String startDt = paramMap.getString("startDt");
		String endDt = paramMap.getString("endDt");
		
		//page
		int rowMax = 0;
		int pageCount = 10;
		String rowCount = paramMap.getString("rowCount");
		String nowPage = paramMap.getString("nowPage");
		
		if (StringUtils.isEmpty(rowCount)) {
			rowCount = "5";
		}
		
		if (StringUtils.isEmpty(nowPage)) {
			nowPage = "1";
		}

		paramMap.put("nowPage", nowPage);
		
		//rowMax
		try {
			rowMax = textNoticeService.getNoticeListCount(paramMap);
		} catch (Exception e) {
			logger.error("게시물 갯수 조회 오류 : {}", e);
		}
		
		//페이지 정보
		DataMap pageMap = Paging.initDataMapPage(rowMax, pageCount, Integer.parseInt(rowCount), Integer.parseInt(nowPage));
		paramMap.put("rowCount", rowCount);
		paramMap.put("scopeRow", pageMap.getInt("scopeRow"));
		
		try {
			resultList = textNoticeService.getNoticeList(paramMap);
			
		} catch (Exception e) {
			logger.error("게시물 조회 오류 : {}", e);
		}

		model.addAttribute("resultList", resultList);
		model.addAttribute("pageInfo", pageMap);
		model.addAttribute("searchText", searchText);
		model.addAttribute("startDt", startDt);
		model.addAttribute("endDt", endDt);
		
		logger.info("검색어 테스트 : {}", searchText);
		logger.info("시작일 테스트 : {}", startDt);
		logger.info("종료일 테스트 : {}", endDt);
		
		HttpUtil.getParams(paramMap, model);
		return "/textNotice/textNotice";
	}
	
	/**************************************************
	* @MethodName : getBoardNoticeView
	* @Description: 공지사항 상세보기
	* @param request
	* @param model
	* @param response
	* @return String
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	@RequestMapping(value = "/textNoticeView", method = {RequestMethod.GET, RequestMethod.POST})
	public String getNoticeView(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		CamelMap noticeInfo;
		
		textNoticeService.updateViewCount(paramMap);
		try {
			noticeInfo = this.textNoticeService.getNoticeView(paramMap);
			
		} catch (SQLException e) {
			noticeInfo = new CamelMap();
			logger.debug("notice read", e);
		}
		model.addAttribute("noticeInfo", noticeInfo);
		
		return "/textNotice/textNoticeView";
	}
	
	@RequestMapping(value = "/textNoticePreview", method = {RequestMethod.GET, RequestMethod.POST})
	public String getNoticePreview(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		CamelMap noticeInfo;

		try {
			noticeInfo = this.textNoticeService.getNoticePreview(paramMap);
			
		} catch (SQLException e) {
			noticeInfo = new CamelMap();
			logger.debug("notice read", e);
		}
		model.addAttribute("noticeInfo", noticeInfo);
		
		return "/textNotice/textNoticePreview";
	}
	
	/**************************************************
	* @MethodName : getBoardNoticeWrite
	* @Description: 공지사항 등록화면
	* @param request
	* @param model
	* @param response
	* @return String
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	@RequestMapping(value = "/textNoticeWrite", method = {RequestMethod.GET, RequestMethod.POST})
	public String getNoticeWrite(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		
		CamelMap noticeInfo = new CamelMap();
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		String textnoticeSeq = paramMap.getString("textnoticeSeq");
		String crud = "INS";
		
		if (StringUtils.isNotEmpty(textnoticeSeq)) crud = "MOD";
		
		try {
			if ("MOD".equals(crud)) {
				noticeInfo = textNoticeService.getNoticeView(paramMap);
			}
		} catch (Exception e) {
			logger.error("게시물 조회 오류 : {}", e);
		}
		
		model.addAttribute("noticeInfo", noticeInfo);
		model.addAttribute("crud", crud);
		
		HttpUtil.getParams(paramMap, model);
		
		return "/textNotice/textNoticeWrite";
	}

	/*
	 * 
	 * 
	 * 
	 *  등록 , 수정, 삭제
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**************************************************
	* @MethodName : ajaxNoticeDelete
	* @Description: 공지사항 삭제
	* @param request
	* @param response
	* @param model
	* @return Model
	* @throws IOException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	@RequestMapping(value = {"/ajaxNoticeDelete"}, method = RequestMethod.POST)
	@ResponseBody
    public Model ajaxNoticeDelete(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		boolean sw = false;
		
		try {
			sw = this.textNoticeService.deleteNotice(paramMap);
		} catch (Exception e) {
			logger.error("공지사항 삭제 오류 : {}", e);
		}
		
		model.addAttribute("sw", sw);
		
		return model;
	}
	
	/**************************************************
	* @MethodName : ajaxNoticeSubmit
	* @Description: 공지사항 등록
	* @param request
	* @param model
	* @return Model
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/ajaxNoticeSubmit" }, method = {RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Model ajaxNoticeSubmit(HttpServletRequest request, Model model) {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);

		boolean sw = false;
		int result = 0;

		paramMap.put("filePath", "textNotice");
		paramMap.put("imgNm", "");
		
		List<DataMap> rstFileList = null;
		
		try {
			rstFileList = commonService.saveFile(request, paramMap);
			for (DataMap fileMap : rstFileList) {
				
				logger.info("fileMap : {}", fileMap);
				paramMap.put("imgNm", fileMap.get("saveFilePath") + "/" + fileMap.get("saveFileName"));
				paramMap.put("orgImgNm", fileMap.get("realFileName"));
			}
			
			if (!"MOD".equals(paramMap.getString("crud"))){
				result = this.textNoticeService.insertNotice(paramMap);
			} else {
				result = this.textNoticeService.updateNotice(paramMap);
			}
			
			sw = (result==1);
		} catch (Exception e1) {
			logger.debug("공지사항 저장 오류", e1);
		}
		model.addAttribute("sw", sw);
		return model;
	}
	
}
