package web.system.user.service;

import java.util.List;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.user.entity.UserEntity;

public interface UserService {
	public ExamResult<List<UserEntity>> findAllUser();
	
	public ExamResult<Page<UserEntity>> findPage(int pageNum,int pageSize);
	
	public ExamResult<List<UserEntity>> findOneUser(String userparam);
	
	public void deleteUser(String user_id);
	
	public void saveUser(String userid,String username,String usernick,String roleid,String rolename);
	
	public ExamResult<Integer> addUser(String username,String password,String usernick,String roleid,String rolename);
	
}
