package web.system.shijuan.service;

import java.util.List;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.question.entity.ShitiEntity;
import web.system.shijuan.entity.ShijuanEntity;

public interface ShijuanService {
	
	public ExamResult<Page<ShijuanEntity>> findPage(int pageNum,int pageSize);
	
	public ExamResult<ShijuanEntity> findByid(String id);
	
	public ExamResult<Integer> deleteShijuan(String id);

	public ExamResult<Integer> deleteStById(String sj_id,List<String> st_ids);
	
	public ExamResult<List<ShitiEntity>> findShitiBySjId(String sj_id);

	public ExamResult<List<ShitiEntity>> findShitiByKm(String km_id,String sj_id);
	
	public ExamResult<Integer> addStToSj(String sj_id,List<String> st_ids);

}
