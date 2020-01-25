package com.sociotweet.service;

import java.util.List;

import com.sociotweet.model.Issue;
import com.sociotweet.model.Response;
import com.sociotweet.model.User;

public interface DashboardService {

	public Response userIssueService(Issue issue);
	List<Issue> getIssueListService();
	Response updateIssueService(Issue issue);
	List<User> getUserListService();
	List<Issue> getIssueListByLocationService(User user);
	List<Issue> getSolvedIssueListService(User user);
	Response updateUserInfoService(User user);
	Response addMlaService(User user);
	Response deleteUserService(User user);
}
