package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Vocabulary;
import com.dao.VocabularyDAO;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private ServletContext application;
	
	private VocabularyDAO vocabularyDAO = new VocabularyDAO();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		session=request.getSession();
		//String key=request.getParameter("key");
		//System.out.println(key);
		String action=request.getParameter("action");
		if(action.equals("getSearch")){
			getSearch(request,response);
		}else if (action.equals("getAll")){
			getAll(request,response);
		}else if(action.equals("adminModify")){
			adminModify(request,response);
		}else if(action.equals("adminDelete")){
			adminDelete(request,response);
		}else if(action.equals("adminAdd")){	
			adminAdd(request,response);
		}
		
	}
	public void adminModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			String abbreviation=request.getParameter("abbreviation");
			String fullWords=request.getParameter("fullWords");
			String specificMeanning=request.getParameter("specificMeanning");
			String lemmaSummary=request.getParameter("lemmaSummary");
			String remark=request.getParameter("remark");
			Vocabulary vocabulary=new Vocabulary();
			vocabulary.setId(id);
			vocabulary.setAbbreviation(abbreviation);
			vocabulary.setFullWords(fullWords);
			vocabulary.setSpecificMeanning(specificMeanning);
			vocabulary.setLemmaSummary(lemmaSummary);
			vocabulary.setRemark(remark);
			int result=vocabularyDAO.modify(vocabulary);
			System.out.println("result:"+result);;
			PrintWriter out=response.getWriter();
			out.print(result);
			out.flush();
			out.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adminDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		
		int result;
		try {
			result = vocabularyDAO.delete(id);
			System.out.println("result:"+result);;
			PrintWriter out=response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adminAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String abbreviation=request.getParameter("abbreviation");
		String fullWords=request.getParameter("fullWords");
		String specificMeanning=request.getParameter("specificMeanning");
		String lemmaSummary=request.getParameter("lemmaSummary");
		String remark=request.getParameter("remark");
		
		Vocabulary vocabulary=new Vocabulary();
		vocabulary.setAbbreviation(abbreviation);
		vocabulary.setFullWords(fullWords);
		vocabulary.setSpecificMeanning(specificMeanning);
		vocabulary.setLemmaSummary(lemmaSummary);
		vocabulary.setRemark(remark);
		int result;
		try {
			result = vocabularyDAO.saveVocabulary(vocabulary);
			System.out.println("result:"+result);
			PrintWriter out=response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void getSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String key=request.getParameter("key");
			ArrayList<Vocabulary> vocabulary = vocabularyDAO.search(key);
			JSONArray jsonArray=JSONArray.fromObject(vocabulary);
			//System.out.println(jsonArray);
			PrintWriter out=response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String key=request.getParameter("key");
			ArrayList<Vocabulary> vocabulary1 = vocabularyDAO.search(key);
			if(session.getAttribute("vocabulary")!=null){
				System.out.println("不等于空");
			}
			System.out.println(session.getId());
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			JSONArray jsonArray=JSONArray.fromObject(vocabulary1);
			//System.out.println(jsonArray);
			PrintWriter out=response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
