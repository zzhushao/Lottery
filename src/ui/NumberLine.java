package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**  
 * @TODO 步步高电子商务有限责任公司  
 * TODO <一句话功能描述>
 * @author  
 * @date 2015-1-28 上午9:50:16  
 * @version V2.0 
 */
public class NumberLine implements ActionListener{
	
	private static final String COMMAND_QUAN = "quan";
	private static final String COMMAND_0 = "0";
	private static final String COMMAND_1 = "1";
	private static final String COMMAND_2 = "2";
	private static final String COMMAND_DA = "da";
	private static final String COMMAND_XIAO = "xiao";
	private static final String COMMAND_JI = "ji";
	private static final String COMMAND_OU = "ou";
	private static final String COMMAND_ZHI = "zhi";
	private static final String COMMAND_HE = "he";
	private static final String COMMAND_QING = "qing";

	private Panel mFlowLayoutPanel;
	private Panel mFlowLayoutPanel1;
	private Panel mFlowLayoutPanel2;

	private JCheckBox checkBox0, checkBox1, checkBox2,checkBox3,
			checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9;

	private String mTitle;
	private boolean mShowTools;

	public NumberLine(String title, boolean showTools){
		mTitle = title;
		mShowTools = showTools;
	}

	public Panel getPanel(){
		if(mFlowLayoutPanel == null){
			mFlowLayoutPanel = new Panel();
			mFlowLayoutPanel.setLayout(new FlowLayout()/*new BoxLayout(mFlowLayoutPanel, BoxLayout.Y_AXIS)*/);

			addCheckBox();

			if(mShowTools){
//				mFlowLayoutPanel1 = new Panel();
//				mFlowLayoutPanel1.setLayout(new FlowLayout());
				addButtons();
//				mFlowLayoutPanel.add(mFlowLayoutPanel1);
			}

//			mFlowLayoutPanel2 = new Panel();
//			mFlowLayoutPanel2.setLayout(new FlowLayout());
			
//			mFlowLayoutPanel.add(mFlowLayoutPanel2);
		}
		return mFlowLayoutPanel;
	}

	private void addButtons(){
		addButton("全", COMMAND_QUAN);
		addButton("0", COMMAND_0);
		addButton("1", COMMAND_1);
		addButton("2", COMMAND_2);
		addButton("奇", COMMAND_JI);
		addButton("偶", COMMAND_OU);
		addButton("大", COMMAND_DA);
		addButton("小", COMMAND_XIAO);
		addButton("质", COMMAND_ZHI);
		addButton("合", COMMAND_HE);
		addButton("清", COMMAND_QING);
	}

	private void addButton(String text, String actionCommand){
		JButton quanJButton = new JButton(text);
		quanJButton.setActionCommand(actionCommand);
		quanJButton.setPreferredSize(new Dimension(20, 20));
		quanJButton.setMargin(new Insets(0, 0, 0 ,0));
		quanJButton.addActionListener(this);
		mFlowLayoutPanel.add(quanJButton);
	}

	private void addCheckBox(){
		JLabel label = new JLabel(mTitle);
		mFlowLayoutPanel.add(label);
		checkBox0 = addCheckBox("0");
		checkBox1 = addCheckBox("1");
		checkBox2 = addCheckBox("2");
		checkBox3 = addCheckBox("3");
		checkBox4 = addCheckBox("4");
		checkBox5 = addCheckBox("5");
		checkBox6 = addCheckBox("6");
		checkBox7 = addCheckBox("7");
		checkBox8 = addCheckBox("8");
		checkBox9 = addCheckBox("9");
	}

	private JCheckBox addCheckBox(String text){
		JCheckBox jbox = new JCheckBox(text);
		jbox.setBackground(Color.pink);
		jbox.setForeground(Color.WHITE);
		mFlowLayoutPanel.add(jbox);
		return jbox;
	}

	public String getSelectNumber(){
		String text = "" ;
		text = addString(text, getCheckboxNumber(checkBox0));
		text = addString(text, getCheckboxNumber(checkBox1));
		text = addString(text, getCheckboxNumber(checkBox2));
		text = addString(text, getCheckboxNumber(checkBox3));
		text = addString(text, getCheckboxNumber(checkBox4));
		text = addString(text, getCheckboxNumber(checkBox5));
		text = addString(text, getCheckboxNumber(checkBox6));
		text = addString(text, getCheckboxNumber(checkBox7));
		text = addString(text, getCheckboxNumber(checkBox8));
		text = addString(text, getCheckboxNumber(checkBox9));
		System.out.println(text);
		return text;
	}

