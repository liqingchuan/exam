package web.system.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.system.login.dao.UserLoginDao;
import web.system.login.entity.UserLoginEntity;
import web.util.Md5Util;

@Service("userLoginServices")
public class UserLoginServiceImpl implements UserLoginService{

	@Resource
	private UserLoginDao dao;
	/**
	 * 登录功能，账号密码比对
	 * 
	 */
	public ExamResult<UserLoginEntity> FindByUsername(String username,String password) {
		ExamResult<UserLoginEntity> result = new ExamResult<UserLoginEntity>();
		UserLoginEntity user = dao.findByUsername(username);
		if(user==null) {
			result.setStatus(1);
			result.setMsg("账号不存在");
			return result;
		}
		if(!user.getPassword().equals(Md5Util.MD5(password))) {
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登陆成功");
		result.setData(user);
		return result;
	}
	/**
	 * 根据userId获取当前登录用户
	 * 返回userEntity对象
	 */
	public ExamResult<UserLoginEntity> FindById(String userid) {
		ExamResult<UserLoginEntity> result = new ExamResult<UserLoginEntity>();
		UserLoginEntity user = dao.findById(userid);
		if(user==null) {
			result.setStatus(1);
			result.setMsg("找不到id");
			return result;
		}
		result.setStatus(0);
		result.setMsg("获取用户成功");
		result.setData(user);
		return result;
	}

}
