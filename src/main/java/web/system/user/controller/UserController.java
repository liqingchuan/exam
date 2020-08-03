package web.system.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.user.entity.UserEntity;
import web.system.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userServices;
	
	@ResponseBody
	@RequestMapping("/findOneUser.do")
	public ExamResult<List<UserEntity>> findOneUser(String userparam){
		ExamResult<List<UserEntity>> result = userServices.findOneUser(userparam);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/updateUser.do")
	public void saveUser(String userid,String username,String usernick,String roleid,String rolename){
		userServices.saveUser(userid, username, usernick, roleid, rolename);
	}
	
	@ResponseBody
	@RequestMapping("/deleteUser.do")
	public void deleteUser(String user_id){
		userServices.deleteUser(user_id);
	}
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllUser.do")
	public ExamResult<Page<UserEntity>> findAllUser(int pageNum, int pageSize){
		ExamResult<Page<UserEntity>> result = userServices.findPage(pageNum, pageSize);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/addUser.do")
	public ExamResult<Integer> addUser(String username,String password,String nickname,String roleid,String rolename){
		ExamResult<Integer> result = userServices.addUser(username, password, nickname, roleid, rolename);
		return result;
	}
}
