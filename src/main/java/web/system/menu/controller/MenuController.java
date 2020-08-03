package web.system.menu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.system.menu.entity.MenuEntity;
import web.system.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Resource
	private MenuService menuServices;
	
	@ResponseBody
	@RequestMapping("/loadMenu.do")
	public ExamResult<List<MenuEntity>> LoadMenu(String user_id) {
		ExamResult<List<MenuEntity>> result = menuServices.findByUserId(user_id);
		return result;
	}
	
}
