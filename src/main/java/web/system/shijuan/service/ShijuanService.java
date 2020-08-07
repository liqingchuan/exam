package web.system.shijuan.service;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.shijuan.entity.ShijuanEntity;

public interface ShijuanService {
	
	public ExamResult<Page<ShijuanEntity>> findPage(int pageNum,int pageSize);
	
	public ExamResult<ShijuanEntity> findByid(String id);
	
	public ExamResult<Integer> deleteShijuan(String id);
	
}
