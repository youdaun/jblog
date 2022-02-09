package com.javaex.vo;

public class BlogVo {
	private String id;
	private String blogTitle;
	private String logoFile;
	
	

	public BlogVo() {
	}

	public BlogVo(String blogTitle, String logoFile) {
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	@Override
	public String toString() {
		return "blogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + "]";
	}
}
