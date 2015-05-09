package ui;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**  
 * @TODO 步步高电子商务有限责任公司  
 * TODO <一句话功能描述>
 * @author  
 * @date 2015-3-16 下午1:54:30  
 * @version V2.0 
 */
public class HistoryPanel {

	private Panel mFlowLayoutPanel;
	private JCheckBox mLuShucheckBox;
	private JTextField mHistoryTextField = null;
	private Panel mFlowLayoutPanel1;
	private Panel mFlowLayoutPanel2;

	public Panel getPanel(){
		if(mFlowLayoutPanel == null){
			mFlowLayoutPanel = new Panel();
			mFlowLayoutPanel.setLayout(new BoxLayout(mFlowLayoutPanel, BoxLayout.Y_AXIS));

			mFlowLayoutPanel1 = new Panel();
			mFlowLayoutPanel1.setLayout(null);
			mFlowLayoutPanel.add(mFlowLayoutPanel1);

			mFlowLayoutPanel2 = new Panel();
			mFlowLayoutPanel2.setLayout(null);
			mFlowLayoutPanel.add(mFlowLayoutPanel2);

			addLabel();
			addHistoryTextField();
			addCheckBox();
		}
		return mFlowLayoutPanel;
	}

	private void addLabel(){
		JLabel label = new JLabel(AppStrings.LABEL_HISTORY);
		label.setBounds(AppFrameSize2.HISTORY_LABEL_Rectangle);
		mFlowLayoutPanel1.add(label);
	}

	private void addHistoryTextField(){
		mHistoryTextField = new JTextField();
		mHistoryTextField.setBounds(AppFrameSize2.HISTORY_TEXT_FILED_Rectangle);
		mFlowLayoutPanel1.add(mHistoryTextField);
	}

	private void addCheckBox(){
		mLuShucheckBox = addCheckBox(AppStrings.LABLE_DELETE_LUSHU_NUMBER, AppFrameSize2.HISTORY_DELETE_LUSHU_Rectangle);
	}

	private JCheckBox addCheckBox(String text, Rectangle r){
		JCheckBox jbox = new JCheckBox(text);
		jbox.setBounds(r);
		jbox.setBackground(Color.pink);
		jbox.setForeground(Color.WHITE);
		mFlowLayoutPanel2.add(jbox);
		return jbox;
	}

	public String getHistoryNumber(){
		return mHistoryTextField.getText();
	}

	public boolean getDeleteLushu(){
		return mLuShucheckBox.isSelected();
	}
}
