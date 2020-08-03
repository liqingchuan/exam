package web.system.kemu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.auth.ExamResult;
import web.system.kemu.entity.KemuEntity;
import web.system.kemu.service.KemuService;

@Controller
@RequestMapping("/kemu")
public class KemuController {

	@Resource
	private KemuService kemuServices;
	
	@ResponseBody
	@RequestMapping("/getKemuAll.do")
	public ExamResult<List<KemuEntity>> getKemuAll(){
		ExamResult<List<KemuEntity>> result = kemuServices.getKemuAll();
		return result;
	}
}
