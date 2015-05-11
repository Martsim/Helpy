package com.smartsystem.keywordsearch.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.smartsystem.keywordsearch.core.KeyWord;
import com.smartsystem.keywordsearch.key.SearchKeyWord;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class SearchKey extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;
	
	private SearchKeyWord keywordSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchKey frame = new SearchKey();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchKey() {
		try{
			keywordSearch = new SearchKeyWord();
			
		}catch(Exception exc){
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		setTitle("KeyWord Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterKeyword = new JLabel("Enter KeyWord");
		panel.add(lblEnterKeyword);
		
		searchTextField = new JTextField();
		panel.add(searchTextField);
		searchTextField.setColumns(15);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String keyword = searchTextField.getText();
					
					List<KeyWord> wordKey = null;
					if(keyword != null && keyword.trim().length() > 0){
						wordKey = keywordSearch.searchKeyWords(keyword);
					}else{
						wordKey = keywordSearch.getAllKeyWords();
					}
					
					// create the model and update the table
					KeyWordTableModel model = new KeyWordTableModel(wordKey);
					table.setModel(model);
					
					//for(KeyWord temp : wordKey){
						//System.out.println(temp);
					//}
					
				}catch(Exception exc){
					JOptionPane.showMessageDialog(SearchKey.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton addKeyWordButton = new JButton("Add KeyWord");
		addKeyWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create the dialog
				AddKeyWordDialog dialog = new AddKeyWordDialog(keywordSearch, SearchKey.this);
				
				// show dialog
				dialog.setVisible(true);
			}
		});
		panel_1.add(addKeyWordButton);
	}

	public void refreshKeyWordView() {
		try{
			List<KeyWord> wordKey = keywordSearch.getAllKeyWords();
			
			// create the model and update the table
			KeyWordTableModel model = new KeyWordTableModel(wordKey);
			table.setModel(model);
			
		}catch(Exception exc){
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
