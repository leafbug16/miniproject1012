package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.UserDAO;
import dto.User;

@WebServlet("/userIdCheck")
public class UserIdCheckController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		UserDAO userDao = new UserDAO();
		User userDto = userDao.selectUser(id);
		int res = 1;
		if (userDto == null) {
			res = 0;
		} else {
			res = 1;
		}
		System.out.println(res);
		
	    JSONArray jArray = new JSONArray();
	    JSONObject jObject = new JSONObject();
	    jObject.put("res", res+"");
	    jArray.add(jObject);
	    resp.setContentType("application/json; charset=utf-8");
	    PrintWriter out = resp.getWriter();
	    out.println(jArray.toJSONString());
	}
	
}
