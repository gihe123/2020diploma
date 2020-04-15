package com.textUser.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.textUser.bean.CaseBean;
import com.textUser.bean.UsefulCaseBean;
import com.textUser.dao.UsefulcaseDao;
import com.util.Jacksonutil;
import com.util.UserUtil;

public class UsefulCaseAction  extends ActionSupport{

	private  HttpServletRequest request = ServletActionContext.getRequest();
	private 	HttpServletResponse response = ServletActionContext.getResponse();
	private UsefulcaseDao usefulcaseDaoimpl = new com.textUser.dao.impl.UsefulcaseDaoimpl();
	private static final long serialVersionUID = 8730614045002757086L;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private  UserUtil userUtil =new UserUtil() ;
	
	private CaseBean  caseBean =new CaseBean();
	/**
	 * 
	 * 生成组合测试方法
	 * @throws Exception 
	 * @throws Exception 
	 */
	public void showUsefulcaseList() throws Exception 
	{
		String msg="";
		if(request.getParameter("msg")==null){
			msg="";
		}else {
			msg=request.getParameter("msg");
		}
		List<UsefulCaseBean> list =usefulcaseDaoimpl.showUsefulcaseList(msg);
		Map<String, Object> m =new HashMap<String, Object>();
		m.put("root", list);		
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("pragma", "no-cache");
	    response.setHeader("cache-control", "no-cache");
	    String s =Jacksonutil.objectToJson(m);
	    response.getWriter().write(s);
		System.out.println(s);
	}
	/**
	 * 
	 * 展示usecase具体信息
	 * @throws Exception 
	 * 
	 * 
	 */
	public void showUsefulcaseDetail() throws Exception
	{
		String flag =request.getParameter("flag");
		List<CaseBean> list =usefulcaseDaoimpl.showUsefulcaseDetail(flag);
		Map<String, Object>m =new HashMap<String, Object>();
		m.put("root", list);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("pragma", "no-cache");
	    response.setHeader("cache-control", "no-cache");
	    String s =Jacksonutil.objectToJson(m);
	    System.out.println(s);
	    response.getWriter().write(s);
	}
	/**
	 * 
	 * 更新或者保存
	 * 
	 * @throws Exception
	 */
	public void saveOrUpdateUsefulCase() throws Exception{
		String[] str =request.getParameterValues("record");
		String minlength="";
		String maxlength="";
		String ischar="";
		String isnum="";
		String ismark="";	
		StringBuffer condition =new StringBuffer();
		String method ="";
		
		if(request.getParameter("maxlength")!=null&&request.getParameter("minlength")!=null&&request.getParameter("ismark")!=null&&request.getParameter("isnum")!=null&&request.getParameter("ischar")!=null){
			ismark=request.getParameter("ismark");
			ischar =request.getParameter("ischar");
			isnum =request.getParameter("isnum");
			condition.append("条件：");
			if(isnum.equals("1"))
			{
				condition.append(" 存在数字");						
			}else{
				condition.append(" 不存在数字");
			}
			if(ischar.equals("1"))
			{
				condition.append(" 存在字母");						
			}else{
				condition.append(" 不存在字母");
			}if(ismark.equals("1"))
			{
				condition.append(" 存在特殊符号");						
			}else{
				condition.append(" 不存在特殊符号");
			}
			maxlength=request.getParameter("maxlength");
			condition.append(" 最大长度为:"+maxlength);
			minlength=request.getParameter("minlength");
			condition.append(" 最小长度为:"+minlength);
		}else{
			condition.append("条件：无");
		}
		String flag = request.getParameter("flag");
		String []tmp =flag.split(",");
		if(tmp[1].indexOf(tmp[0])!=-1)
		{
			tmp[1] =tmp[1].replace(tmp[0], "");
			flag =tmp[0]+","+tmp[1];
			condition.setLength(0);
			condition.append("历史记录");			
		}
		String method1[]={"txtboundary","comboboundary","txtequivalent","doublecombined","thirdcombined","combined","comboequivalent"};
		String method2[]={"文本框的边界法","下拉框的边界法","文本框的等价类法","相邻参数两两组合组合测试法","相邻参数三三组合测试法","全组合测试法","下拉框的等价类法"};
		for(int i=0;i<method1.length;i++){
			if(method1[i].equals(tmp[0])){
				method=method2[i];
				break;
			}
		}
		/*if(tmp[0].equals("txtboundary")){
			method="文本框的边界法";
		}else if(tmp[0].equals("comboboundary")){
			method="下拉框的边界法";
		}else if(tmp[0].equals("txtequivalent")){
			method="文本框的等价类法";
		}else if(tmp[0].equals("doublecombined")){
			method="对偶组合测试法";
		}else if(tmp[0].equals("thirdcombined")){
			method="三三组合测试法";
		}else if(tmp[0].equals("combined")){
			method="全组合测试法";
		}else{
			method="下拉框的等价类法";
		}*/
		flag =flag.replace(",", "");
		UsefulCaseBean usefulCaseBean =new  UsefulCaseBean(null, method, flag, tmp[1], condition.toString());
		String msg = usefulcaseDaoimpl.saveUsefulcase(usefulCaseBean);	
		Map<String, Object> m =new HashMap<String, Object>();
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("pragma", "no-cache");
	    response.setHeader("cache-control", "no-cache");
		if(msg!=null){
			
			m.put("msg","exit");		
			
		}else{
			m.put("msg","");
		}				
	    String s =Jacksonutil.objectToJson(m);
	    response.getWriter().write(s);
		System.out.println(s);

	}
	/**
	 * 
	 * 
	 * 导出excel
	 * 
	 */
	public String outputUsefulcaseExcel() throws IOException{
		String record =request.getParameter("record");
	
		String[] titles={"用例"};
		String filename ="用例生成表";
	
		response.setHeader("Content-type",
				"application/x-msdownload;charset=UTF-8");

		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(filename.getBytes("GBK"), "ISO-8859-1") + ".xls");
		OutputStream os = null;
		os = response.getOutputStream();
		usefulcaseDaoimpl.outputExcel(os, filename, titles, record);
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
		usefulcaseDaoimpl.deleteRow(id);
	}
	/**
	 * 模糊查询联想查询
	 * 
	 * 
	 */
	public void showSearchResult(){
		String msg="";
		if(request.getParameter("query")==null){
			msg="";
			return;
		}else {
			msg=request.getParameter("query");
		}
		List<Map<String,Object>> list =usefulcaseDaoimpl.showsearchList(msg);
		Map<String, Object> m =new HashMap<String, Object>();
		m.put("root", list);		
		response.setContentType("application/json;charset=UTF-8");
	    response.setHeader("pragma", "no-cache");
	    response.setHeader("cache-control", "no-cache");
	    String s =Jacksonutil.objectToJson(m);
	    try {
			response.getWriter().write(s);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(s);
	}


	

}
