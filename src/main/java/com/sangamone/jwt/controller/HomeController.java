package com.sangamone.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String testing() {
		return "Welcome to Jwt Authenication";
	}

}
