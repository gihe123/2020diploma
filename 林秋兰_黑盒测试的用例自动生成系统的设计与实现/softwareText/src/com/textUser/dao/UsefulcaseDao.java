package com.textUser.dao;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.textUser.bean.CaseBean;
import com.textUser.bean.UsefulCaseBean;

public interface UsefulcaseDao {
	public void deleteRow(String id);
	public void outputExcel(OutputStream os, String filename, String[] titles,String date);
	public String saveUsefulcase(UsefulCaseBean usefulcaseben);
	public List<UsefulCaseBean> showUsefulcaseList(String msg);
	public List<CaseBean> showUsefulcaseDetail(String flag);
	public List<Map<String, Object>> showsearchList(String msg);

}
