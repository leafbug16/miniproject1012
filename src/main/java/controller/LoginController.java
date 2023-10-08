package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.User;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("id") != null) {
			resp.sendRedirect("./main");
			return;
		}
		String back = req.getParameter("back");
		System.out.println("로그인컨트롤러 get에서 back : " + back);
		req.setAttribute("back", back);
		req.getRequestDispatcher("./login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String rememberId = req.getParameter("rememberId");
		String back = req.getParameter("back");
		System.out.println("로그인컨트롤러 post에서 back : " +back);
		UserDAO udao = new UserDAO();
		User udto = udao.selectUser(id);
		if (udto!=null && udto.getId().equals(id) && udto.getPassword().equals(password)) {
			if (rememberId!=null) {
				Cookie cookie = new Cookie("id", id);
				resp.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("id", "");
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", udto.getName());
			if (!back.equals("") && back != null) {
				resp.sendRedirect(back);
				return;
			} else {
				resp.sendRedirect("./main");
				return;
			}
		}
		req.getRequestDispatcher("./login.jsp?logError=1").forward(req, resp);
	}
	
}































