package practice_days04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBconn;

public class BoardController {
	private Scanner sc = null;
	private BoardService service = null;
	private int selectedNumber;
	
	private int currentPage = 1;
	private int numberPerPage = 15;
	private int numberOfPageBlock = 10;
	
	public BoardController() {
		this.sc = new Scanner(System.in);
	}

	public BoardController(BoardService service) {
		this();
		this.service = service;
	}
	
	public void start() {
		while (true) {
			메뉴출력();
			메뉴선택();
			메뉴처리();
		} // while
	}

	private void 메뉴처리() {
		switch (selectedNumber) {
		case 1: // 새글
			새글쓰기();
			break;
		case 2: // 목록
			목록보기();
			break;
		case 3: // 상세보기
			상세보기();
			break;
		case 4: // 수정하기
			수정하기();
			break;
		case 5: // 삭제하기
			삭제하기();
			break;
		case 6: // 검색하기
			검색하기();
			break;
		case 7:
			exit();
			break;
		}
	}
	
	private void 목록보기() {
		System.out.print("> 몇 페이지를 보시겠습니까? ");
		this.currentPage = this.sc.nextInt();
		
		ArrayList<BoardDTO> list = this.service.serviceSelect(this.currentPage, this.numberPerPage);
		
		System.out.println("\t\t\t== 게시판 == ");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.printf("%s\t%-10s\t%-10s\t%s\t\t%s\n", "글번호", "작성자", "제목", "작성일자", "조회수");
		System.out.println("-------------------------------------------------------------------------------------");
		if (list == null) {
			System.out.println("[알림] 게시글이 존재하지 않습니다.");
		} else {
			Iterator<BoardDTO> ir = list.iterator();
			while (ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.printf("%d\t%-10s\t%-10s\t%s\t%d\n",dto.getSeq(), dto.getWriter(), dto.getTitle(), dto.getWritedate(), dto.getReaded());
			} // while
			System.out.println("-------------------------------------------------------------------------------------");
			String pagingBlock = this.service.servicePage(this.currentPage, this.numberPerPage, this.numberOfPageBlock);
			System.out.println(pagingBlock);
			System.out.println("-------------------------------------------------------------------------------------");
		} // if
		
		일시정지();
	} // 목록보기
	
	private void 검색하기() {
		System.out.println("1. 제목, 2. 내용, 3. 작성자, 4. 제목+내용");
		System.out.print("> 검색 조건을 선택하세요? ");
		int searchCondition = this.sc.nextInt();
		System.out.print("> 검색 단어를 입력하세요? ");
		String searchWord = this.sc.next();
		System.out.print("> 몇 페이지를 보시겠습니까? ");
		this.currentPage = this.sc.nextInt();
		
		ArrayList<BoardDTO> list = this.service.searchService(searchCondition, searchWord, this.currentPage, this.numberPerPage);
		
		System.out.println("\t\t\t== 게시판 == ");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.printf("%s\t%-10s\t%-10s\t%s\t\t%s\n", "글번호", "작성자", "제목", "작성일자", "조회수");
		System.out.println("-------------------------------------------------------------------------------------");
		if (list == null) {
			System.out.println("[알림] 게시글이 존재하지 않습니다.");
		} else {
			Iterator<BoardDTO> ir = list.iterator();
			while (ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.printf("%d\t%-10s\t%-10s\t%s\t%d\n",dto.getSeq(), dto.getWriter(), dto.getTitle(), dto.getWritedate(), dto.getReaded());
			} // while
			System.out.println("-------------------------------------------------------------------------------------");
			String pagingBlock = this.service.servicePage(searchCondition, searchWord, this.currentPage, this.numberPerPage, this.numberOfPageBlock);
			System.out.println(pagingBlock);
			System.out.println("-------------------------------------------------------------------------------------");
		} // if
		
		일시정지();
		
	}

	private void 삭제하기() {
		int seq;
		System.out.print("> 글번호(seq)를 입력하세요? ");
		seq = this.sc.nextInt();
		
		int rowCount = this.service.deleteService(seq);
		if (rowCount == 1) {
			System.out.printf("[알림] %d번 게시글 삭제 완료!\n", seq);
		} else {
			System.out.println("[알림] 게시글 삭제 실패..");
		}
	}

