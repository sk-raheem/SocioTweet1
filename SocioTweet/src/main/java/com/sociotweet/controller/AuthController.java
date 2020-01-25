package com.sociotweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sociotweet.model.Response;
import com.sociotweet.model.User;
import com.sociotweet.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/regs")
	public Response userRegistration(@RequestBody User user) {
		if(user==null || user.getEmailId()==null || user.getFirstName()==null || user.getLastName()==null || user.getPassword()==null || user.getWardNo()<100) {
			Response response=new Response();
			response.setResult("fail");
			return response;
		}
		
		else {
			user.setUserRole(1);
			Response response=authService.serviceRegister(user);
			return response;
		}
		
	}
	@PostMapping("/login")
	public User userLogin(@RequestBody User user) {
		if(user==null || user.getEmailId()==null || user.getPassword()==null) {
			return user;
		}
		else {
			User loginUser=authService.serviceLogin(user);
			return loginUser;
		}
		
	}
	
}
