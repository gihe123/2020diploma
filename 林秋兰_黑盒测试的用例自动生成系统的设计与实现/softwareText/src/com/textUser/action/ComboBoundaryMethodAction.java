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

import com.opensymphony.xwork2.ActionSupport;
import com.textUser.bean.CaseBean;
import com.textUser.dao.ComboBoundaryDao;
import com.textUser.dao.impl.ComboBoundaryDaoimpl;
import com.util.FileUtil;
import com.util.Jacksonutil;

public class ComboBoundaryMethodAction extends ActionSupport {
	ComboBoundaryDao comboBoundaryDaoimpl =new ComboBoundaryDaoimpl();
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private 	HttpServletResponse response = ServletActionContext.getResponse();
	Random random =new Random();
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
		comboBoundaryDaoimpl.outputExcel(os, filename, titles, "comboboundary"+record);
		if (os != null) {
			os.close();
		}
		return null;				
	}
	public void comboBoundaryMethod() throws Exception
	{
		Date date =new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now =dateFormat.format(date);	
		String msg =request.getParameter("msg");
		String[] msgs =msg.split(",|，|、");
		Set<CaseBean> resultlist =new HashSet<CaseBean>();
		if(msgs.length>=5){
			resultlist.add(new CaseBean(null, msgs[0], now) );
			resultlist.add(new CaseBean(null, msgs[1], now));
			resultlist.add(new CaseBean(null, msgs[random.nextInt(msgs.length-4)+2], now));		
			resultlist.add(new CaseBean(null, msgs[msgs.length-2], now));
			resultlist.add(new CaseBean(null, msgs[msgs.length-1], now)); 
		}else{
			resultlist.add(new CaseBean(null, msgs[0], now) );
			if(msgs.length!=1){
				resultlist.add(new CaseBean(null, msgs[msgs.length-1], now)); 
				resultlist.add(new CaseBean(null, msgs[1], now));
				resultlist.add(new CaseBean(null, msgs[msgs.length-2], now));
			}
		}
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("cache-control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> m =new HashMap<String, Object>();
		m.put("root", resultlist);
		String s = Jacksonutil.objectToJson(m);
		response.getWriter().write(s);  
	}
	public void searchCase(){
		String flag =request.getParameter("flag");
		List<CaseBean> result =new ArrayList<CaseBean>();
		result =comboBoundaryDaoimpl.showCase(flag);
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
	
	public void saveOrUpdateBoundary() throws Exception
	{
		String[] str =request.getParameterValues("record");
		
		List<CaseBean>list= Jacksonutil.jsontoObject(str, CaseBean.class);
		if(list.get(0).getFlag().indexOf("comboboundary")==-1)
		{
			for(int i=0;i< list.size();i++)
			{
				String flag =list.get(i).getFlag();
				list.get(i).setFlag("comboboundary"+flag);
			}
		}
		List<Long> ids=comboBoundaryDaoimpl.saveOrUpdateBoundaryCase(list);
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
	}

	
	
	/**
	 * 
	 * 
	 * 上传文件
	 * @throws Exception 
	 */
	private String uploadFileName;
	private File upload;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void impDataExcel() throws Exception{
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		FileUtil fileUtil=new FileUtil();
		if (uploadFileName != null && !uploadFileName.equals("")){
			 //文件上传路径
			 String realpath = servletContext.getRealPath("/fileTemp");
			 String realName = uploadFileName;
			 String encoding = "GBK";
			 if (upload != null)
				{
					File savedir = new File(realpath);
					if (!savedir.exists()){
						savedir.mkdirs();
					}
					String tou = "TempDataExcel"+new Random().nextLong(); // 服务器上文件生成到什么地方;
					String wei = this.uploadFileName.substring(this.uploadFileName.indexOf("."));
					this.uploadFileName = tou + wei;
					File savefile = new File(realpath, this.uploadFileName);
					// 把要上传的文件复制到服务器指定的目录上
					FileUtil.copy(upload, savefile);
					Long fileLength =savefile.length();
					 byte[] filecontent = new byte[fileLength.intValue()]; 
				     FileInputStream in;
					try {
						in = new FileInputStream(savefile);
						in.read(filecontent); 
						in.close();
						System.out.println(new String(filecontent, encoding));
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
			         
			     
					
					//删除上传的文件
					savefile.delete();
					upload.delete();
					String context =new String(filecontent, encoding);
					response.setContentType("text/html;charset=UTF-8");
					response.setHeader("pragma", "no-cache");
					response.setHeader("cache-control", "no-cache");
					PrintWriter out = response.getWriter();
					Map<String, Object> map = new HashMap<String, Object>();
					//表单提交判断执行success方法还是failure方法是根据后台传递success是true或者false决定的
					if(context==null||context=="")
					{
						map.put("success", false);
					}else{
						map.put("success", true);
					}			
					map.put("msg", context);
					String json = Jacksonutil.objectToJson(map);
					out.print(json);
					out.flush();
					
				}
		 }
	}
	public void showCombobox()
	{
	   
		List<CaseBean> result =new ArrayList<CaseBean>();
		result=comboBoundaryDaoimpl.showCombobox();
		List<CaseBean> result1 =new ArrayList<CaseBean>();
		List<CaseBean> result2 =new ArrayList<CaseBean>();
		
		result1.addAll(result);
		
		for(CaseBean caseBean: result1)
		{
			if(caseBean.getFlag().indexOf("comboboundary")!=-1)
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
			String flag = result3.get(i).getFlag().replace("comboboundary", "");
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
