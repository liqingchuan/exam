package web.system.role.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.system.role.entity.RoleEntity;
import web.system.role.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource
	private RoleService roleservice;
	
	@ResponseBody
	@RequestMapping("/findAll.do")
	public ExamResult<List<RoleEntity>> findAll(){
		ExamResult<List<RoleEntity>> result = roleservice.findAll();
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/findOne.do")
	public ExamResult<RoleEntity> findOne(String roleid){
		ExamResult<RoleEntity> result = roleservice.findById(roleid);
		return result;
	}
	
}
