package board.board.service;

import board.board.mapper.BoardMapper; 
import board.board.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Spring MVC의 서비스임을 나타낼 때 사용하는 어노테이션. 비즈니스 로직을 처리하는 서비스 클래스를 나타낸다.
public class BoardServiceImpl implements BoardService{
	
	// database에 접근하는 DAO 빈을 선언
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> selectBoardList() throws Exception{
		// 사용자 요청을 처리하기 위한 비즈니스 로직. controller와 호응하므로 서로 비슷하다.
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board) throws Exception{
		boardMapper.insertBoard(board);
	}
	
	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception{ // return -> BoardDto
		boardMapper.updateHitCount(boardIdx);
		//int wow = 10/0; // Exception Making to check Transaction RollBack ! 
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		return board;
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception{
		boardMapper.updateBoard(board); // must add the logic to Mapper
	}
	
	@Override
	public void deleteBoard(int boardIdx) throws Exception{
		boardMapper.deleteBoard(boardIdx); // must add the logic to Mapper
	}
}
