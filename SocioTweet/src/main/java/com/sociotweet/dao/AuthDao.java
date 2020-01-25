package com.sociotweet.dao;

import com.sociotweet.model.Response;
import com.sociotweet.model.User;

public interface AuthDao {

	public Response register(User user);
	
	public User login(User user);
	
}
