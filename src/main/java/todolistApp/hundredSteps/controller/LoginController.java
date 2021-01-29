package todolistApp.hundredSteps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import todolistApp.hundredSteps.service.LoginService;

@Controller
@SessionAttributes("name") //takes from model name and put it in, persist at multiple request
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showWelcomePage(@RequestParam String myName,@RequestParam String password,ModelMap model) {
		boolean isValidUser = service.validateUser(myName, password);
		if(!isValidUser) {
			model.put("error", "Invalid Credential");
			return "login";
		}else {
			model.put("name", myName);
			model.put("password", password);
			return "welcome";
		}
	}
}
