package web.system.login.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.system.login.entity.UserLoginEntity;
import web.system.login.service.UserLoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private UserLoginService userLoginServices;
	
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("/index.do")
	public String toIndex() {
		return "index";
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public ExamResult<UserLoginEntity> login(String username,String password){
		ExamResult<UserLoginEntity> result = userLoginServices.FindByUsername(username, password);
		return result;
	}
	
	@RequestMapping("/getUser.do")
	@ResponseBody
	public ExamResult<UserLoginEntity> getUser(String userId){
		ExamResult<UserLoginEntity> result = userLoginServices.FindById(userId);
		return result;
	}
	
}
