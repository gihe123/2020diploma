package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.User;
import com.tools.ConnDB;

public class UserDAO {
	private ConnDB connDB = new ConnDB();
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	public UserDAO() {
		super();
	}
	
	public int isValid(User user) {
		boolean result = false;
		int id = 0;
		conn = connDB.getConnection();
		
		String sql = "select id, username, password from t_user where username=? and password=?";
		try {	         
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
				id=rs.getInt(1);
				}
			modify();
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;	
		
	}
	
	public int saveUser(User user) throws SQLException {
		conn = connDB.getConnection();
		
		String sql = "insert into t_user(username, password) values(?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1,user.getUsername());
		ps.setString(2,user.getPassword());
		int result = ps.executeUpdate();
		
		modify();
		return result;	
			
	}
	//修改密码
	public int modifyPwd(User user)throws SQLException {
		conn = connDB.getConnection();
		String sql = "UPDATE t_user SET password=? WHERE id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1,user.getPassword());
		ps.setInt(2,user.getId());
		int result = ps.executeUpdate();
		
		modify();
		return result;

	}
	public void modify() throws SQLException{
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
