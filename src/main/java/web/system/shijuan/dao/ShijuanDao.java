package web.system.shijuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import web.system.shijuan.entity.ShijuanEntity;

public interface ShijuanDao {
	
	public List<ShijuanEntity> findAll();
	
	public List<ShijuanEntity> findPage(@Param("startIndex")int startIndex,@Param("endIndex")int endIndex);
	
	public ShijuanEntity findByid(@Param("id")String id);
	
	public int deleteShijuan(@Param("id")String id);
	
	public int deleteShijuanToShiti(@Param("id")String id);
}
