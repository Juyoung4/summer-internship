/**
 * 
 */
package ETS.common.util.http.session;

import java.util.Date;

import org.springframework.security.core.session.SessionInformation;

/**
 * @author ADUP
 *
 */
public class UserSessionInformation extends SessionInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8828680880781725426L;

	private String username;
	/**
	 * @param principal
	 * @param sessionId
	 * @param lastRequest
	 */
	public UserSessionInformation(String username, Object principal, String sessionId, Date lastRequest) {
		super(principal, sessionId, lastRequest);
		this.username = username;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
