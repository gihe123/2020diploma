package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Admin;
import com.bean.User;
import com.dao.AdminDAO;
import com.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	private AdminDAO adminDao;
	//private HttpSession session;
	//private ServletContext application;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        userDao=new UserDAO();
        adminDao=new AdminDAO();
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
		//session=request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		String action=request.getParameter("action");
		
		boolean result=false;
		//System.out.println(username+";"+password+";"+action);
		if(action.equals("user")){
			result=user(request,response);
		}else if(action.equals("admin")){
			result=admin(request,response);
		}else if(action.equals("checkStatus")){

			if(request.getSession().getAttribute("currentUser")!= null){
				//System.out.println(request.getSession().getAttribute("currentUser").toString());
				result=true;
			}
			
		}else if(action.equals("register")){
			result=register(request,response);
		}else if(action.equals("userExit")){
			result=userExit(request,response);
		}else if(action.equals("adminExit")){
			result=adminExit(request,response);
		}else if(action.equals("modifyPwd")){
			modifyPwd(request,response);
		}
		PrintWriter out=response.getWriter();
		out.print(result);
		out.flush();
		out.close();

	}
	
	public boolean user(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result=false;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		int id=userDao.isValid(user);
		if(id!=0){
			result=true;
			user.setId(id);
			request.getSession().setAttribute("currentUser", user);
		}
		return result;
		//返回结果 true or false
	}
	public boolean admin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result=false;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Admin admin=new Admin();
		admin.setPassword(password);
		admin.setUsername(username);
		result=adminDao.isValid(admin);
		if(result){
			request.getSession().setAttribute("currentAdmin", admin);
		}
		return result;
		//返回结果 true or false
	}
	public boolean register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result=false;
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		try {
			int re=userDao.saveUser(user);
			if(re!=0){
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean userExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession().getAttribute("currentUser")!=null){
			request.getSession().invalidate();
			return true;
		}else{
			return false;
		}
	}
	public boolean adminExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession().getAttribute("currentAdmin")!=null){
			request.getSession().invalidate();
			return true;
		}else{
			return false;
		}
	}
	public void modifyPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=new User();
		user=(User) request.getSession().getAttribute("currentUser");
		String oldPwd=request.getParameter("oldPwd");
		String newPwd=request.getParameter("newPwd");
		int flag=0;
		if(user.getPassword().equals(oldPwd)){
			user.setPassword(newPwd);
			
			try {
				flag=userDao.modifyPwd(user);
				request.getSession().setAttribute("currentUser", user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			flag=2;//原密码不一致；
		}
		PrintWriter out=response.getWriter();
		out.print(flag);
		out.flush();
		out.close();
		return;
	}

}
