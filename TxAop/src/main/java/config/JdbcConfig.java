package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

public class JdbcConfig {
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
/*@Value("${jdbc.driver}")
private String driver;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;*/

	@Bean(name = "queryRunner")
	@Scope("prototype")
	public QueryRunner createQueryRunner(@Qualifier("dataSource") DataSource dataSource) {
		return new QueryRunner(dataSource);
	}

	@Bean(name = "dataSource")
	public DataSource createDatasource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(driver);
			ds.setJdbcUrl(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建Datasource失败");
		}
	}
}
