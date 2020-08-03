package web.system.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import web.system.user.entity.UserEntity;

public interface UserDao {
	
	public List<UserEntity> findAllUser();
	
	public List<UserEntity> findUser(String userparam);
	
	public List<UserEntity> findPage(@Param("startIndex")int startIndex,@Param("endIndex")int endIndex);
	
	public List<UserEntity> findOneUser(@Param("userparam")String userparam);
	
	public void deleteUser(String user_id);
	
	public void saveUser(@Param("userid")String userid,
						@Param("username")String username,
						@Param("usernick")String usernick,
						@Param("roleid")String roleid,
						@Param("rolename")String rolename);
	
	public int addUser(@Param("userid")String userid,
			@Param("username")String username,
			@Param("password")String password,
			@Param("nickname")String usernick,
			@Param("roleid")String roleid,
			@Param("rolename")String rolename);
	
}
