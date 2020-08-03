package web.system.kemu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.system.kemu.dao.KemuDao;
import web.system.kemu.entity.KemuEntity;

@Service("kemuServices")
public class KemuServiceImpl implements KemuService {

	@Resource
	private KemuDao dao;
	
	public ExamResult<List<KemuEntity>> getKemuAll() {
		ExamResult<List<KemuEntity>> result = new ExamResult<List<KemuEntity>>();
		
		List<KemuEntity> list = dao.getKemu();
		
		result.setData(list);
		result.setMsg("º”‘ÿ≥…π¶");
		result.setStatus(0);
		return result;
	}

}
