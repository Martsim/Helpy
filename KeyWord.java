package com.smartsystem.keywordsearch.core;

public class KeyWord {
	private int id;
	private String keyword;
	private String posts;
	
	public KeyWord(String keyword, String posts){
		//this.id = id;
		this.keyword = keyword;
		this.posts = posts;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPosts() {
		return posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString(){
		return String.format("key_table[id=%s, keyword=%s, posts=%s]", id, keyword, posts);
	}

}
