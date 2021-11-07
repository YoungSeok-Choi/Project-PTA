package dev.mvc.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberVO {
	
	//private long id;
	private int member_id;
	private String name;
	private String id;
	private String passwd;
	private String address;
	
	
	public MemberVO(String name, String id, String passwd, String address) {
		this.name = name;
		this.id = id;
		this.passwd = passwd;
		this.address = address;
	}
	
	public MemberVO() {	}
	
}
