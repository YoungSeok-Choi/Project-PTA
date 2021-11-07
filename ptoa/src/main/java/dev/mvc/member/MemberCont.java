package dev.mvc.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * 절대경로 ./~
 * 상대경로 /~
 * */

// 서비스, dao 구현 및 사용설정하기.
@Controller
public class MemberCont {
	
	@Autowired
	@Qualifier("dev.mvc.member.MemberProc")
	private MemberProcInter mp;
	
	// 회원등록 폼 보여주기
	@GetMapping(value = "/member")
	public ModelAndView memberForm() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberform");
		
		return mav;
	}
		
	@PostMapping(value = "/member")
	public ModelAndView create(MemberVO memberVO) {
		
		int cnt = mp.create(memberVO);
		ModelAndView mav = new ModelAndView();
		
		/* 기본crud 연산 개발 완료 이후, 에러처리 부분 다루기.(미완) */
		if (cnt == 0) {
			mav.addObject("code", "enroll_fail");
		} else {
			mav.addObject("code", "enroll_success");
			
		}
		
		mav.setViewName("redirect:/members");		
		return mav;
	}
	
	@GetMapping(value = "/members")
	public ModelAndView readAllMembers() {
		
		ModelAndView mav = new ModelAndView();
		
		List<MemberVO> members = mp.readAllMembers();
		mav.addObject("members", members);
		
		mav.setViewName("/member/memberlist");
		return mav;
	}
	
	@GetMapping(value = "/read_update.do")
	public ModelAndView readForUpdate(int member_id) {
		
		ModelAndView mav = new ModelAndView();
		MemberVO memberVO = mp.readOne(member_id);
		List<MemberVO> members = mp.readAllMembers();
		
		mav.addObject("memberVO", memberVO);
		mav.addObject("members", members);
		mav.setViewName("/member/read_update");
		return mav;
	}
	
	@PostMapping(value = "/update.do")
	public ModelAndView updateMember(MemberVO memberVO) {
		
		ModelAndView mav = new ModelAndView();
		int cnt = mp.updateInfo(memberVO);
		
		/* 기본crud 연산 개발 완료 이후, 에러처리 부분 다루기.(미완) */
		if (cnt == 0) {
			mav.addObject("code", "update_fail");
		} else {
			mav.addObject("code", "update_success");
		}
		
		mav.setViewName("redirect:/members");
		
		return mav;
	}
	
	@GetMapping(value = "/read_delete.do")
	public ModelAndView readForDelete(int member_id) {
		
		ModelAndView mav = new ModelAndView();
		MemberVO memberVO = mp.readOne(member_id);
		List<MemberVO> members = mp.readAllMembers();
		
		mav.addObject("memberVO", memberVO);
		mav.addObject("members", members);
		mav.setViewName("/member/read_delete");
		return mav;
	}
	
	@PostMapping(value = "/delete.do")
	public ModelAndView deleteMember(int member_id) {
		
		ModelAndView mav = new ModelAndView();
		int cnt = mp.deleteMember(member_id);
		
	    /* 기본crud 연산 개발 완료 이후,
		 * 
		 *  에러처리 부분 다루기.(미완) 
		 *  */
		if (cnt == 0) {
			mav.addObject("code", "delete_fail");
		} else {
			mav.addObject("code", "delete_success");
		}
		
		mav.setViewName("redirect:/members");		
		return mav;
	}
}
