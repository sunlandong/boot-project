package org.boot.facede.core.service;

import java.util.List;
import java.util.Map;

import org.boot.facede.core.model.User;

public interface UserService {

	User getUserById(String id) throws Exception;
	
	List<User> findUsersList(int start, int limit, Map<String, Object> params)throws Exception;
	
	void saveUser(User user ) throws Exception;
	
	void updateUser(User user) throws Exception;
	
	void deleteUser(String ...userId) throws Exception;
	
}
