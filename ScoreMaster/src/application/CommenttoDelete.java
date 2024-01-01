package application;

public class CommenttoDelete {
	
	private String game_name;
	
	private String usernameS;
	
	private String user_comment;
	
	private Integer user_score;

	public CommenttoDelete(String game_name, String usernameS, String user_comment, Integer user_score) {
		super();
		this.game_name = game_name;
		this.usernameS = usernameS;
		this.user_comment = user_comment;
		this.user_score= user_score;
	}
	
	
	
	public Integer getUser_score() {
		return user_score;
	}



	public void setUser_score(Integer user_score) {
		this.user_score = user_score;
	}



	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getUsernameS() {
		return usernameS;
	}

	public void setUsername(String usernameD) {
		this.usernameS = usernameD;
	}

	public String getUser_comment() {
		return user_comment;
	}

	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}
	
	
	

}