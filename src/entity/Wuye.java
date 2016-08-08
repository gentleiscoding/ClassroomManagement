package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConn;

public class Wuye {
	int wid;
	String password;
	String wname;
	int pnumber;

	public Wuye(int wid, String password, String wname, int pnumber) {
		this.password = password;
		this.wid = wid;
		this.pnumber = pnumber;
		this.wname = wname;
	}

	public Wuye() {
	}

	public int getwid() {
		return wid;
	}

	public void setwid(int wid) {
		this.wid = wid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getwname() {
		return wname;
	}

	public void setwname(String wname) {
		this.wname = wname;
	}

	public int getPnumber() {
		return pnumber;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

}
