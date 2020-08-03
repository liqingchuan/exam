package web.system.login.dao;

import web.system.login.entity.UserLoginEntity;

public interface UserLoginDao {
	public UserLoginEntity findByUsername(String username);
	public UserLoginEntity findById(String userId);
	
}
