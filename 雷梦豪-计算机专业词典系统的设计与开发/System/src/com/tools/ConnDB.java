package com.tools;

import java.sql.*;

public class ConnDB {
	private Connection conn=null;
	public final static String DRIVER="com.mysql.jdbc.Driver";
	public final static String URL="jdbc:mysql://localhost:3306/db_sys?useUnicode=true&characterEncoding=UTF-8";
	public final static String USER="root";
	public final static String PASS="root";
	
	public ConnDB(){
		
	}
	public Connection getConnection(){
		try{
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, USER, PASS);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(conn==null){
			System.out.println("数据库连接失败！");
		}
		return conn;
	}
	public void close() throws Exception{
		if(this.conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				throw e;
			}
		}
		
	}

}
