package board.board.service;

import java.util.List;

import board.board.dto.BoardDto;

//** 이 인터페이스(interface) 키워드 하나로 에러를 모두 없앤다.
public interface BoardService {
	// 서비스 영역의 인터페이스이기 때문에 특별히 살펴볼 내용은 없다. Impl인 구현체에 있다.
	List<BoardDto> selectBoardList() throws Exception;
	
	void insertBoard(BoardDto board) throws Exception;
	
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	void updateBoard(BoardDto board) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
}


