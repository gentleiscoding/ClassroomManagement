package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConn;
import entity.Student;

public class Search {
	public Search() {

	};

	// 搜空教室的方法
	public String searchroom(String week, String day, String time) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {

			// 得到数量
			pre = con
					.prepareStatement("Select count(distinct room) from classroomoccupancy WHERE week='"
							+ week
							+ "'&&day='"
							+ day
							+ "'&&time='"
							+ time
							+ "'&&occupancy='否'");
			System.out.println(pre);
			rs = pre.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count(distinct room)");
			}
			if (count > 0) {
				pre = con
						.prepareStatement("Select distinct room from classroomoccupancy WHERE week='"
								+ week
								+ "'&&day='"
								+ day
								+ "'&&time='"
								+ time
								+ "'&&occupancy='否'");
				System.out.println(pre);
				rs = pre.executeQuery();
				int i = 1;
				result = result + "<p>";
				while (rs.next()) {
					if (i % 6 == 0) {
						result = result + " " + rs.getString("room")
								+ "</p><p>";
					} else {
						result = result + " " + rs.getString("room");
					}
					i++;
				}
				result = result + "</p>";
				return result;
			} else {
				return "";
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
		return "";
	}

	// 搜空时段的方法
	public String searchtime(String room, String week, String day) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {
			// 得到数量
			pre = con
					.prepareStatement("Select count(distinct time) from classroomoccupancy WHERE week='"
							+ week
							+ "'&&day='"
							+ day
							+ "'&&room='"
							+ room
							+ "'&&occupancy='否'");
			System.out.println(pre);
			rs = pre.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count(distinct time)");
			}
			if (count > 0) {
				pre = con
						.prepareStatement("Select distinct time from classroomoccupancy WHERE week='"
								+ week
								+ "'&&day='"
								+ day
								+ "'&&room='"
								+ room
								+ "'&&occupancy='否'");
				System.out.println(pre);
				rs = pre.executeQuery();
				while (rs.next()) {
					result = result + "<p>" + rs.getString("time") + "</p>";
				}
				return result;
			} else {
				return "";
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
		return "";
	}
}
