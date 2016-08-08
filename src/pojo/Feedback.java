package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBConn;
import entity.Student;

public class Feedback {
	public Feedback() {

	};

	public String sendfeedback(String room, String problem) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime = df.format(new Date());
		try {

			pre = con
					.prepareStatement("INSERT INTO `feedback`(`room`, `problem`, `feedbacktime`) VALUES ('"
							+ room + "','" + problem + "','" + createtime + "')");
			System.out.println(pre);
			pre.executeUpdate();
			System.out.println("aaaaa");
			return "反馈成功";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return "反馈失败";
	}
}