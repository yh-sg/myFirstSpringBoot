package todolistApp.hundredSteps.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	public boolean validateUser(String userid, String password) {
		return userid.equalsIgnoreCase("John") && password.equalsIgnoreCase("Doe");
	}
}
