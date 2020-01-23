package com.priyanka.demorest;
import java.sql.*;
import java.util.*;
public class portalRepo {
	Connection con;
	public portalRepo(){
		try {
			getClass().forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public List<Record> getAllRecord(){
		List<Record> recList = new ArrayList<Record>();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from recorduser");
		while(rs.next()) {
			Record rec = new Record();
			rec.setCustomerId(rs.getString(1));
			rec.setCustomerName(rs.getString(2));
			rec.setPlace(rs.getString(3));
			rec.setPhoneNo(rs.getString(4));
			rec.setUsername(rs.getString(5));
			rec.setPassword(rs.getString(6));
			recList.add(rec);
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return recList;
	}
	public boolean isAuthenticated(String userName,String Password) {
		String actualPass;
		try {
		PreparedStatement st = con.prepareStatement("select password from recorduser where userName = ?");
		st.setString(1, userName);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			actualPass = rs.getString("password");
			if(actualPass.equals(Password)) {
				return true;
			}
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean adminAuthenticated(String userName,String Password) {
		String actualPass;
		try {
		PreparedStatement st = con.prepareStatement("select password from recordadmin where userName = ?");
		st.setString(1, userName);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			actualPass = rs.getString("password");
			if(actualPass.equals(Password)) {
				return true;
			}
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public Record getUserRecord(String custId) {
		Record al = new Record();
		try {
			PreparedStatement st = con.prepareStatement("select * from recorduser where customerId = ?");
			st.setString(1, custId);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				al.setCustomerId(rs.getString(1));
				al.setCustomerName(rs.getString(2));
				al.setPlace(rs.getString(3));
				al.setPhoneNo(rs.getString(4));
				al.setUsername(rs.getString(5));
				al.setPassword(rs.getString(6));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return al;
	}
	public boolean deleteUserRecord(String custId) {
		try {
			PreparedStatement st = con.prepareStatement("delete from recorduser where customerId = ?");
			st.setString(1, custId);
			if(st.executeUpdate()>0) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean isPresent(String custId) {
		try {
			PreparedStatement st = con.prepareStatement("select * from recorduser where customerId = ?");
			st.setString(1, custId);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean adminMod(String custId, String cname, String place, String phoneNo, String uname, String pass) {
		try {
			PreparedStatement st = con.prepareStatement("update recorduser set customerName = ?,place = ?,phoneNo = ?,userName = ?,password = ? where customerId = ?");
			st.setString(1, cname);
			st.setString(2, place);
			st.setString(3, phoneNo);
			st.setString(4, uname);
			st.setString(5, pass);
			st.setString(6, custId);
			if(st.executeUpdate()>0) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean isIdPresent(String uname) {
		try {
			PreparedStatement st = con.prepareStatement("select * from recorduser where userName = ?");
			st.setString(1, uname);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean userMod(String cname, String place, String phoneNo, String uname) {
		try {
			PreparedStatement st = con.prepareStatement("update recorduser set customerName = ?,place = ?,phoneNo = ? where userName = ?");
			st.setString(1, cname);
			st.setString(2, place);
			st.setString(3, phoneNo);
			st.setString(4, uname);
			if(st.executeUpdate()>0) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;

	}
	public Record viewUserRecord(String uname) {
		Record al = new Record();
		try {
			PreparedStatement st = con.prepareStatement("select * from recorduser where userName = ?");
			st.setString(1, uname);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				al.setCustomerId(rs.getString(1));
				al.setCustomerName(rs.getString(2));
				al.setPlace(rs.getString(3));
				al.setPhoneNo(rs.getString(4));
				al.setUsername(rs.getString(5));
				al.setPassword(rs.getString(6));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(al);
		return al;
	}
	public boolean createUser(String custId, String cname, String place, String phoneNo, String uname, String pass) {
		try {
			PreparedStatement st = con.prepareStatement("insert into recorduser values(customerId = ?,customerName = ?,place = ?,phoneNo = ?,userName = ?,password = ?)");
			st.setString(1, custId);
			st.setString(2, cname);
			st.setString(3, place);
			st.setString(4, phoneNo);
			st.setString(5, uname);
			st.setString(6, pass);
			//if(st.executeUpdate()>0) {
				System.out.println("yass!!");
				return true;
			//}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}

