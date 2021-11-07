package dev.mvc.ptoa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	//main page
	@GetMapping(value = "/" )
	public ModelAndView home() {
		
		ModelAndView mav =  new ModelAndView();
		mav.setViewName("index");
		
		return mav;
	}

}
