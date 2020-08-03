package test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import web.system.question.dao.ShitiDao;

public class TestCase {
	private ApplicationContext ac;
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("conf/springmvc.xml");
	}
	
	
	@Test
	public void test3() {
		String str ="[{da_name:øÏ¿÷,code:0},{da_name:≤ªøÏ¿÷,code:1}]";
		String s1 = str.substring(2, str.length()-2);
		String s2[] = s1.split("\\}\\,\\{");
		for(String s3:s2) {
			System.out.println(s3+";");
		}
	}
}
