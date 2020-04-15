package com.textUser.action;

import java.io.IOException;
import java.io.OutputStream;
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

import com.opensymphony.xwork2.ActionSupport;
import com.textUser.bean.CaseBean;
import com.textUser.dao.BoundaryDao;
import com.textUser.dao.impl.BoundaryDaoimpl;
import com.util.Jacksonutil;
import com.util.UserUtil;

public class BoundaryMethodAction  extends ActionSupport{


	/**
	 * 
	 */
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private 	HttpServletResponse response = ServletActionContext.getResponse();
	private BoundaryDao boundaryDaoimpl = new BoundaryDaoimpl();
	private static final long serialVersionUID = 8730614045002757086L;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private  UserUtil userUtil =ctx.getBean("userutil",UserUtil.class) ;
	
	private CaseBean  caseBean =new CaseBean();
	/**
	 * 
	 * 生成文本框的等价类方法
	 * @throws Exception 
	 */
	public void txtBoundaryMethod() 
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
		
		
		List<CaseBean> resultList =new ArrayList<CaseBean>();
		
		resultList.add(new CaseBean(null,userUtil.createByRandom(maxLength, isChar, isNum, isMark),now));					
		resultList.add(new CaseBean(null,userUtil.createByRandom(maxLength+1, isChar, isNum, isMark),now));					
		resultList.add(new CaseBean(null,userUtil.createByRandom(maxLength-1, isChar, isNum, isMark),now));					
		
		resultList.add(new CaseBean(null,userUtil.createByRandom(minLength, isChar, isNum, isMark),now));					
		resultList.add(new CaseBean(null,userUtil.createByRandom(minLength+1, isChar, isNum, isMark),now));					
		resultList.add(new CaseBean(null,userUtil.createByRandom(minLength-1, isChar, isNum, isMark),now));					
		resultList.add(new CaseBean(null,userUtil.createByRandom(random.nextInt(maxLength-minLength+1)+minLength, isChar, isNum, isMark),now));					
		
	
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
	public void saveOrUpdateBoundary() throws Exception{
		String[] str =request.getParameterValues("record");
		
		List<CaseBean>list= Jacksonutil.jsontoObject(str, CaseBean.class);
		if(list.get(0).getFlag().indexOf("txtboundary")==-1)
		{
			for(int i=0;i< list.size();i++)
			{
				String flag =list.get(i).getFlag();
				list.get(i).setFlag("txtboundary"+flag);
			}
		}
		List<Long> ids=boundaryDaoimpl.saveOrUpdateBoundaryCase(list);
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
	public String outputBoundaryExcel() throws IOException{
		String record =request.getParameter("record");
		String[] titles={"用例"};
		String filename ="边界法的用例生成表";
	
		response.setHeader("Content-type",
				"application/x-msdownload;charset=UTF-8");

		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(filename.getBytes("GBK"), "ISO-8859-1") + ".xls");
		OutputStream os = null;
		os = response.getOutputStream();
		boundaryDaoimpl.outputExcel(os, filename, titles, "txtboundary"+record);
		if (os != null) {
			os.close();
		}
		return null;				
	}
	public void deleteRow()
	{
		String id =request.getParameter("id");
		boundaryDaoimpl.deleteRow(id);
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
		result =boundaryDaoimpl.showCase(flag);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("cache-control", "no-cache");
		response.setCharacterEncoding("UTF-8");
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
		result=boundaryDaoimpl.showCombobox();
		List<CaseBean> result1 =new ArrayList<CaseBean>();
		List<CaseBean> result2 =new ArrayList<CaseBean>();
		
		result1.addAll(result);
		
		for(CaseBean caseBean: result1)
		{
			if(caseBean.getFlag().indexOf("txtboundary")!=-1)
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
			String flag = result3.get(i).getFlag().replace("txtboundary", "");
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
