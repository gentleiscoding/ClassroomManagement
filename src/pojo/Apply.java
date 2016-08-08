package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBConn;
import entity.Student;

public class Apply {
	public Apply() {

	};

	// 申请教室的方法
	public String applyroom(String week, String day, String time, String room,
			String duomeiti, String purpose, long sid) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime = df.format(new Date());
		try {

			pre = con
					.prepareStatement("Select occupancy from classroomoccupancy WHERE week='"
							+ week
							+ "'&&day='"
							+ day
							+ "'&&time='"
							+ time
							+ "'&&room='" + room + "'");
			System.out.println(pre);
			rs = pre.executeQuery();
			String occupancy = "";
			while (rs.next()) {
				occupancy = rs.getString("occupancy");
			}
			if (occupancy.equals("是")) {
				return "该教室已被占用";
			}

			pre = con
					.prepareStatement("INSERT INTO `apply`(`room`, `week`, `day`, `time`, `sid`, `duomeiti`, `purpose`, `applytime`, `result`) VALUES('"
							+ room
							+ "','"
							+ week
							+ "','"
							+ day
							+ "','"
							+ time
							+ "',"
							+ sid
							+ ",'"
							+ duomeiti
							+ "','"
							+ purpose
							+ "','" + createtime + "','" + "待审核" + "')");
			System.out.println(pre);
			pre.executeUpdate();
			return "申请成功";

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

	// 返回个人申请的个数
	public String pagenumber(long sid) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("Select count(applyid) from apply WHERE sid="
							+ sid);
			System.out.println(pre);
			rs = pre.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count(applyid)");
			}
			int pagenumber = count / 8 + 1;
			System.out.println("pagenumber=" + pagenumber);
			result = result
					+ "<ul>";
			for (int i = 1; i <= pagenumber; i++) {
				result = result
						+ "<li><a href=\"javascript:void(0)\" onclick=\"showapply("
						+ i + ")\">" + i + "</a></li>";
			}
			result = result
					+ "</ul><script src=\"../js/applyajax.js\"></script>	";
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
		return "";
	}

	// 显示个人申请
	public String showapply(int page, long sid) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("Select room,week,day,time,result from apply WHERE sid="
							+ sid
							+ " order  by applytime desc limit "
							+ (page - 1) * 8 + "," + 8);
			System.out.println(pre);
			rs = pre.executeQuery();
			while (rs.next()) {
				if (null == rs.getString("room")) {
					return "";
				}
				result = result + "<p id=\"applyitemp\">对"
						+ rs.getString("room") + rs.getString("week")
						+ rs.getString("day") + rs.getString("time") + "的申请";
				if (rs.getString("result").equals("待审核")) {
					result = result + "正在审核";
				} else if (rs.getString("result").equals("通过")) {
					result = result + "成功";
				} else if (rs.getString("result").equals("不通过")) {
					result = result + "失败";
				}
				result = result + "</p>";
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}
}