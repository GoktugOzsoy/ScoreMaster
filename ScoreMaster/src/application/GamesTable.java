package application;

public class GamesTable {
	
	String  game_name, game_releaseDate, game_type, game_image, game_id, game_summary;
	
	int game_score, id;
	
	public GamesTable(String game_name, String game_releaseDate, String game_type, int game_score, String game_id, String game_image, int id, String game_summary) {
		
		this.game_name = game_name;
		this.game_releaseDate = game_releaseDate;
		this.game_type = game_type;
		this.game_score = game_score;
		this.game_id = game_id;	
		this.game_image = game_image;
		this.id = id;
		this.game_summary = game_summary;
	}
	
	
	public String getGame_summary() {
		return game_summary;
	}


	public void setGame_summary(String game_summary) {
		this.game_summary = game_summary;
	}


	public String getImage() {
		return game_image;
	}


	public void setImage(String image) {
		this.game_image = image;
	}


	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getGame_releaseDate() {
		return game_releaseDate;
	}

	public void setGame_releaseDate(String game_releaseDate) {
		this.game_releaseDate = game_releaseDate;
	}

	public String getGame_type() {
		return game_type;
	}

	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}

	public int getGame_score() {
		return game_score;
	}

	public void setGame_score(int game_score) {
		this.game_score = game_score;
	}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}




	public String getGame_image() {
		return game_image;
	}




	public void setGame_image(String game_image) {
		this.game_image = game_image;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}
	
	
	

	
	

}