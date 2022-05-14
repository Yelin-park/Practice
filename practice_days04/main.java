package practice_days04;

import java.sql.Connection;

import com.util.DBconn;

public class main {

	public static void main(String[] args) {
		Connection conn = DBconn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		BoardService service = new BoardService(dao);
		BoardController con = new BoardController(service);
		
		con.start();
		
	} // main

} // class
