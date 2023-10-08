package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.Bbs;

public class BbsDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement psmt;
	private Statement stmt;

	public BbsDAO() {
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
	
	// 검색 조건에 맞는 게시물 개수 반환 (ano, free 구별)
	public int selectCount(Map<String, String> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM bbs";
		if (map.get("searchWord") != null && "1".equals(map.get("ano"))) {
			sql += " WHERE ano=1 AND "+map.get("searchField")+ " LIKE '%" +map.get("searchWord")+ "%'";
		} else if (map.get("searchWord") != null && "1".equals(map.get("free"))) {
			sql += " WHERE free=1 AND "+map.get("searchField")+ " LIKE '%" +map.get("searchWord")+ "%'";
		}
		if (map.get("searchWord") == null && "1".equals(map.get("ano"))) {
			sql += " WHERE ano=1";
		} else if (map.get("searchWord") == null && "1".equals(map.get("free"))) {
			sql += " WHERE free=1";
		}
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 검색 조건에 맞는 모든 게시물 개수 반환
	public int selectCountAll(Map<String, String> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM bbs";
		if (map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	// 검색 조건에 맞는 게시물 목록 반환 (ano, free 구별)
	public List<Bbs> selectList(Map<String, String> map) {
		List<Bbs> bbs = new ArrayList<>(); // 결과(게시물 목록)를 담을 변수
		String sql = "SELECT * FROM bbs ";
		if (map.get("searchWord") != null && "1".equals(map.get("ano"))) {
			sql += " WHERE ano=1 AND "+map.get("searchField")+ " LIKE '%" +map.get("searchWord")+ "%'";
		} else if (map.get("searchWord") != null && "1".equals(map.get("free"))) {
			sql += " WHERE free=1 AND "+map.get("searchField")+ " LIKE '%" +map.get("searchWord")+ "%'";
		}
		if (map.get("searchWord") == null && "1".equals(map.get("ano"))) {
			sql += " WHERE ano=1";
		} else if (map.get("searchWord") == null && "1".equals(map.get("free"))) {
			sql += " WHERE free=1";
		}
		sql += " ORDER BY num DESC ";
		sql += " LIMIT " + map.get("offset") + "," + map.get("pageSize");
		System.out.println(sql);
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) { // 결과를 순화하며...
				// 한 행(게시물 하나)의 내용을 DTO에 저장
				Bbs dto = new Bbs();
				dto.setNum(rs.getInt("num")); // 일련번호
				dto.setTitle(rs.getString("title")); // 제목
				dto.setContent(rs.getString("content")); // 내용
				dto.setPostDate(rs.getTimestamp("postdate")); // 작성일
				dto.setId(rs.getString("id")); // 작성자 아이디
				dto.setViewCnt(rs.getInt("viewCnt")); // 조회수
				dto.setCommentCnt(rs.getInt("commentCnt"));
				dto.setLikeNum(rs.getInt("likeNum"));
				bbs.add(dto); // 결과 목록에 저장
			}
		} catch (Exception e) {
			System.out.println("검색 조건에 맞는 게시물 목록 반환 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	// 검색 조건에 맞는 게시물 목록 모두 반환
	public List<Bbs> selectListAll(Map<String, String> map) {
		List<Bbs> bbs = new ArrayList<>(); // 결과(게시물 목록)를 담을 변수
		String sql = "SELECT * FROM bbs ";
		if (map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		sql += " ORDER BY num DESC ";
		sql += " LIMIT " + map.get("offset") + "," + map.get("pageSize");
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) { // 결과를 순화하며...
				// 한 행(게시물 하나)의 내용을 DTO에 저장
				Bbs dto = new Bbs();
				dto.setAno(rs.getInt("ano"));
				dto.setFree(rs.getInt("free"));
				dto.setNum(rs.getInt("num")); // 일련번호
				dto.setTitle(rs.getString("title")); // 제목
				dto.setContent(rs.getString("content")); // 내용
				dto.setPostDate(rs.getTimestamp("postdate")); // 작성일
				dto.setId(rs.getString("id")); // 작성자 아이디
				dto.setViewCnt(rs.getInt("viewCnt")); // 조회수
				dto.setCommentCnt(rs.getInt("commentCnt")); //댓글 수
				dto.setLikeNum(rs.getInt("likeNum"));
				bbs.add(dto); // 결과 목록에 저장
			}
		} catch (Exception e) {
			System.out.println("검색 조건에 맞는 게시물 목록 반환 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	//글쓰기
	public int write(int ano, int free, String title, String content, String id) {
		String sql = "INSERT INTO bbs(ano, free, title, content, id) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, ano);
			psmt.setInt(2, free);
			psmt.setString(3, title);
			psmt.setString(4, content);
			psmt.setString(5, id);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//글 상세보기
	public Bbs getBbs(int num) {
		String sql = "SELECT * FROM bbs WHERE num = ?";
		Bbs dto = null;
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new Bbs();
				dto.setAno(rs.getInt(1));
				dto.setFree(rs.getInt(2));
				dto.setNum(rs.getInt(3));
				dto.setTitle(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setId(rs.getString(6));
				dto.setPostDate(rs.getTimestamp(7));
				dto.setViewCnt(rs.getInt(8));
				dto.setCommentCnt(rs.getInt("commentCnt"));
				dto.setLikeNum(rs.getInt("likeNum"));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//조회수 증가
	public int updateViewCnt(int num) {
		String sql = "UPDATE bbs SET viewCnt = viewCnt + 1 WHERE num = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//글 수정
	public int update(Bbs dto) {
		String sql = "UPDATE bbs SET title=?, content=? WHERE num=?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNum());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//글 삭제
	public int delete(int num) {
		String sql = "DELETE FROM bbs WHERE num = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, num);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// 댓글 수
	public int updateCommentCnt(int bno, int commentCnt) {
		String sql = "UPDATE bbs SET commentCnt = commentCnt + ? WHERE num = ?";
		int res = 0;
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, commentCnt);
			psmt.setInt(2, bno);
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// 좋아요 수
	public int updateLikeNumCnt(int like, int num) {
		int res = 0;
		String sql = "UPDATE bbs SET likeNum = likeNum + ? WHERE num = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, like);
			psmt.setInt(2, num);
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insertLike(String id, int bno) {
		int res = 0;
		String sql = "INSERT INTO board_like(id, bno) VALUES(?, ?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, bno);
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deleteLike(String id, int bno) {
		int res = 0;
		String sql = "DELETE FROM board_like WHERE id = ? AND bno = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, bno);
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int selectLike(String id, int bno) {
		int res = 0;
		String sql = "SELECT COUNT(*) FROM board_like WHERE id = ? and bno = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, bno);
			rs = psmt.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int selectAllLike(int bno) {
		int res = 0;
		String sql = "SELECT COUNT(*) FROM board_like WHERE bno = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, bno);
			rs = psmt.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}



































