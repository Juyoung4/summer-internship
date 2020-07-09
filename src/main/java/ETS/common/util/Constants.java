package ETS.common.util;

import javax.servlet.http.HttpServletRequest;

import ETS.common.util.HttpUtil;

import org.apache.commons.lang.StringUtils;

/**************************************************
 * @FileName   : Constants.java
 * @Description: Common Constants Setting
 * @Author     : joon
 * @Version    : 2016. 01. 14.
 * @Copyright  : ⓒ ADUP. All Right Reserved
 **************************************************/
public class Constants {
    
	public static String langCheck(HttpServletRequest request){
		String LANG = (String)request.getParameter("LANG");
		if(StringUtils.isEmpty(LANG)) LANG = HttpUtil.getValueFromCookie("selectLocale", request);
		if(StringUtils.isEmpty(LANG)) LANG = "ko";

		return LANG;
	}	
}
