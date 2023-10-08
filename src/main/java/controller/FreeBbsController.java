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

@WebServlet("/freeBbs")
public class FreeBbsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BbsDAO dao = new BbsDAO();
		int pageSize = Integer.parseInt(req.getServletContext().getInitParameter("pageSize"));
		
		SearchCondition sc = null;
		PageHandler ph = null;
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !"".equals(pageTemp)) {
			pageNum = Integer.parseInt(pageTemp);
		}
		
		Map<String, String> param = new HashMap<>();
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		//new
		String free = req.getParameter("free");
		System.out.println("free값 들어왔는지 확인 : "+free);
		
		if (searchWord != null && !"".equals(searchWord)) {
			param.put("searchField", searchField);
			param.put("searchWord", searchWord);
			//new
			param.put("free", free);
			
			sc = new SearchCondition(searchField, searchWord, pageNum, pageSize);
		} else {
			param.put("free", free);
			sc = new SearchCondition(pageNum, pageSize);
		}
		
		//selectCountAll -> selectCount
		int totalCount = dao.selectCount(param);
		ph = new PageHandler(totalCount, sc);
		param.put("offset", sc.getOffset(pageNum)+"");
		param.put("pageSize", pageSize+"");
		//selectListAll -> selectList
		List<Bbs> boardLists = dao.selectList(param);
		
		req.setAttribute("ph", ph);
		req.setAttribute("boardLists", boardLists);
		req.getRequestDispatcher("/freeBbs.jsp").forward(req, resp);
		
		/*
		 * int pageNumber = 1; if (req.getParameter("pageNumber") != null) { pageNumber
		 * = Integer.parseInt(req.getParameter("pageNumber")); } BbsDAO dao = new
		 * BbsDAO(); ArrayList<Bbs> list = dao.getList(pageNumber); HttpSession session
		 * = req.getSession(); session.setAttribute("list", list);
		 * session.setAttribute("pageNumber", pageNumber); session.setAttribute("dao",
		 * dao); req.getRequestDispatcher("/bbs.jsp").forward(req, resp);
		 */
	}
	
}
