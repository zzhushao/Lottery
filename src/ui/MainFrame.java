package ui;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.DataModel;
import data.MethodUtils;

public class MainFrame {

	private JFrame mJFrame = null;
	private Container mContainer = null;
	
	private ScrollEditText mSourceTextField;
	private ScrollEditText mResultTextField;
	private JButton mMakeJButton = null;
	private JButton mMakeAllNumberJButton = null;

	private JTextField mDeleteGeJTextField = null;
	private JTextField mDeleteShiJTextField = null;
	private JTextField mDeleteBaiJTextField = null;

	private JCheckBox mDeleteRepeatNumberBox = null;
	private JCheckBox mDeleteAdd3EqualNumberBox = null;
	private JCheckBox mDeleteHistoryNumberBox = null;
	private JCheckBox mDeleteGroup1NumberBox = null;
	private JCheckBox mDeleteGroup2NumberBox = null;

	private JCheckBox mDeleteHou2LastNumberBox = null;
	private JCheckBox mDeleteQian2LastNumberBox = null;
	private JCheckBox mDeleteEqualsBaiLastNumberBox = null;
	private JCheckBox mDeleteEqualsGeLastNumberBox = null;
	private JCheckBox mDeleteRandomNumberBox = null;
	private JCheckBox mDeleteJIouBox = null;

	public MainFrame(){
		initFrame();
		initTextField();
		initMakeJButton();
		initDeleteLabel();
		initCheckBox();
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
		mJFrame.setSize(AppFrameSize.MAIN_FRAME_W, AppFrameSize.MAIN_FRAME_W);
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
		JLabel titleLabel = new JLabel(AppStrings.Label_Delete);
		titleLabel.setBounds(AppFrameSize.LABEL_DELETE_TITLE_Rectangle);
		mContainer.add(titleLabel);
		
		JLabel titleGeLabel = new JLabel(AppStrings.Label_gewei);
		titleGeLabel.setBounds(AppFrameSize.LABEL_DELETE_TITLE_GE_Rectangle);
		mContainer.add(titleGeLabel);

		JLabel titleShiLabel = new JLabel(AppStrings.Label_shiwei);
		titleShiLabel.setBounds(AppFrameSize.LABEL_DELETE_TITLE_SHI_Rectangle);
		mContainer.add(titleShiLabel);
		
		JLabel titleBaiLabel = new JLabel(AppStrings.Label_baiwei);
		titleBaiLabel.setBounds(AppFrameSize.LABEL_DELETE_TITLE_BAI_Rectangle);
		mContainer.add(titleBaiLabel);

		mDeleteGeJTextField = getJTextField(AppFrameSize.EDIT_DELETE_GE_Rectangle);
		mDeleteShiJTextField = getJTextField(AppFrameSize.EDIT_DELETE_SHI_Rectangle);
		mDeleteBaiJTextField = getJTextField(AppFrameSize.EDIT_DELETE_BAI_Rectangle);

		mContainer.add(mDeleteGeJTextField);
		mContainer.add(mDeleteShiJTextField);
		mContainer.add(mDeleteBaiJTextField);
	}

