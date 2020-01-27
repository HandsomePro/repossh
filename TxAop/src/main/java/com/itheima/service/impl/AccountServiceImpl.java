package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {
	@Autowired
	private IAccountDao accountDao;


	public Account findAccountByName(String accountName) {
		return accountDao.findAccountByName(accountName);
	}

	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	public void transfer(String src, String target, Float money) {
		Account srcAccount = accountDao.findAccountByName(src);
		Account targetAccount = accountDao.findAccountByName(target);
		srcAccount.setMoney(srcAccount.getMoney() - 100);
		targetAccount.setMoney(targetAccount.getMoney() + 100);
		updateAccount(srcAccount);
//		int i = 1/0;
		updateAccount(targetAccount);

	}

}
