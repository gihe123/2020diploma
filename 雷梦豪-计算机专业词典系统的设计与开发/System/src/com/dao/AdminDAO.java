package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Admin;
import com.tools.ConnDB;

public class AdminDAO {
	private ConnDB connDB = new ConnDB();
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	public AdminDAO() {
		super();
	}
	public boolean isValid(Admin admin) {
		boolean result = false;
		conn = connDB.getConnection();
		
		String sql = "select username, password from t_admin where username=? and password=?";
		try {	         
			ps = conn.prepareStatement(sql);
			ps.setString(1,admin.getUsername());
			ps.setString(2, admin.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()) result = true;
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;	
		
	}
	
	public void close() throws SQLException{
		if(rs!=null)
			rs.close();
		if(ps!=null)
			ps.close();
		try {
			connDB.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
