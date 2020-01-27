package com.itheima;

import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
事务xml配置测试,已无用,此项目为注解,留住只为笔记
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean2.xml")
public class AccountTest {
	@Autowired
	private IAccountService as;

	@Test
	public void name() {
		as.transfer("仁宣之治", "张飞", 100f);
	}
}
