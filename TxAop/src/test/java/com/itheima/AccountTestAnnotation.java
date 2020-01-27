package com.itheima;

import config.Configuration;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class AccountTestAnnotation {
	@Autowired
	private IAccountService as;

	@Test
	public void name() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(Configuration.class);
		IAccountService as2 = ac.getBean("accountService", IAccountService.class);
		as2.transfer("仁宣之治", "张飞", 100f);
	}

	@Test
	public void name2() {
		as.transfer("仁宣之治", "张飞", 100f);
	}
}
