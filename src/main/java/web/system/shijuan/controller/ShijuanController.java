package web.system.shijuan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.question.entity.ShitiEntity;
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
	
	@ResponseBody
	@RequestMapping("/getShitiBySjId.do")
	public ExamResult<List<ShitiEntity>> getShitiBySjId(String shijuanId){
		ExamResult<List<ShitiEntity>> result = shijuanServices.findShitiBySjId(shijuanId);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteStToSj.do")
	public ExamResult<Integer> deleteStToSj(String shijuanId,@RequestParam(value = "shitiIds[]") List<String> shitiIds){
		ExamResult<Integer> result = shijuanServices.deleteStById(shijuanId, shitiIds);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/addStToSj.do")
	public ExamResult<Integer> addStToSj(String shijuanId,@RequestParam(value = "shitiIds[]") List<String> shitiIds){
		ExamResult<Integer> result = shijuanServices.addStToSj(shijuanId, shitiIds);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getShitiByKm.do")
	public ExamResult<List<ShitiEntity>> getShitiByKm(String km_id,String sj_id){
		ExamResult<List<ShitiEntity>> result = shijuanServices.findShitiByKm(km_id,sj_id);
		return result;
	}
	
	
	@RequestMapping("/sj_view.do")
	public String toSjView(){
		return "sj_view";
	}
	
}
