package dev.mvc.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProc;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;

//view prefix: /WEB-INF/views/
//view suffix: .jsp

@Controller
public class CategoryCont {
	
	@Autowired
	@Qualifier("dev.mvc.category.CategoryProc") // -> 명시를 해주지 않으면, 인터페이스와 구현체 모두다 bean으로 등록해버림. 오류발생
	private CategoryProcInter cp;
	
	/*블로그에 빈이 인터페이스에 등록되어있을때랑, 클래스 빈등록 둘다 되어 싱글톤이 안되는 현상 정리하기.*/
	@Autowired // 초기에 Qualifier로 빈 이름을 지정해주어야한다??
	@Qualifier("dev.mvc.member.MemberProc")
	private MemberProcInter mp;
	
	// 생성할 회원정보 model에 담아 forward
	@GetMapping(value = "/category")
	public ModelAndView categoryform(int member_id) {
		
		ModelAndView mav = new ModelAndView();
		
		MemberVO memberVO = mp.readOne(member_id);
		
		mav.addObject("memberVO", memberVO);
		mav.setViewName("/category/create");
		return mav;
	}
	
	//@RequestParam을 붙이면 바인딩이 안된다... 뭐가 생략된 형태였었지..
	@PostMapping(value = "/category")
	public ModelAndView create(CategoryVO categoryVO) {
		
		ModelAndView mav = new ModelAndView();	
		int result = cp.create(categoryVO);
		
		//처리 됐는지 아닌지 판단해서 에러코드를 모델에 담아 view에서 처리 (리다이렉트와 viewpath 설정의 새로고침 차이 이해하기)
		if (result == 1) {
            mav.addObject("code", "create_success");
            
         // equals : response.sendRedirect("Path") + 여기는 컨트롤러 호출 경로를 명시해야함. jsp뷰리졸브 경로가 아님. 
            mav.setViewName("redirect:./members"); 
        } else {
            mav.addObject("code", "create_fail");
            mav.setViewName("/result_msg"); 
        }		
		return mav;
	}
	
	// memberlist에서는 jsp에서 mybatis로 바로 보냈음.. 여기서는 컨트롤러 단에서 해결해보기 (완)
	// 카테고리 목록 및 누구의 카테고리인지 표시하는 회원객체 read하여 view로 forward
	@GetMapping(value = "/categories")
	public ModelAndView listAllCate(int member_id) {
		
		ModelAndView mav = new ModelAndView();
		
		List<CategoryVO> categories = cp.listAllCate(member_id);
		MemberVO memberVO = mp.readOne(member_id);
		
		mav.addObject("categories", categories);
		mav.addObject("memberVO", memberVO);
		
		mav.setViewName("/category/categorylist");
		//빈 리스트 반환 처리하는 로직 일단 생략하고 개발. 추후에 개발할 것
		
		return mav;
	}
	
	
	@GetMapping(value = "/category/read_update")
	public ModelAndView readForUpdateCate(int cate_id) {
		
		ModelAndView mav = new ModelAndView();
		
		CategoryVO categoryVO = cp.readOne(cate_id);
		MemberVO memberVO = mp.readOne(categoryVO.getMember_id());
		
		mav.addObject("categoryVO", categoryVO);
		mav.addObject("memberVO", memberVO);
		
		mav.setViewName("/category/read_update");
		
		return mav;
	}
	
	//jsp에서 mybatis로 바로 넘어가는 부분 잘 연결이 안됨.{자주 복습 필요}
	@PostMapping(value = "/category/update.do")
	public ModelAndView update(CategoryVO categoryVO) {
		
		ModelAndView mav = new ModelAndView();
		
		// list로 전체 리스트 리턴해주는 부분 누락됨.
		int result = cp.updateCate(categoryVO);
		
		//예외처리 로직 추후 개발요망
		//처리 됐는지 아닌지 판단해서 에러코드를 모델에 담아 view에서 처리 (리다이렉트와 viewpath 설정의 새로고침 차이 이해하기)
		if (result == 1) {
            mav.addObject("code", "update_success");
            
         // equals : response.sendRedirect("Path") + 여기는 컨트롤러 호출 경로를 명시해야함. jsp뷰리졸브 경로가 아님. 
            mav.setViewName("redirect:/members"); 
        } else {
            mav.addObject("code", "create_fail");
            mav.setViewName("/result_msg"); 
        }		
		
		return mav;
	}
	
	@GetMapping(value = "/category/read_delete")
	public ModelAndView readForDeleteCate(int cate_id) {
		
		ModelAndView mav = new ModelAndView();
		
		CategoryVO categoryVO = cp.readOne(cate_id);
		MemberVO memberVO = mp.readOne(categoryVO.getMember_id());
		List<CategoryVO> categories = cp.listAllCate(categoryVO.getMember_id());
		
		mav.addObject("categoryVO", categoryVO);
		mav.addObject("memberVO", memberVO);
		mav.addObject("categories", categories);
		mav.setViewName("/category/read_delete");
		
		return mav;
	}
	
	@PostMapping(value = "/category/delete.do")
	public ModelAndView delete(int cate_id) {
		
		ModelAndView mav = new ModelAndView();
		
		int result = cp.deleteCate(cate_id);
		
		if (result == 1) {
            mav.addObject("code", "delete_success");
            
         // equals : response.sendRedirect("Path") + 여기는 컨트롤러 호출 경로를 명시해야함. jsp뷰리졸브 경로가 아님. 
            mav.setViewName("redirect:/members"); 
        } else {
            mav.addObject("code", "create_fail");
            mav.setViewName("/result_msg"); 
        }		
		return mav;
	}
}
