
package com.sociotweet.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.sociotweet.dao.DashboardDao;
import com.sociotweet.model.Issue;
import com.sociotweet.model.Response;
import com.sociotweet.model.User;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Response userIssue(Issue issue) {
		jdbcTemplate.update("insert into raised_issue (title,description,ward_no,status,issue_date) values(?,?,?,?,curdate())",issue.getTitle(),issue.getDescription(),issue.getWard_no(),issue.getStatus());
		Response response=new Response();
		response.setResult("success");
		return response;
	}

	@Override
	public List<Issue> getIssueList() {
		
		//String s="unsolved";
		String sql = "SELECT * FROM raised_issue ";

        List<Issue> list = jdbcTemplate.query(sql,new Object[] {},new RowMapper<Issue>() {

			@Override
			public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
				Issue issueTemp=new Issue();
				issueTemp.setTitle(rs.getString("title"));
				issueTemp.setDescription(rs.getString("description"));
				issueTemp.setWard_no(rs.getInt("ward_no"));
				issueTemp.setTitle(rs.getString("title"));
				issueTemp.setIssueDate(rs.getDate("issue_date"));
				issueTemp.setStatus(rs.getString("status"));
				issueTemp.setIssueId(rs.getInt("issue_no"));
				return issueTemp;
			}
        	
		});

		return list;
	}

	@Override
	public Response updateIssue(@RequestBody Issue issue) {
		jdbcTemplate.update("update raised_issue set status='solved' where issue_no=?",issue.getIssueId());
		Response response=new Response();
		response.setResult("success");
		return response;
	}

	@Override
	public List<User> showUserList() {
		// TODO Auto-generated method stub
		String sql="select * from user";
		List<User> list=jdbcTemplate.query(sql, new Object[] {},new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				User user=new User();
				user.setEmailId(rs.getString("email_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setWardNo(rs.getInt("ward_no"));
				user.setUserRole(rs.getInt("role_id"));
				return user;
			}
		});
		
		return list;
	}

	@Override
	public List<Issue> getIssueListByLocation(User user) {
		
		String s="unsolved";
		String sql = "SELECT * FROM raised_issue where status=? and ward_no=?";

        List<Issue> list = jdbcTemplate.query(sql,new Object[] {s,user.getWardNo()},new RowMapper<Issue>() {

			@Override
			public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
				Issue issueTemp=new Issue();
				issueTemp.setTitle(rs.getString("title"));
				issueTemp.setDescription(rs.getString("description"));
				issueTemp.setWard_no(rs.getInt("ward_no"));
				issueTemp.setTitle(rs.getString("title"));
				issueTemp.setIssueDate(rs.getDate("issue_date"));
				issueTemp.setStatus(rs.getString("status"));
				issueTemp.setIssueId(rs.getInt("issue_no"));
				
				return issueTemp;
			}
        	
		});

		return list;
	}

	@Override
	public List<Issue> getSolvedIssueList(User user) {
		String s="solved";
		String sql = "SELECT * FROM raised_issue where status=? and ward_no=?";

        List<Issue> list = jdbcTemplate.query(sql,new Object[] {s,user.getWardNo()},new RowMapper<Issue>() {

			@Override
			public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {
				Issue issueTemp=new Issue();
				issueTemp.setTitle(rs.getString("title"));
				issueTemp.setDescription(rs.getString("description"));
				issueTemp.setWard_no(rs.getInt("ward_no"));
				issueTemp.setTitle(rs.getString("title"));
				issueTemp.setIssueDate(rs.getDate("issue_date"));
				issueTemp.setStatus(rs.getString("status"));
				issueTemp.setIssueId(rs.getInt("issue_no"));
				
				return issueTemp;
			}
        	
		});

		return list;
	}

	@Override
	public Response updateUserInfo(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update user set password=?,first_name=?,last_name=?,ward_no=? where email_id=?",user.getPassword(),user.getFirstName(),user.getLastName(),user.getWardNo(),user.getEmailId());
		Response response=new Response();
		response.setResult("success");
		return response;
	}

	@Override
	public Response addMla(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into user values(?,?,?,?,?,?)",user.getEmailId(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getWardNo(),user.getUserRole());
		Response response=new Response();
		response.setResult("success");
		return response;
	}

	@Override
	public Response deleteUser(User user) {
		Response response=new Response();
		
		try {
			String deleteQuery = "delete from user where email_id = ?";
			int i=jdbcTemplate.update(deleteQuery, user.getEmailId());
			System.out.println(i);
			response.setResult("success");
			return response;
			
		}catch (Exception e) {
			response.setResult("fail");
			return response;
		}
	
	}

}
