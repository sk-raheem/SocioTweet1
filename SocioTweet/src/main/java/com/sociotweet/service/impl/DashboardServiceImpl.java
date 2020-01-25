package com.sociotweet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sociotweet.dao.DashboardDao;
import com.sociotweet.dao.impl.DashboardDaoImpl;
import com.sociotweet.model.Issue;
import com.sociotweet.model.Response;
import com.sociotweet.model.User;
import com.sociotweet.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	DashboardDao dashboardDao;
	@Override
	public Response userIssueService(Issue issue) {
		// TODO Auto-generated method stub
		return dashboardDao.userIssue(issue);
	}

	@Override
	public List<Issue> getIssueListService() {
		// TODO Auto-generated method stub
		return dashboardDao.getIssueList();
	}

	@Override
	public Response updateIssueService(Issue issue) {
		// TODO Auto-generated method stub
		 return dashboardDao.updateIssue(issue);
	}

	@Override
	public List<User> getUserListService() {
		// TODO Auto-generated method stub
		return dashboardDao.showUserList();
	}
	@Override
	public List<Issue> getIssueListByLocationService(User user) {
		// TODO Auto-generated method stub
		return dashboardDao.getIssueListByLocation(user);
	}

	@Override
	public List<Issue> getSolvedIssueListService(User user) {
		// TODO Auto-generated method stub
		return dashboardDao.getSolvedIssueList(user);
	}

	@Override
	public Response updateUserInfoService(User user) {
		// TODO Auto-generated method stub
		return dashboardDao.updateUserInfo(user);
	}

	@Override
	public Response addMlaService(User user) {
		// TODO Auto-generated method stub
		return dashboardDao.addMla(user);
	}

	@Override
	public Response deleteUserService(User user) {
		// TODO Auto-generated method stub
		return dashboardDao.deleteUser(user);
	}

}
