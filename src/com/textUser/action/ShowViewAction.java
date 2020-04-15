package com.textUser.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.textUser.bean.NodeBean;
import com.textUser.dao.GetMenuDao;
import com.textUser.dao.impl.GetMenuDaoimpl;
import com.util.Jacksonutil;
import com.opensymphony.xwork2.ActionSupport;


public class ShowViewAction extends ActionSupport {
	
	/**
	 * 
	 */
	private  GetMenuDao getMenuDao=new GetMenuDaoimpl();
	private static final long serialVersionUID = 5583524700181164652L;
	public String showUsefulCase(){
		return "usefulcase";
	}
	public String txtBoundaryMethod(){
		return "txtboundarymethod";
	}
	public String comboBoundaryMethod(){
		return "comboboundarymethod";
	}
	public String txtEquivalentMethod(){
		return "txtequivalentmethod";
		//ShowViewAction!txtEquivalentMethod.action
	}
	public String showCombinedMethod(){
		return "combinedmethod";
		//ShowViewAction!comboEquivalentMethod.action
	}
	public String showDoubleCombinedMethod()
	{
		return "doublecombinedmethod";
	}
	public String showThirdCombinedMethod(){
		return "thirdcombinedmethod";
	}
	public String comboEquivalentMethod(){
		return "comboequivalentmethod";
	}
	public void getTreeList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String node="";
		if(request.getParameter("node")!=null)
			 node =String.valueOf( request.getParameter("node"));
		
		String fatherid=" ";
		if(node!=null)
			fatherid=node;
		List<NodeBean> list =  getMenuDao.getMenu(fatherid);
		Map<String, Object> m =new HashMap<String, Object>();						
		m.put("tree", list);		
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("pragma", "no-cache");
	    response.setHeader("cache-control", "no-cache");
	    String str =Jacksonutil.objectToJson(m);
		System.out.println(str);
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
	}
}
