package ETS.common.util.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ETS.common.util.DataMap;
import ETS.common.util.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

/**************************************************
 * @FileName : HttpUtil.java
 * @Description: Http Common cookie or map setting
 * @Author : joon
 * @Version : 2016. 01. 14.
 * @Copyright : ⓒ ADUP. All Right Reserved
 **************************************************/
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);;

	public static HttpServletRequest getHttpServletRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) throw new RuntimeException("Expecting an HTTP request");
		return (HttpServletRequest) request;
	}

	public static HttpServletResponse getHttpServletResponse(ServletResponse response) {
		if (!(response instanceof HttpServletResponse)) throw new RuntimeException("Expecting an HTTP response");
		return (HttpServletResponse) response;
	}
	public static String getPathQueryString(HttpServletRequest request) {
		String servlet_path = request.getServletPath();
		String query_string = request.getQueryString();
		query_string = ( StringUtils.isEmpty(query_string) ) ? "" : "?" + query_string;
		return servlet_path + query_string;
	}

	public static boolean isAjax(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		String ajax = request.getHeader("X-Requested-With");

		return ( StringUtils.indexOf(accept, "json") > -1 && StringUtils.isNotEmpty(ajax));
	}
	/**************************************************
	 * @MethodName : createCookie
	 * @Description: Cookies created
	 * @param setName
	 * @param setValue
	 * @param maxage
	 * @param path
	 * @param response
	 *            void
	 * @Author : joon
	 * @Version : 2016. 01. 14.
	 **************************************************/
	public static void createCookie(String setName, String setValue, int maxage, String path, HttpServletResponse response) {
		Cookie cookie = new Cookie(setName, setValue);
		cookie.setMaxAge(maxage);
		cookie.setPath(path);

		response.addCookie(cookie);
	}

	/**************************************************
	 * @MethodName : deleteCookie
	 * @Description: cookie delete
	 * @param getName
	 * @param cookies
	 * @param path
	 * @param response
	 *            void
	 * @Author : joon
	 * @Version : 2016. 01. 14.
	 **************************************************/
	public static void deleteCookie(String getName, Cookie[] cookies, String path, HttpServletResponse response) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(getName)) {
					cookies[i].setMaxAge(0);
					cookies[i].setPath(path);
					response.addCookie(cookies[i]);
				}
			}
		}
	}

	/**************************************************
	 * @MethodName : getValueFromCookie
	 * @Description: Load cookies settings
	 * @param getName
	 * @param request
	 * @return String
	 * @Author : joon
	 * @Version : 2016. 01. 14.
	 **************************************************/
	public static String getValueFromCookie(String getName, HttpServletRequest request) {

		String getValue = "";
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(getName)) {
					getValue = cookies[i].getValue();
					break;
				}
			}
		}
		return getValue;
	}

	/**************************************************
	 * @MethodName : getRequestMap
	 * @Description: Setting the parameter value map
	 * @param request
	 * @return HashMap<String,String>
	 * @Author : joon
	 * @Version : 2016. 01. 14.
	 **************************************************/
	public static HashMap<String, String> getRequestMap(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();

		for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
			String reqName = e.nextElement();
			map.put(reqName, StringUtil.nvl(request.getParameter(reqName)));
		}
		map.put("lang", langCheck(request));
		return map;
	}

	/**************************************************
	 * @MethodName : jsonObjToMap
	 * @Description: Setting the parameter json value map
	 * @param obj
	 * @return HashMap<String,String>
	 * @Author : joon
	 * @Version : 2016. 01. 14.
	 **************************************************/
	public static HashMap<String, String> jsonObjToMap(JSONObject obj) {
		HashMap<String, String> map = new HashMap<String, String>();
		Iterator<?> keys = obj.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			map.put(key, (String) obj.get(key));
		}
		return map;
	}

	/**************************************************
	 * @MethodName : getRequestMap
	 * @Description: Setting the parameter value DataMap
	 * @param request
	 * @return HashMap<String,String>
	 * @Author : joon
	 * @Version : 2016. 01. 14.
	 **************************************************/
	@SuppressWarnings("unchecked")
	public static DataMap getRequestDataMap(HttpServletRequest request) {
		DataMap map = new DataMap();
		String reqName = "";

		for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
			reqName = e.nextElement();
			String val = StringUtil.evl(request.getParameter(reqName), "");
			map.put(reqName, val);
			logger.info("reqName : {}, value : {}", reqName, val);
		}
		if (StringUtils.isEmpty(map.getString("LANG"))){
			map.put("LANG", langCheck(request));
		}

		return map;
	}
	public static String langCheck(HttpServletRequest request){
		String LANG = request.getParameter("LANG");
		if(StringUtils.isEmpty(LANG)) LANG = HttpUtil.getValueFromCookie("selectLocale", request);
		if(StringUtils.isEmpty(LANG)) LANG = "ko";
		return LANG;
	}

	/**************************************************
	 * @MethodName : jsonObjToDataMap
	 * @Description: Setting the parameter json value DataMap
	 * @param obj
	 * @return DataMap
	 * @Author : joon
	 * @Version : 2016. 01. 14.
	 **************************************************/
	@SuppressWarnings("unchecked")
	public static DataMap jsonToDataMap(JSONObject obj) {
		DataMap map = new DataMap();
		Iterator<?> keys = obj.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			map.put(key, obj.get(key));
		}
		return map;
	}

	public static boolean testInet(String url, int port) {
		Socket sock = new Socket();
		InetSocketAddress addr = new InetSocketAddress(url, port);
		try {
			sock.connect(addr, 3000);
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				sock.close();
			} catch (IOException e) {
			}
		}
	}

	public static String getMacAddress() throws UnknownHostException,
			SocketException {
		InetAddress ipAddress = InetAddress.getLocalHost();
		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddress);
		byte[] macAddressBytes = networkInterface.getHardwareAddress();
		StringBuilder macAddressBuilder = new StringBuilder();

		for (int macAddressByteIndex = 0; macAddressByteIndex < macAddressBytes.length; macAddressByteIndex++) {
			String macAddressHexByte = String.format("%02X", macAddressBytes[macAddressByteIndex]);
			macAddressBuilder.append(macAddressHexByte);

			if (macAddressByteIndex != macAddressBytes.length - 1) {
				macAddressBuilder.append(":");
			}
		}

		logger.info("::::::: " + macAddressBuilder.toString());
		return macAddressBuilder.toString();
	}

	/**************************************************
	 * @MethodName : getParams
	 * @Description: 파라미터 정보 페이지 전달
	 * @param paramMap
	 * @param model
	 *            void
	 * @Author : Jong-Hoon. Jung
	 * @Version : 2018. 12. 3.
	 **************************************************/
	@SuppressWarnings("unchecked")
	public static void getParams(DataMap paramMap, Model model){
		
		for(Iterator<String> it = paramMap.keySet().iterator();it.hasNext();){
			String key = it.next();
			String value = paramMap.getString(key);
//			logger.info("model param set ::: key : {}, value : {}", key, value);
			if(StringUtils.isNotEmpty(value)) model.addAttribute(key, value);
		}
	}
}