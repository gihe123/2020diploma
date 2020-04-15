package com.textUser.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.textUser.bean.CaseBean;
import com.textUser.dao.txtEquivalentDao;
import com.util.Jacksonutil;
import com.util.UserUtil;

public class EquivalentMethodAction extends ActionSupport {

	/**
	 * 
	 */
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private 	HttpServletResponse response = ServletActionContext.getResponse();
	private txtEquivalentDao txtEquivalentDaoimpl = new com.textUser.dao.impl.txtEquivalentDaoimpl();
	private static final long serialVersionUID = 8730614045002757086L;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private  UserUtil userUtil =new UserUtil() ;
	
	private CaseBean  caseBean =new CaseBean();
	/**
	 * 
	 * 生成文本框的等价类方法
	 * @throws Exception 
	 */
	public void txtEquivalentMethod() 
	{
		Random random =new Random();
		Date date =new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now =dateFormat.format(date);	
		
		int isNum;int isChar;int isMark;int  minLength=0;
		int  maxLength=0;
		if(request.getParameter("minLength").trim()!=null)
			 minLength= Integer.valueOf(request.getParameter("minLength").trim());
		if(request.getParameter("maxlength").trim()!=null)
			maxLength =Integer.valueOf(request.getParameter("maxlength").trim());
		if(request.getParameter("isNum").equals("")||request.getParameter("isNum")==null)
		{
			isNum=0;
		}else{
			isNum =Integer.valueOf(request.getParameter("isNum").trim());
		}
		if(request.getParameter("isChar").equals("")||request.getParameter("isChar")==null)
		{
			isChar=0;
		}else{
			 isChar =Integer.valueOf(request.getParameter("isChar").trim());				
		}		
		if(request.getParameter("isMark").equals("")||request.getParameter("isMark")==null)			
		{
			isMark=0;
		}else{
		 isMark =Integer.valueOf(request.getParameter("isMark").trim());
		}
		
		int  belowMinLength=random.nextInt(minLength);
		int upperMaxLength =random.nextInt(4)+maxLength+1;
		List<CaseBean> resultList =new ArrayList<CaseBean>();
		
		do
		{	
			resultList.add(new CaseBean(null,userUtil.createByRandom(random.nextInt(maxLength-minLength+1)+minLength, isChar, isNum,isMark),now));					
		}while(random.nextInt(2) % 2 == 0);		
		resultList.add(new CaseBean(null,userUtil.createByRandom(minLength, isChar, isNum,isMark),now));
		resultList.add(new  CaseBean(null,userUtil.createByRandom(maxLength, isChar, isNum,isMark),now));	
		//无效等价类
		do
		{	
			isChar=random.nextInt(2) % 2 == 0 ? 1:0;
			isNum=random.nextInt(2) % 2 == 0 ? 1:0;
			isMark=random.nextInt(2) % 2 == 0 ? 1:0;
			resultList.add(new CaseBean(null,userUtil.createByRandom(belowMinLength,  isChar, isNum,isMark),now));
			resultList.add(new CaseBean(null,userUtil.createByRandom(upperMaxLength,  isChar, isNum,isMark),now));	
			resultList.add(new CaseBean(null,userUtil.createByRandom(random.nextInt(maxLength-minLength+1)+minLength, isChar, isNum,isMark),now));

		}while(random.nextInt(2) % 2 == 0);
		Set<CaseBean> set =new HashSet<CaseBean>();
		set.addAll(resultList);
		resultList.clear();
		resultList.addAll(set);
		Map<String, Object> m =new HashMap<String, Object>();						
		m.put("root", resultList);		
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
	/**
	 * 
	 * 更新或者保存
	 * 
	 * @throws Exception
	 */
	public void saveOrUpdateEquivalent() throws Exception{
		String[] str =request.getParameterValues("record");
		
		List<CaseBean>list= Jacksonutil.jsontoObject(str, CaseBean.class);
		if(list.get(0).getFlag().indexOf("txtequivalent")==-1)
		{
			for(int i=0;i< list.size();i++)
			{
				String flag =list.get(i).getFlag();
				list.get(i).setFlag("txtequivalent"+flag);
			}
		}
		List<Long> ids=txtEquivalentDaoimpl.saveOrUpdateEquivalentCase(list);
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("pragma", "no-cache");
	    List<CaseBean> result =new ArrayList<CaseBean>();
	    for(Long id : ids)
	    {
	    	result.add(new CaseBean(id));
	    }
	    response.setHeader("cache-control", "no-cache");
	    Map<String, Object> m =new HashMap<String, Object>();
	    m.put("root", result);
	    m.put("totalcount",result.size());
	    String s = Jacksonutil.objectToJson(m);
	    System.out.println(s);
	    if(ids.size()!=0)
	    {
	    	response.getWriter().write(s);
	    }
		//如果全部是增加的 则没有时间 要newdate
		//如果全部是增加的 则没有时间 要newdate
	}
	/**
	 * 
	 * 
	 * 导出excel
	 * 
	 */
	public String outputEquivalentExcel() throws IOException{
		String record =request.getParameter("record");
		
		String[] titles={"用例"};
		String filename ="等价类法的用例生成表";
	
		response.setHeader("Content-type",
				"application/x-msdownload;charset=UTF-8");

		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(filename.getBytes("GBK"), "ISO-8859-1") + ".xls");
		OutputStream os = null;
		os = response.getOutputStream();
		txtEquivalentDaoimpl.outputExcel(os, filename, titles, "txtequivalent"+record);
		if (os != null) {
			os.close();
		}
		return null;				
	}
	/**
	 * 
	 * 删除行
	 * 
	 * 
	 */
	public void deleteRow()
	{
		String id =request.getParameter("id");
		txtEquivalentDaoimpl.deleteRow(id);
	}
	/**
	 * 
	 * 
	 * 查询usecase
	 * 
	 * 
	 */
	public void searchCase(){
		String flag =request.getParameter("flag");
		List<CaseBean> result =new ArrayList<CaseBean>();
		result =txtEquivalentDaoimpl.showCase(flag);
		response.setHeader("cache-control", "no-cache");
	    Map<String, Object> m =new HashMap<String, Object>();
	    m.put("root", result);
	    String s = Jacksonutil.objectToJson(m);
	    System.out.println(s);	   
	    try {
			response.getWriter().write(s);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		
	}
	/**
	 * 
	 * 
	 * showCombobox
	 *  
	 */
	public void showCombobox()
	{
	   
		List<CaseBean> result =new ArrayList<CaseBean>();
		result=txtEquivalentDaoimpl.showCombobox();
		List<CaseBean> result1 =new ArrayList<CaseBean>();
		List<CaseBean> result2 =new ArrayList<CaseBean>();
		
		result1.addAll(result);
		
		for(CaseBean caseBean: result1)
		{
			if(caseBean.getFlag().indexOf("txtequivalent")!=-1)
			{
				result2.add(caseBean);
			}
		}
		Set<Map<String, Object>> list =new HashSet<Map<String,Object>>();	
		List<CaseBean> result3 =new ArrayList<CaseBean>();
		result3.addAll(result2);
		int count=0;
		for(int i=0;i<result3.size();i++)
		{
			Map<String, Object> m =new HashMap<String, Object>();
			String flag = result3.get(i).getFlag().replace("txtequivalent", "");
			m.put("flag", flag);
			m.put("date", flag);			
			list.add(m);
		}
		response.setHeader("cache-control", "no-cache");
	    Map<String, Object> m =new HashMap<String, Object>();
	    m.put("root", list);
	    String s = Jacksonutil.objectToJson(m);
	    try {
			response.getWriter().write(s);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
