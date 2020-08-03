package web.system.question.service;


import java.util.List;
import java.util.Map;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.question.entity.DaanEntity;
import web.system.question.entity.ShitiEntity;

public interface ShitiService {
	
	public ExamResult<Page<ShitiEntity>> findPage(int pageNum,int pageSize);
	
	public ExamResult<ShitiEntity> findById(String shitiId);

	public ExamResult<List<ShitiEntity>> queryShiti(String shitiParam);

	public ExamResult<List<DaanEntity>> queryDaan(String shitiId);
	
	public ExamResult<Integer> deleteShiti(String shitiId); 
	
	public ExamResult<Integer> updateShitiDan(String shitiId,String timu,String daan); 

	public ExamResult<Integer> updateJiedaTi(String shitiId,String timu,String daan); 
	
	public ExamResult<Integer> addShiti(String kemuId,String timu,String st_lb); 
	
}
