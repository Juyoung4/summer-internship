package ETS.base;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ETS.common.util.CamelMap;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hadler) throws Exception {
		//logger.debug("preHandle");

		SessionInformation sessionInfomation = sessionRegistry.getSessionInformation(request.getSession().getId());
		
		if(sessionInfomation == null){
			PrintWriter out = null;
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			out = response.getWriter();
			out.write("<script>alert(\"로그인정보가 없습니다.\");location.href=\"/login\"</script>");
			out.flush();
			out.close();
		}else{
			CamelMap sessionMap = (CamelMap) sessionInfomation.getPrincipal();
			if(sessionMap.getString("removeSessionId") != null && StringUtils.isNotEmpty(sessionMap.getString("removeSessionId"))){
				String removeSessionId = sessionMap.getString("removeSessionId");
				sessionRegistry.removeSessionInformation(removeSessionId);
				
				PrintWriter out = null;
				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");

				out = response.getWriter();
				out.write("<script>alert(\"다른기기에서 로그인되었습니다. 로그인페이지로 이동합니다.\");location.href=\"/login\"</script>");
				out.flush();
				out.close();
			}			
		}
				
		return true;
	}
}
