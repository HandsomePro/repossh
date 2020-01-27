package com.itheima.service;

import com.itheima.domain.Account;

public interface IAccountService {
	Account findAccountByName(String accountName);

	void updateAccount(Account account);

	void transfer(String src, String target, Float money);

}
