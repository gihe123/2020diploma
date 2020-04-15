package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;
import com.bean.Vocabulary;
import com.tools.ConnDB;

public class VocabularyDAO {
	private ConnDB connDB = new ConnDB();
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
		
	public VocabularyDAO(){
		super();
	}
	
	public ArrayList<Vocabulary> getVacabulary() throws SQLException{
		ArrayList<Vocabulary> vacabulary =null;
		conn=connDB.getConnection();
		String sql = "SELECT * FROM t_vocabulary"; 
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs != null){
			vacabulary=new ArrayList<Vocabulary>();
			while(rs.next()){
				Vocabulary vcl=new Vocabulary();
				vcl.setId(rs.getInt(1));
				vcl.setAbbreviation(rs.getString(2));
				vcl.setFullWords(rs.getString(3));
				vcl.setSpecificMeanning(rs.getString(4));
				vcl.setLemmaSummary(rs.getString(5));
				vcl.setAddress(rs.getString(6));
				vcl.setPriority(rs.getLong(7));
				//System.out.println(vcl.toString());
				
				vacabulary.add(vcl);
				
			}
		}
		//System.out.println("1次查询");
		close();
		return vacabulary;
	}
	
	public ArrayList<Vocabulary> search(String key) throws SQLException{
		ArrayList<Vocabulary> vacabulary =null;
		conn=connDB.getConnection();
		String sql = "SELECT * FROM t_vocabulary WHERE abbreviation LIKE '%"+key+"%' or fullwords LIKE '%"+key+"%' or fullwords LIKE '% "+key+"%' or specificmeaning LIKE '%"+key+"%' ORDER BY priority DESC"; 
		
		System.out.println(sql);
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs != null){
			vacabulary=new ArrayList<Vocabulary>();
			while(rs.next()){
				Vocabulary vcl=new Vocabulary();
				vcl.setId(rs.getInt(1));
				vcl.setAbbreviation(rs.getString(2));
				vcl.setFullWords(rs.getString(3));
				vcl.setSpecificMeanning(rs.getString(4));
				vcl.setLemmaSummary(rs.getString(5));
				vcl.setAddress(rs.getString(6));
				vcl.setPriority(rs.getLong(7));
				vcl.setRemark(rs.getString(8));
				//System.out.println(vcl.toString());
				vacabulary.add(vcl);
				
			}
		}
		System.out.println("查询成功");
		close();
		return vacabulary;
	}
	
	public int saveVocabulary(Vocabulary vocabulary) throws SQLException {

		conn=connDB.getConnection();
		String sql = "insert into t_vocabulary(abbreviation, fullWords,specificmeaning,lemmasummary,priority,remark) values(?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1,vocabulary.getAbbreviation());
		ps.setString(2,vocabulary.getFullWords());
		ps.setString(3,vocabulary.getSpecificMeanning());
		ps.setString(4,vocabulary.getLemmaSummary());
		ps.setInt(5,1);//新添加的优先级默认为1
		ps.setString(6,vocabulary.getRemark());
		int result = ps.executeUpdate();
		
		close();
		return result;	
	}
	
	public int delete(int id) throws SQLException{
		conn=connDB.getConnection();
		String sql="DELETE FROM t_vocabulary where id="+id;
		ps = conn.prepareStatement(sql);
		int result = ps.executeUpdate();
		
		close();
		return result;
		
	}
	public int modify(Vocabulary vocabulary) throws SQLException{
		conn=connDB.getConnection();
		int result=0;
		String sql = "update t_vocabulary set abbreviation=?, fullwords=?,specificmeaning=?,lemmasummary=?,remark=? where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,vocabulary.getAbbreviation());
			ps.setString(2,vocabulary.getFullWords());
			ps.setString(3,vocabulary.getSpecificMeanning());
			ps.setString(4,vocabulary.getLemmaSummary());
			ps.setString(5,vocabulary.getRemark());
			ps.setInt(6,vocabulary.getId());
			result = ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
