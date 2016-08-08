package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConn;

public class Student {
	long sid;
	String password;
	String sname;
	int pnumber;
	String college;
	String classname;
	public Student(long sid,String password,String sname,int pnumber,String college,String classname ){
		this.classname=classname;
		this.password=password;
		this.college=college;
		this.sid=sid;
		this.sname=sname;
		this.pnumber=pnumber;
	}
	public Student() {
	}
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getPnumber() {
		return pnumber;
	}
	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	};
	
}
