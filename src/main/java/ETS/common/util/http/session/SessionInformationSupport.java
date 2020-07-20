package ETS.common.util.http.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 9. 22.
 */
public class SessionInformationSupport {
	private final SessionRegistry sessionRegistry;

	public SessionInformationSupport(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public List<SessionInformation> getSessionInformations() {
		List<SessionInformation> sessionInformations = new ArrayList<SessionInformation>();
		for(Object principal : this.sessionRegistry.getAllPrincipals()) {
			sessionInformations.addAll(this.sessionRegistry.getAllSessions(principal, false));
		}

		List<SessionInformation> informations = new ArrayList<SessionInformation>();
		for(SessionInformation sessionInformation : sessionInformations) {


			Date lastRequest = sessionInformation.getLastRequest();
			String sessionId = sessionInformation.getSessionId();

			String username = null;

			Object principal = sessionInformation.getPrincipal();
			if (principal instanceof User) {
				User user = (User) principal;
				username = user.getUsername();
			}

			SessionInformation information = new UserSessionInformation(username, principal, sessionId, lastRequest);

			informations.add(information);
		}

		return informations;
	}
	public boolean userExists(String username) {
		for(SessionInformation information : getSessionInformations()) {
			String _username = ((UserSessionInformation) information).getUsername();

			if (username.equals(_username)) {
				return true;
			}
		}
		return false;
	}
}
