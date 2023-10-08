package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.User;

public class UserDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement psmt;
	private Statement stmt;

	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/miniproject1012";
			String dbID = "root";
			String dbPassword = "0000";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결 실패");
		}
	}
	
	//로그인
	public User selectUser(String id) {
		User dto = null;
		String sql = "SELECT * FROM user WHERE id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new User();
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setRegDate(rs.getTimestamp(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//회원가입
	public int insertUser(User dto) {
		String sql = "INSERT INTO user(id, password, name) VALUES(?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getName());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}



























