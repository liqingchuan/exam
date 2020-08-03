package web.system.menu.dao;

import java.util.List;

import web.system.menu.entity.MenuEntity;

public interface MenuDao {
	public List<MenuEntity> findByUserId(String user_id);
}
