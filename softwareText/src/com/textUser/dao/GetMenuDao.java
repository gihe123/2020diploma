package com.textUser.dao;

import java.util.List;

import com.textUser.bean.NodeBean;


public interface GetMenuDao {
	public List<NodeBean> getMenu(String fatherid);

}
