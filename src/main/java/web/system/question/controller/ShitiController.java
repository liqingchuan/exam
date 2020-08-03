package web.system.question.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.auth.Page;
import web.system.question.entity.DaanEntity;
import web.system.question.entity.ShitiEntity;
import web.system.question.service.ShitiService;

@Controller
@RequestMapping("/shiti")
public class ShitiController {

	@Resource
	private ShitiService shitiServices;
	
	@ResponseBody
	@RequestMapping("/findpage.do")
	public ExamResult<Page<ShitiEntity>> findPage(int pageNum,int pageSize){
		ExamResult<Page<ShitiEntity>> result = shitiServices.findPage(pageNum, pageSize);
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/findById.do")
	public ExamResult<ShitiEntity> findById(String shitiid){
		ExamResult<ShitiEntity> result = shitiServices.findById(shitiid);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteShiti.do")
	public ExamResult<Integer> deleteShiti(String shitiId){
		ExamResult<Integer> result = shitiServices.deleteShiti(shitiId);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/queryShiti.do")
	public ExamResult<List<ShitiEntity>> queryShiti(String shitiParam){
		ExamResult<List<ShitiEntity>> result = shitiServices.queryShiti(shitiParam);
		return result;
	}

	@ResponseBody
	@RequestMapping("/queryDaan.do")
	public ExamResult<List<DaanEntity>> queryDaan(String shitiid){
		ExamResult<List<DaanEntity>> result = shitiServices.queryDaan(shitiid);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/updateShitiDan.do")
	public ExamResult<Integer> updateShitiDan(String shitiid,String shiti, String daan){
		ExamResult<Integer> result = shitiServices.updateShitiDan(shitiid, shiti, daan);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/updateJiedaTi.do")
	public ExamResult<Integer> updateJiedaTi(String shitiid,String shiti, String daan){
		ExamResult<Integer> result = shitiServices.updateJiedaTi(shitiid, shiti, daan);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/addShiti.do")
	public ExamResult<Integer> addShiti(String kemuId,String timu, String st_lb){
		ExamResult<Integer> result = shitiServices.addShiti(kemuId, timu, st_lb);
		return result;
	}
	
	
}
