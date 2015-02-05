package data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataModel {

	private String[][] mDeleteNumbers = { {""},
		 {""},
		 {""},
		 {""},
		 {""}
		 };

	private String[] mDefault= { "" };
	private String[] mResDataArray = null;
	private boolean isDeleteRepeatNumber = false;
	private boolean isDeleteAdd3EqualsNumber = false;
	private boolean isDeleteHistoryNumber = false;
	private boolean isDeleteGroup1Number = false;
	private boolean isDeleteGroup2Number = false;
	private boolean isDeleteAddQian2EqualsNumber = false;
	private boolean isDeleteAddHou2EqualsNumber = false;
	private boolean isAddEqualsForBaiNumber = false;
	private boolean isAddEqualsForGeNumber = false;
	private boolean isDeleteRandomANumber = false;
	private boolean isDeleteJiOuGroupNumber = false;

	private String resSplit_Douhao = ",";
	private String resSplit_Space = " ";

	private int mLengh = 3;

	public DataModel(){

	}

	public void setDeleteRepeatNumber(boolean isDelete){
		isDeleteRepeatNumber = isDelete;
	}

	public void setDeleteAdd3EqualsNumber(boolean isDelete){
		isDeleteAdd3EqualsNumber = isDelete;
	}

	public void setDeleteHistoryNumber(boolean isDelete){
		isDeleteHistoryNumber = isDelete;
	}

	public void setDeleteGroup1Number(boolean isDelete){
		isDeleteGroup1Number = isDelete;
	}

	public void setDeleteGroup2Number(boolean isDelete){
		isDeleteGroup2Number = isDelete;
	}

	public void setDeleteAddQian2EqualsNumber(boolean isDelete){
		isDeleteAddQian2EqualsNumber = isDelete;
	}

	public void setDeleteAddHou2EqualsNumber(boolean isDelete){
		isDeleteAddHou2EqualsNumber = isDelete;
	}

	public void setDeleteAddEqualsForBaiNumber(boolean isDelete){
		isAddEqualsForBaiNumber = isDelete;
	}

	public void setDeleteAddEqualsForGeNumber(boolean isDelete){
		isAddEqualsForGeNumber = isDelete;
	}

	public void setDeleteRandomANumber(boolean isDelete){
		isDeleteRandomANumber = isDelete;
	};

	public void setDeleteJiOuGroupNumber(boolean isDelete){
		isDeleteJiOuGroupNumber = isDelete;
	}

	public void setResoureData(String resData){
		if(resData.contains(resSplit_Douhao)){
			mResDataArray = resData.split(resSplit_Douhao);
		}else{
			mResDataArray = resData.split(resSplit_Space);
		}
	}

	public void setDeleteData(String ge, String shi, String bai, String qian, String wan){
		mDeleteNumbers[4] = getDeleteData(ge);
		mDeleteNumbers[3] = getDeleteData(shi);
		mDeleteNumbers[2] = getDeleteData(bai);
		mDeleteNumbers[1] = getDeleteData(qian);
		mDeleteNumbers[0] = getDeleteData(wan);
	}

	private String[] getDeleteData(String data){
		if(data != null && data.trim().length() > 1){
			return data.split(" ");
		}else if(data != null && data.trim().length() == 1){
			return new String[]{ data };
		}else{
			return mDefault;
		}
	}

	public String getResultData(){
		StringBuffer buffer = new StringBuffer();
		String space = " ";
		int lastTotal = 0;
		String[] deleteHistories = null;
		if(isDeleteHistoryNumber || isDeleteAdd3EqualsNumber || isDeleteGroup1Number ||
				isDeleteGroup2Number || isDeleteAddQian2EqualsNumber || isDeleteAddHou2EqualsNumber ||
				isDeleteRandomANumber || isDeleteJiOuGroupNumber){
			deleteHistories = MethodUtils.readHistoryNumber();//Numbers.mDeleteHistories;
		}
		String[][] group1 = null;
		String[][] group2 = null;
		String[][] randomA = null;
		String lastNumber = null;
		String lastNumber2 = null;
		if(deleteHistories != null && deleteHistories.length > 1){
			lastNumber = deleteHistories[deleteHistories.length - 1];
			lastTotal = MethodUtils.getAddTotal(lastNumber, mLengh);
		}
		if(deleteHistories != null && deleteHistories.length > 2){
			lastNumber2 = deleteHistories[deleteHistories.length - 2];
		}
		System.out.println(lastNumber +"," + lastNumber2);
		if(isDeleteGroup1Number ){
			group1 = MethodUtils.getGroupsForThreeNumber(lastNumber);
		}
		if(isDeleteGroup2Number ){
			group2 = MethodUtils.getGroupsForThreeNumber(lastNumber2);
		}
		if(isDeleteRandomANumber){
			randomA = MethodUtils.getRandomDeleteANumber(lastNumber);
		}
		int[] lastIntArr = null;
		if(isDeleteJiOuGroupNumber){
			lastIntArr = MethodUtils.getJiOUArray(lastNumber, true);
		}

		for(String number : mResDataArray){
			if(MethodUtils.isDeleteNumber(number, 3, mDeleteNumbers) ||
					(isDeleteRepeatNumber && MethodUtils.hasRepeatCount(number)) ||
					(isDeleteAdd3EqualsNumber && MethodUtils.isAddNumberEquals(number, lastTotal, mLengh)) ||
					(isDeleteHistoryNumber && MethodUtils.isHistoryNumber(number, deleteHistories)) ||
					(isDeleteGroup1Number && MethodUtils.isDeleteGroup(number, group1, mLengh)) ||
					(isDeleteGroup2Number && MethodUtils.isDeleteGroup(number, group2, mLengh)) ||
					(isDeleteAddQian2EqualsNumber && MethodUtils.isTwoNumberAddEqualsLast(number, lastNumber, MethodUtils.TYPE_QIAN2)) ||
					(isDeleteAddHou2EqualsNumber && MethodUtils.isTwoNumberAddEqualsLast(number, lastNumber, MethodUtils.TYPE_HOU2)) ||
					(isAddEqualsForBaiNumber && MethodUtils.isAddEqualsForBaiNumber(number)) ||
					(isAddEqualsForGeNumber && MethodUtils.isAddEqualsForGeNumber(number)) ||
					(isDeleteRandomANumber && MethodUtils.isDeleteNumber(number, 3, randomA)) ||
					(isDeleteJiOuGroupNumber && MethodUtils.isEqualsIntArray(lastIntArr, MethodUtils.getJiOUArray(number, false)))
					){
				
			}else{
				buffer.append(number);
				buffer.append(space);
			}
		}
		return buffer.toString();
	}
}
