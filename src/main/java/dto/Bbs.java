package dto;

import java.util.Date;

public class Bbs {
	private int ano;
	private int free;
	private int num;
	private String title;
	private String content;
	private String id;
	private Date postDate;
	private int viewCnt;
	private int commentCnt;
	private int likeNum;
	public Bbs(int num, String title, String content) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
	}
	public Bbs() {}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content.replace("\r\n", "<br/>");
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	@Override
	public String toString() {
		return "Bbs [ano=" + ano + ", free=" + free + ", num=" + num + ", title=" + title + ", content=" + content + ", id=" + id + ", postDate=" + postDate
				+ ", viewCnt=" + viewCnt + ", commentCnt=" + commentCnt + "]";
	}
}
