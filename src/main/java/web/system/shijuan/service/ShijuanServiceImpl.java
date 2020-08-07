package web.system.shijuan.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.shijuan.dao.ShijuanDao;
import web.system.shijuan.entity.ShijuanEntity;

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
}
