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

@WebServlet("/delete")
public class DeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String snum = req.getParameter("num");
		if (snum == null) {
			JSFunction.alertLocation("잘못된 접근입니다", "main", resp);
			return;
		}
		int num = Integer.parseInt(snum);
		BbsDAO dao = new BbsDAO();
		Bbs dto = new Bbs();
		dto = dao.getBbs(num);
		HttpSession session = req.getSession();
		String id = session.getAttribute("id").toString();
		int result = 0;
		if (id == null || dto == null) {
			JSFunction.alertBack("잘못된 접근입니다", resp);
		} else if (dto.getId().equals(id)) {
			result = dao.delete(num);
			if (result == -1) {
				JSFunction.alertBack("삭제 실패", resp);
			} else {
				resp.sendRedirect("bbs");
			}
		}
	}
	
}
