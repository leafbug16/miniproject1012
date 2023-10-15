package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BbsDAO;
import dto.Bbs;

@WebServlet("/like")
public class LikeController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String bno = req.getParameter("boardId");
    String mode = req.getParameter("mode");
    BbsDAO dao = new BbsDAO();
    HttpSession session = req.getSession();
    String id = session.getAttribute("id")+"";
    System.out.println("id췍" +id);
    
    if (mode != null && !"".equals(mode)) {
    	if (mode.equals("addLike")) {
    		dao.insertLike(id, Integer.parseInt(bno));
    		dao.updateLikeNumCnt(1, Integer.parseInt(bno));
    	} else if (mode.equals("deleteLike")) {
    		dao.deleteLike(id, Integer.parseInt(bno));
    		dao.updateLikeNumCnt(-1, Integer.parseInt(bno));
    	}
    }
    
    int res = dao.selectLike(id, Integer.parseInt(bno));
    Bbs bbs = dao.getBbs(Integer.parseInt(bno));
    
    JSONArray jArray = new JSONArray();
    JSONObject jObject = new JSONObject();
    jObject.put("res", res+"");
    jObject.put("likeCnt", bbs.getLikeNum()+"");
    jArray.add(jObject);
    resp.setContentType("application/json; charset=utf-8");
    PrintWriter out = resp.getWriter();
    out.println(jArray.toJSONString());

	}
}
