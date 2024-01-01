package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


//database i bağlama
public class Database {
	
	public static Connection connectDB() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connect 
			= DriverManager.getConnection("jdbc:mysql://localhost:3306/scoremaster","root","");
			
			return connect;
			
		}catch(Exception e) {
			e. printStackTrace();
		}
		return null;
	}
	
	public static ObservableList<GamesTable> getDataGames(){
		
		Connection connect = connectDB();
		ObservableList<GamesTable> mylist = FXCollections.observableArrayList();
		
		try {
			
			PreparedStatement prepare = connect.prepareStatement("select * from games");
			
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				
				mylist.add(new GamesTable(result.getString("game_name"),
						result.getString("game_releaseDate"),
						result.getString("game_type"),
						Integer.parseInt(result.getString("game_score")),
						result.getString("game_id"),
						result.getString("game_image"),
						Integer.parseInt(result.getString("id")),
						result.getString("game_summary")));
			}
					
		}catch(Exception e) {
			System.out.println("hata");
			
		}
			
		return mylist;
	}
	
	public static ObservableList<GametoCommentTable> getImageButtonGames(){
		
		Connection connect = connectDB();
		ObservableList<GametoCommentTable> mylist = FXCollections.observableArrayList();
		
		try {
			
			PreparedStatement prepare = connect.prepareStatement("select * from games");
			
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				
				mylist.add(new GametoCommentTable(result.getString("game_image"),
						result.getString("game_name")));
			}
					
		}catch(Exception e) {
			System.out.println("hata");
			
		}
			
		return mylist;
			
	}
	
	public static ObservableList<CommenttoDelete> getGameComments(String gameID){
		
		Connection connect = connectDB();
		ObservableList<CommenttoDelete> mylist = FXCollections.observableArrayList();
		
		try {
			
			PreparedStatement prepare = connect.prepareStatement("select * from combined_view where game_name = '"
					+ gameID + "'");
				
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				
				mylist.add(new CommenttoDelete(result.getString("game_name"),
						result.getString("username"),
						result.getString("user_comment"),
						result.getInt("user_score")));
			}
					
		}catch(Exception e) {
			System.out.println("hata");
			
		}
			
		return mylist;
		
		
		
	}
	
	public static ObservableList<UsersTable> getDataUsers(){
		
		Connection connect = connectDB();
		ObservableList<UsersTable> mylist2 = FXCollections.observableArrayList();
		
		try {
			
			PreparedStatement prepare = connect.prepareStatement("select * from user");
			
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				
				mylist2.add(new UsersTable(result.getInt("user_id"),
						result.getString("username"),
						result.getString("mail_address")));			
			}
					
		}catch(Exception e) {
			System.out.println("hata");
			
		}
		
		return mylist2;
		
		
		
		
	}
	
	public static ObservableList<CommentTable> getDataComments(){
		
		Connection connect = connectDB();
		ObservableList<CommentTable> mylist12 = FXCollections.observableArrayList();
		
		try {
			
			PreparedStatement prepare = connect.prepareStatement("SELECT * FROM combined_view WHERE game_name = '"
	    			+ Data.temp_gameName + "'");
			
			
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				
				mylist12.add(new CommentTable(result.getString("username"),
						result.getInt("user_score"),
						result.getString("user_comment")));			
			}
					
		}catch(Exception e) {
			System.out.println("hata");
			
		}
		
		return mylist12;
		
		
		
		
	}

	
}