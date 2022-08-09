package com.ssc.druedmoviestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssc.druedmoviestore.model.User;
import com.ssc.druedmoviestore.service.UserService;

@RestController
@RequestMapping ("api")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/login")
	public String login(@RequestBody User user) {
		if (userService.verifyCredentials(user)) {
			return "OK";
		}
		return "FAIL";
	}
}
