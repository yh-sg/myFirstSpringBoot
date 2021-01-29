package todolistApp.hundredSteps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import todolistApp.hundredSteps.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService service;

	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		model.put("todos", service.retrieveTodos("John"));
		return "list-todos";
	}
	
}
