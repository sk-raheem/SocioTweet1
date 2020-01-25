package com.sociotweet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sociotweet.dao.AuthDao;
import com.sociotweet.model.Response;
import com.sociotweet.model.User;
import com.sociotweet.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthDao authDao;
	@Override
	public Response serviceRegister(User user) {
		// TODO Auto-generated method stub
		return authDao.register(user);
	}

	@Override
	public User serviceLogin(User user) {
		// TODO Auto-generated method stub
		return authDao.login(user);
	}

}
