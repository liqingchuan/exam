package web.system.question.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.question.dao.ShitiDao;
import web.system.question.entity.DaanEntity;
import web.system.question.entity.ShitiEntity;
import web.util.UuidUtil;

@Service("shitiServices")
public class ShitiServiceImpl implements ShitiService {

	@Resource
	private ShitiDao dao;
	
	public ExamResult<Page<ShitiEntity>> findPage(int pageNum, int pageSize) {
		ExamResult<Page<ShitiEntity>> result = new ExamResult<Page<ShitiEntity>>();
		int totalRecord = dao.findAll().size();
		if(totalRecord==0) {
			result.setStatus(1);
			result.setMsg("查无数据");
		}else {
			Page<ShitiEntity> pageList = new Page<ShitiEntity>(pageNum, pageSize, totalRecord);
			int start = pageList.getStartIndex();
			int end = start+pageSize;
			pageList.setList(dao.findPage(start,end));
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(pageList);
		}
		return result;
	}

	public ExamResult<ShitiEntity> findById(String shitiId) {
		ExamResult<ShitiEntity> result = new ExamResult<ShitiEntity>();
		ShitiEntity shiti = dao.findById(shitiId);
		if(shiti!=null) {
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(shiti);
		}else {
			result.setStatus(1);
			result.setMsg("查无数据");
		}
		return result;
	}

	public ExamResult<Integer> deleteShiti(String shitiId) {
		ExamResult<Integer> result = new ExamResult<Integer>();
		dao.deleteDaan(shitiId);
		Integer row = dao.deleteShiti(shitiId);
		if(row!=0) {
			result.setStatus(0);
			result.setMsg("删除成功");
			result.setData(row);
		}else {
			result.setStatus(1);
			result.setMsg("删除失败");
		}
		return result;
	}

	public ExamResult<List<ShitiEntity>> queryShiti(String shitiParam) {
		ExamResult<List<ShitiEntity>> result = new ExamResult<List<ShitiEntity>>();
		List<ShitiEntity> shitiList = dao.queryShiti(shitiParam);
		if(shitiList!=null) {
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(shitiList);
		}else {
			result.setStatus(1);
			result.setMsg("查询失败");
		}
		return result;
	}

	public ExamResult<List<DaanEntity>> queryDaan(String shitiId) {
		ExamResult<List<DaanEntity>> result = new ExamResult<List<DaanEntity>>();
		List<DaanEntity> daanList = dao.queryDaan(shitiId);
		if(daanList!=null) {
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(daanList);
		}else {
			result.setStatus(1);
			result.setMsg("查询失败");
		}
		return result;
	}

	public ExamResult<Integer> updateShitiDan(String shitiId, String timu, String daan) {
		dao.deleteDaan(shitiId);
		dao.updateTimu(shitiId, timu);
		if(daan.length()>2) {
			String s1 = daan.substring(2, daan.length()-2);
			String ss[] = s1.split("\\}\\,\\{");
			for(String s:ss) {
				String s2[] = s.split("\\,");
				String da_name = s2[0].substring(s2[0].indexOf(":")+1, s2[0].length());
				String code = s2[1].substring(s2[1].indexOf(":")+1, s2[1].length());
				dao.insertDaan(UuidUtil.idNoline(), shitiId, da_name, code);
			}
		}
		ExamResult<Integer> result = new ExamResult<Integer>();
		result.setStatus(0);
		result.setMsg("修改成功");
		return result;
	}

	public ExamResult<Integer> updateJiedaTi(String shitiId, String timu, String daan) {
		ExamResult<Integer> result = new ExamResult<Integer>();
		dao.updateTimu(shitiId, timu);
		int indan = dao.updateDaan(shitiId, daan);
		if(indan<1) {
			dao.insertDaan(UuidUtil.idNoline(), shitiId, daan, "0");
		}
		result.setStatus(0);
		result.setMsg("修改成功");
		return result;
	}

	public ExamResult<Integer> addShiti(String kemuId, String timu, String st_lb) {
		ExamResult<Integer> result = new ExamResult<Integer>();
		String shitiId = UuidUtil.idNoline();
		dao.insertShiti(shitiId, kemuId, timu, st_lb);
		if("2".equals(st_lb)) {
			dao.insertDaan(UuidUtil.idNoline(), shitiId, "正确", "0");
			dao.insertDaan(UuidUtil.idNoline(), shitiId, "错误", "1");
		}
		result.setMsg("添加成功");
		result.setStatus(0);
		return result;
	}
}
