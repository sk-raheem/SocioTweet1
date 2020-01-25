
package com.sociotweet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sociotweet.model.Issue;
import com.sociotweet.model.Response;
import com.sociotweet.model.User;
import com.sociotweet.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;
	
	
	@PostMapping("/user")
	public Response raiseIssue(@RequestBody Issue issue) {
		if(issue.getTitle()==null||issue.getDescription()==null||issue.getWard_no()<100) {
			
			Response response=new Response();
			response.setResult("fail");
			return response;
		}
		else {
			issue.setStatus("unsolved");
			Response response=dashboardService.userIssueService(issue);
			return response;
		}
			
	}
	
	
	@GetMapping("/allissue")
	public List<Issue> getIssues(){
		List<Issue> list=dashboardService.getIssueListService();
		return list;
	}
	
	@PutMapping("/mla/update")
	public Response solvedIssue(@RequestBody Issue issue) {
		if(issue.getIssueId()==0) {
			Response response=new Response();
			response.setResult("failed");
			return response;
		}
		else
		{
			Response response=dashboardService.updateIssueService(issue);
			return response;
		}
		
	}
	@GetMapping("/admin")
	public List<User> showUser(){
	List<User> list=dashboardService.getUserListService();
			return list;
	}
	
	@PostMapping("/updateuser")
	public Response updateUser(@RequestBody User user) {
		if(user.getEmailId()==null) {
			Response response =new Response();
			response.setResult("failed");
			return response;
		}
		else {
			Response response=dashboardService.updateUserInfoService(user);
			return response;
		}
	}
	
	@PostMapping("/admin")
	public Response addMla(@RequestBody User user) {
		if(user==null || user.getEmailId()==null || user.getFirstName()==null || user.getLastName()==null || user.getPassword()==null || user.getWardNo()<100) {
			Response response=new Response();
			response.setResult("fail");
			return response;
		}
		
		else {
			user.setUserRole(2);
			Response response=dashboardService.addMlaService(user);
			return response;
	}
	}
	
	
	@PostMapping("/admin/deluser")
	public Response deleteUser(@RequestBody User user) {
		if(user==null || user.getEmailId()==null) {
			Response response=new Response();
			response.setResult("fail");
			return response;
		}
		else {
			Response response=dashboardService.deleteUserService(user);
			return response;
		}
		
	}
	
	
	@PostMapping("/mla/unsolved")
	public List<Issue> getIssueByWardNo(@RequestBody User user){
		if(user==null || user.getWardNo()<100){
			List<Issue>list=new ArrayList<Issue>();
			return list;
		}
		else{
		List<Issue> list=dashboardService.getIssueListByLocationService(user);
		return list;
		}
	}
	
	@PostMapping("/mla/solved")
	public List<Issue> getSolvedIssueList(@RequestBody User user){
		if(user==null || user.getWardNo()<100){
			List<Issue>list=new ArrayList<Issue>();
			return list;
		}
		else{
		List<Issue> list=dashboardService.getSolvedIssueListService(user);
		return list;
		}
	}
}
