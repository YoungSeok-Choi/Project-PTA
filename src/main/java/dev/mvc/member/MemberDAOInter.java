package dev.mvc.member;

import java.util.List;


// MemberDAO interface 구현체 클래스는 스프링이 자동생성
public interface MemberDAOInter {
	
	/**
	 * 
	 * @param user
	 * @return INT
	 */
	public int create(MemberVO memberVO);
	
	/**
	 * 
	 * @return List<User>
	 */
	public List<MemberVO> readAllMembers();

	/**
	 * 
	 * @param member_id
	 * @return one of MemberVO instance
	 */
	public MemberVO readOne(int member_id);
	
	/**
	 * 
	 * @param memberVO
	 * @return
	 */
	public int updateInfo(MemberVO memberVO);
	
	
	/**
	 * 
	 * @param member_id
	 * @return
	 */
	public int deleteMember(int member_id);
	
}
