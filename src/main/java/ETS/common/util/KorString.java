package ETS.common.util;

import java.util.regex.Pattern;

public class KorString {

	StringBuffer snt;

    static String[] josalist= new String[] {"은", "는", "이", "가", "을", "를", "과", "와", "으로", "로", "으로서", "로서", "으로서의", "로서의", "으로써", "로써"};

    public KorString() {
        this(null);
    }

    public KorString(String snt) {
        this.snt=new StringBuffer(snt==null ? "":snt);
    }

    /**
     * 마지막 글자가 한글이고 받침이 존재하는지를 체크함 .
     * @param value
     * @return
     */
	public boolean hasFinalConsonant() {
		if (snt == null || snt.length() == 0)
			return false;
		int code = snt.substring(snt.length() - 1).codePointAt(0);
		// 한글 문자이고 받침이 존재함
		if (code >= 44032 && code <= 55203 && (code - 44032) % 28 > 0) {
			return true;
		}

		return false;
	}
	
	/**
     * 마지막 글자가 숫자인지를 체크함 .
     * @param value
     * @return
     */
	public boolean hasFinalNumberic() {
		if (snt == null || snt.length() == 0)
			return false;
		
		String code = snt.substring(snt.length() - 1);

		if(Pattern.matches("^[0-9]+$", code)){
			return true;
		}else{
			return false;
		}
	}

    /**
     * 문자열을 더하는데, 별도의 처리를 하지 않고서
     * @param str
     * @return
     */
    public KorString appendOnly(String str) {
        this.snt.append(str);
        return this;
    }

    /**
     * 문자열을 더하는데, 첫번째 단어가 조사인경우에는 조사자동처리를 한다.
     * @param str
     * @return
     */
    public KorString append(String str) {
        int idx=str.indexOf(" ");
        if (idx<=0) return appendOnly(str);
        else {
            return appendJosa(str.substring(0, idx)).appendOnly(str.substring(idx));
        }
    }

    /**
     * 모든 문자열을 삭제한다.
     * @return
     */
    public KorString clear() {
        this.snt=new StringBuffer();
        return this;
    }

    /**
     * 조사를 더한다. (받침에 따라서 조사가 자동으로 변화하면서)
     * @param josa1
     * @return
     */
    public KorString appendJosa(String josa1) {
    	if(this.hasFinalNumberic()){
    		
    		String returnStr = josalist[2];
    		int num = Integer.parseInt(snt.substring(snt.length() - 1));
    		
    		switch(num){
    		case 1 :
    			returnStr = josalist[2];
    			break;
    		case 2 :
    			returnStr = josalist[3];
    			break;
    		case 3 :
    			returnStr = josalist[2];
    			break;
    		case 4 :
    			returnStr = josalist[3];
    			break;
    		case 5 :
    			returnStr = josalist[3];
    			break;
    		case 6 :
    			returnStr = josalist[2];
    			break;
    		case 7 :
    			returnStr = josalist[2];
    			break;
    		case 8 :
    			returnStr = josalist[2];
    			break;
    		case 9 :
    			returnStr = josalist[3];
    			break;
			default :
				returnStr = josalist[2];
    			break;
    				
    		}
    		
    		return appendOnly(returnStr);
    	}else if (this.hasFinalConsonant()) {
            for (int i=0;i<josalist.length;++i) {
                if (josa1.equals(josalist[i])) {
                    if (i%2==0)     return appendOnly(josalist[i]);
                    else     return appendOnly(josalist[i-1]);
                }
            }
        }else {
            for (int i=0;i<josalist.length;++i) {
                if (josa1.equals(josalist[i])) {
                    if (i%2==0)     return appendOnly(josalist[i+1]);
                    return appendOnly(josalist[i]);
                }
            }
        }
        return appendOnly(josa1);
    }

    public String toString() {
        return this.snt.toString();
    }
    
    public static void main(String[] args) {

    	String[] fruit_list=new String[] {"무한도전", "몬스터", "수상한 휴가 two", "복면가왕", "비정상회담", "더 벙커7", "더 벙커1", "더 벙커2", "더 벙커3", "더 벙커4", "더 벙커5", "더 벙커6", "더 벙커8", "더 벙커9", "더 벙커10", "더 벙커11"};

    	
    	KorString korString=new KorString();

    	
    	for (String fruit: fruit_list) {

            korString.clear().append(fruit).append("가 20:00에 방송됩니다.");

            System.out.println(korString.toString());            

        }
        

        // '를'과 '을' 어떤것을 사용해서 알아서 잘 조사를 붙여줍니다.

        System.out.println("[ 1 ]");

        for (String fruit: fruit_list) {

        

            korString.clear();

            korString.append("나는 ").append(fruit).appendJosa("를").append(" 좋아합니다.");

            System.out.println(korString.toString());

            

            korString.clear();

            korString.append("나는 ").append(fruit).appendJosa("을").append(" 좋아합니다.");

            System.out.println(korString.toString());

      

            System.out.println("-------------------------------------------------------------");

        }

      

        // append() 에 조사가 붙인 형식이면 알아서 잘 조사를 붙여줍니다.

        System.out.println("[ 2 ]");

        for (String fruit: fruit_list) {

      

            korString.clear();

            korString.append("나는 ").append(fruit).append("을 싫어합니다.");

            System.out.println(korString.toString());

            

            korString.clear();

            korString.append("나는 ").append(fruit).append("를 싫어합니다.");

            System.out.println(korString.toString());

            

            System.out.println("-------------------------------------------------------------");

        }

          

        

        // 좀더 다양한 조사를 붙이는 예시입니다. 받침신경쓰지 말고 그냥 적으면 됩니다.

        System.out.println("[ 3 ]");

        for (String fruit: fruit_list) {

            korString.clear().append(fruit).append("은 지금 10% 세일을 하고 있다.");

            System.out.println(korString.toString());            

        }

        System.out.println("-------------------------------------------------------------");

        

        for (String fruit: fruit_list) {

            korString.clear().append(fruit).append("이 가진 대표적인 영양소는 비타민이다.");

            System.out.println(korString.toString());            

        }

        System.out.println("-------------------------------------------------------------");

        

        for (String fruit: fruit_list) {

            korString.clear().append(fruit).append("와 참외는 오늘 다 판매가 되었습니다.");

            System.out.println(korString.toString());            

        }

        System.out.println("-------------------------------------------------------------");

        

        for (String fruit: fruit_list) {

            korString.clear().append("오늘부터 구매에 들어갈 상품은 ").append(fruit).append("로 결정되었습니다.");

            System.out.println(korString.toString());            

        }

        System.out.println("-------------------------------------------------------------");

        

    	
	}
}
