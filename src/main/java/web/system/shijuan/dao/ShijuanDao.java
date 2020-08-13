package web.system.shijuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import web.system.question.entity.DaanEntity;
import web.system.question.entity.ShitiEntity;
import web.system.shijuan.entity.ShijuanEntity;
import web.system.shijuan.entity.StToSjEntity;

public interface ShijuanDao {
	
	public List<ShijuanEntity> findAll();
	
	public List<ShijuanEntity> findPage(@Param("startIndex")int startIndex,@Param("endIndex")int endIndex);
	
	public ShijuanEntity findByid(@Param("id")String id);
	
	public int deleteShijuan(@Param("id")String id);
	
	public int deleteShijuanToShiti(@Param("id")String id);
	
	public int deleteStById(@Param("sj_id")String sj_id ,@Param("st_ids") List<String> st_ids );
	
	public List<ShitiEntity> findShitiBySjId(@Param("sj_id")String sj_id);

	public List<ShitiEntity> findShitiByKm(@Param("km_id")String km_id,@Param("sj_id")String sj_id);
	
	public List<DaanEntity> queryDaan(@Param("shitiId")String shitiId); 
	
	public int addShiti(@Param("st_sjs") List<StToSjEntity> st_sjs);
	
}
