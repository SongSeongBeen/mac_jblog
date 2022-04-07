package com.javaex.vo;

public class SearchVo {
	private String logoFile;
	private String blogTitle;
	private String userName;
	private String id;
	private String joinDate;

	public SearchVo() {
	}

	public SearchVo(String logoFile, String blogTitle, String userName, String id, String joinDate) {
		this.logoFile = logoFile;
		this.blogTitle = blogTitle;
		this.userName = userName;
		this.id = id;
		this.joinDate = joinDate;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "SearchVo [logoFile=" + logoFile + ", blogTitle=" + blogTitle + ", userName=" + userName + ", id=" + id
				+ ", joinDate=" + joinDate + "]";
	}

}
