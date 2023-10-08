package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import dto.Bbs;
import util.JSFunction;

@WebServlet("/view")
public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String snum = req.getParameter("num");
		if (snum == null) {
			JSFunction.alertLocation("잘못된 접근입니다", "main", resp);
			return; // or throw an exception
		}

		int num = Integer.parseInt(snum);
		Bbs dto = new Bbs();
		BbsDAO dao = new BbsDAO();
		dto = dao.getBbs(num);
		dao.updateViewCnt(num);
		req.setAttribute("dto", dto);
		
	  // 세션에서 저장된 id정보를 가져와서
		String id = req.getSession().getAttribute("id")+"";
		int res =dao.selectLike(id, num); // 현재 사용자가 좋아요를 눌렀는지를 확인 ( 좋아요 개수 조회 )
		req.setAttribute("res", res); // request영역에 공유
		
		//원래 게시판으로 나오는 기능 추가
		String back = req.getParameter("back");
		req.setAttribute("back", back);
		
		req.getRequestDispatcher("view.jsp").forward(req, resp);
	}
	
}
