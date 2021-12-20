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
	
	// each = ������ ����, ī�װ��̸�, ��������
	private int cnt;
	private String name;
	private String cdate;
	
	// ���⼭ �ڵ��������� ��� ����Ǵ� ������ ���� �����ڿ� ����.. member_id�� cī���ͺ����� ���ε��� �Ǿ������
	// �̸��� �Է¹ް�, �������� jsp���� �����ų� db ����Ʈ�� ���
	public CategoryVO(String name) { this.name = name; }
	
	public CategoryVO() {
		
	}

}
