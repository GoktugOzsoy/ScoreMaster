package application;

public class GametoCommentTable {
	
	private String game_image;
	
	private String game_name;

	public GametoCommentTable(String game_image, String game_name) {
		super();
		this.game_image = game_image;
		this.game_name = game_name;
		
	}

	public String getGame_image() {
		return game_image;
	}

	public void setGame_image(String game_image) {
		this.game_image = game_image;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}


}