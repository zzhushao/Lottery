package data;

public class Heima {

	public static void test(){
		String[] arr = Numbers.DATA.split(" ");
		int lastTotal = MethodUtils.getAddTotal(Numbers.mDeleteHistories[Numbers.mDeleteHistories.length - 1], 3);
		String[][] deleteGroups = MethodUtils.getGroupsForThreeNumber(Numbers.mDeleteHistories[Numbers.mDeleteHistories.length - 1]);

		for(int i = 0; i < arr.length; i++){
			String number = arr[i];
			if (
//					Test.hasRepeatCount(number) ||
					MethodUtils.isDeleteNumber(number, 3, Numbers.mDeleteNumbers) 
//					MethodUtils.isDeleteGroup(number, Numbers.mDeleteGroups, 3) ||
//					MethodUtils.isAddNumberEquals(number, lastTotal, 3) ||
//					MethodUtils.isDeleteGroup(number, deleteGroups, 3)
//					Main.isHistoryNumber(number, Numbers.mDeleteHistories)
					) {

			}else{
				System.out.print(number);
				System.out.print(" ");
			}
			
		}
	}

	private static boolean containNumber(String[] array, String data, int index){
		for(int j = 0; j < array.length; j++){
			if(containNumber(data, index, array[j])){
				return true;
			}
		}
		return false;
	}

	private static boolean containNumber(String data, int index, String number){
		int lengh = data.length();
		int dex = lengh - index - 1;
		String str = data.substring(dex, dex + 1);
		if(str.equals(number)){
			return true;
		}
		return false;
	}
}
