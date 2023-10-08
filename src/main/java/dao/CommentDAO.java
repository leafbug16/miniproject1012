package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import dto.Comment;

public class CommentDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement psmt;
	private Statement stmt;

	public CommentDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/board_db";
			String dbID = "root";
			String dbPassword = "0000";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 실패");
		}
	}
	
	//댓글 모두 조회
	public ArrayList<Comment> selectAll(int bno) {
		String sql = "SELECT * FROM comment WHERE bno=? ORDER BY reg_date";
		ArrayList<Comment> dtos = new ArrayList<>();
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, bno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Comment dto = new Comment();
				dto.setCno(rs.getInt(1));
				dto.setBno(rs.getInt(2));
				dto.setComment(rs.getString(3));
				dto.setCommenter(rs.getString(4));
				dto.setReg_date(rs.getTimestamp(5));
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
	
	//댓글 작성
	public int insert(Comment dto) {
		int res = 0;
		String sql = "INSERT INTO comment(bno, comment, commenter) VALUES(?, ?, ?);";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, dto.getBno());
			psmt.setString(2, dto.getComment());
			psmt.setString(3, dto.getCommenter());
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//댓글 수정
	public int update(Comment dto) {
		int res = 0;
		String sql = "UPDATE comment SET comment=?, reg_date=now() WHERE cno=? AND commenter=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getComment());
			psmt.setInt(2, dto.getCno());
			psmt.setString(3, dto.getCommenter());
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//댓글 삭제
	public int delete(HashMap<String, String> param) {
		int res = 0;
		String sql = "DELETE FROM comment WHERE cno=? AND commenter=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, param.get("cno"));
			psmt.setString(2, param.get("commenter"));
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}





























