package web.system.kemu.service;

import java.util.List;

import web.auth.ExamResult;
import web.system.kemu.entity.KemuEntity;

public interface KemuService {

	public ExamResult<List<KemuEntity>> getKemuAll();
	
}
