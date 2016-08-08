package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBConn;
import entity.Student;

public class Examine {
	public Examine() {

	};

	// 返回待申请的个数
	public String unpagenumber() {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("Select count(applyid) from apply WHERE result='待审核' order by applytime asc");
			System.out.println(pre);
			rs = pre.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count(applyid)");
			}
			int unpagenumber = count / 9 + 1;
			System.out.println("unpagenumber=" + unpagenumber);
			result = result
					+ "<a href=\"javascript:void(0)\" onclick=\"olderun()\"  class=\"btn btn-default previous\">上一页</a><ul>";
			for (int i = 1; i <= unpagenumber; i++) {
				result = result
						+ "<li><a href=\"javascript:void(0)\" onclick=\"showun("
						+ i + ")\">" + i + "</a></li>";
			}
			result = result
					+ "</ul><a href=\"javascript:void(0)\" onclick=\"newerun()\" class=\"btn btn-default next\">下一页</a>";
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

	// 返回已申请的个数
	public String edpagenumber() {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("Select count(applyid) from apply WHERE result='通过'||result='不通过' order by applytime desc");
			System.out.println(pre);
			rs = pre.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count(applyid)");
			}
			int edpagenumber = count / 9 + 1;
			System.out.println("edpagenumber=" + edpagenumber);
			result = result
					+ "<a href=\"javascript:void(0)\" onclick=\"oldered()\"  class=\"btn btn-default previous\">上一页</a><ul>";
			for (int i = 1; i <= edpagenumber; i++) {
				result = result
						+ "<li><a href=\"javascript:void(0)\" onclick=\"showed("
						+ i + ")\">" + i + "</a></li>";
			}
			result = result
					+ "</ul><a href=\"javascript:void(0)\" onclick=\"newered()\" class=\"btn btn-default next\">下一页</a><script src=\"../js/examineajax.js\"></script>	";
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

	// 显示un申请
	public String showun(int page) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("Select applyid,room,week,day,time,sname,sid,duomeiti,purpose from apply natural join student WHERE result='待审核' order  by applytime asc limit "
							+ (page - 1) * 9 + "," + 9);
			System.out.println(pre);
			rs = pre.executeQuery();

			result = result
					+ "<table class=\"table table-striped \" data-toggle=\"table\" data-url=\"data1.json\"><thead id=\"thead\"><tr><th data-field=\"roomname\">教室号</th><th data-field=\"week\">周数</th><th data-field=\"day\">周号</th><th data-field=\"time\">时段</th><th data-field=\"sname\">申请人姓名</th><th data-field=\"sid\">申请人学号</th><th data-field=\"duomeiti\">多媒体</th><th data-field=\"purpose\">用途</th><th data-field=\"solution\">处理</th></tr></thead><tbody>";
			while (rs.next()) {
				if (null == rs.getString("room")) {
					return "";
				}
				result = result
						+ "<tr><td data-field=\"roomname\">"
						+ rs.getString("room")
						+ "</td><td data-field=\"week\">"
						+ rs.getString("week")
						+ "</td><td data-field=\"day\">"
						+ rs.getString("day")
						+ "</td><td data-field=\"time\">"
						+ rs.getString("time")
						+ "</td><td data-field=\"sname\">"
						+ rs.getString("sname")
						+ "</td><td data-field=\"sid\">"
						+ rs.getString("sid")
						+ "</td><td data-field=\"duomeiti\">"
						+ rs.getString("duomeiti")
						+ "</td><td data-field=\"purpose\">"
						+ rs.getString("purpose")
						+ "</td><td data-field=\"solution\"><button class=\"btn btn-success\" onclick=\"pass("
						+ rs.getString("applyid")
						+ ")\">通过</button><button class=\"btn btn-danger\"onclick=\"fail("
						+ rs.getString("applyid")
						+ ")\">不通过</button></td></tr>";
			}
			result = result + "</tbody></table></div>";
			System.out.println(result);
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

	// 显示ed申请
	public String showed(int edpage) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {		
			pre = con
					.prepareStatement("Select applyid,room,week,day,time,sname,sid,duomeiti,purpose,result from apply natural join student WHERE result='通过'||result='不通过' order by applytime desc limit "
							+ (edpage - 1) * 9 + "," + ((edpage - 1) * 9 + 8));
			System.out.println(pre);
			rs = pre.executeQuery();

			result = result
					+ "<table class=\"table table-striped \" data-toggle=\"table\" data-url=\"data1.json\"><thead id=\"thead\"><tr><th data-field=\"roomname\">教室号</th><th data-field=\"week\">周数</th><th data-field=\"day\">周号</th><th data-field=\"time\">时段</th><th data-field=\"sname\">申请人姓名</th><th data-field=\"sid\">申请人学号</th><th data-field=\"duomeiti\">多媒体</th><th data-field=\"purpose\">用途</th><th data-field=\"solution\">处理</th></tr></thead><tbody>";
			while (rs.next()) {
				result = result + "<tr><td data-field=\"roomname\">"
						+ rs.getString("room")
						+ "</td><td data-field=\"week\">"
						+ rs.getString("week") + "</td><td data-field=\"day\">"
						+ rs.getString("day") + "</td><td data-field=\"time\">"
						+ rs.getString("time")
						+ "</td><td data-field=\"sname\">"
						+ rs.getString("sname")
						+ "</td><td data-field=\"sid\">" + rs.getString("sid")
						+ "</td><td data-field=\"duomeiti\">"
						+ rs.getString("duomeiti")
						+ "</td><td data-field=\"purpose\">"
						+ rs.getString("purpose")
						+ "</td><td data-field=\"solution\">"
						+ rs.getString("result") + "</td></tr>";
			}
			result = result + "</tbody></table></div>";
			System.out.println(result);
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

	// 通过的方法
	public String pass(int applyid) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("update  apply set result='通过' where applyid="
							+ applyid);
			System.out.println(pre);
			pre.executeUpdate();
			// 获取相关信息
			pre = con
					.prepareStatement("select  week,day,time,room,purpose,duomeiti from apply where applyid="
							+ applyid);
			System.out.println(pre);
			rs = pre.executeQuery();
			String week = "";
			String day = "";
			String time = "";
			String room = "";
			String purpose = "";
			String duomeiti = "";
			while (rs.next()) {
				week = rs.getString("week");
				day = rs.getString("day");
				time = rs.getString("time");
				room = rs.getString("room");
				purpose = rs.getString("purpose");
				duomeiti = rs.getString("duomeiti");
			}
			pre = con
					.prepareStatement("update classroomoccupancy set occupancy='是',purpose='"
							+ purpose
							+ "',duomeiti='"
							+ duomeiti
							+ "' where room='"
							+ room
							+ "'&&week='"
							+ week
							+ "'&&day='" + day + "'&&time='" + time + "'");
			System.out.println(pre);
			pre.executeUpdate();
			return "操作成功";
		} catch (SQLException e) {
			e.printStackTrace();

			return "操作失败";
		} finally {
			try {
				con.rollback();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	// 不通过的方法
	public String fail(int applyid) {
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("update  apply set result='不通过' where applyid="
							+ applyid);
			System.out.println(pre);
			pre.executeUpdate();
			return "操作成功";
		} catch (SQLException e) {
			e.printStackTrace();
			return "操作失败";
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}
}