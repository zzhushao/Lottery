import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Lottery {

	public static NumberFormat NUMBERFORMAT3 = new DecimalFormat("000");

	public static void main(String[] args) {
//		makeBehindOfThreeNumbersMethod1();
		makeBehindOfThreeNumbersMethod2();
	}

	/**
	 * ���ɺ�3���뷽��1 ����3���� ��3��β
	 */
	private static void makeBehindOfThreeNumbersMethod1() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			String numStr = NUMBERFORMAT3.format(i);
			if (isContainNumbers(numStr, new String[]{"1", "4", "7"})) { //�����ĵ���
				if(!isEqualsWithEndNumber(numStr, 2) //���˺�βֵ
//						&& !isGroupThree(numStr)     //������3
						){ 
					stringBuffer.append(numStr +" ");
				}
			}
		}
		System.out.print(stringBuffer.toString());
	}

	/**
	 * ���ɺ�3���뷽��1 ��2����4���� ��1����4���� ��2��ֵ
	 */
	private static void makeBehindOfThreeNumbersMethod2(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			String numStr = NUMBERFORMAT3.format(i);
			String tensStr = numStr.substring(1, 2);
			String unitsStr = numStr.substring(2, 3);
			if (isContainNumbers(tensStr, new String[]{"0", "1", "3", "4"}) //ʮλ����
					|| isContainNumbers(unitsStr, new String[]{"0", "1", "3", "4"}) ) { //��λ����
				if(!isEqualsWithBehindOfTwoNumbersAdd(numStr, 7)){
					stringBuffer.append(numStr +" ");
				}
			}
		}
		System.out.print(stringBuffer.toString());
	}

	/**
	 * ����2��ֵ�Ƿ����
	 * @param value
	 * @param sum ƥ��ĺ�ֵ
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
	 * ����Ƿ�ָ����������
	 * @param vaule
	 * @param ���뼯��
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
	 * ����βֵ�Ƿ����
	 * @param value ���ֵ
	 * @param endNumber ��ֵβ��(0-9)
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
	 * �Ƿ�Ϊ��3
	 * @param value ���ֵ
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
