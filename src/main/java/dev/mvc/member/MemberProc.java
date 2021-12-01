package dev.mvc.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//service
@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
	
	@Autowired
	private MemberDAOInter memberDAO;

	@Override
	public int create(MemberVO memberVO) { return memberDAO.create(memberVO); }

	@Override
	public List<MemberVO> readAllMembers() { return memberDAO.readAllMembers(); }

	@Override
	public MemberVO readOne(int member_id) { return memberDAO.readOne(member_id); }

	@Override
	public int updateInfo(MemberVO memberVO) { return memberDAO.updateInfo(memberVO); }

	@Override
	public int deleteMember(int member_id) { return memberDAO.deleteMember(member_id); }
	
	
}
