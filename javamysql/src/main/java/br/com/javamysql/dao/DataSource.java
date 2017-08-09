package br.com.javamysql.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by tqi_agimenes on 09/08/2017.
 */
public class DataSource {

	private static final MysqlDataSource dataSource = new MysqlDataSource();

	static {
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("simpos");
		dataSource.setURL("jdbc:mysql://localhost/simpos?user=root&password=root&useTimezone=true&serverTimezone=UTC");
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
