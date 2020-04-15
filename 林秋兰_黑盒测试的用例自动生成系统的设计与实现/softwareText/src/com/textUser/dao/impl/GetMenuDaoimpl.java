package com.textUser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.hibernate.HibernateSessionFactory;
import com.textUser.bean.NodeBean;
import com.textUser.dao.GetMenuDao;

public class GetMenuDaoimpl implements GetMenuDao {
	
	

	@Override
	public List<NodeBean> getMenu( String id) {
		// TODO 自动生成的方法存根
		List<NodeBean> list =new ArrayList<NodeBean>();
		Session session=HibernateSessionFactory.getSession();
		session.beginTransaction();
		try {
			String hql ="from NodeBean where fatherid="+id;
			System.out.println(hql);
			list = session.createQuery(hql).list();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();	
		}
		
		
		return list;
	}

}
