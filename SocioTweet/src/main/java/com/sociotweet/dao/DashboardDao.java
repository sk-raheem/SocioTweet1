package com.sociotweet.dao;

import java.util.List;

import com.sociotweet.model.Issue;
import com.sociotweet.model.Response;
import com.sociotweet.model.User;

public interface DashboardDao {

	public Response userIssue(Issue issue);
	List<Issue> getIssueList();
	Response updateIssue(Issue issue);
	List<User> showUserList();
	List<Issue> getIssueListByLocation(User user);
	List<Issue> getSolvedIssueList(User user);
	Response updateUserInfo(User user);
	Response addMla(User user);
	Response deleteUser(User user);
}
