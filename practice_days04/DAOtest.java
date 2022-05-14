package practice_days04;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.util.DBconn;

class DAOtest {
	
	
	@Test
	void insert_test() {
		Connection conn = DBconn.getConnection();
		BoardDAOImpl dao = new BoardDAOImpl(conn);
		
		try {
			BoardDTO dto = new BoardDTO();
			// writer, pwd, email, title, tag, content
			dto.setWriter("홍길동");
			dto.setPwd("1234");
			dto.setEmail("hong@naver.com");
			dto.setTitle("첫 번째 게시글");
			dto.setTag(0); // 0 텍스트모드, 1 html 모드
			dto.setContent("첫 번째 게시글 내용...");
			
			int rowCount = dao.insert(dto);
			
			if (rowCount == 1) {
				System.out.println("> 새 게시글 작성 완료!");
			} else {
				System.out.println("> 게시글 작성 실패!");
			} // if
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close();
		} // try
	}
	
	@Test
	void select_test() {
		Connection conn = DBconn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		ArrayList<BoardDTO> list = null;
		try {
			list = dao.select();
			if (list == null) {
				System.out.println("게시글이 존재하지 않습니다.");
			} else {
				Iterator<BoardDTO> ir = list.iterator();
				while (ir.hasNext()) {
					BoardDTO dto = ir.next();
					System.out.println(dto);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
} // class
