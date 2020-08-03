package web.system.role.service;

import java.util.List;

import web.auth.ExamResult;
import web.system.role.entity.RoleEntity;

public interface RoleService {
	public ExamResult<RoleEntity> findById(String roleid);
	public ExamResult<List<RoleEntity>> findAll();
}
