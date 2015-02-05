package ui;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ScrollEditText {

	private JTextArea mJTextArea = null;
	private JScrollPane mJScrollPane = null;
	
	public ScrollEditText(int rows, int columns){
		mJTextArea = new JTextArea(rows, columns);
		mJTextArea.setTabSize(4);  
		mJTextArea.setFont(new Font("标楷体", Font.BOLD, 16));  
		mJTextArea.setLineWrap(true);
		mJTextArea.setWrapStyleWord(true);
	
		mJScrollPane = new JScrollPane(mJTextArea);
	}

	public JScrollPane getJScrollPane(){
		return mJScrollPane;
	}

	public String getText(){
		return mJTextArea.getText();
	}

	public void setText(String text){
		mJTextArea.setText(text);
	}
}
