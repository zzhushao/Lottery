package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MethodUtils {

	private static String LU0 = "0 3 6 9";
	private static String LU1 = "1 4 7";
	private static String LU2 = "2 5 8";

	public static final int TYPE_QIAN2 = 1;
	public static final int TYPE_HOU2 = 2;

	public static final int TYPE_JI = 1;
	public static final int TYPE_OU = 2;

	private static final int[] mTmpIntArr = new int[3];

	public static boolean isHistoryNumber(String number, String[] deleteHistories){
		if(deleteHistories == null || deleteHistories.length == 0){
			return false;
		}
		for(String hStr : deleteHistories){
			if(hStr != null && hStr.endsWith(number)){
				return true;
			}
		}
		return false;
	}

	public static boolean isDeleteGroup(String number, String[][] deleteGroups, int lengh){
		if(deleteGroups != null && deleteGroups.length > 0){
			for(int i = 0; i < deleteGroups.length; i++){
				String[] garr = deleteGroups[i];
				if(garr != null && garr.length > 0 && isDeleteGroup(number, garr, lengh)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isDeleteGroup(String number, String[] deleteGroups, int lengh){
		for(int j = 0; j < lengh; j ++){
			String str = deleteGroups[deleteGroups.length - j - 1];
			if (str != null && str.length() > 0 && !containString(number, j, str)){
				return false;
			}
		}
		return true;
	}

	public static boolean isDeleteNumber(String number, int lengh, String[][] deleteNumbers){
		for(int i = 0; i < lengh; i++){
			if(containString(number, i, deleteNumbers[deleteNumbers.length - i - 1])){
				return true;
			}
		}
		return false;
	}

	public static boolean hasRepeatCount(String number){
		int lengh = number.length();
		for (int i = 0; i < lengh; i++) {
			for (int j = 0; j < lengh; j++) {
				if (i == j) {
					continue;
				}
				if (number.substring(i, i + 1).equals(number.substring(j, j + 1))) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean containString(String number, int index, String[] strArr){
		if(strArr == null || strArr.length < 1){
			return false;
		}
		for(int i = 0; i < strArr.length; i++){
			if(containString(number, index, strArr[i])){
				return true;
			}
		}
		return false;
	}

	public static boolean containString(String number, String str){
		if(number.contains(str)){
			return true;
		}
		return false;
	}

	public static boolean containString(String number, int index, String str){
		int lengh = number.length();
		int dex = lengh - index - 1;
		String temp = number.substring(dex, dex + 1);
		if(temp.equals(str)){
			return true;
		}
		return false;
	}

	public static boolean isAddNumberEquals(String number, int lastTotal, int lengh){
		int totalN = getAddTotal(number, lengh);
		if(totalN == lastTotal){
			return true;
		}
		return false;
	}

	public static int getAddTotal(String number, int lengh){
		int total = 0;
		for(int i = 0; i < lengh; i++){
			int index = number.length() - i;
			int iN = Integer.valueOf(number.substring(index - 1, index));
			total += iN;
		};
		return total;
	}

	public static String[][] getGroupsForThreeNumber(String number){
		String geStr = number.substring(number.length() - 1, number.length());
		String shiStr = number.substring(number.length() - 2, number.length() - 1);
		String baiStr = number.substring(number.length() - 3, number.length() - 2);
		String qianStr = number.substring(number.length() - 4, number.length() - 3);
		String wanStr = number.substring(number.length() - 4, number.length() - 3);

		String [][] groups = {
			{"", "", baiStr, shiStr, ""},
			{"", "", ""  ,shiStr, geStr},
			{"", "", baiStr  ,"", geStr},

			{"", "", shiStr, baiStr, ""},
			{"", "", "", geStr, shiStr},
			{"", "", geStr, "", baiStr},
		
			{"", "", shiStr, geStr, ""},
			{"", "", "", baiStr, shiStr},
			
			{"", "", geStr, shiStr, ""},
			{"", "", "", shiStr, baiStr},
			
			{"", "", baiStr, geStr, shiStr},
			{"", "", shiStr, geStr, baiStr},
			{"", "", geStr, shiStr, baiStr},
			{"", "", geStr, baiStr, shiStr},

			{"", "", qianStr, baiStr, shiStr},
			{"", "", baiStr, qianStr, shiStr},
			{"", "", shiStr, baiStr, qianStr},
			
			{"", "", wanStr, qianStr, baiStr},
			{"", "", qianStr, wanStr, baiStr},
			{"", "", baiStr, qianStr, wanStr},
		};
		return groups;
	}

	public static boolean isAddEqualsForBaiNumber(String number){
		int total = getAddTotalForType(number, TYPE_HOU2);
		String baiStr = number.substring(number.length() - 3, number.length() - 2);
		int iB = Integer.parseInt(baiStr);
		if(total == iB){
			return true;
		}
		return false;
	}

	public static boolean isAddEqualsForGeNumber(String number){
		int total = getAddTotalForType(number, TYPE_QIAN2);
		String geStr = number.substring(number.length() - 1, number.length());
		int iG = Integer.parseInt(geStr);
		if(total == iG){
			return true;
		}
		return false;
	}

	public static boolean isTwoNumberAddEqualsLast(String number, String lastNumber, int type){
		int totalN = getAddTotalForType(number, type);
		int totalL = getAddTotalForType(lastNumber, type);
		if(totalN == totalL){
			return true;
		}
		return false;
	}

	private static int getAddTotalForType(String number, int type){
		String geStr = number.substring(number.length() - 1, number.length());
		String shiStr = number.substring(number.length() - 2, number.length() - 1);
		String baiStr = number.substring(number.length() - 3, number.length() - 2);
		int iG = Integer.parseInt(geStr);
		int iS = Integer.parseInt(shiStr);
		int iB = Integer.parseInt(baiStr);
		if(type == TYPE_QIAN2){
			return (iB + iS);
		}else if(type == TYPE_HOU2){
			return (iS + iG);
		}
		return -1;
	}

	public static String getAllNumber(int lengh){
		StringBuffer buffer = new StringBuffer();
		int maxNumber = (int)Math.pow(10, lengh);
		String space = " ";
		for (int i = 0; i < maxNumber; i++) {
			addFormatNumber(buffer, i, lengh);
			if(i < maxNumber -1){
				buffer.append(space);
			}
		}
		return buffer.toString();
	}

	private static void addFormatNumber(StringBuffer buffer, int num, int lengh) {
		String numStr = String.valueOf(num);
		int l = lengh - numStr.length();
		for (int i = 0; i < lengh; i++) {
			if (i < l) {
				buffer.append("0");
			}
		}
		buffer.append(numStr);
	}

	public static String[][] getRandomDeleteANumber(String lastNumber){
		int index = (int)(Math.random() * 3);
		String[][] deleteNumbers = { {""},
			 {""},
			 {""},
			 {""},
			 {""}
			 };
		String s = lastNumber.substring(lastNumber.length() - 1 - index, lastNumber.length() - index);
		String[] a = { s };
		deleteNumbers[deleteNumbers.length - 1 - index] = a;
		return deleteNumbers;
	}

	public static String[] readHistoryNumber(){
		String dir = System.getProperty("user.dir");
		try {
			dir = URLDecoder.decode(dir,"UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String path = dir + "/res/history_numbers.txt";
		File file = new File(path);
		List<String> list = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                line++;
                list.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }       }
        String[] arr = new String[list.size()];
        list.toArray(arr);
        return arr;
	}

	public static int[] getJiOUArray(String number, boolean newArray){
		int[] arr = null;
		if(newArray){
			arr = new int[3];
		}else{
			arr = mTmpIntArr;
		}
		for(int i = 0; i < 3; i++){
			int index = number.length() - i;
			int v = Integer.valueOf(number.substring(index - 1, index));
			if(v % 2 == 0){
				arr[i] = TYPE_OU;
			}else{
				arr[i] = TYPE_JI;
			}
		}
		return arr;
	}

	public static boolean isEqualsIntArray(int[] arrA, int[] arrB){
		for(int i = 0; i < arrA.length; i++){
			if(arrA[i] != arrB[i]){
				return false;
			}
		}
		return true;
	}

	public static boolean isEqualsHewei(String number, String[] deleteArr){
		String geStr = number.substring(number.length() - 1, number.length());
		String shiStr = number.substring(number.length() - 2, number.length() - 1);
		String baiStr = number.substring(number.length() - 3, number.length() - 2);
		int iG = Integer.parseInt(geStr);
		int iS = Integer.parseInt(shiStr);
		int iB = Integer.parseInt(baiStr);
		int totlal = iG + iS + iB;
		if(deleteArr == null || deleteArr.length == 0){
			return false;
		}
		for(String str : deleteArr){
			if(str != null && str.length() > 0){
				int i = Integer.parseInt(str);
				if(totlal % 10 == i){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEqualsHou2he(String number, String[] deleteArr){
		String geStr = number.substring(number.length() - 1, number.length());
		String shiStr = number.substring(number.length() - 2, number.length() - 1);
		String baiStr = number.substring(number.length() - 3, number.length() - 2);
		int iG = Integer.parseInt(geStr);
		int iS = Integer.parseInt(shiStr);
		int iB = Integer.parseInt(baiStr);
		int totlal = iG + iS;
		if(deleteArr == null || deleteArr.length == 0){
			return false;
		}
		for(String str : deleteArr){
			if(str != null && str.length() > 0){
				int i = Integer.parseInt(str);
				if(totlal == i){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isDanmaNumber(String number, int lengh, String[][] deleteNumbers){
		for(int i = 0; i < lengh; i++){
			if(containString(number, i, deleteNumbers[deleteNumbers.length - i - 1])){
				return true;
			}
		}
		return false;
	}

	public static int[] getLushuArr(String numStr){
		int[] tem = new int[3];
		tem[0] = getLushu(numStr.substring(0, 1));
		tem[1] = getLushu(numStr.substring(1, 2));
		tem[2] = getLushu(numStr.substring(2, 3));
		return tem;
	}

	public static boolean isLushu(String numStr, int lB, int lS, int lG){
		if(isLshusss(numStr.substring(0, 1), lB) && isLshusss(numStr.substring(1, 2), lS) && 
				isLshusss(numStr.substring(2, 3), lG)){
			return true;
		}
		return false;
	}

	private static boolean isLshusss(String num, int l){
		if(l == 0 && LU0.contains(num)){
			return true;
		}else if(l == 1 && LU1.contains(num)){
			return true;
		}else if(l == 2 && LU2.contains(num)){
			return true;
		}
		return false;
	}

	private static int getLushu(String num){
		if(LU0.contains(num)){
			return 0;
		}else if(LU1.contains(num)){
			return 1;
		}else if(LU2.contains(num)){
			return 2;
		}
		return -1;
	}
}
