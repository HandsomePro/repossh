package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.utils.ConnectU;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("accountDao")
public class AccountDaoImpl2 implements IAccountDao {
	@Autowired
	private QueryRunner queryRunner;
	@Autowired
	private ConnectU connectionUtils;


	public void setQueryRunner(QueryRunner queryRunner) {
		this.queryRunner = queryRunner;
	}

	public Account findAccountByName(String accountName) {
		String sql = "select * from account where name = ?";
		try {
			return queryRunner.query(connectionUtils.getThreadLocalConnection(), sql, new BeanHandler<Account>(Account.class), accountName);
		} catch (Exception e) {
			throw new RuntimeException("查找账号异常");
		}
	}

	//	public Account findAccountByName(String accountName) {
//		try{
//			List<Account> accounts = queryRunner.query(connectionUtils.getThreadLocalConnection(),"select * from account where name = ? ",new BeanListHandler<Account>(Account.class),accountName);
//			if(accounts == null || accounts.size() == 0){
//				return null;
//			}
//			if(accounts.size() > 1){
//				throw new RuntimeException("结果集不唯一，数据有问题");
//			}
//			return accounts.get(0);
//		}catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	public void updateAccount(Account account) {
		String sql = "update account set money = ? where id = ?";
		try {
			queryRunner.update(connectionUtils.getThreadLocalConnection(), sql, account.getMoney(), account.getId());
		} catch (SQLException e) {
			throw new RuntimeException("更新账号异常");
		}
	}
}
