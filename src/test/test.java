package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import db.DBConn;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = "";
		ResultSet rs = null;
		int capacity = 0;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		String d = "";
		try {
//			 for (int floor = 1; floor <= 4; floor++) {
//			 for (int room = 1; room <= 7; room = room + 2) {
//			 for (int week = 1; week <= 2; week++) {
//			 for (int day = 1; day <= 5; day++) {
//			 if (day == 1) {
//			 d = "周一";
//			 }
//			 if (day == 2) {
//			 d = "周二";
//			 }
//			 if (day == 3) {
//			 d = "周三";
//			 }
//			 if (day == 4) {
//			 d = "周四";
//			 }
//			 if (day == 5) {
//			 d = "周五";
//			 }
//			 for (int time = 1; time <= 5; time++) {
//			 pre = con
//			 .prepareStatement(" INSERT INTO `classroommanage`.`classroomoccupancy` (`room`, `week`, `day`, `time`, `occupancy`, `duomeiti`, `purpose`, `sid`) VALUES ('5-"+floor+"0"+room+"', '第"+week+"周', '"+d+"', '第"+time+"大节', '否', '', '',0)");
//			 System.out.println(pre);
//			 pre.executeUpdate();
//			 }
//			 }
//			 }
//			 }}
//	for (int floor1 = 1; floor1 <= 4; floor1++) {
//				for (int room = 1; room <= 7; room = room + 2) {
//					if (room == 1 || room == 3 || room == 5) {
//						capacity = 80;
//
//					}
//					if (room == 7) {
//						capacity = 150;
//					}
//					pre = con
//							.prepareStatement("INSERT INTO `room`(`room`, `capacity`) VALUES ('5-"
//									+ floor1
//									+ "0"
//									+ room
//									+ "',"
//									+ capacity
//									+ ")");
//					System.out.println(pre);
//					pre.executeUpdate();
//				}
//			}
			
			for(int i=0;i<130;i++){
				Random random = new Random();
				int a=random.nextInt(800);
				pre = con
				 .prepareStatement("update classroomoccupancy  set occupancy='是',duomeiti='是',purpose='上课'where oid="+a);
				 pre.executeUpdate();
				 System.out.println("here");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		try {
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
