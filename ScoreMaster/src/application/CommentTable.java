package application;

public class CommentTable {

	private String username;
	
	private Integer user_score;
	
	private String user_comment;

	public CommentTable(String username, Integer user_score, String user_comment) {
		super();
		this.username = username;
		this.user_score = user_score;
		this.user_comment = user_comment;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUser_score() {
		return user_score;
	}

	public void setUser_score(Integer user_score) {
		this.user_score = user_score;
	}

	public String getUser_comment() {
		return user_comment;
	}

	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}