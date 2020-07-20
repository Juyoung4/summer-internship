package ETS.common.util;

public class ReplaceString {

	public static void main(String[] args) throws Exception {
        String str = "1995/07/04";
        
        System.out.println(replaceString(str, ""));
    }
	
	/**
	 * Turn gives the desired string.
	 * @param str
	 * @param oldChar
	 * @param newChar
	 * @return
	 * @throws Exception
	 */
	public static String replaceNoLoopString(String str, String oldChar, String newChar){
		
		str = str.replace(oldChar, newChar);
//		System.out.println("str : " + str);
		
		return str;
	}
	
	/**
	 * Turn gives the desired string.
	 * @param str
	 * @param oldChar
	 * @param newChar
	 * @return
	 * @throws Exception
	 */
	public static String replaceString(String str, String oldChar, String newChar){
		
		while(str.indexOf(oldChar) > -1){
			str = str.replace(oldChar, newChar);
//			System.out.println("str : " + str);
		}
		
		return str;
	}
	
	/**
	 * Give words special symbols.
	 * @param str
	 * @param newChar
	 * @return
	 * @throws Exception
	 */
	public static String replaceString(String str, String newChar){
		
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str =str.replaceAll(match, newChar);
		
		return str;
	}
}
