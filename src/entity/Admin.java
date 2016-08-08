package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConn;

public class Admin {
	int aid;
	String password;
	String aname;
	int pnumber;

	public Admin(int aid,String password,String aname,int pnumber){
		this.password=password;
		this.aid=aid;
		this.aname=aname;
		this.pnumber=pnumber;
	}
	public Admin() {
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public int getPnumber() {
		return pnumber;
	}
	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}
	
}
