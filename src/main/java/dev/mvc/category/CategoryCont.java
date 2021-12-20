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
	@Qualifier("dev.mvc.category.CategoryProc") // -> ��ø� ������ ������, �������̽��� ����ü ��δ� bean���� ����ع���. �����߻�
	private CategoryProcInter cp;
	
	/*��α׿� ���� �������̽��� ��ϵǾ���������, Ŭ���� ���� �Ѵ� �Ǿ� �̱����� �ȵǴ� ���� �����ϱ�.*/
	@Autowired // �ʱ⿡ Qualifier�� �� �̸��� �������־���Ѵ�??
	@Qualifier("dev.mvc.member.MemberProc")
	private MemberProcInter mp;
	
	// ������ ȸ������ model�� ��� forward
	@GetMapping(value = "/category")
	public ModelAndView categoryform(int member_id) {
		
		ModelAndView mav = new ModelAndView();
		
		MemberVO memberVO = mp.readOne(member_id);
		
		mav.addObject("memberVO", memberVO);
		mav.setViewName("/category/create");
		return mav;
	}
	
	//@RequestParam�� ���̸� ���ε��� �ȵȴ�... ���� ������ ���¿�����..
	@PostMapping(value = "/category")
	public ModelAndView create(CategoryVO categoryVO) {
		
		ModelAndView mav = new ModelAndView();	
		int result = cp.create(categoryVO);
		
		//ó�� �ƴ��� �ƴ��� �Ǵ��ؼ� �����ڵ带 �𵨿� ��� view���� ó�� (�����̷�Ʈ�� viewpath ������ ���ΰ�ħ ���� �����ϱ�)
		if (result == 1) {
            mav.addObject("code", "create_success");
            
         // equals : response.sendRedirect("Path") + ����� ��Ʈ�ѷ� ȣ�� ��θ� ����ؾ���. jsp�丮���� ��ΰ� �ƴ�. 
            mav.setViewName("redirect:./members"); 
        } else {
            mav.addObject("code", "create_fail");
            mav.setViewName("/result_msg"); 
        }		
		return mav;
	}
	
	// memberlist������ jsp���� mybatis�� �ٷ� ������.. ���⼭�� ��Ʈ�ѷ� �ܿ��� �ذ��غ��� (��)
	// ī�װ� ��� �� ������ ī�װ����� ǥ���ϴ� ȸ����ü read�Ͽ� view�� forward
	@GetMapping(value = "/categories")
	public ModelAndView listAllCate(int member_id) {
		
		ModelAndView mav = new ModelAndView();
		
		List<CategoryVO> categories = cp.listAllCate(member_id);
		MemberVO memberVO = mp.readOne(member_id);
		
		mav.addObject("categories", categories);
		mav.addObject("memberVO", memberVO);
		
		mav.setViewName("/category/categorylist");
		//�� ����Ʈ ��ȯ ó���ϴ� ���� �ϴ� �����ϰ� ����. ���Ŀ� ������ ��
		
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
	
	//jsp���� mybatis�� �ٷ� �Ѿ�� �κ� �� ������ �ȵ�.{���� ���� �ʿ�}
	@PostMapping(value = "/category/update.do")
	public ModelAndView update(CategoryVO categoryVO) {
		
		ModelAndView mav = new ModelAndView();
		
		// list�� ��ü ����Ʈ �������ִ� �κ� ������.
		int result = cp.updateCate(categoryVO);
		
		//����ó�� ���� ���� ���߿��
		//ó�� �ƴ��� �ƴ��� �Ǵ��ؼ� �����ڵ带 �𵨿� ��� view���� ó�� (�����̷�Ʈ�� viewpath ������ ���ΰ�ħ ���� �����ϱ�)
		if (result == 1) {
            mav.addObject("code", "update_success");
            
         // equals : response.sendRedirect("Path") + ����� ��Ʈ�ѷ� ȣ�� ��θ� ����ؾ���. jsp�丮���� ��ΰ� �ƴ�. 
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
            
         // equals : response.sendRedirect("Path") + ����� ��Ʈ�ѷ� ȣ�� ��θ� ����ؾ���. jsp�丮���� ��ΰ� �ƴ�. 
            mav.setViewName("redirect:/members"); 
        } else {
            mav.addObject("code", "create_fail");
            mav.setViewName("/result_msg"); 
        }		
		return mav;
	}
}
