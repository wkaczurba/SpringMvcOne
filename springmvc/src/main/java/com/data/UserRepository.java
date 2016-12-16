package com.data;

import com.User;

public interface UserRepository {
	
	// Needs a database engine (to be injected?)
	User findByUserName(String userName);
	User saveUser(User user);
}
