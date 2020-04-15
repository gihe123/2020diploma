package com.textUser.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;














import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.textUser.bean.CaseBean;
import com.textUser.dao.CombinedDao;
import com.util.FileUtil;
import com.util.Jacksonutil;
import com.util.UserUtil;

public class CombinedMethodAction extends ActionSupport {
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private 	HttpServletResponse response = ServletActionContext.getResponse();
	private CombinedDao combinedDaoimpl = new com.textUser.dao.impl.CombinedDaoimpl();
	private static final long serialVersionUID = 8730614045002757086L;
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private  UserUtil userUtil =new UserUtil() ;
	
	private CaseBean  caseBean =new CaseBean();
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
	/**
	 * 
	 * 生成组合测试方法
	 * @throws Exception 
	 */
	public void combinedMethod() 
	{
		Random random =new Random();
		Date date =new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String now =dateFormat.format(date);
		//条件的正则表达式list
		List<String> falseRegex =new ArrayList<String>();	
	//	A:阮，1\nB:123,1
		String msg =request.getParameter("msg");
		List<String[]> regexlist =new ArrayList<String[]>();
		String[] str1 =msg.split("\n");
		//分开冒号
		for(int i=0;i<str1.length;i++)
		{
			
			regexlist.add(str1[i].split(":|："));
		}
		List<String[]> regexlist1 =new ArrayList<String[]>();
		//分开逗号
		for(int i=0;i<regexlist.size();i++)
		{
			regexlist1.add(regexlist.get(i)[1].split(",|，"));
			
		}
		
		//存前台的数据
		List<List<String>> dataList =new ArrayList<List<String>>();
		for(int i=0;i<regexlist1.size();i++)
		{
			List<String> data =new ArrayList<String>();			
			for(int j=0;j<regexlist1.get(i).length;j++)
			{
				data.add(regexlist1.get(i)[j].trim());
			}
			dataList.add(data);
		}
		
		//存前台传的条件		
		List<String> selections=new ArrayList<String>();
		//分开selectstr\n
		//判断前台是否传递条件
		String selectStr = request.getParameter("selection");		
		if (selectStr.trim() != "") {
			String[] selectStrs = selectStr.split("\n");
			for (String str : selectStrs) {
				selections.add(str);
			}
			List<Map<String, Object>> selectMsg = new ArrayList<Map<String, Object>>();
			for (String s : selections) {
				HashMap<String, Object> selection = new HashMap<String, Object>();

				s = s.replaceAll("\\s{1,}", "");
				selection.put("if",
						s.substring(s.indexOf("[") + 1, s.indexOf("]")));
				s = s.replace("if[" + selection.get("if") + "]", "");
				if (s.indexOf("notin") == 0) {
					selection.put("ifcondition", "in");
					int index = Integer.valueOf((String) selection.get("if"));
					String str = s
							.substring(s.indexOf("{") + 1, s.indexOf("}"));
					String[] strs = str.split(",|，");
					List<String> ifNotInValues = new ArrayList<String>();
					ifNotInValues.addAll(dataList.get(index));
					for (String tmp : strs) {
						ifNotInValues.remove(tmp);
					}
					selection.put("ifValue", ifNotInValues);
					s = s.replace("notin{" + str + "}", "");
				} else if (s.indexOf("in") == 0) {
					selection.put("ifcondition", "in");
					String str = s
							.substring(s.indexOf("{") + 1, s.indexOf("}"));
					String[] strs = str.split(",|，");
					List<String> ifInValues = new ArrayList<String>();
					for (String tmp : strs) {
						ifInValues.add(tmp);
					}
					selection.put("ifValue", ifInValues);
					s = s.replace("in{" + str + "}", "");
				}
				selection.put("then",
						s.substring(s.indexOf("[") + 1, s.indexOf("]")));
				s = s.replace(
						s.substring(s.indexOf("then["), s.indexOf("]") + 1), "");
				if (s.indexOf("in") == 0) {

					selection.put("thencondition", "notin");
					int index = Integer.valueOf((String) selection.get("then"));
					String str = s
							.substring(s.indexOf("{") + 1, s.indexOf("}"));
					String[] strs = str.split(",|，");
					List<String> thenInValues = new ArrayList<String>();
					thenInValues.addAll(dataList.get(index));
					for (String tmp : strs) {
						thenInValues.remove(tmp);
					}
					selection.put("thenValue", thenInValues);
					s = s.replace("in{" + str + "}", "");
				} else if (s.indexOf("notin") == 0) {
					selection.put("thencondition", "notin");
					String str = s
							.substring(s.indexOf("{") + 1, s.indexOf("}"));
					String[] strs = str.split(",|，");
					List<String> thenNotInValues = new ArrayList<String>();
					for (String tmp : strs) {
						thenNotInValues.add(tmp);
					}
					selection.put("thenValue", thenNotInValues);
					s = s.replace("notin{" + str + "}", "");

				}
				selectMsg.add(selection);
			}
			// 这样才会按插入顺序输出
		
			//拼正则表达式
			for(Map<String, Object> map :selectMsg)
			{	
				String Regex ="";
				int index1 =(Integer.valueOf((String)map.get("if")));
				for(int i=0;i<index1;i++)
				{
					Regex+=".*,";
				}
				String ifvalue =String.valueOf( map.get("ifValue"));	
				List<String> ifValueList = new ArrayList<String>(); 
				if(ifvalue.indexOf("[")!=-1)
				{
					String str= ifvalue.substring(ifvalue.indexOf("[")+1, ifvalue.indexOf("]"));
					String[] values = str.split(",");
					for(int i=0;i<values.length;i++)
					{
						ifValueList.add(values[i]);
					}
				}else
				{
					ifValueList.add(ifvalue);
				}			
				// 赋值
				Regex+="(";
				for(int i=0;i<ifValueList.size();i++)
				{

					Regex+=ifValueList.get(i).trim();
					if(i!=ifValueList.size()-1)
					{
						Regex+="|";
					}
				}
				Regex+="),";
				int index2 =(Integer.valueOf((String)map.get("then")));
				for(int i=index1;i<index2-1;i++)
				{
					Regex+=".*,";
				}
				String thenvalue = String.valueOf( map.get("thenValue"));	
				List<String> thenValueList = new ArrayList<String>(); 
				if(thenvalue.indexOf("[")!=-1)
				{
					String str= thenvalue.substring(thenvalue.indexOf("[")+1, thenvalue.indexOf("]"));
					String[] values = str.split(",");
					for(int i=0;i<values.length;i++)
					{
						thenValueList.add(values[i]);
					}
				}else{
					thenValueList.add(thenvalue);
				}
				String thencondition =String.valueOf( map.get("thencondition"));	
				Regex+="(";
				for(int i=0;i<thenValueList.size();i++)
				{

					Regex+=thenValueList.get(i).trim();
					if(i!=thenValueList.size()-1)
					{
						Regex+="|";
					}
				}
				Regex+=")";
				//补齐末尾的正则			
				Regex+=".*";			
				falseRegex.add(Regex);
			}
			
			
		}
		//存储最后结果
		List<Map<String,Object>> resultList =new ArrayList<Map<String,Object>>();
		StringBuffer result1=new StringBuffer();
		//存去组合后的String 以逗号隔开
		List<String> resultlist =new ArrayList<String>();
		
		userUtil.deleteCaseByRegex(0,0, dataList, falseRegex, result1,resultlist);		
		//吧Stinglist 操作存入casebeanlist
		for(String str: resultlist)
		{
			Map<String, Object> m1 =new HashMap<String, Object>();	
			String []tmp =str.split(",|，");
			for(int i=0;i<tmp.length;i++){
				int j=i+1;
				m1.put("column"+j, tmp[i]);
			}
			m1.put("user", str);
			m1.put("flag", now);
			m1.put("tab",0);
			resultList.add(m1);
		}
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
	public void saveOrUpdateCombined() throws Exception{
		String[] str =request.getParameterValues("record");
		
		List<CaseBean>list= Jacksonutil.jsontoObject(str, CaseBean.class);
		if(list.get(0).getFlag().indexOf("combined")==-1)
		{
			for(int i=0;i< list.size();i++)
			{
				String flag =list.get(i).getFlag();
				list.get(i).setFlag("combined"+flag);
			}
		}
		List<Long> ids=combinedDaoimpl.saveOrUpdateCombinedCase(list);
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
	public String outputCombinedExcel() throws IOException{
		String record =request.getParameter("record");
		
		String[] titles={"用例"};
		String filename ="组合测试法的用例生成表";
	
		response.setHeader("Content-type",
				"application/x-msdownload;charset=UTF-8");

		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(filename.getBytes("GBK"), "ISO-8859-1") + ".xls");
		OutputStream os = null;
		os = response.getOutputStream();
		combinedDaoimpl.outputExcel(os, filename, titles, "combined"+record);
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
		combinedDaoimpl.deleteRow(id);
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
		result =combinedDaoimpl.showCase(flag);
		List<Map<String, Object>> resultList =new ArrayList<Map<String, Object>>();
		for(CaseBean casebean: result)
		{
			Map<String, Object> m1 =new HashMap<String, Object>();	
			String []tmp =casebean.getUser().split(",|，");
			for(int i=0;i<tmp.length;i++){
				int j=i+1;
				m1.put("column"+j, tmp[i]);
			}
			String Flag =casebean.getFlag();
			m1.put("user", casebean.getUser());
			m1.put("flag", Flag);
			m1.put("tab",1);
			resultList.add(m1);
		}
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
	public void searchCaseColumn(){
		String flag =request.getParameter("flag");
		List<CaseBean> result =new ArrayList<CaseBean>();
		result =combinedDaoimpl.showCase(flag);
		String s1[] =result.get(0).getUser().split(",");
		Map<String ,Object> sort = new HashMap<String, Object>();
		sort.put("header", "序号");
		sort.put("xtype", "rownumberer");
		sort.put("width","50");
		List<Map<String,Object>> column =new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> model =new ArrayList<Map<String,Object>>();
		column.add(sort);
		Map<String, Object> m =new HashMap<String, Object>();
		for(int i=0;i<s1.length;i++){
			 	int j =i+1;
				Map<String,Object> m1 =new HashMap<String, Object>();
				Map<String,Object> m2 =new HashMap<String, Object>();		
					m1.put("dataIndex", "column"+j);
					m1.put("header", "列"+j);
				//	m1.put("hidden", "false");
			//		m1.put("hiddeable", "true");
					m2.put("name", "column"+j);
					column.add(m1);
					model.add(m2);
		}
		Map<String,Object> m3 =new HashMap<String, Object>();
		Map<String,Object> m4 =new HashMap<String, Object>();
		Map<String,Object> m5 =new HashMap<String, Object>();
		Map<String,Object> m6 =new HashMap<String, Object>();
		m3.put("dataIndex", "user");
		m3.put("header","user");
		m3.put("itemId", "user");
		m3.put("hidden",true);
		m3.put("hiddeable", false);
		
		m4.put("dataIndex", "flag");
		m4.put("header","flag");
		m3.put("itemId", "flag");
		m4.put("hidden", true);
		m4.put("hiddeable", false);
		
		m5.put("dataIndex", "tab");
		m5.put("header","tab");
		m3.put("itemId", "tab");
		m5.put("hidden", true);
		m5.put("hiddeable", false);
		
		m6.put("dataIndex", "id");
		m6.put("header","id");
		m3.put("itemId", "id");
		m6.put("hidden", true);
		m6.put("hiddeable", false);
		column.add(m3);column.add(m4);column.add(m5);column.add(m6);
		Map<String,Object> m7 =new HashMap<String, Object>();
		Map<String,Object> m8 =new HashMap<String, Object>();
		Map<String,Object> m9 =new HashMap<String, Object>();
		Map<String,Object> m10 =new HashMap<String, Object>();
		m7.put("name", "id");m9.put("name", "user");
		m8.put("name", "flag");m10.put("name", "tab");
		model.add(m10);model.add(m9);
		model.add(m7);model.add(m8);
		m.put("model", model);
		m.put("column", column);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("cache-control", "no-cache");
		response.setCharacterEncoding("UTF-8");

	 
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
	 * showCombobox
	 * showCombobox
	 *  
	 */
	public void showCombobox()
	{
	   
		List<CaseBean> result =new ArrayList<CaseBean>();
		result=combinedDaoimpl.showCombobox();
		List<CaseBean> result1 =new ArrayList<CaseBean>();
		List<CaseBean> result2 =new ArrayList<CaseBean>();
		
		result1.addAll(result);
		
		for(CaseBean caseBean: result1)
		{
			if(caseBean.getFlag().indexOf("combined")!=-1)
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
			String flag = result3.get(i).getFlag().replace("combined", "");
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

	/**
	 * 
	 * 
	 * 上传文件
	 * @throws Exception 
	 */

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
	public void getColumnForGirdpanel() throws Exception{
		List<String> falseRegex =new ArrayList<String>();	
		//	A:阮，1\nB:123,1
			String msg =request.getParameter("msg");
			List<String[]> regexlist =new ArrayList<String[]>();
			String[] str1 =msg.split("\n");
			//分开冒号
			int length =str1.length;
			Map<String ,Object> sort = new HashMap<String, Object>();
			sort.put("header", "序号");
			sort.put("xtype", "rownumberer");
			sort.put("width","50");
			List<Map<String,Object>> column =new ArrayList<Map<String,Object>>();
			column.add(sort);
			List<Map<String,Object>> model =new ArrayList<Map<String,Object>>();
			Map<String,Object> m =new HashMap<String, Object>();
			
			for(int i=0;i<length;i++){
			 int j =i+1;
			Map<String,Object> m1 =new HashMap<String, Object>();
			Map<String,Object> m2 =new HashMap<String, Object>();		
				m1.put("dataIndex", "column"+j);
				m1.put("header", "列"+j);
			//	m1.put("hidden", "false");
		//		m1.put("hiddeable", "true");
				m2.put("name", "column"+j);
				column.add(m1);
				model.add(m2);
			}
			Map<String,Object> m3 =new HashMap<String, Object>();
			Map<String,Object> m4 =new HashMap<String, Object>();
			Map<String,Object> m5 =new HashMap<String, Object>();
			Map<String,Object> m6 =new HashMap<String, Object>();
			m3.put("dataIndex", "user");
			m3.put("header","user");
			m3.put("itemId", "user");
			m3.put("hidden",true);
			m3.put("hiddeable", false);
			
			m4.put("dataIndex", "flag");
			m4.put("header","flag");
			m3.put("itemId", "flag");
			m4.put("hidden", true);
			m4.put("hiddeable", false);
			
			m5.put("dataIndex", "tab");
			m5.put("header","tab");
			m3.put("itemId", "tab");
			m5.put("hidden", true);
			m5.put("hiddeable", false);
			
			m6.put("dataIndex", "id");
			m6.put("header","id");
			m3.put("itemId", "id");
			m6.put("hidden", true);
			m6.put("hiddeable", false);
			column.add(m3);column.add(m4);column.add(m5);column.add(m6);
			Map<String,Object> m7 =new HashMap<String, Object>();
			Map<String,Object> m8 =new HashMap<String, Object>();
			Map<String,Object> m9 =new HashMap<String, Object>();
			Map<String,Object> m10 =new HashMap<String, Object>();
			m7.put("name", "id");m9.put("name", "user");
			m8.put("name", "flag");m10.put("name", "tab");
			model.add(m10);model.add(m9);model.add(m7);model.add(m8);
			m.put("model", model);
			m.put("column", column);
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
