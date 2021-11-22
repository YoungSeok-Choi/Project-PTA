package board.board.controller;

import board.board.dto.BoardDto;    
import board.board.service.BoardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller // Spring MVC 컨트롤러 --> 해당 클래스를 컨트롤러로 동작하게 함.
public class BoardController {
	
	// logger - openBoardList,
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	// 비즈니스 로직을 처리하는 서비스 빈을 연결한다.
	@Autowired
	private BoardService boardService; // 사용자 정의 클래스 사용.
	
	// 주소 요청 --> 동일한 메서드를 찾아서 실행. 
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception{
		// use Logger - print log
		log.debug("openBoardList!"); 
		
		// 호출 결과로 보여줄 뷰를 지정하여 초기화. boardList.html를 의미하는데 Thymeleaf 덕에 .html 생략.
		ModelAndView mv = new ModelAndView("board/boardList"); 
		
		// 게시글 목록을 조회(select)한다. 즉 비즈니스 로직을 수행. 저장을 위해 List 인터페이스로 받음.
		List<BoardDto> list = boardService.selectBoardList();
		// 뷰에 실행된 비즈니스 로직의 결과 값을 list라는 이름으로 저장. 뷰에서 사용할 경우 list라는 이름으로 조회 결과를 사용할 수 있음.
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception{
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
		ModelAndView mv =  new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(boardIdx); // must add the logic to Service
		mv.addObject("board", board);
		
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board); // must add the logic to Service
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx); // must add the logic to Service
		return "redirect:/board/openBoardList.do";
	}
	
	
	
	
	
	
}