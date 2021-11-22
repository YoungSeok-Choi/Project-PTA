package board.board.mapper;

import java.util.List;    

import org.apache.ibatis.annotations.Mapper;

import board.board.dto.BoardDto;

@Mapper // mybatis의 매퍼 인터페이스임을 선언한다.
		//** interface임에 유의 !
public interface BoardMapper {
	// 인터페이스이기 때문에 메서드의 이름과 반환 형식만 지정한다. 이 메서드 이름은 'sql 쿼리 이름'과 동일해야 한다. 
	List<BoardDto> selectBoardList() throws Exception;
	void insertBoard(BoardDto board) throws Exception;
	void updateHitCount(int boardIdx) throws Exception;
	
	
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	
	void updateBoard(BoardDto board) throws Exception; // must add the login to sql-board.xml
	void deleteBoard(int boardIdx) throws Exception; // must add the login to sql-board.xml
}


