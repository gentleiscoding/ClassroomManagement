package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBConn;
import entity.Student;

public class Solve {
	public Solve() {

	};

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
					.prepareStatement("Select fid,room,problem,feedbacktime from feedback  WHERE result='待处理' order  by feedbacktime asc limit "
							+ (page - 1) * 9 + "," + 9);
			System.out.println(pre);
			rs = pre.executeQuery();

			result = result
					+ "<table class=\"table table-striped \" data-toggle=\"table\" data-url=\"data1.json\"><thead id=\"thead\"><tr><th data-field=\"room\">教室号</th><th data-field=\"problem\">故障内容</th><th data-field=\"feedbacktime\">反馈时间</th><th data-field=\"solution\">解决</tr></thead><tbody>";
			while (rs.next()) {
				if (null == rs.getString("room")) {
					return "";
				}
				result = result
						+ "<tr><td data-field=\"room\">"
						+ rs.getString("room")
						+ "</td><td data-field=\"problem\">"
						+ rs.getString("problem")
						+ "</td><td data-field=\"feedbacktime\">"
						+ rs.getDate("feedbacktime")
						+ "</td><td data-field=\"solution\"><button class=\"btn btn-success\"onclick=\"solve("
						+ rs.getInt("fid") + ")\">解决</button></td></tr>";
			}
			result = result
					+ "</tbody></table></div> <script src=\"../js/solve.js\"></script>";
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
	public String showed(int page) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			pre = con
					.prepareStatement("Select room,problem,solvetime,solver from feedback  WHERE result='已解决' order  by solvetime desc limit "
							+ (page - 1) * 9 + "," + 9);
			System.out.println(pre);
			rs = pre.executeQuery();

			result = result
					+ "<table class=\"table table-striped \" data-toggle=\"table\" data-url=\"data1.json\"><thead id=\"thead\"><tr><th data-field=\"room\">教室号</th><th data-field=\"problem\">故障内容/th><th data-field=\"solvetime\">处理时间</th><th data-field=\"solver\">处理人</th></thead><tbody>";
			while (rs.next()) {
				if (rs.getString("room") == null) {
					return "";
				}
				result = result + "<tr><td data-field=\"room\">"
						+ rs.getString("room")
						+ "</td><td data-field=\"problem\">"
						+ rs.getString("problem")
						+ "</td><td data-field=\"solvetime\">"
						+ rs.getDate("solvetime")
						+ "</td><td data-field=\"solver\">"
						+ rs.getString("solver") + "</td></tr>";
			}
			result = result + "</tbody></table>	";

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

	// 解决故障
	public String solve(int fid, String solver) {
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime = df.format(new Date());
		try {

			pre = con
					.prepareStatement("update  feedback set result='已解决',solver='"
							+ solver
							+ "',solvetime='"
							+ createtime
							+ "' where fid=" + fid);
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