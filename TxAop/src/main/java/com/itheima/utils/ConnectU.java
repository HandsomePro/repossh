package com.itheima.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
@Component("connectionUtils")
public class ConnectU {
	private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	@Autowired
	private DataSource dataSource;


	public Connection getThreadLocalConnection() {
		try {
			Connection conn = tl.get();
			if (conn == null) {
				conn = dataSource.getConnection();
				tl.set(conn);
			}
			return conn;
		} catch (Exception e) {
			throw new RuntimeException("获取连接异常");
		}
	}

	public void removeConnection() {
		tl.remove();
	}
}
