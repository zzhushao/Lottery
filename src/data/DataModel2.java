package data;

/**
 * @TODO 步步高电子商务有限责任公司 TODO <一句话功能描述>
 * @author
 * @date 2015-1-28 上午10:07:50
 * @version V2.0
 */
public class DataModel2 {

	private String[][] mDeleteNumbers = { { "" }, { "" }, { "" }, { "" },
			{ "" } };

	private String[] mDefault = { "" };
	private String[] mResDataArray = null;
	private String[][] mDamaNumbers = { { "" }, { "" }, { "" }, { "" },
			{ "" } };
	private String[] mHeiWei;
	private String[] mHou2He;

	private String resSplit_Douhao = ",";
	private String resSplit_Space = " ";

	private int mLengh = 3;

	private String mHistoryNumber;
	private boolean isDeleteLushu;

	public DataModel2() {

	}

	public void setHistoryNumber(String number){
		mHistoryNumber = number;
	}

	public void setDeleteLushu(boolean delete){
		isDeleteLushu = delete;
	}

	public void setResoureData(String resData) {
		if (resData.contains(resSplit_Douhao)) {
			mResDataArray = resData.split(resSplit_Douhao);
		} else {
			mResDataArray = resData.split(resSplit_Space);
		}
	}

	public void setHewei(String data){
		mHeiWei = getDeleteData(data);
	}

	public void setHou2He(String data){
		mHou2He = getDeleteData(data);
	}

	public void setDanmaData(String ge, String shi, String bai, String qian,
			String wan){
		mDamaNumbers[4] = getDeleteData(ge);
		mDamaNumbers[3] = getDeleteData(shi);
		mDamaNumbers[2] = getDeleteData(bai);
		mDamaNumbers[1] = getDeleteData(qian);
		mDamaNumbers[0] = getDeleteData(wan);
	}

	public void setDeleteData(String ge, String shi, String bai, String qian,
			String wan) {
		mDeleteNumbers[4] = getDeleteData(ge);
		mDeleteNumbers[3] = getDeleteData(shi);
		mDeleteNumbers[2] = getDeleteData(bai);
		mDeleteNumbers[1] = getDeleteData(qian);
		mDeleteNumbers[0] = getDeleteData(wan);
	}

	private String[] getDeleteData(String data) {
		if (data != null && data.trim().length() > 1) {
			return data.split(" ");
		} else if (data != null && data.trim().length() == 1) {
			return new String[] { data };
		} else {
			return mDefault;
		}
	}

	public String getResultData(boolean isThree) {
		StringBuffer buffer = new StringBuffer();
		String space = ",";

		int[] lArr = null;
		if(isDeleteLushu){
			lArr = MethodUtils.getLushuArr(mHistoryNumber);
		}
		for (String number : mResDataArray) {
			if (MethodUtils.isDeleteNumber(number, mLengh, mDeleteNumbers) ||
					MethodUtils.isEqualsHewei(number, mHeiWei) || 
					MethodUtils.isEqualsHou2he(number, mHou2He)||
					!MethodUtils.isDanmaNumber(number, mLengh, mDamaNumbers) ||
					(isDeleteLushu && MethodUtils.isLushu(number, lArr[0], lArr[1], lArr[2]))

					) {

			} else {
				if(isThree){
					buffer.append(number);
					buffer.append(space);
				}else{
					int num = Integer.valueOf(number);
					if(num < 100){
						String text = number.substring(1, 3);
						buffer.append(text);
						buffer.append(space);
					}
				}
			}
		}
		return buffer.toString();
	}
}
