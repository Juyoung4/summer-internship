package com.juyoung.dto;

import org.springframework.stereotype.Component;

@Component
public class MemberVO {
	//table의 칼럼에 해당되는 변수를 만들고, getter와 setter 메소드를 통해 data를 가져오거나 넣을 수 있음
	private String id;
    private String pw;
    private String name;
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getPw() {
        return pw;
    }
 
    public void setPw(String pw) {
        this.pw = pw;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    @Override
	public String toString() {
		return "\n====================\n"
				+ "MemberVO [member_id=" + id + ", member_pw="+ pw+"\n" 
				+ ", member_name=" + name +"\n====================\n";
	}

}
