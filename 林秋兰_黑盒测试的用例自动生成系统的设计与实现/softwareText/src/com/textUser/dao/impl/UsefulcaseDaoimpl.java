package com.textUser.dao.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.hibernate.HibernateSessionFactory;
import com.textUser.bean.CaseBean;
import com.textUser.bean.UsefulCaseBean;
import com.textUser.dao.UsefulcaseDao;
import com.util.UserUtil;

public class UsefulcaseDaoimpl implements UsefulcaseDao {


		private  UserUtil userutil =new UserUtil();
	
		@Override
		public List<UsefulCaseBean> showUsefulcaseList(String msg){
			
			Session session =HibernateSessionFactory.getSession();
			session.beginTransaction();
			StringBuffer sql =new StringBuffer();
			sql.append("select * from t_usefulcaselist");
			List<UsefulCaseBean> list  =new ArrayList<UsefulCaseBean>();
			if(msg.trim()!=""){
				if(msg.indexOf("/")!=-1||msg.indexOf(":")!=-1||msg.matches("^[0-9]+$")==true){
					if(msg.indexOf(":")!=-1){
						sql.append(" where dates  = ?");				
						 list =(List<UsefulCaseBean>) session.createSQLQuery(sql.toString()).addEntity(UsefulCaseBean.class).setParameter(0, msg).list();
						
					}else{
						sql.append(" where dates like \"%"+msg+"%\"");				
						 list =(List<UsefulCaseBean>) session.createSQLQuery(sql.toString()).addEntity(UsefulCaseBean.class).list();
						
					}
					
				}else{
					sql.append(" where method like\"%"+msg+"%\"");
					 list =(List<UsefulCaseBean>) session.createSQLQuery(sql.toString()).addEntity(UsefulCaseBean.class).list();						
				}
			}else{
				 list =(List<UsefulCaseBean>) session.createSQLQuery(sql.toString()).addEntity(UsefulCaseBean.class).list();					
			}
			session.getTransaction().commit();
			session.close();
			return list;
		}
	/**
	 * 
	 * 
	 * 删除选中行
	 */
		@Override
		public void deleteRow(String id) {
			// TODO 自动生成的方法存根
			Session session=HibernateSessionFactory.getSession();		
			session.beginTransaction();
			try {
				UsefulCaseBean casebean= (UsefulCaseBean) session.createSQLQuery("select *  FROM t_usefulcaselist where id=?").addEntity(UsefulCaseBean.class).setString(1, id).uniqueResult();
				session.delete(casebean);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.getTransaction().rollback();
				return ;
			}
			session.getTransaction().commit();
			session.close();
			
		}
		/**
		 * 
		 * 
		 * 从数据库中导出生成excel
		 */
	@Override
	public void outputExcel(OutputStream os, String filename, String[] titles,String date) {
		// TODO 自动生成的方法存根
		List<CaseBean> list =new ArrayList<CaseBean>();
		Session session=HibernateSessionFactory.getSession();		
		session.beginTransaction();
		try {
			String sql ="select * from t_usecase where flag=?";
			//设置类型
			list=session.createSQLQuery(sql).addEntity(CaseBean.class).setString(1, date).list();
		} catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
		userutil.outputCombinedExcel(os, list, filename, titles);
		session.getTransaction().commit();
		session.close();

	}
	/**
	 * 
	 * 保存
	 * 
	 */
		@Override
		public String saveUsefulcase(UsefulCaseBean usefulcaseben) {
			// TODO 自动生成的方法存根
			Session session=HibernateSessionFactory.getSession();		
			session.beginTransaction();
			
			UsefulCaseBean tmp =(UsefulCaseBean )session.createSQLQuery("select * from t_usefulcaselist where flag =?").addEntity(UsefulCaseBean.class).setString(1, usefulcaseben.getFlag()).uniqueResult();
			if(tmp ==null){
				session.save(usefulcaseben);
				session.getTransaction().commit();
				session.close();
				return null;
			}			
			session.getTransaction().commit();
			session.close();
			return "exit";
		}
	@Override
	public List<CaseBean> showUsefulcaseDetail(String flag) {
		// TODO 自动生成的方法存根
		Session session =HibernateSessionFactory.getSession();
		session.beginTransaction();
		String sql ="select * from t_usecase where flag=?";
		List<CaseBean> list=(List<CaseBean>) session.createSQLQuery(sql).addEntity(CaseBean.class).setString(1, flag).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	@Override
	public List<Map<String, Object>> showsearchList(String msg) {
		// TODO 自动生成的方法存根
		Session session =HibernateSessionFactory.getSession();
		session.beginTransaction();
		List<UsefulCaseBean> list =new ArrayList<UsefulCaseBean>();
		StringBuffer sql =new StringBuffer();
		sql.append("select * from t_usefulcaselist");
		if(msg.trim()!=""){
			if(msg.indexOf("/")!=-1||msg.indexOf(":")!=-1||msg.matches("^[0-9]+$")==true){
				if(msg.indexOf(":")!=-1){
					sql.append(" where dates =?");				
					 list =(List<UsefulCaseBean>) session.createSQLQuery(sql.toString()).addEntity(UsefulCaseBean.class).setString(0, msg).list();
					
				}else{
					sql.append(" where dates like \"%"+msg+"%\"");				
					 list =(List<UsefulCaseBean>) session.createSQLQuery(sql.toString()).addEntity(UsefulCaseBean.class).list();
					
				}
				
			}else{
				sql.append(" where method like\"%"+msg+"%\"");
				 list =(List<UsefulCaseBean>) session.createSQLQuery(sql.toString()).addEntity(UsefulCaseBean.class).list();
					
			}
		}
		session.getTransaction().commit();
		session.close();
		List<Map<String, Object>> resultlist =new ArrayList<Map<String, Object>>();
		if(msg.indexOf("/")!=-1||msg.indexOf(":")!=-1||msg.matches("^[0-9]+$")==true){
			for(UsefulCaseBean bean : list){
				Map<String, Object> map =new HashMap<String, Object>();
				map.put("msg", bean.getDates());
				resultlist.add(map);
			}
		}else{
			for(UsefulCaseBean bean : list){
				Map<String, Object> map =new HashMap<String, Object>();
				map.put("msg", bean.getMethod());				
				resultlist.add(map);
			}
		}
		
		return resultlist;
		
	}

	
	


}
