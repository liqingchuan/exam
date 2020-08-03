package web.system.menu.service;

import java.util.List;

import web.auth.ExamResult;
import web.system.menu.entity.MenuEntity;

public interface MenuService {
	public ExamResult<List<MenuEntity>> findByUserId(String user_id);
}
