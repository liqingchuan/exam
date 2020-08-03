package web.system.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.system.menu.dao.MenuDao;
import web.system.menu.entity.MenuEntity;

@Service("menuServices")
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao dao;
	
	public ExamResult<List<MenuEntity>> findByUserId(String user_id) {
		ExamResult<List<MenuEntity>> result = new ExamResult<List<MenuEntity>>();
		List<MenuEntity> menus = dao.findByUserId(user_id);
		if(menus==null) {
			result.setStatus(1);
			result.setMsg("查不到菜单");
			return null;
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(menus);
		return result;
	}

}
