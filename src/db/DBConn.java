package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {


	/* 设置连接数据库相关参数 */

	public DBConn() {
	}

	/* 获取Conncetion对象并返回 */

	public Connection getConnection() {

		Connection con = null;

		try {
			System.out.println("1");
			Class.forName("com.mysql.jdbc.Driver"); // 加载Jdbc驱动程序
			System.out.println("2");
			con = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/classroommanage?useUnicode=true&characterEncoding=utf8",
							"root", "");
		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("getConnection()内部跟踪错误:" + e.getMessage());
		}

		return con;

	}
}