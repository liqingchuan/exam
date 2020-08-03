package web.system.login.service;

import web.auth.ExamResult;
import web.system.login.entity.UserLoginEntity;

public interface UserLoginService {
	public ExamResult<UserLoginEntity> FindByUsername(String username,String password);
	public ExamResult<UserLoginEntity> FindById(String userid);
}
