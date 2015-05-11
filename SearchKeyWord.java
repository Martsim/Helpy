package com.smartsystem.keywordsearch.key;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smartsystem.keywordsearch.core.KeyWord;

public class SearchKeyWord {
	private Connection myConn = null;
	
	public SearchKeyWord() throws Exception{
		myConn = DriverManager.getConnection("jdbc:mysql://localhost/smart_db", "root", "");
		System.out.println("DB Connection Successful to: " + myConn);
	}
	
	public void addKeyWord(KeyWord theKeyWord) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			// prepare statement
			myStmt = myConn.prepareStatement("insert into key_table (keyword, posts) values (?, ?)");
			
			// set parameters
			myStmt.setString(1, theKeyWord.getKeyword());
			myStmt.setString(2, theKeyWord.getPosts());
			
			// Execute query
			myStmt.executeUpdate();
		}finally{
			close(null, myStmt, null);
		}
	}
	
	public List<KeyWord> getAllKeyWords() throws Exception{
		List<KeyWord> list = new ArrayList<KeyWord>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from key_table");
			
			while(myRs.next()){
				KeyWord tempKeyWord = convertRowToKeyWord(myRs);
				list.add(tempKeyWord);
			}
			return list;
		}finally{
			close(null, myStmt, myRs);
		}
	}
	
	public List<KeyWord> searchKeyWords(String keyword) throws Exception{
		List<KeyWord> list = new ArrayList<KeyWord>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			keyword += "%";
			myStmt = myConn.prepareStatement("select * from key_table where keyword like ?");
			
			myStmt.setString(1, keyword);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				KeyWord tempKeyWord = convertRowToKeyWord(myRs);
				list.add(tempKeyWord);
			}
			return list;
		}finally{
			close(myStmt, myRs);
		}
	}
	
	private KeyWord convertRowToKeyWord(ResultSet myRs) throws Exception{
		int id = myRs.getInt("id");
		String keyword = myRs.getString("keyword");
		String posts = myRs.getString("posts");
		
		KeyWord tempKeyWord = new KeyWord(keyword, posts);
		return tempKeyWord;
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException{
		if(myRs != null){
			myRs.close();
		}
		
		if(myStmt != null){
			
		}
		
		if(myConn != null){
			myConn.close();
		}
	}
	
	private void close(Statement myStmt, ResultSet myRs) throws SQLException{
		close(null, myStmt, myRs);
	}

	public static void main(String[] args) throws Exception {
		SearchKeyWord word = new SearchKeyWord();
		System.out.println(word.searchKeyWords(""));
		
		System.out.println(word.getAllKeyWords());

	}
}
