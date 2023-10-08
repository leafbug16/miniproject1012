package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BbsDAO;
import dto.Bbs;
import util.JSFunction;

@WebServlet("/update")
public class UpdateController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String snum = req.getParameter("num");
		if (snum == null) {
			JSFunction.alertLocation("잘못된 접근입니다", "index.jsp", resp);
			return;
		}
		int num = Integer.parseInt(snum);
		BbsDAO dao = new BbsDAO();
		Bbs dto = new Bbs();
		dto = dao.getBbs(num);
		HttpSession session = req.getSession();
		String sid = session.getAttribute("id").toString();
		if (!sid.equals(dto.getId())) {
			JSFunction.alertBack("작성자 본인만 수정할 수 있습니다", resp);
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int num = Integer.parseInt(req.getParameter("num"));
		 String title = req.getParameter("title");
		 String content = req.getParameter("content");
		 Bbs dto = new Bbs(num, title, content);
		 BbsDAO dao = new BbsDAO();
		 int result = dao.update(dto);
		 if (result == -1) {
			 JSFunction.alertBack("수정 실패", resp);
		 } else {
			 resp.sendRedirect("view?num="+dto.getNum());
		 }
	}
	
}
