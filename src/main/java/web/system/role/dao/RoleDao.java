package web.system.role.dao;

import java.util.List;

import web.system.role.entity.RoleEntity;

public interface RoleDao {
	public RoleEntity findById(String roleid);
	public List<RoleEntity> findAll();
}
