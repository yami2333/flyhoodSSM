package com.neuedu.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private static DBUtils db;
	private String driver_;
	private String url_;
	private String user_;
	private String password_;
	private DBUtils() {
		try {
			// 加载配置文件信息
			Properties p = new Properties();
			p.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
//			getProperty通过key值获取配置文件中的value值（返回值）
			driver_ = p.getProperty("jdbc.driverClassName");
			url_ = p.getProperty("jdbc.url");
			user_ = p.getProperty("jdbc.username");
			password_ = p.getProperty("jdbc.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static DBUtils getInstance() {
		if(db == null) {
			db = new DBUtils();
		}
		return db;
	}
	/**
	 * 获取数据库连接的方法
	 * @return 连接对象
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driver_);
		Connection conn = DriverManager.getConnection(url_, user_, password_);
		return conn;
	}
	public void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
