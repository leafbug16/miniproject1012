package util;

public class SearchCondition {
	//검색 조건
	private String searchField = "";
	private String searchWord = "";
	//현재 페이지. 기본 1
	private int page = 1;
	//페이지당 게시물 수. 기본 5
	private int pageSize = 5;
	
	//생성자
	//검색어가 지정된 경우
	//검색조건과 현재페이지, 페이지당게시물수를 매개값으로 받아서 멤버변수 초기화
	public SearchCondition(String searchField, String searchWord, int page, int pageSize) {
		this.searchField = searchField;
		this.searchWord = searchWord;
		this.page = page;
		this.pageSize = pageSize;
	}
	//검색어가 없는 경우
	//현재 페이지, 페이지당게시물수를 매개값으로 받아서 멤버변수 초기화
	public SearchCondition(int page, int pageSize) {
		this("title", "", page, pageSize);
	}
	
	//메서드
	//offset은 page를 매개값으로 계산한 결과를 반환만 하면 되므로 getter로 구현
	//현재 페이지에서 1을 빼고 페이지당게시물수를 곱하면 뭐가 나오는가?
	//현재 페이지를 3, 페이지당게시물수를 5라고 가정하면 10이 나온다. 뭐지?
	public int getOffset(int page) {
		return (page - 1) * pageSize;
	}

	//현재 페이지를 기준으로 쿼리스트링을 반환하는 경우
	public String getQueryString() {
		return getQueryString(page);
	}

	//지정된 페이지가 있을 경우 쿼리스트링 반환
	public String getQueryString(int page) {
		//검색어가 존재하면
		if (searchWord != null && !"".equals(searchWord))
			//pageNum=현재페이지&pageSize=페이지사이즈&검색어들 이라는 쿼리스트링 형식의 문자열을 반환
			return "?pageNum=" + page + "&pageSize=" + pageSize + "&searchField=" + searchField + "&searchWord=" + searchWord;
		else
			//검색어가 없다면 현재페이지와 페이지사이즈 값만
			return "?pageNum=" + page + "&pageSize=" + pageSize;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SearchCondition [searchField=" + searchField + ", searchWord=" + searchWord + ", page=" + page + ", pageSize=" + pageSize + "]";
	}

}