	private void initCheckBox(){
		mDeleteRepeatNumberBox = getJCheckBox(AppFrameSize.DELETE_REPEAT_NUMBER_Rectangle, AppStrings.LABEL_DELETE_REPEAT_NUMBER);
		mContainer.add(mDeleteRepeatNumberBox);
		
		mDeleteAdd3EqualNumberBox = getJCheckBox(AppFrameSize.DELETE_THREEADDEQUALS_NUMBER_Rectangle, AppStrings.LABEL_DELETE_THREEADDEQUALS);
		mContainer.add(mDeleteAdd3EqualNumberBox);
		
		mDeleteHistoryNumberBox = getJCheckBox(AppFrameSize.DELETE_HISTORY_NUMBER_Rectangle, AppStrings.LABEL_DELETE_HISTORY_NUMBER);
		mContainer.add(mDeleteHistoryNumberBox);
		
		mDeleteGroup1NumberBox = getJCheckBox(AppFrameSize.DELETE_GROUP1_NUMBER_Rectangle, AppStrings.LABEL_DELETE_GROUP1_NUMBER);
		mContainer.add(mDeleteGroup1NumberBox);

		mDeleteGroup2NumberBox = getJCheckBox(AppFrameSize.DELETE_GROUP2_NUMBER_Rectangle, AppStrings.LABEL_DELETE_GROUP2_NUMBER);
		mContainer.add(mDeleteGroup2NumberBox);
		
		mDeleteHou2LastNumberBox = getJCheckBox(AppFrameSize.DELETE_HOU2_LAST_NUMBER_Rectangle, AppStrings.LABLE_DELETE_HOU2_EQUAL_LAST_NUMBER);
		mContainer.add(mDeleteHou2LastNumberBox);
		
		mDeleteQian2LastNumberBox = getJCheckBox(AppFrameSize.DELETE_QIAN2_LAST_NUMBER_Rectangle, AppStrings.LABLE_DELETE_QIAN2_EQUAL_LAST_NUMBER);
		mContainer.add(mDeleteQian2LastNumberBox);

		mDeleteEqualsBaiLastNumberBox = getJCheckBox(AppFrameSize.DELETE_EQUAL_BAI_NUMBER_Rectangle, AppStrings.LABLE_DELETE_BAI_EQUAL_NUMBER);
		mContainer.add(mDeleteEqualsBaiLastNumberBox);
		
		mDeleteEqualsGeLastNumberBox = getJCheckBox(AppFrameSize.DELETE_EQUAL_GE_NUMBER_Rectangle, AppStrings.LABLE_DELETE_GE_EQUAL_NUMBER);
		mContainer.add(mDeleteEqualsGeLastNumberBox);
		
		mDeleteRandomNumberBox = getJCheckBox(AppFrameSize.DELETE_RANDOM_NUMBER_Rectangle, AppStrings.LABEL_DELETE_RADOM_NUMBER);
		mContainer.add(mDeleteRandomNumberBox);
		
		mDeleteJIouBox = getJCheckBox(AppFrameSize.DELETE_JIOUGROUP_NUMBER_Rectangle, AppStrings.LABLE_DELETE_JIOUGROUPL_NUMBER);
		mContainer.add(mDeleteJIouBox);
	}
	
	private JCheckBox getJCheckBox(Rectangle r, String label){
		JCheckBox checkBox= new JCheckBox(label);
		checkBox.setBounds(r);
		return checkBox;
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
	
		boolean isDeleteRepeatCount = mDeleteRepeatNumberBox.isSelected();
		boolean mDeleteAdd3EqualNumber = mDeleteAdd3EqualNumberBox.isSelected();
		boolean isDeleteHistory = mDeleteHistoryNumberBox.isSelected();
		boolean deleteGroup1 = mDeleteGroup1NumberBox.isSelected();
		boolean deleteGroup2 = mDeleteGroup2NumberBox.isSelected();
		boolean deleteHou2 = mDeleteHou2LastNumberBox.isSelected();
		boolean deleteQian2 = mDeleteQian2LastNumberBox.isSelected();
		boolean deleteEqualBai = mDeleteEqualsBaiLastNumberBox.isSelected();
		boolean deleteEqualGe = mDeleteEqualsGeLastNumberBox.isSelected();
		boolean deleteRandom = mDeleteRandomNumberBox.isSelected();
		boolean deleteJIOU = mDeleteJIouBox.isSelected();

		DataModel dataModel = new DataModel();
		dataModel.setDeleteRepeatNumber(isDeleteRepeatCount);
		dataModel.setDeleteAdd3EqualsNumber(mDeleteAdd3EqualNumber);
		dataModel.setDeleteHistoryNumber(isDeleteHistory);
		dataModel.setDeleteGroup1Number(deleteGroup1);
		dataModel.setDeleteGroup2Number(deleteGroup2);
		dataModel.setDeleteAddHou2EqualsNumber(deleteHou2);
		dataModel.setDeleteAddQian2EqualsNumber(deleteQian2);
		dataModel.setDeleteAddEqualsForBaiNumber(deleteEqualBai);
		dataModel.setDeleteAddEqualsForGeNumber(deleteEqualGe);
		dataModel.setDeleteRandomANumber(deleteRandom);
		dataModel.setDeleteJiOuGroupNumber(deleteJIOU);

		dataModel.setResoureData(resData);
		dataModel.setDeleteData(geStr, shiStr, baiStr, qianStr, wanStr);

		mResultTextField.setText(dataModel.getResultData());
	}
}
