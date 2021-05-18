package com.github.east196.starter.auth;

public interface UserService {
	User save(User user);

	User findByEmail(String email);

}
