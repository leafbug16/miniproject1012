package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import dto.Bbs;
import util.PageHandler;
import util.SearchCondition;

@WebServlet("/bbs")
public class BbsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> param = new HashMap<>();
		SearchCondition sc = null;
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !"".equals(pageTemp)) {
			pageNum = Integer.parseInt(pageTemp);
		}
		int pageSize = 5;
		if (searchWord != null && !"".equals(searchWord)) {
			param.put("searchField", searchField);
			param.put("searchWord", searchWord);
			sc = new SearchCondition(searchField, searchWord, pageNum, pageSize);
		} else {
			sc = new SearchCondition(pageNum, pageSize);
		}
		
		BbsDAO dao = new BbsDAO();
		int totalCount = dao.selectCountAll(param);
		param.put("offset", sc.getOffset(pageNum)+"");
		param.put("pageSize", pageSize+"");
		List<Bbs> boardLists = dao.selectListAll(param);
		
		PageHandler ph = new PageHandler(totalCount, sc);
		req.setAttribute("ph", ph);
		req.setAttribute("boardLists", boardLists);
		req.getRequestDispatcher("/bbs.jsp").forward(req, resp);
		
	}
}
