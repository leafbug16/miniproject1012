package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BbsDAO;

@WebServlet("/like")
public class LikeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String bno = req.getParameter("bno");
    String mode = req.getParameter("mode");
    BbsDAO dao = new BbsDAO();
    HttpSession session = req.getSession();
    String id = session.getAttribute("id")+"";
    if (mode.equals("like")) {
        int res = dao.insertLike(id, Integer.parseInt(bno));
        if(res==1) {
        	System.out.println("등록 성공");
        	dao.updateLikeNumCnt(1, Integer.parseInt(bno));
        }
    }
    else {
        int res = dao.deleteLike(id, Integer.parseInt(bno));
        if(res==1) {
        	System.out.println("삭제 성공");
        	dao.updateLikeNumCnt(-1, Integer.parseInt(bno));
        }
    }
   resp.sendRedirect("view?num="+bno);
	}
	
}
