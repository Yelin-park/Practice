package practice_days04;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardService {
	private BoardDAO dao = null;
	
	public BoardService() {}
	
	public BoardService(BoardDAO dao) {
		this.dao = dao;
	}

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	public ArrayList<BoardDTO> serviceSelect(int currentPage, int numberPerPage) {
		ArrayList<BoardDTO> list = null;
		try {
			list = this.dao.select(currentPage, numberPerPage);
			System.out.println("게시글 조회 - 로그 기록");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int serviceInsert(BoardDTO dto) {
		int rowCount = 0;
		try {
			((BoardDAOImpl) this.dao).getConn().setAutoCommit(false);
			rowCount = this.dao.insert(dto);
			System.out.println("게시글 추가 - 로그 기록");
			((BoardDAOImpl) this.dao).getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				((BoardDAOImpl) this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				((BoardDAOImpl) this.dao).getConn().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	public String servicePage(int currentPage, int numberPerPage, int numberOfPageBlock) {
		String pagingBlock = "\t\t\t";

		try {
			int totalPages = this.dao.getTotalPages(numberPerPage);
			int startBlock = 1;
			int endBlock;
			
			startBlock = (currentPage-1) / numberOfPageBlock * numberOfPageBlock + 1;
			endBlock = startBlock + numberOfPageBlock - 1;
			if (endBlock > totalPages) endBlock = totalPages;
			
			if(startBlock != 1) pagingBlock += " < ";
			for (int i = startBlock; i <=endBlock ; i++) {
				pagingBlock += String.format(i == currentPage ? "[%d] " : "%d ", i);
			}
			if(endBlock != totalPages) pagingBlock += " > ";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pagingBlock;
	}
	
	public String servicePage(int searchCondition, String searchWord, int currentPage, int numberPerPage, int numberOfPageBlock) {
		String paginBlock = "\t\t\t";
		
		try {
			int totalPages = this.dao.getTotalPages(numberPerPage, searchCondition, searchWord);
			int startBlock = 1;
			int endBlock;
			
			startBlock = (currentPage - 1) / numberOfPageBlock * numberOfPageBlock + 1;
			endBlock = startBlock + numberOfPageBlock - 1;
			if(endBlock > totalPages) endBlock = totalPages;
			if (startBlock != 1)  paginBlock += " < ";
			for (int i = startBlock; i <= endBlock; i++) {
				paginBlock += String.format(i == currentPage ? "[%d] " : "%d ", i);
			}
			if (endBlock != totalPages)  paginBlock += " > ";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paginBlock;
	}

	public BoardDTO serviceView(int seq) {
		BoardDTO dto = null;
		
		try {
			this.dao.increaseReaded(seq);
			dto = this.dao.view(seq);
			System.out.println("> 게시글 상세보기 - 로그 기록");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public int deleteService(int seq) {
		int rowCount = 0;
		
		try {
			rowCount = this.dao.delete(seq);
			System.out.println("> 게시글 삭제하기 - 로그기록");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}

	public int updateService(BoardDTO dto) {
		int rowCount = 0;
		
		try {
			rowCount = this.dao.update(dto);
			System.out.println("> 게시글 수정하기 - 로그기록");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}

	public ArrayList<BoardDTO> searchService(int searchCondition, String searchWord) {
		ArrayList<BoardDTO> list = null;
		try {
			list = this.dao.search(searchCondition, searchWord);
			System.out.println("> 게시글 검색하기 - 로그기록");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<BoardDTO> searchService(int searchCondition, String searchWord, int currentPage, int numberPerPage) {
		ArrayList<BoardDTO> list = null;
		try {
			list = this.dao.search(searchCondition, searchWord, currentPage, numberPerPage);
			System.out.println("> 게시글 검색하기 - 로그기록");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
