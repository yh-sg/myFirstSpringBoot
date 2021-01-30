package todolistApp.hundredSteps.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import todolistApp.hundreadSteps.model.Todo;
import todolistApp.hundredSteps.service.TodoService;

@Controller
//@SessionAttributes("name") //takes from model name and put it in, persist at multiple request
public class TodoController {

	@Autowired
	TodoService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showTodo(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showAddToDo(ModelMap model) {
		//this will map into modelattr in jsp, , Bean->Form
		model.addAttribute("todo",new Todo(0, getLoggedInUserName(model),"Default Desc", new Date(), false));
		return "add-todo";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addToDo(ModelMap model, @Valid Todo todo, BindingResult result) {
		//form packing object(directly map from todo object) -> Command Bean | Implementing Server Side Validation
		//form -> bean
		if(result.hasErrors()) {
			return "add-todo";
		}
		service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.GET)
	public String showUpdateToDo(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodos(id);
		model.put("todo", todo);
		return "add-todo";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		todo.setUser(getLoggedInUserName(model));
		service.updateTodo(todo);
		
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteToDo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
}
