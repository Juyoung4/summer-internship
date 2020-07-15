package ETS.common;

import java.util.ResourceBundle;

import org.springframework.context.support.MessageSourceAccessor;

/************************************************** 
 * @FileName   : Global.java
 * @Description: properties file info read
 * @Author     : joon
 * @Version    : 2016. 01. 14.
 * @Copyright  : ⓒ ADUP. All Right Reserved
 **************************************************/
public class Global {

	public static ResourceBundle resource = ResourceBundle.getBundle("properties/env");
	//PagingTag 등.. Global.resource.getString("CONTEXT_PATH") = request.getContextPath() request로 받기 힘든 경우..

	public static MessageSourceAccessor messageSourceAccessor;

	public String getMessages(String key){
		return messageSourceAccessor.getMessage(key);
	}
	public String getMessages(String key, Object[] obj){
		return messageSourceAccessor.getMessage(key, obj);
	}
	public String getMessages(String key, String...str){
		return messageSourceAccessor.getMessage(key, str);
	}
	public MessageSourceAccessor getMessageSourceAccessor() {
		return messageSourceAccessor;
	}
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		Global.messageSourceAccessor = messageSourceAccessor;
	}
}
