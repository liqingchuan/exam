package web.system.question.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import web.system.question.entity.DaanEntity;
import web.system.question.entity.ShitiEntity;

public interface ShitiDao {
	
	
	public List<ShitiEntity> findAll(); 
	
	public List<ShitiEntity> queryShiti(@Param("shitiParam")String shitiParam); 

	public List<DaanEntity> queryDaan(@Param("shitiId")String shitiId); 

	public List<ShitiEntity> findPage(@Param("start")int start,@Param("end")int end);
	
	public ShitiEntity findById(@Param("shitiId")String shitiId);
	
	public int deleteShiti(@Param("shitiId")String shitiId);
	
	public int deleteDaan(@Param("shitiId")String shitiId);

	public int updateTimu(@Param("shitiId")String shitiid,@Param("timu")String timu);
	
	public int updateDaan(@Param("shitiId")String shitiid,@Param("daan")String daan);

	public int insertDaan(	@Param("id")String id,
							@Param("st_id")String stid,
							@Param("da_name")String daan,
							@Param("code")String code);
	
	public int insertShiti(	@Param("id")String id,
							@Param("km_id")String kemuId,
							@Param("tm_name")String timu,
							@Param("st_lb")String st_lb);
	
}
