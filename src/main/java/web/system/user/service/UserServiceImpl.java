package web.system.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.user.dao.UserDao;
import web.system.user.entity.UserEntity;
import web.util.Md5Util;
import web.util.UuidUtil;

@Service("userServices")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao dao;
	
	public ExamResult<List<UserEntity>> findAllUser() {
		ExamResult<List<UserEntity>> result = new ExamResult<List<UserEntity>>();
		List<UserEntity> users = dao.findAllUser();
		if(users==null) {
			result.setStatus(1);
			result.setMsg("未查询到数据");
			return null;
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(users);
		return result;
	}

	public ExamResult<List<UserEntity>> findOneUser(String userparam) {
		ExamResult<List<UserEntity>> result = new ExamResult<List<UserEntity>>();
		List<UserEntity> users = dao.findOneUser(userparam);
		if(users.size()>0) {
			result.setMsg("查询成功");
			result.setStatus(0);
			result.setData(users);
		}else {
			result.setMsg("查无数据");
			result.setStatus(1);
		}
		return result;
	}

	public void deleteUser(String user_id) {
		dao.deleteUser(user_id);
	}

	public ExamResult<Page<UserEntity>> findPage(int pageNum,int pageSize) {
		int totalRecord = dao.findAllUser().size();
		Page<UserEntity> page = new Page<UserEntity>(pageNum,pageSize,totalRecord);
		int start = page.getStartIndex();
		int end = start+pageSize;
		page.setList(dao.findPage(start, end));
		ExamResult<Page<UserEntity>> result = new ExamResult<Page<UserEntity>>();
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(page);
		return result;
	}

	public void saveUser(String userid, String username, String usernick, String roleid, String rolename) {
		dao.saveUser(userid, username, usernick, roleid, rolename);
	}

	public ExamResult<Integer> addUser(String username, String password, String usernick, String roleid,
			String rolename) {
		String userid = UuidUtil.idNoline();
		String pwd = Md5Util.MD5(password);
		int line = dao.addUser(userid, username, pwd, usernick, roleid, rolename);
		ExamResult<Integer> result = new ExamResult<Integer>();
		if(line==0) {
			result.setStatus(1);
			result.setMsg("保存失败");
		}
		result.setStatus(0);
		result.setMsg("保存成功");
		result.setData(line);
		
		return result;
	}

}
