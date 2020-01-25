package com.sociotweet.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sociotweet.dao.AuthDao;
import com.sociotweet.model.Response;
import com.sociotweet.model.User;

@Repository
public class AuthDaoImpl implements AuthDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public Response register(User user) {
		jdbcTemplate.update("Insert into user values(?,?,?,?,?,?)",user.getEmailId(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getWardNo(),user.getUserRole());
		Response response = new Response();
		response.setResult("success");
		return response;
	}

	@Override
	public User login(User user) {
		//jdbcTemplate.queryForObject(sql, rowMapper)
		 String sql = "SELECT * FROM user WHERE email_id = ? AND password=? ";
		 User user1=jdbcTemplate.queryForObject(sql, new Object[] {user.getEmailId(),user.getPassword()}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				User user=new User();
				user.setEmailId(rs.getString("email_id"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setWardNo(rs.getInt("ward_no"));
				user.setUserRole(rs.getInt("role_id"));
				return user;
			}
		}); 	
		 return user1;
		
	}
}
