package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.User;
import com.data.UserRepository;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

// TOOD: Add a repository + contents for storing. 

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;
	
	public MainController(UserRepository userRepository) {
		this.userRepository = userRepository;
		System.out.println("Main controller instantiated.");
	}
	
	@RequestMapping(value="/", method=GET)
	public ModelAndView main() {
		//System.out.println("main requested");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/subpage", method=GET)
	public String hello() {
		//System.out.println("subpage");
		return "subpage";
	}
	
	@RequestMapping(value="/argsdemo1", method=GET)
	public ModelAndView argsDemo1(
			@RequestParam(name="first_int", required=false) Integer firstInt, 
			@RequestParam(name="some_string", required=false) String someString) {
		
		System.out.println("first_int: " + firstInt);
		System.out.println("some_string: " + someString);
		
		ModelAndView modelAndView = new ModelAndView("argsdemo_one", "first_int", firstInt);
		modelAndView.addObject("first_int", firstInt);
		modelAndView.addObject("someString", someString);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/aspath/{command}/{number}/{number2}", method=GET)
	public String asPath(@PathVariable("command") String command,
			@PathVariable("number") int number,
			@PathVariable("number2") int number2,
			Model model) {
		//model.
		
		model.addAttribute("command", command);
		model.addAttribute("number", number);
		model.addAttribute("number2", number2);
		return "aspath";
	}
	
	@RequestMapping(value="/forms", method=GET)
	public String showForms() {
		return "forms";
	}
	
	@RequestMapping(value="/forms", method=POST)
	public String processForm(User user) {
		System.out.println("Registered: " + user.getFirstName() + user.getUserName() + user.getLastName());
		
		userRepository.saveUser(user);
		
		return "redirect:/user/" + user.getUserName();
	}
	
	@RequestMapping(value="/user/{username}", method=GET)
	public String showUser(@PathVariable("username") String userName, Model model) {
		User user = userRepository.findByUserName(userName); // FIXME: userName may not exist; this will require external exception handling.
		
		model.addAttribute("user", user);
		return "user"; // user view
	}
	
	@RequestMapping(value="/registered", method=GET)
	public String registered() {
		return "registered";
	}
	
}
