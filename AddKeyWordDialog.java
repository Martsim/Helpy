package com.smartsystem.keywordsearch.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.smartsystem.keywordsearch.core.KeyWord;
import com.smartsystem.keywordsearch.key.SearchKeyWord;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddKeyWordDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField keywordTextField;
	private JTextField postsTextField;
	
	private SearchKeyWord keywordSearch;
	private SearchKey keySearch;
	
	public AddKeyWordDialog(SearchKeyWord thekeyWordSearch, SearchKey theKeySearch){
		this();
		keywordSearch = thekeyWordSearch;
		keySearch = theKeySearch;
	}


	/*public static void main(String[] args) {
		try {
			AddKeyWordDialog dialog = new AddKeyWordDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public AddKeyWordDialog() {
		setTitle("Add KeyWord");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblKeyword = new JLabel("KeyWord");
			lblKeyword.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPanel.add(lblKeyword, "2, 2, right, default");
		}
		{
			keywordTextField = new JTextField();
			contentPanel.add(keywordTextField, "4, 2, fill, default");
			keywordTextField.setColumns(10);
		}
		{
			JLabel lblPosts = new JLabel("Posts");
			lblPosts.setFont(new Font("Tahoma", Font.BOLD, 13));
			contentPanel.add(lblPosts, "2, 4, right, default");
		}
		{
			postsTextField = new JTextField();
			contentPanel.add(postsTextField, "4, 4, fill, default");
			postsTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveKeyWord();
					}
				});
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveKeyWord() {
		String keyword = keywordTextField.getText();
		String posts = postsTextField.getText();
		
		KeyWord tempKeyWord = new KeyWord(keyword, posts);
		try{
			// Save to database
			keywordSearch.addKeyWord(tempKeyWord);
			
			// close dialog
			setVisible(false);
			dispose();
			
			// Refresh the GUI list
			keySearch.refreshKeyWordView();
			
			// Show success message
			JOptionPane.showMessageDialog(keySearch,"KeyWord Added Successfully.","KeyWord Added",JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception exc){
			JOptionPane.showMessageDialog(keySearch,"Error Saving KeyWord: "+ exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
