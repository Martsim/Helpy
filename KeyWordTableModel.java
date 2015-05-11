package com.smartsystem.keywordsearch.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.smartsystem.keywordsearch.core.KeyWord;

public class KeyWordTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int KEYWORD_COL = 0;
	private static final int POSTS_COL = 1;
	
	private String[] colunmNames = {"KeyWord", "Posts"};
	private List<KeyWord> wordKey;
	
	public KeyWordTableModel(List<KeyWord> theKeyWord){
		wordKey = theKeyWord;
	}

	@Override
	public int getColumnCount() {
		return colunmNames.length;
	}

	@Override
	public int getRowCount() {
		return wordKey.size();
	}
	
	public String getColunmName(int col) {
		return colunmNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		KeyWord tempKeyWord = wordKey.get(row);
		
		switch(col){
		case KEYWORD_COL:
			return tempKeyWord.getKeyword();
		case POSTS_COL:
			return tempKeyWord.getPosts();
		default:
			return tempKeyWord.getKeyword();
		}
	}

	public static int getKeywordCol() {
		return KEYWORD_COL;
	}

	public static int getPostsCol() {
		return POSTS_COL;
	}
	
	@Override
	public Class getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
	

}
