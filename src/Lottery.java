import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import ui.MainFrame;
import ui.MainFrame2;

public class Lottery {

	public static NumberFormat NUMBERFORMAT3 = new DecimalFormat("000");

	public static void main(String[] args) {
//		makeBehindOfThreeNumbersMethod1();
//		makeBehindOfThreeNumbersMethod3();
		makeBehindOfThreeNumbersMethod2();
		
		MainFrame2 mainFrame = new MainFrame2();
		mainFrame.show();
	}

	/**
	 * 生成后3号码方法1 胆码3个数 后3和尾
	 */
	private static void makeBehindOfThreeNumbersMethod1() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			String numStr = NUMBERFORMAT3.format(i);
			if (isContainNumbers(numStr, new String[]{"1", "4", "7"})) { //包含的胆码
				if(!isEqualsWithEndNumber(numStr, 2) //过滤和尾值
//						&& !isGroupThree(numStr)     //过滤组3
						){ 
					stringBuffer.append(numStr +" ");
				}
			}
		}
		System.out.print(stringBuffer.toString());
	}

	/**
	 * 生成后3号码方法1 后2胆码4个数 后1胆码4个数 后2和值
	 */
	private static void makeBehindOfThreeNumbersMethod2(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			String numStr = NUMBERFORMAT3.format(i);
			String tensStr = numStr.substring(1, 2);
			String unitsStr = numStr.substring(2, 3);
			
			if (isContainNumbers(tensStr, new String[]{"0", "1", "2", "3"}) //十位胆码
					|| isContainNumbers(unitsStr, new String[]{"2", "3", "0", "1"}) ) { //个位胆码
//				if(!isEqualsWithEndNumber(numStr, 8)){
				if(!isEqualsWithBehindOfTwoNumbersAdd(numStr, 6)){
					stringBuffer.append(numStr +" ");
				}
			}

//			if (isContainNumbers(tensStr, new String[]{"7", "6", "9", "5"}) //十位胆码
//					|| isContainNumbers(unitsStr, new String[]{"5", "7", "8", "9"}) ) { //个位胆码
////				if(!isEqualsWithBehindOfTwoNumbersAdd(numStr, 11)){
//				if(!isEqualsWithEndNumber(numStr, 0)){
//					stringBuffer.append(numStr +" ");
//				}
//			}
			
//			if (isContainNumbers(tensStr, new String[]{"2", "4", "5", "9"}) //十位胆码
//					|| isContainNumbers(unitsStr, new String[]{"2", "4", "5", "9"}) ) { //个位胆码
//				if(!isEqualsWithBehindOfTwoNumbersAdd(numStr, 7)){
//					stringBuffer.append(numStr +" ");
//				}
//			}

//			if (isContainNumbers(tensStr, new String[]{"4", "2", "6", "0"}) //十位胆码
//					|| isContainNumbers(unitsStr, new String[]{"4", "0", "8", "6"}) ) { //个位胆码
//				if(!isEqualsWithBehindOfTwoNumbersAdd(numStr, 7)){
////				if(!isEqualsWithEndNumber(numStr, 3)){
//					stringBuffer.append(numStr +" ");
//				}
//			}

//			if (isContainNumbers(tensStr, new String[]{"5", "7", "3", "9"}) //十位胆码
//			|| isContainNumbers(unitsStr, new String[]{"7", "9", "5", "1"}) ) { //个位胆码
////				if(!isEqualsWithBehindOfTwoNumbersAdd(numStr, 7)){
//				if(!isEqualsWithEndNumber(numStr, 8)){
//					stringBuffer.append(numStr +" ");
//				}
//			}
		}
		System.out.print(stringBuffer.toString());
	}


	/**
	 * 生成后3号码方法1 后2胆码4个数 后1胆码4个数 后2和值
	 */
	private static void makeBehindOfThreeNumbersMethod3(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			String numStr = NUMBERFORMAT3.format(i);
			String aaStr = numStr.substring(0, 1);
			String tensStr = numStr.substring(1, 2);
			String unitsStr = numStr.substring(2, 3);
			
			if (isContainNumbers(tensStr, new String[]{"2", "4", "0", "6"}) //十位胆码
					|| isContainNumbers(unitsStr, new String[]{"4", "0", "6", "2"})
							|| isContainNumbers(aaStr, new String[]{"4", "0", "6", "2"}) ) { //个位胆码
				if(!isEqualsWithEndNumber(numStr, 0)){
//				if(!isEqualsWithBehindOfTwoNumbersAdd(numStr, 7)){
					stringBuffer.append(numStr +" ");
				}
			}
		}
		System.out.print(stringBuffer.toString());
	}


	/**
	 * 检查后2和值是否相等
	 * @param value
	 * @param sum 匹配的和值
	 */
	private static boolean isEqualsWithBehindOfTwoNumbersAdd(String value, int sum){
		int tens = Integer.parseInt(value.substring(1, 2));
		int units = Integer.parseInt(value.substring(2, 3));
		if((tens + units) == sum){
			return true;
		}
		return false;
	}

	/**
	 * 检查是否指定包含胆码
	 * @param vaule
	 * @param 胆码集合
	 */
	private static boolean isContainNumbers(String value, String[] numbers){
		for(String str : numbers){
			if(value.contains(str)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查和尾值是否相等
	 * @param value 检测值
	 * @param endNumber 和值尾数(0-9)
	 */
	private static boolean isEqualsWithEndNumber(String value, int endNumber){
		int kilobit = Integer.parseInt(value.substring(0, 1));
		int tens = Integer.parseInt(value.substring(1, 2));
		int units = Integer.parseInt(value.substring(2, 3));

		int sum1 = endNumber;
		int sum2 = endNumber + 10;
		int sum3 = endNumber + 20;
		int total = kilobit + tens + units ;
		if(total == sum1 || total == sum2 || total == sum3){
			return true;
		}
		return false;
	}

	/**
	 * 是否为组3
	 * @param value 检测值
	 */
	private static boolean isGroupThree(String value) {
		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (value.indexOf(c, i + 1) > -1) {
				Character ch = new Character(c);
				if (!list.contains(ch))
					list.add(ch);
			}
		}
		if (list.size() > 0) {
			return true;
		}
		return false;
	}
}
