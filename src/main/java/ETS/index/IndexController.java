package ETS.index;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ETS.common.service.CommonService;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.common.Global;
import ETS.common.spring.http.StatusCode;
import ETS.common.spring.http.SuccessBody;
import ETS.common.spring.security.session.SessionInformationSupport;
import ETS.common.util.HttpUtil;
import ETS.index.service.IndexService;

@Controller
public class IndexController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private IndexService indexService;
	
	@Value("#{config.usernameParameter}")
	private String usernameParameter;
	@Value("#{config.passwordParameter}")
	private String passwordParameter;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String dispMemberLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		model.addAttribute("usernameParameter", usernameParameter);
		model.addAttribute("passwordParameter", passwordParameter);
		
		logger.debug("usernameParameter : {}", usernameParameter);
		logger.debug("passwordParameter : {}", passwordParameter);
		return "login";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login/duplication", method = RequestMethod.POST)
	public @ResponseBody SuccessBody procMemberLoginDuplication(@RequestBody MultiValueMap<String, String> params) {
		String username = params.getFirst(usernameParameter);
		String password = params.getFirst(passwordParameter);
		
		logger.debug("username : {}", username);
		logger.debug("password : {}", password);

		SuccessBody body = new SuccessBody();
		body.setStatusCode(StatusCode.DuplicationLogin);
		
		body.setStatusCode(StatusCode.Unauthorized);

		try {						
			UserDetails details = userDetailsService.loadUserByUsername(username);
			
			if (details != null) {
				
				String comparePassword = "";
				if(StringUtils.isNotEmpty(password)){
					try {
//						comparePassword = SHA256.encrypt(password);
						comparePassword = password;
					} catch (Exception e) {
						logger.info("비밀번호 암호화 오류 : {}", e.getMessage());
					}
				}
				
				// 사용자 정보 일치여부
				if (details.getPassword().equals(comparePassword)) {
					try {
						DataMap paramMap = new DataMap();
						paramMap.put("user_id", username);
						paramMap.put("sessionid", this.usernameParameter);
						CamelMap userInfo = this.commonService.selectUserLoginInfo(paramMap);
						if(userInfo != null){
							body.setStatusCode(StatusCode.OK);
						}else{
							body.setStatusCode(StatusCode.MemberStatus);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// 이미 로그인 사용자 여부
					SessionInformationSupport sessionInformationSupport = new SessionInformationSupport(this.sessionRegistry);
					
					if(sessionInformationSupport != null){
						for(int i=0;i<sessionInformationSupport.getSessionInformations().size();i++){			
							CamelMap sesMap = (CamelMap) sessionInformationSupport.getSessionInformations().get(i).getPrincipal();
							if(username.equals(sesMap.getString(Global.resource.getString("DUP_ID")))){								
								body.setData(sesMap);
								body.setError(true);
								break;
							}
						}
					}
				}
			}
		} catch (UsernameNotFoundException e) {
			// 없는 경우 로그인 처리.
			body.setStatusCode(StatusCode.Unauthorized);
		}
		
		return body;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public void signin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataMap paramMap = HttpUtil.getRequestDataMap(request);
		
		String removeSessionId = paramMap.getString("removeSessionId");		
		if(StringUtils.isNotEmpty(removeSessionId)){
			sessionRegistry.removeSessionInformation(removeSessionId);
			
			CamelMap removeMap = new CamelMap();
			removeMap.put("removeSessionId", removeSessionId);
			removeMap.put(Global.resource.getString("DUP_ID"), "");
			removeMap.put("sessionId", removeSessionId);
			removeMap.put("lastRequest", "");
			
			sessionRegistry.registerNewSession(removeSessionId, removeMap);
		}
		
		String sessionId = request.getSession().getId();
		paramMap.put("sessionId", sessionId);
		CamelMap userInfo = commonService.selectUserLoginInfo(paramMap);
		
		sessionRegistry.registerNewSession(sessionId, userInfo);
		request.getSession().setAttribute("userInfo", userInfo);
		
		//20190822
		String referer = request.getHeader("referer");
		String refererRp;
		refererRp = referer.replace("login?returnUrl=", "").replace("&error=logout", "");
		
//		if(refererRp.matches(".*login*.")){
			response.sendRedirect("/board/notice");
//		}else{
//			response.sendRedirect(refererRp);
//		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sessionId = request.getSession().getId();
		
		sessionRegistry.removeSessionInformation(sessionId);
		request.getSession().invalidate();
		
		response.sendRedirect("/login");
	}
	
	@RequestMapping(value = "/error/{id}", method = RequestMethod.GET)
	public String dispMemberMypage(@PathVariable("id") String error) {
		logger.info("error : {}", error);
		return "error/" + error;
	}	
}