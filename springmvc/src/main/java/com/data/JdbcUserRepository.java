package com.data;

import com.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class JdbcUserRepository implements UserRepository {
	
	JdbcOperations jdbcOperations;
	
	@Autowired
	public JdbcUserRepository(JdbcOperations jdbcOperations) {		
		this.jdbcOperations = jdbcOperations; 
	}

	@Override
	public User findByUserName(String userName) {
		return jdbcOperations.queryForObject("select id, username, null, first_name, last_name, email from UserRepo where username=?",
				new UserRowMapper(),
				userName);
		//return null;
	}

	@Override
	public User saveUser(User user) {
		jdbcOperations.update("insert into UserRepo "
			      + "(username, password, first_name, last_name, email) values (?, ?, ?, ?, ?)",
			      user.getUserName(),
			      user.getPassword(),
			      user.getFirstName(),
			      user.getLastName(),
			      user.getEmail());
		
		return user; // TODO: Determine ID;
	}

	private class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(
					rs.getLong("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("userName"),
					null,
					rs.getString("email")
				);
		}
		
	}
	
}
