package web.system.role.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.system.role.dao.RoleDao;
import web.system.role.entity.RoleEntity;

@Service("roleservice")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao dao;
	
	public ExamResult<RoleEntity> findById(String roleid) {
		ExamResult<RoleEntity> result = new ExamResult<RoleEntity>();
		RoleEntity role = dao.findById(roleid);
		if(role==null) {
			result.setStatus(1);
			result.setMsg("��������");
		}
		result.setData(role);
		result.setMsg("��ѯ�ɹ�");
		result.setStatus(0);
		return result;
	}

	public ExamResult<List<RoleEntity>> findAll() {
		ExamResult<List<RoleEntity>> result = new ExamResult<List<RoleEntity>>();
		List<RoleEntity> roles = dao.findAll();
		if(roles==null) {
			result.setStatus(1);
			result.setMsg("��������");
		}
		result.setData(roles);
		result.setMsg("��ѯ�ɹ�");
		result.setStatus(0);
		return result;
	}

}
