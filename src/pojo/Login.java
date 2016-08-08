package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConn;
import entity.Student;

public class Login {
	public Login() {

	};

	// 学生的登陆方法
	public String studentresult(long sid, String password) {
		String result = "";

		System.out.println("here");

		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {
			pre = con
					.prepareStatement("Select password,sname,pnumber,college,classname from student WHERE sid="
							+ sid);
			rs = pre.executeQuery();
			String passw = "";
			String sname = "";
			int pnumber = 0;
			String college = "";
			String classname = "";
			while (rs.next()) {
				passw = rs.getString("password");
				pnumber = rs.getInt("pnumber");
				sname = rs.getString("sname");
				college = rs.getString("college");
				classname = rs.getString("classname");
			}
			if (passw.equals("")) {
				result = "学号不存在";
				return result;

			}
			if (!passw.equals(password)) {
				result = "密码错误";
				return result;
			}
			if (passw.equals(password)) {
				result = "登陆成功";

				return result + "," + sid + "," + sname + "," + password + ","
						+ pnumber + "," + college + "," + classname;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return result;
	}

	// admin的登陆方法
	public String adminresult(int aid, String password) {

		String result = "";

		System.out.println("here");

		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {
			pre = con
					.prepareStatement("Select password,aname,pnumber from admin WHERE aid="
							+ aid);
			System.out.println(pre);
			rs = pre.executeQuery();
			String passw = "";
			String aname = "";
			int pnumber = 0;
			while (rs.next()) {
				passw = rs.getString("password");
				pnumber = rs.getInt("pnumber");
				aname = rs.getString("aname");
			}
			if (passw.equals("")) {
				result = "工作号不存在";
				return result;

			}
			if (!passw.equals(password)) {
				result = "密码错误";
				return result;
			}
			if (passw.equals(password)) {
				result = "登陆成功";

				return result + "," + aid + "," + password + "," + aname + ","
						+ pnumber;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return result;
	}

	// 物业的登陆方法
	public String wuyeresult(int wid, String password) {

		String result = "";

		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {
			pre = con
					.prepareStatement("Select password,wname,pnumber from wuye WHERE wid="
							+ wid);
			System.out.println(pre);
			rs = pre.executeQuery();
			String passw = "";
			String wname = "";
			int pnumber = 0;
			while (rs.next()) {
				passw = rs.getString("password");
				pnumber = rs.getInt("pnumber");
				wname = rs.getString("wname");
			}
			if (passw.equals("")) {
				result = "工作号不存在";
				return result;

			}
			if (!passw.equals(password)) {
				result = "密码错误";
				return result;
			}
			if (passw.equals(password)) {
				result = "登陆成功";

				return result + "," + wid + "," + password + "," + wname + ","
						+ pnumber;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return result;
	}
}
