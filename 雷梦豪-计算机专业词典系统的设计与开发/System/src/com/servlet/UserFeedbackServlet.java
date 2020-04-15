package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Feedback;
import com.bean.User;
import com.bean.Vocabulary;
import com.dao.FeedbackDAO;
import com.dao.VocabularyDAO;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class UserFeedbackServlet
 */
@WebServlet("/UserFeedbackServlet")
public class UserFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
    private FeedbackDAO feedbackDAO=new FeedbackDAO();
    private VocabularyDAO vocabularyDAO=new VocabularyDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFeedbackServlet() {
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
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		int  result=0;
		if(action.equals("userAdd")){
			result=userAdd(request,response);
		}else if(action.equals("getFeedbackData")){
			getFeedbackData(request,response);
		}else if(action.equals("postError")){
			result=postError(request,response);
		}else if(action.equals("notFound")){
			result=notFound(request,response);
		}else if(action.equals("addToVocabulary")){
			result=addToVocabulary(request,response);
			
		}else if(action.equals("delete")){
			result=delete(request,response);
		}
		PrintWriter out=response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	public int userAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result=0;
		
		session=request.getSession();
		String abbreviation=request.getParameter("abbreviation");
		String fullWords=request.getParameter("fullWords");
		String specificMeanning=request.getParameter("specificMeanning");
		String lemmaSummary=request.getParameter("lemmaSummary");
		User currentUser=(User) session.getAttribute("currentUser");
		String username=currentUser.getUsername();
		Feedback feedback=new Feedback();
		feedback.setF_abbreviation(abbreviation);
		feedback.setF_fullWords(fullWords);
		feedback.setF_specificMeanning(specificMeanning);
		feedback.setF_lemmaSummary(lemmaSummary);  
		feedback.setF_username(username);
		feedback.setF_remark("add");
		try {
			result=feedbackDAO.UserAdd(feedback);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void getFeedbackData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result=0;
		try {
			ArrayList<Feedback> feedbacks=feedbackDAO.getFeedback();
			JSONArray jsonArray=JSONArray.fromObject(feedbacks);
			PrintWriter out=response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int postError(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result=0;
		session=request.getSession();
		if(session.getAttribute("currentUser")!=null){
			//int v_id=Integer.parseInt(request.getParameter("v_id"));
			String abbreviation=request.getParameter("abbreviation");
			String fullWords=request.getParameter("fullWords");
			String specificMeanning=request.getParameter("specificMeanning");
			String lemmaSummary=request.getParameter("lemmaSummary");
			User currentUser=(User) session.getAttribute("currentUser");
			String username=currentUser.getUsername();
			Feedback feedback=new Feedback();
			//feedback.setV_id(v_id);
			feedback.setF_abbreviation(abbreviation);
			feedback.setF_fullWords(fullWords);
			feedback.setF_specificMeanning(specificMeanning);
			feedback.setF_lemmaSummary(lemmaSummary);  
			feedback.setF_username(username);
			feedback.setF_remark("error");
			try {
				result=feedbackDAO.UserAdd(feedback);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			result=10;//用户未登录
		}
		return result;
	}
	public int notFound(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result=0;
		session=request.getSession();
		String abbreviation=request.getParameter("abbreviation");
		String fullWords=request.getParameter("fullWords");
		String specificMeanning=request.getParameter("specificMeanning");

		User currentUser=(User) session.getAttribute("currentUser");
		String username=currentUser.getUsername();
		Feedback feedback=new Feedback();
		feedback.setF_abbreviation(abbreviation);
		feedback.setF_fullWords(fullWords);
		feedback.setF_specificMeanning(specificMeanning);
		
		feedback.setF_username(username);
		feedback.setF_remark("notFound");
		try {
			result=feedbackDAO.UserAdd(feedback);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	public int addToVocabulary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result=0;
		String f_remark=request.getParameter("f_remark");
		String abbreviation=request.getParameter("abbreviation");
		String fullWords=request.getParameter("fullWords");
		String specificMeanning=request.getParameter("specificMeanning");
		String lemmaSummary=request.getParameter("lemmaSummary");
		String remark="由用户："+request.getParameter("remark")+"添加提供！";//提供反馈者
		
		int f_id=Integer.parseInt(request.getParameter("f_id"));
		
		Vocabulary vocabulary=new Vocabulary();
		vocabulary.setAbbreviation(abbreviation);
		vocabulary.setFullWords(fullWords);
		vocabulary.setSpecificMeanning(specificMeanning);
		vocabulary.setLemmaSummary(lemmaSummary);
		vocabulary.setRemark(remark);
		
		if(f_remark.equals("add") || f_remark.equals("notFound")){
			//int v_id=Integer.parseInt(request.getParameter("v_id"));

			try {
				result=vocabularyDAO.saveVocabulary(vocabulary);
				int row=feedbackDAO.deleteFeedback(f_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(f_remark.equals("error")){
			int v_id=Integer.parseInt(request.getParameter("v_id"));
			vocabulary.setId(v_id);
			try {
				result=vocabularyDAO.modify(vocabulary);
				int row=feedbackDAO.deleteFeedback(f_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	public int delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result=0;
		int f_id=Integer.parseInt(request.getParameter("f_id"));
		try {
			result=feedbackDAO.deleteFeedback(f_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