	private void 수정하기() {
		System.out.print("> 글번호(seq)를 입력하세요? ");
		int seq = this.sc.nextInt();
		BoardDTO dto = null;
		dto = this.service.serviceView(seq);
		if (dto == null) {
			System.out.println("[알림] 해당 게시글은 존재하지 않습니다.");
			return;
		} else {
			System.out.println("-----------------------------------");
			System.out.println("\t == 게시글 상세보기 ==");
			System.out.println("-----------------------------------");
			System.out.printf("1) 글번호 : %d\n", seq);
			System.out.printf("2) 작성자 : %s\n", dto.getWriter());
			System.out.printf("3) 이메일 : %s\n", dto.getEmail());
			System.out.printf("4) 글제목 : %s\n", dto.getTitle());
			System.out.printf("5) 작성일자 : %s\n", dto.getWritedate());
			System.out.printf("6) 조회수 : %d\n", dto.getReaded());
			System.out.printf("7) 내용 : %s\n", dto.getContent());
			System.out.println("-----------------------------------");
		}
		
		System.out.print("> 수정할 이메일을 입력하세요? ");
		String email = this.sc.next();
		System.out.print("> 수정할 제목을 입력하세요? ");
		String title = this.sc.next();
		System.out.print("> 수정할 내용을 입력하세요? ");
		String content = this.sc.next();
		
		dto = new BoardDTO();
		dto.setSeq(seq);
		dto.setEmail(email);
		dto.setTitle(title);
		dto.setContent(content);
		
		int rowCount = this.service.updateService(dto);
		
		if (rowCount == 1) {
			System.out.printf("[알림] %d번 게시글 수정 완료!\n", seq);
		} else {
			System.out.println("[알림] 게시글 수정 실패..");
		}
	}

	private void 상세보기() {
		int seq;
		System.out.print("> 글번호(seq)를 입력하세요? ");
		seq = this.sc.nextInt();
		
		BoardDTO dto = this.service.serviceView(seq);
		if (dto == null) {
			System.out.println("[알림] 해당 게시글은 존재하지 않습니다.");
			return;
		}
		
		System.out.println("-----------------------------------");
		System.out.println("\t == 게시글 상세보기 ==");
		System.out.println("-----------------------------------");
		System.out.printf("1) 글번호 : %d\n", seq);
		System.out.printf("2) 작성자 : %s\n", dto.getWriter());
		System.out.printf("3) 이메일 : %s\n", dto.getEmail());
		System.out.printf("4) 글제목 : %s\n", dto.getTitle());
		System.out.printf("5) 작성일자 : %s\n", dto.getWritedate());
		System.out.printf("6) 조회수 : %d\n", dto.getReaded());
		System.out.printf("7) 내용 : %s\n", dto.getContent());
		System.out.println("-----------------------------------");
		
		일시정지();
	}

	private void 새글쓰기() {
		BoardDTO dto = null;
		
		String writer, pwd, email, title, content;
		int tag;
		System.out.print("> 작성자를 입력하세요? ");
		writer = this.sc.next();
		System.out.print("> 비밀번호를 입력하세요? ");
		pwd = this.sc.next();
		System.out.print("> E-Mail을 입력하세요? ");
		email = this.sc.next();
		System.out.print("> 제목을 입력하세요? ");
		title = this.sc.next();
		System.out.print("> Tag를 입력하세요? ");
		tag = this.sc.nextInt();
		System.out.print("> 내용을 입력하세요? ");
		content = this.sc.next();
		
		dto = new BoardDTO();
		
		dto.setWriter(writer);
		dto.setPwd(pwd);
		dto.setEmail(email);
		dto.setTitle(title);
		dto.setTag(tag);
		dto.setContent(content);
		
		int rowCount = this.service.serviceInsert(dto);
		if (rowCount == 1) {
			System.out.println("[알림] 새글 쓰기 완료!");
		} else {
			System.out.println("[알림] 새글 쓰기 실패..");
		}
	} // 새글쓰기

	private void 일시정지() {
		System.out.println("[알림] 계속하시려면 엔터를 치세요.");
		try {
			System.in.read();
			System.in.skip(System.in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // 일시정지

	private void exit() {
		System.out.println("\t\t[알림] 프로그램을 종료합니다.");
		DBconn.close();
		System.exit(-1);
	} // exit

	private void 메뉴선택() {
		System.out.print("> 메뉴를 선택하세요? ");
		this.selectedNumber = this.sc.nextInt();
	} // 메뉴선택

	private void 메뉴출력() {
		System.out.println("== 메뉴 ==");
		String[] menus = {"새글", "목록", "보기", "수정", "삭제", "검색", "종료"};
		for (int i = 0; i < menus.length; i++) {
			System.out.printf("%d. %s\t", i+1, menus[i]);
		} // for
		System.out.println();
	} // 메뉴출력
	
	
} // class
