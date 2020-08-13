package test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import web.system.question.dao.ShitiDao;
import web.system.shijuan.dao.ShijuanDao;

public class TestCase {
	private ApplicationContext ac;
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("conf/springmvc.xml");
	}
	
	
	@Test
	public void test3() {
		ShijuanDao sj = ac.getBean("shijuanDao",ShijuanDao.class);
		List<String> l = new ArrayList<String>();
		l.add("fecf4520aca74c7cb73c1726919d85fe");
		l.add("70fde07cf4d847e3b97fe8871c085579");
		int i = sj.deleteStById("f86128d0ec3a4a96a6ab09513d50e9bf", l);
		System.out.println(i);
	}
}
