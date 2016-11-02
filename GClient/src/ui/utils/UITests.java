package ui.utils;

/**
 * class UITests have all methods to check valid inputs . the classes UI uses
 * this methods to check valid inputs
 * 
 * @author maisam marjieh
 *
 */
public class UITests {
	/**
	 * check if string is empty 
	 * @param str -string that will be checked 
	 * @return true if the string is not empty ,else rtuen false
	 */
	public static boolean notEmpty(String str) {
		if (str.length() == 0)
			return false;
		return true;

	}

	/**
	 * check if the birthday not empty 
	 * @param str  - Object contains birthday
	 * @return true if the birthday not empty , else return false 
	 */
	public static boolean notEmptybirth(Object str) {
		if (((String) str).compareTo("Year") == 0
				|| ((String) str).compareTo("Month") == 0
				|| ((String) str).compareTo("Day") == 0)
			return false;
		return true;

	}

	/**
	 * check if id is correct 
	 * @param id - the person id 
	 * @return true if id is correct ,id contains 9 char
	 */
	public static boolean correctId(String id) {
		if (id.length() != 9)
			return false;
		return true;
	}

	/**
	 * 
	 * @param name
	 * @return true if the name containers 9 chars 
	 */
	public static boolean correctName(String name) {
		if (name.length() != 9)
			return false;
		return true;
	}

	/**
	 * check if the string contains only character 
	 * @param field 
	 * @return true if string contains only  character  
	 */
	public static boolean checkIsCh(String field) {
		char[] array = field.toCharArray();

		for (int i = 0; i < field.length(); i++) {
			if (!(Character.isLetter(array[i])) && array[i] != ' ') {
				return false;
			}
		}
		return true;

	}

	/**
	 * check if string contains only digits 
	 * @param field
	 * @return true if the string Consists of digits , else return false 
	 */
	public static boolean checkIsDigit(String field) {
		char[] array = field.toCharArray();

		for (int i = 0; i < field.length(); i++) {
			if (!(Character.isDigit(array[i]))) {
				return false;
			}
		}
		return true;

	}

}
