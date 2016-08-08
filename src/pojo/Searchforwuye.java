package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBConn;
import entity.Student;

public class Searchforwuye {
	public Searchforwuye() {

	};

	// 查询的方法
	public String search(String week, String day, int page) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			pre = con
					.prepareStatement("Select week,day,time,duomeiti,room  from classroomoccupancy WHERE week='"
							+ week
							+ "'&&day='"
							+ day
							+ "'&&occupancy='是' limit "
							+ (page - 1)
							* 9
							+ ","
							+ 8);
			System.out.println(pre);
			rs = pre.executeQuery();

			while (rs.next()) {
				result = result
						+ "<div class=\"applyitem\" style=\"text-align:center\">";
				result = result + rs.getString("week") + rs.getString("day")
						+ rs.getString("time") + "使用" + rs.getString("room");
				if (rs.getString("duomeiti").equals("是")) {
					result = result + "(使用多媒体) </div>";
				} else {
					result = result + "</div>";
				}

			}
			System.out.println(result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return "申请失败";
	}

}