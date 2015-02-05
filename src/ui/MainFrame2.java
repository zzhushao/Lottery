package ui;

import java.awt.Container;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.DataModel2;
import data.MethodUtils;

public class MainFrame2 {

	private JFrame mJFrame = null;
	private Container mContainer = null;
	
	private ScrollEditText mSourceTextField;
	private ScrollEditText mResultTextField;
	private JButton mMakeJButton = null;
	private JButton mMakeAllNumberJButton = null;

	private JTextField mDeleteGeJTextField = null;
	private JTextField mDeleteShiJTextField = null;
	private JTextField mDeleteBaiJTextField = null;

	private JTextField mHou2HeJTextField = null;

	private NumberLine mBaiNumberLine, mShiNumberLine, mGeNumberLine;
	private NumberLine mHeweiNumber;

	public MainFrame2(){
		initFrame();
		initTextField();
		initMakeJButton();
		initDeleteLabel();
		initDanmaBox();
		initHeweiBox();
		initHou2He();
	}

	private void initFrame(){
		mJFrame = new JFrame(AppStrings.Frame_Title);
		mJFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);  
            }  
 
            public void windowActivated(WindowEvent e) {
            
            }  
        }); 
		mJFrame.setResizable(false);  
		mJFrame.setSize(AppFrameSize.MAIN_FRAME_W, AppFrameSize.MAIN_FRAME_H);
		mContainer = mJFrame.getContentPane();
		mContainer.setLayout(null);
	}

	private void initTextField(){
		mSourceTextField = new ScrollEditText(10, 15);
		mResultTextField = new ScrollEditText(10, 15);

		mSourceTextField.getJScrollPane().setBounds(AppFrameSize.SOURCE_TEXTFEILD_Rectangle);
		mResultTextField.getJScrollPane().setBounds(AppFrameSize.RESULT_TEXTFEILD_Rectangle);
		mContainer.add(mSourceTextField.getJScrollPane());
		mContainer.add(mResultTextField.getJScrollPane());
	}

	
	private void initMakeJButton(){
		mMakeJButton = new JButton(AppStrings.BUTTON_MAKE);
		mMakeJButton.setBounds(AppFrameSize.BUTTON_MAKE_Rectangle);
		mContainer.add(mMakeJButton);
		mMakeJButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				makeData();
			}
		});

		mMakeAllNumberJButton = new JButton(AppStrings.BUTTON_MAKE_ALL_NUMBER);
		mMakeAllNumberJButton.setBounds(AppFrameSize.BUTTON_MAKE_ALL_NUMBER_Rectangle);
		mContainer.add(mMakeAllNumberJButton);
		mMakeAllNumberJButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				makeAllNumber();
			}
		});
	}

	private void initDeleteLabel(){
		addJLabel(AppStrings.Label_Delete, AppFrameSize2.LABEL_DELETE_TITLE_Rectangle);
		addJLabel(AppStrings.Label_gewei, AppFrameSize2.LABEL_DELETE_TITLE_GE_Rectangle);
		addJLabel(AppStrings.Label_shiwei, AppFrameSize2.LABEL_DELETE_TITLE_SHI_Rectangle);
		addJLabel(AppStrings.Label_baiwei, AppFrameSize2.LABEL_DELETE_TITLE_BAI_Rectangle);

		mDeleteGeJTextField = getJTextField(AppFrameSize2.EDIT_DELETE_GE_Rectangle);
		mDeleteShiJTextField = getJTextField(AppFrameSize2.EDIT_DELETE_SHI_Rectangle);
		mDeleteBaiJTextField = getJTextField(AppFrameSize2.EDIT_DELETE_BAI_Rectangle);

		mContainer.add(mDeleteGeJTextField);
		mContainer.add(mDeleteShiJTextField);
		mContainer.add(mDeleteBaiJTextField);
	}

	private void initDanmaBox(){
		addJLabel(AppStrings.Label_danma, AppFrameSize2.DANMA_Rectangle);
		mBaiNumberLine = addNumberLinePanel(AppStrings.Label_baiwei, AppFrameSize2.BAI_NUMBER_Rectangle, true);
		mShiNumberLine = addNumberLinePanel(AppStrings.Label_shiwei, AppFrameSize2.SHI_NUMBER_Rectangle, true);
		mGeNumberLine = addNumberLinePanel(AppStrings.Label_gewei, AppFrameSize2.GE_NUMBER_Rectangle, true);
	}

	private NumberLine addNumberLinePanel(String title, Rectangle r, boolean showTools){
		NumberLine line = new NumberLine(title, showTools);
		Panel gePanel = line.getPanel();
		gePanel.setBounds(r);
		mContainer.add(gePanel);
		return line;
	}

	private JLabel addJLabel(String title, Rectangle r){
		JLabel label = new JLabel(title);
		label.setBounds(r);
		mContainer.add(label);
		return label;
	}

	private void initHeweiBox(){
		addJLabel(AppStrings.Label_Hewei, AppFrameSize2.HEWEI_LABEL_Rectangle);
		mHeweiNumber = addNumberLinePanel("", AppFrameSize2.HEWEI_Rectangle, false);
	}

	private void initHou2He(){
		addJLabel(AppStrings.Label_Hou2he, AppFrameSize2.HOU2HE_LABEL_Rectangle);
		mHou2HeJTextField = getJTextField(AppFrameSize2.HOU2HE_Rectangle);
		mContainer.add(mHou2HeJTextField);
	}

	private JTextField getJTextField(Rectangle r){
		JTextField textField= new JTextField();
		textField.setBounds(r);
		return textField;
	}

	public void show() {
		mJFrame.setVisible(true);
    }

	private void makeAllNumber(){
		String allStr = MethodUtils.getAllNumber(3);
		mSourceTextField.setText(allStr);
	}

	private void makeData(){
		String resData = mSourceTextField.getText();
		String geStr = mDeleteGeJTextField.getText();
		String shiStr = mDeleteShiJTextField.getText();
		String baiStr = mDeleteBaiJTextField.getText();
		String qianStr = null;
		String wanStr = null;
	
		DataModel2 dataModel = new DataModel2();
		dataModel.setResoureData(resData);
		dataModel.setDeleteData(geStr, shiStr, baiStr, qianStr, wanStr);
		dataModel.setDanmaData(mGeNumberLine.getSelectNumber(), mShiNumberLine.getSelectNumber(), mBaiNumberLine.getSelectNumber(), null, null);
		dataModel.setHewei(mHeweiNumber.getSelectNumber());
		dataModel.setHou2He(mHou2HeJTextField.getText());

		mResultTextField.setText(dataModel.getResultData());
	}
}
