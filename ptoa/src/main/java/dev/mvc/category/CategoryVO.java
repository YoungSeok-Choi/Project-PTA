package dev.mvc.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CategoryVO {
	
	//PK
	private int cate_id;
	//FK
	private int member_id;
	
	// each = 컨텐츠 숫자, 카테고리이름, 생성일자
	private int cnt;
	private String name;
	private String cdate;
	
	// 여기서 자동생성으로 디비에 저장되는 사진의 수가 생성자에 있음.. member_id가 c카운터변수에 바인딩이 되어버린듯
	// 이름만 입력받고, 나머지는 jsp에서 보내거나 db 디폴트값 사용
	public CategoryVO(String name) { this.name = name; }
	
	public CategoryVO() {
		
	}

}
