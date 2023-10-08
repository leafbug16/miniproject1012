package dto;

import java.util.Date;

public class Comment {
	private Integer cno;
	private Integer bno;
	private String comment;
	private String commenter;
	private Date reg_date;
	
	//getter, setter
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	//생성자
	public Comment(Integer bno, String comment, String commenter) {
		super();
		this.bno = bno;
		this.comment = comment;
		this.commenter = commenter;
	}
	public Comment(String comment, Integer cno, String commenter) {
		super();
		this.cno = cno;
		this.comment = comment;
		this.commenter = commenter;
	}
	public Comment() {}
	
	//toString
	@Override
	public String toString() {
		return "Comment [cno=" + cno + ", bno=" + bno + ", comment=" + comment + ", commenter=" + commenter + ", reg_date=" + reg_date + "]";
	}
	
	
}
