package ETS.common.util;

import java.util.Random;

/************************************************** 
 * @FileName   : RandomNumber.java
 * @Description: 랜덤 함수
 * @Author     : joon
 * @Version    : 2016. 01. 19.
 * @Copyright  : ⓒBIG WAY. All Right Reserved
 **************************************************/
public class RandomNumber {
	
	/************************************************** 
	 * @MethodName : rndNum
	 * @Description: 파라미터 num 범위내 랜덤 숫자 1개 리턴 - 0미포함
	 * @param num
	 * @return int
	 * @Author     : joon
	 * @Version    : 2016. 01. 19.
	**************************************************/
	public static int rndNum(int num){
		
		Random rnd = new Random();
		
		int rndNum = rnd.nextInt(num) + 1;
		
		return rndNum;
	}
	
	/************************************************** 
	 * @MethodName : zeroRndNum
	 * @Description: 파라미터 num 범위내 랜덤 숫자 1개 리턴 - 0포함
	 * @param num
	 * @return int
	 * @Author     : joon
	 * @Version    : 2016. 01. 19.
	**************************************************/
	public static int zeroRndNum(int num){
		
		Random rnd = new Random();
		
		int rndNum = rnd.nextInt(num);
		
		return rndNum;
	}
	
	/************************************************** 
	 * @MethodName : rndLengthNum
	 * @Description: 1 ~ 9 랜덤 값으로 파라미터 num 길이의 값을 반환
	 * @param num
	 * @return String
	 * @Author     : joon
	 * @Version    : 2016. 01. 19.
	**************************************************/
	public static String rndLengthNum(int num){
		
		String rndNum = null;
		
		Random rnd = new Random();	
		
		for(int i=0;i<num;i++){
			int rNum = rnd.nextInt(9)+1;
			if(i == 0) rndNum = String.valueOf(rNum);
			else rndNum += String.valueOf(rNum);
		}
		
		return rndNum;
	}
	
	/************************************************** 
	 * @MethodName : zeroRndLengthNum
	 * @Description: 0 ~ 9 랜덤 값으로 파라미터 num 길이의 값을 반환
	 * @param num
	 * @return String
	 * @Author     : joon
	 * @Version    : 2016. 01. 19.
	**************************************************/
	public static String zeroRndLengthNum(int num){
		
		String rndNum = null;
		
		Random rnd = new Random();	
		
		for(int i=0;i<num;i++){
			int rNum = rnd.nextInt(9);
			if(i == 0) rndNum = String.valueOf(rNum);
			else rndNum += String.valueOf(rNum);
		}
		
		return rndNum;
	}

	public static void main(String[] arg) throws Exception{
		System.out.println("rndNum : " + rndNum(2));
		System.out.println("zeroRndNum : " + zeroRndNum(2));
		System.out.println("rndLengthNum : " + rndLengthNum(8));
		System.out.println("zeroRndLengthNum : " + zeroRndLengthNum(8));
	}
}
