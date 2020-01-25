package com.sociotweet.service;

import com.sociotweet.model.Response;
import com.sociotweet.model.User;

public interface AuthService {
	
	public Response serviceRegister(User user);
	public User serviceLogin(User user);
}
