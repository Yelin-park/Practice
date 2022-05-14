package practice_days04;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BoardDAO {
	
	// 게시글 조회
	ArrayList<BoardDTO> select() throws SQLException;
	
	ArrayList<BoardDTO> select(int currentPage, int numberPerPage) throws SQLException;
	
	// 새글추가
	int insert(BoardDTO dto) throws SQLException;
	
	int getTotalPages(int numberPerPage) throws SQLException;
	
	int getTotalPages(int numberPerPage, int searchCondition, String searchWord) throws SQLException;
	
	int getTotalRecords() throws SQLException;

	BoardDTO view(int seq) throws SQLException;
	
	void increaseReaded(int seq) throws SQLException;

	int delete(int seq) throws SQLException;

	int update(BoardDTO dto) throws SQLException;

	ArrayList<BoardDTO> search(int searchCondition, String searchWord) throws SQLException;
	
	ArrayList<BoardDTO> search(int searchCondition, String searchWord, int currentPage, int numberPerPage) throws SQLException;
	
}
