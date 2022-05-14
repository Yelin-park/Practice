package practice_days04;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAOImpl implements BoardDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public BoardDAOImpl() {}

	public BoardDAOImpl(Connection conn) {
		this.conn = conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public ArrayList<BoardDTO> select() throws SQLException{
		ArrayList<BoardDTO> list = null;
		BoardDTO dto = null;
		int seq;
		String writer;
		String pwd;
		String email;
		String title;
		Date writedate;
		int readed;
		String content;

		String sql = "SELECT * FROM t_Board ORDER BY seq DESC";

		this.pstmt = this.conn.prepareStatement(sql);
		this.rs = this.pstmt.executeQuery();

		if (this.rs.next()) {
			list = new ArrayList<BoardDTO>();
			do {
				seq = this.rs.getInt("seq");
				writer = this.rs.getString("writer");
				email = this.rs.getString("email");
				title = this.rs.getString("title");
				writedate = this.rs.getDate("writedate");
				readed = this.rs.getInt("readed");
				pwd = this.rs.getString("pwd");
				content = this.rs.getString("content");

				dto = new BoardDTO(seq, writer, pwd, email, title, writedate, readed, readed, content);
				list.add(dto);
			} while (this.rs.next());
		} // if

		this.pstmt.close();
		this.rs.close();

		return list;
	} // select

	@Override
	public ArrayList<BoardDTO> select(int currentPage, int numberPerPage) throws SQLException {
		ArrayList<BoardDTO> list = null;
		BoardDTO dto = null;

		int seq;
		String writer;
		String pwd;
		String email;
		String title;
		Date writedate;
		int readed;
		String content;

		String sql = "SELECT * "
				+ "FROM(     "
				+ "    SELECT ROWNUM no, t.* "
				+ "    FROM( "
				+ "        SELECT * "
				+ "        FROM t_board "
				+ "        ORDER BY seq DESC "
				+ "    ) t "
				+ ") m "
				+ "WHERE m.no BETWEEN ? AND ?";

		int begin = (currentPage-1)  * numberPerPage + 1;
		int end = begin + numberPerPage - 1;

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, begin);
		this.pstmt.setInt(2, end);
		this.rs = this.pstmt.executeQuery();

		if (this.rs.next()) {
			list = new ArrayList<BoardDTO>();
			do {
				seq = this.rs.getInt("seq");
				writer = this.rs.getString("writer");
				email = this.rs.getString("email");
				title = this.rs.getString("title");
				writedate = this.rs.getDate("writedate");
				readed = this.rs.getInt("readed");
				pwd = this.rs.getString("pwd");
				content = this.rs.getString("content");

				dto = new BoardDTO(seq, writer, pwd, email, title, writedate, readed, readed, content);
				list.add(dto);
			} while (this.rs.next());
		} // if

		this.pstmt.close();
		this.rs.close();

		return list;
	}

	@Override
	public int insert(BoardDTO dto) throws SQLException{
		int rowCount = 0;

		String sql = "INSERT INTO t_board (seq, writer, pwd, email, title, tag, content) "
				+ "VALUES(seq_tboard.nextval, ?, ?, ?, ?, ?, ?)";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, dto.getWriter());
		this.pstmt.setString(2, dto.getPwd());
		this.pstmt.setString(3, dto.getEmail());
		this.pstmt.setString(4, dto.getTitle());
		this.pstmt.setInt(5, dto.getTag());
		this.pstmt.setString(6, dto.getContent());

		rowCount = this.pstmt.executeUpdate();

		this.pstmt.close();

		return rowCount;
	}

	@Override
	public int getTotalPages(int numberPerPage) throws SQLException {
		int totalPages = 0;

		String sql = "SELECT CEIL(COUNT(*) / ?) "
				+ "FROM t_board";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, numberPerPage);
		this.rs = this.pstmt.executeQuery();

		this.rs.next();
		totalPages = this.rs.getInt(1);

		this.rs.close();
		this.pstmt.close();

		return totalPages;
	}

	@Override
	public int getTotalPages(int numberPerPage, int searchCondition, String searchWord) throws SQLException {
		int totalPages = 0;

		String sql = "SELECT CEIL(COUNT(*) / ?) "
					+ "FROM t_board ";

		if (searchCondition == 1) {
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') ";
		} else if(searchCondition == 2) {
			sql += "WHERE REGEXP_LIKE(content, ?, 'i') ";
		} else if(searchCondition == 3) {
			sql += "WHERE REGEXP_LIKE(writer, ?, 'i') ";
		} else if(searchCondition == 4) {
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') OR REGEXP_LIKE(content, ?, 'i') ";
		} // if

		sql += "ORDER BY seq DESC";

		this.pstmt = this.conn.prepareStatement(sql);
		
		this.pstmt.setInt(1, numberPerPage);
		if (searchCondition != 4) {
			this.pstmt.setString(2, searchWord);
		} else if (searchCondition == 4) {
			this.pstmt.setString(2, searchWord);
			this.pstmt.setString(3, searchWord);
		} // if

		this.rs = this.pstmt.executeQuery();
		this.rs.next();
		totalPages = this.rs.getInt(1);
		this.pstmt.close();
		return totalPages;
	}

	@Override
	public int getTotalRecords() throws SQLException {
		int totalrecords = 0;

		String sql = "SELECT COUNT(*) "
				+ "FROM t_board";

		this.pstmt = this.conn.prepareStatement(sql);
		this.rs = this.pstmt.executeQuery();
		if (this.rs.next()) {
			totalrecords = this.rs.getInt(1);
		}

		this.rs.close();
		this.pstmt.close();

		return totalrecords;
	}

	@Override
	public BoardDTO view(int seq) throws SQLException {
		BoardDTO dto = null;

		String sql = "SELECT * "
				+ "FROM t_Board "
				+ "WHERE seq = ?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, seq);
		this.rs = this.pstmt.executeQuery();
		if (this.rs.next()) {
			dto = new BoardDTO();
			dto.setWriter(this.rs.getString("writer"));
			dto.setEmail(this.rs.getString("email"));
			dto.setTitle(this.rs.getString("title"));
			dto.setWritedate(this.rs.getDate("writedate"));
			dto.setReaded(this.rs.getInt("readed"));
			dto.setContent(this.rs.getString("content"));
		}

		this.pstmt.close();
		this.rs.close();
		return dto;
	}

	@Override
	public void increaseReaded(int seq) throws SQLException {
		String sql = "UPDATE t_board "
				+ "SET readed = readed + 1 "
				+ "WHERE seq = ?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, seq);
		this.pstmt.executeUpdate();
		this.pstmt.close();
	}

	@Override
	public int delete(int seq) throws SQLException {
		int rowCount = 0;

		String sql = "DELETE t_board WHERE seq = ?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, seq);
		rowCount = this.pstmt.executeUpdate();

		this.pstmt.close();

		return rowCount;
	}

	@Override
	public int update(BoardDTO dto) throws SQLException {
		int rowCount = 0;

		String sql = "UPDATE t_board "
				+ "SET email = ?, title = ?, content = ? "
				+ "WHERE seq = ?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, dto.getEmail());
		this.pstmt.setString(2, dto.getTitle());
		this.pstmt.setString(3, dto.getContent());
		this.pstmt.setInt(4, dto.getSeq());
		rowCount = this.pstmt.executeUpdate();

		this.pstmt.close();
		return rowCount;
	}

	@Override
	public ArrayList<BoardDTO> search(int searchCondition, String searchWord) throws SQLException {
		ArrayList<BoardDTO> list = null;
		BoardDTO dto = null;
		int seq;
		String writer;
		String pwd;
		String email;
		String title;
		Date writedate;
		int readed;
		String content;

		String sql = "SELECT * "
				+ "FROM t_board ";

		// 1. 제목, 2. 내용, 3. 작성자, 4. 제목+내용
		if (searchCondition == 1) {
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') ";
		} else if(searchCondition == 2) {
			sql += "WHERE REGEXP_LIKE(content, ?, 'i') ";
		} else if(searchCondition == 3) {
			sql += "WHERE REGEXP_LIKE(writer, ?, 'i') ";
		} else if(searchCondition == 4) {
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') OR REGEXP_LIKE(content, ?, 'i') ";
		} // if

		sql += "ORDER BY seq DESC";

		this.pstmt = this.conn.prepareStatement(sql);

		this.pstmt.setString(1, searchWord);
		if (searchCondition == 4) {
			this.pstmt.setString(2, searchWord);
		} // if

		this.rs = this.pstmt.executeQuery();

		if (this.rs.next()) {
			list = new ArrayList<BoardDTO>();
			do {
				seq = this.rs.getInt("seq");
				writer = this.rs.getString("writer");
				email = this.rs.getString("email");
				title = this.rs.getString("title");
				writedate = this.rs.getDate("writedate");
				readed = this.rs.getInt("readed");
				pwd = this.rs.getString("pwd");
				content = this.rs.getString("content");

				dto = new BoardDTO(seq, writer, pwd, email, title, writedate, readed, readed, content);
				list.add(dto);
			} while (this.rs.next());
		} // if

		this.pstmt.close();
		this.rs.close();
		return list;
	}

	@Override
	public ArrayList<BoardDTO> search(int searchCondition, String searchWord, int currentPage, int numberPerPage) throws SQLException {
		ArrayList<BoardDTO> list = null;

		int seq;
		String writer;
		String pwd;
		String email;
		String title;
		Date writedate;
		int readed;
		String content;

		String sql = "SELECT m.seq, m.writer, m.email, m.title, m.writedate, m.readed, m.content "
				+ "FROM(     "
				+ "    SELECT ROWNUM no, t.* "
				+ "    FROM( "
				+ "        SELECT seq, writer, email, title, writedate, readed, content "
				+ "        FROM t_board ";

		if (searchCondition == 1) {
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') ";
		} else if(searchCondition == 2) {
			sql += "WHERE REGEXP_LIKE(content, ?, 'i') ";
		} else if(searchCondition == 3) {
			sql += "WHERE REGEXP_LIKE(writer, ?, 'i') ";
		} else if(searchCondition == 4) {
			sql += "WHERE REGEXP_LIKE(title, ?, 'i') OR REGEXP_LIKE(content, ?, 'i') ";
		} // if

		sql += "        ORDER BY seq DESC ";
		sql += "    ) t ";
		sql += ") m ";
		sql += "WHERE m.no BETWEEN ? AND ?";

		int begin = (currentPage-1)  * numberPerPage + 1;
		int end = begin + numberPerPage - 1;

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, searchWord);
	      if (searchCondition == 4) {
	    	  this.pstmt.setString(2, searchWord);
	    	  this.pstmt.setInt(3, begin);
	    	  this.pstmt.setInt(4, end);
	      } else {
	    	  this.pstmt.setInt(2, begin);
	    	  this.pstmt.setInt(3, end);
	      }

		this.rs = this.pstmt.executeQuery();

		if (this.rs.next()) {
			list = new ArrayList<BoardDTO>();
			do {
				seq = this.rs.getInt("seq");
				writer = this.rs.getString("writer");
				email = this.rs.getString("email");
				title = this.rs.getString("title");
				writedate = this.rs.getDate("writedate");
				readed = this.rs.getInt("readed");
				content = this.rs.getString("content");
				// BoardDTO dto = new BoardDTO(seq, writer, email, title, writedate, readed);
				BoardDTO dto = new BoardDTO(seq, writer, email, title, writedate, readed, readed, content);
				list.add(dto);

			} while (this.rs.next());
		} // if

		this.pstmt.close();
		this.rs.close();

		return list;
	}


}
