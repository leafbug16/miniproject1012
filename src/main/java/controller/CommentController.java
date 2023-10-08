package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BbsDAO;
import dao.CommentDAO;
import dto.Comment;

@WebServlet("/comments")
public class CommentController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("mode");
		if ("del".equals(method)) {
			this.doDelete(req, resp);
		} else if ("mody".equals(method)) {
			this.doPatch(req, resp);
		} else super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String temp = req.getParameter("bno");
		int bno = 0;
		if (temp != null) bno = Integer.parseInt(temp);
		CommentDAO dao = new CommentDAO();
		ArrayList<Comment> dtos = dao.selectAll(bno);
		JSONArray jArray = new JSONArray();
		for (int i=0; i<dtos.size(); i++) {
			JSONObject sObject = new JSONObject();
			sObject.put("cno", dtos.get(i).getCno()+"");
			sObject.put("bno", dtos.get(i).getBno()+"");
			sObject.put("comment", dtos.get(i).getComment());
			sObject.put("commenter", dtos.get(i).getCommenter());
			sObject.put("reg_date", dtos.get(i).getReg_date()+"");
			jArray.add(sObject);
		}
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(jArray.toJSONString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String temp = req.getParameter("bno");
		int bno = 0;
		if (temp != null) bno = Integer.parseInt(temp);
		String comment = req.getParameter("comment");
		CommentDAO dao = new CommentDAO();
		BbsDAO bdao = new BbsDAO();
		HttpSession session = req.getSession();
		String id = session.getAttribute("id").toString();
		Comment dto = new Comment(bno, comment, id);
		int result = dao.insert(dto);
		if (result == 1) {
			bdao.updateCommentCnt(bno, 1);
		}
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String temp = req.getParameter("cno");
		int cno = 0;
		if (temp != null) cno = Integer.parseInt(temp);
		String comment = req.getParameter("comment");
		CommentDAO dao = new CommentDAO();
		HttpSession session = req.getSession();
		String id = session.getAttribute("id").toString();
		Comment dto = new Comment(comment, cno, id);
		dao.update(dto);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String temp = req.getParameter("cno");
		String cno = "";
		if (temp != null) cno = temp;
		String temp1 = req.getParameter("bno");
		int bno = 0;
		if (temp1 != null) bno = Integer.parseInt(temp1);
		CommentDAO dao = new CommentDAO();
		BbsDAO bdao = new BbsDAO();
		HttpSession session = req.getSession();
		String id = session.getAttribute("id").toString();
		HashMap<String, String> param = new HashMap<>();
		param.put("cno", cno);
		param.put("commenter", id);
		int result = dao.delete(param);
		if (result == 1) {
			bdao.updateCommentCnt(bno, -1);
		}
	}
	
}




