	private String addString(String text, String number){
		if(number != null && number.length() > 0){
			if(text.length() == 0){
				text += number;
			}else{
				text += " " + number;
			}
		}
		return text;
	}

	private String getCheckboxNumber(JCheckBox box){
		if(box.isSelected()){
			return box.getText();
		}
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		System.out.println(e.getActionCommand());
		if(action.endsWith(COMMAND_QUAN)){
			clearAll(false);
		}else if(action.endsWith(COMMAND_QING)){
			clearAll(true);
		}else if(action.endsWith(COMMAND_0)){
			set0();
		}else if(action.endsWith(COMMAND_1)){
			set1();
		}else if(action.endsWith(COMMAND_2)){
			set2();
		}else if(action.endsWith(COMMAND_DA)){
			setDa();
		}else if(action.endsWith(COMMAND_XIAO)){
			setXiao();
		}
		else if(action.endsWith(COMMAND_JI)){
			setJishu();
		}else if(action.endsWith(COMMAND_OU)){
			setOushu();
		}
		else if(action.endsWith(COMMAND_ZHI)){
			setZishu();
		}
		else if(action.endsWith(COMMAND_HE)){
			setHeshu();
		}
	}

	private void clearAll(boolean clear){
		checkBox0.setSelected(clear ? false : true);
		checkBox1.setSelected(clear ? false : true);
		checkBox2.setSelected(clear ? false : true);
		checkBox3.setSelected(clear ? false : true);
		checkBox4.setSelected(clear ? false : true);
		checkBox5.setSelected(clear ? false : true);
		checkBox6.setSelected(clear ? false : true);
		checkBox7.setSelected(clear ? false : true);
		checkBox8.setSelected(clear ? false : true);
		checkBox9.setSelected(clear ? false : true);
	}

	private void set0(){
		clearAll(true);
		checkBox0.setSelected(true);
		checkBox3.setSelected(true);
		checkBox6.setSelected(true);
		checkBox9.setSelected(true);
	}

	private void set1(){
		clearAll(true);
		checkBox1.setSelected(true);
		checkBox4.setSelected(true);
		checkBox7.setSelected(true);
	}

	private void set2(){
		clearAll(true);
		checkBox2.setSelected(true);
		checkBox5.setSelected(true);
		checkBox8.setSelected(true);
	}
	private void setDa(){
		clearAll(true);
		checkBox5.setSelected(true);
		checkBox6.setSelected(true);
		checkBox7.setSelected(true);
		checkBox8.setSelected(true);
		checkBox9.setSelected(true);
	}

	private void setXiao(){
		clearAll(true);
		checkBox0.setSelected(true);
		checkBox1.setSelected(true);
		checkBox2.setSelected(true);
		checkBox3.setSelected(true);
		checkBox4.setSelected(true);
	}
	private void setJishu(){
		clearAll(true);
		checkBox1.setSelected(true);
		checkBox3.setSelected(true);
		checkBox5.setSelected(true);
		checkBox7.setSelected(true);
		checkBox9.setSelected(true);
	}
	private void setOushu(){
		clearAll(true);
		checkBox0.setSelected(true);
		checkBox2.setSelected(true);
		checkBox4.setSelected(true);
		checkBox6.setSelected(true);
		checkBox8.setSelected(true);
	}
	private void setZishu(){
		clearAll(true);
		checkBox1.setSelected(true);
		checkBox2.setSelected(true);
		checkBox3.setSelected(true);
		checkBox5.setSelected(true);
		checkBox7.setSelected(true);
	}

	private void setHeshu(){
		clearAll(true);
		checkBox0.setSelected(true);
		checkBox4.setSelected(true);
		checkBox6.setSelected(true);
		checkBox8.setSelected(true);
		checkBox9.setSelected(true);
	}
	
	private class ColorIcon implements Icon {
	    int size = 100;

	    public void paintIcon(Component c, Graphics g, int x, int y) {
	        g.setColor(Color.orange);
	        g.fillOval(x, y, size, size);
	        g.setColor(Color.GREEN);
	        g.drawRect(x, y, size, size);
	    }
	 
	    public int getIconWidth() {
	        return size;
	    }
	 
	    public int getIconHeight() {
	        return size;
	    }
	}
}
