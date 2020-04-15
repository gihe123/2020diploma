package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Vocabulary;
import com.bean.Feedback;
import com.tools.ConnDB;

public class FeedbackDAO {
	private ConnDB connDB = new ConnDB();
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	public FeedbackDAO() {
		super();
	}
	
	public ArrayList<Feedback> getFeedback() throws SQLException{
		ArrayList<Feedback> feedbacks=null;
		conn=connDB.getConnection();
		//String sql = "SELECT * FROM t_vocabulary WHERE abbreviation LIKE '%"+key+"%' or fullwords LIKE '%"+key+"%' or specificmeaning LIKE '%"+key+"%'"; 
		String sql="SELECT * FROM t_feedback";
		System.out.println(sql);
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs != null){
			feedbacks=new ArrayList<Feedback>();
			
			while(rs.next()){
				Feedback feedback=new Feedback();
				feedback.setF_id(rs.getInt(1));
				feedback.setV_id(rs.getInt(2));
				feedback.setF_abbreviation(rs.getString(3));
				feedback.setF_fullWords(rs.getString(4));
				feedback.setF_specificMeanning(rs.getString(5));
				feedback.setF_lemmaSummary(rs.getString(6));
				feedback.setF_username(rs.getString(7));
				feedback.setF_remark(rs.getString(8));
				//System.out.println(vcl.toString());
				feedbacks.add(feedback);
				
			}
		}
		System.out.println("1次查询");
		close();
		return feedbacks;
	}
	public int UserAdd(Feedback feedback) throws SQLException{
		conn=connDB.getConnection();
		String sql="insert into t_feedback(v_id,f_abbreviation,f_fullwords,f_specificmeaning,f_lemmasummary,f_username,f_remark) values(?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1,0);
		ps.setString(2, feedback.getF_abbreviation());
		ps.setString(3, feedback.getF_fullWords());
		ps.setString(4, feedback.getF_specificMeanning());
		ps.setString(5, feedback.getF_lemmaSummary());
		ps.setString(6, feedback.getF_username());
		ps.setString(7, feedback.getF_remark());
		
		int result = ps.executeUpdate();
		
		close();
		return result;	
		
	}
	
	public int deleteFeedback(int id) throws SQLException{
		conn=connDB.getConnection();
		String sql="DELETE FROM t_feedback where f_id="+id;
		ps = conn.prepareStatement(sql);
		int result = ps.executeUpdate();
		
		close();
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
