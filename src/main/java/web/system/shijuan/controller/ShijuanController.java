package web.system.shijuan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.shijuan.entity.ShijuanEntity;
import web.system.shijuan.service.ShijuanService;

@Controller
@RequestMapping("/shijuan")
public class ShijuanController {
	
	@Resource
	private ShijuanService shijuanServices;
	
	@ResponseBody
	@RequestMapping("/findPage.do")
	public ExamResult<Page<ShijuanEntity>> findPage(int pageNum,int pageSize){
		ExamResult<Page<ShijuanEntity>> result = shijuanServices.findPage(pageNum,pageSize);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/findByid.do")
	public ExamResult<ShijuanEntity> findByid(String shijuanId){
		ExamResult<ShijuanEntity> result = shijuanServices.findByid(shijuanId);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteShijuan.do")
	public ExamResult<Integer> deleteShijuan(String shijuanId){
		ExamResult<Integer> result = shijuanServices.deleteShijuan(shijuanId);
		return result;
	}
	
}
