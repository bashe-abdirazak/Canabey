package com.khalid.recipeblog.controllers;

import com.khalid.recipeblog.entities.UserRegistration;
import com.khalid.recipeblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserRegistration userRegistration() {
		return new UserRegistration();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user")  UserRegistration userRegistration ) {
		userService.save(userRegistration);
		return "redirect:/registration?success";
	}

}
