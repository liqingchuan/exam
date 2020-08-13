package web.system.shijuan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jdt.internal.compiler.lookup.TypeConstants.DangerousMethod;
import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.question.entity.DaanEntity;
import web.system.question.entity.ShitiEntity;
import web.system.shijuan.dao.ShijuanDao;
import web.system.shijuan.entity.ShijuanEntity;
import web.system.shijuan.entity.StToSjEntity;

@Service("shijuanServices")
public class ShijuanServiceImpl implements ShijuanService {

	@Resource
	private ShijuanDao dao;
	
	public ExamResult<Page<ShijuanEntity>> findPage(int pageNum, int pageSize) {
		ExamResult<Page<ShijuanEntity>> result = new ExamResult<Page<ShijuanEntity>>();
		int totalRecord = dao.findAll().size();
		if(totalRecord==0) {
			result.setStatus(1);
			result.setMsg("查无数据");
		}else {
			Page<ShijuanEntity> pageList = new Page<ShijuanEntity>(pageNum, pageSize, totalRecord);
			int start = pageList.getStartIndex();
			int end = start+pageSize;
			pageList.setList(dao.findPage(start,end));
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(pageList);
		}
		return result;
	}

	public ExamResult<ShijuanEntity> findByid(String id) {
		ExamResult<ShijuanEntity> result = new ExamResult<ShijuanEntity>();
		ShijuanEntity shijuan = dao.findByid(id);
		if(shijuan!=null) {
			result.setData(shijuan);
			result.setMsg("查询成功");
			result.setStatus(0);
		}else {
			result.setMsg("查询失败");
			result.setStatus(1);
		}
		return result;
	}

	public ExamResult<Integer> deleteShijuan(String id) {
		ExamResult<Integer> result = new ExamResult<Integer>();
		dao.deleteShijuanToShiti(id);
		dao.deleteShijuan(id);
		result.setMsg("删除成功");
		result.setStatus(0);
		return result;
	}

	public ExamResult<List<ShitiEntity>> findShitiBySjId(String sj_id) {
		ExamResult<List<ShitiEntity>> result = new ExamResult<List<ShitiEntity>>();
		List<ShitiEntity> list = dao.findShitiBySjId(sj_id);
		if(list.size()<1) {
			result.setStatus(1);
			result.setMsg("查无数据");
		}else {
			for(ShitiEntity st : list) {
				List<DaanEntity> ds = dao.queryDaan(st.getId());
				st.setDans(ds);
			}
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(list);
		}
		return result;
	}

	public ExamResult<Integer> deleteStById(String sj_id, List<String> st_ids) {
		ExamResult<Integer> result = new ExamResult<Integer>();
		if(st_ids!=null && st_ids.size()>0) {
			dao.deleteStById(sj_id, st_ids);
			result.setMsg("删除成功");
			result.setStatus(0);
		}else {
			result.setMsg("删除失败");
			result.setStatus(1);
		}
		return result;
	}

	public ExamResult<List<ShitiEntity>> findShitiByKm(String km_id,String sj_id) {
		ExamResult<List<ShitiEntity>> result = new ExamResult<List<ShitiEntity>>();
		List<ShitiEntity> allSt = dao.findShitiByKm(km_id,sj_id);
		if(allSt.size()>0) {
			result.setData(allSt);
			result.setMsg("查询成功");
			result.setStatus(0);
		}else {
			result.setMsg("查无数据");
			result.setStatus(1);
		}
		return result;
	}

	public ExamResult<Integer> addStToSj(String sj_id, List<String> st_ids) {
		ExamResult<Integer> result = new ExamResult<Integer>();
		List<StToSjEntity> list = new ArrayList<StToSjEntity>();
		StToSjEntity sts;
		for(String id : st_ids) {
			sts = new StToSjEntity(sj_id, id);
			list.add(sts);
		}
		int i = dao.addShiti(list);
		if(i>0) {
			result.setMsg("添加成功");
			result.setStatus(0);
		}else {
			result.setMsg("添加失败");
			result.setStatus(1);
		}
		return result;
	}
}
