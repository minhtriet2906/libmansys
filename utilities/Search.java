package utilities;
import java.sql.*;

import javax.swing.JTable;

import menu.DatabaseManagement;
import net.proteanit.sql.DbUtils;
public class Search {
	private static DatabaseManagement db = new DatabaseManagement();
	public static void searchTitle(String title, JTable table) {
		if(!title.trim().isEmpty()) {
			try(Connection conn	= db.connect();){
				//String process: Convert the search string to all lowercase
				title = title.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + title + "%");
				ResultSet rs = stmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				stmt.close();
				
			}catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	public static void searchCategory(String category, JTable table) {
		if(!category.trim().isEmpty()) {
			try(Connection conn	= db.connect();){
				//String process: Convert the search string to all lowercase
				category = category.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(category) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + category + "%");
				ResultSet rs = stmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				stmt.close();
				
			}catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	public static void searchPublisher(String publisher, JTable table) {
		if(!publisher.trim().isEmpty()) {
			try(Connection conn	= db.connect();){
				//String process: Convert the search string to all lowercase
				publisher = publisher.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(publisher) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + publisher + "%");
				ResultSet rs = stmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				stmt.close();
				
			}catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	
	public static void searchAuthor(String author, JTable table) {
		if(!author.trim().isEmpty()) {
			try(Connection conn	= db.connect();){
				//String process: Convert the search string to all lowercase
				author = author.toLowerCase();
				String sql = "SELECT * FROM books WHERE LOWER(author) LIKE ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + author + "%");
				ResultSet rs = stmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				stmt.close();
				
			}catch (SQLException err) {
				System.out.println(err.getMessage());
			}
		}
	}

	public static void showAllBook(JTable table) {
		try(Connection conn	= db.connect();){
			String sql = "SELECT * FROM books";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			stmt.close();
			
		}catch (SQLException err) {
			System.out.println(err.getMessage());
		}
	}
	public static void main(String[] args) {
		
	}

}