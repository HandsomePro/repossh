package com.itheima.dao;

import com.itheima.domain.Account;

public interface IAccountDao {
	Account findAccountByName(String accountName);

	void updateAccount(Account account);
}
