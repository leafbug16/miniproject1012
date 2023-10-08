package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BbsDAO;
import util.JSFunction;

@WebServlet("/write")
public class WriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("id") == null) {
			String back = req.getParameter("back");
			System.out.println("write컨트롤러에서 back : " + back);
			JSFunction.alertLocation("로그인 해주세요", "./login?back="+back, resp);
		} else {
			req.getRequestDispatcher("write.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String boardType = req.getParameter("boardType");
		HttpSession session = req.getSession();
		String id = session.getAttribute("id").toString();
		BbsDAO dao = new BbsDAO();
		
		int result = -1;
		if (boardType.equals("ano")) {
			result = dao.write(1, 0, title, content, id);
		} else {
			result = dao.write(0, 1, title, content, id);
		}
		if (result == -1) {
			JSFunction.alertBack("글쓰기 실패", resp);
		} else {
			resp.sendRedirect("./bbs");
		}
	}
	
}
