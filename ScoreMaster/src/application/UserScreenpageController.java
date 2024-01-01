package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserScreenpageController implements Initializable {
	
	
	
	@FXML
    private TableColumn<GamesTable, String> username_gameNameColumn;

    @FXML
    private TableColumn<GamesTable, ImageView> username_imageColumn;

    @FXML
    private AnchorPane userpage_gameListTableView;

    @FXML
    private TableColumn<GamesTable, Button> userpage_gamePageButtonColumn;

    @FXML
    private TableColumn<GamesTable, Integer> userpage_gameScoreColumn;

    @FXML
    private TableColumn<GamesTable, String> userpage_gameTypeColumn;

    @FXML
    private TableView<GamesTable> userpage_gamesTableView;

    @FXML
    private Button userpage_logoutButton;
    
    @FXML
    private Button userpage_mainButton;

    @FXML
    private AnchorPane userpage_mainform;

    @FXML
    private TableColumn<GamesTable, String> userpage_releaseDateColumn;

    @FXML
    private AnchorPane userpage_selectedGamePage;

    @FXML
    private AnchorPane userpage_topAnchor;

    @FXML
    private Label userpage_usernameLabel;
    
    @FXML
    private Label userpage_gamePageGameName;


    @FXML
    private Label userpage_gamePageReleaseDate;

    @FXML
    private Label userpage_gamePageScore;

    @FXML
    private Button userpage_gamePageSendCommentButton;
    
    @FXML
    private ImageView userpage_gamePageImageView;

    @FXML
    private Label userpage_gamePageSummary;
    
    @FXML
    private Button button_0;

    @FXML
    private Button button_1;

    @FXML
    private Button button_10;

    @FXML
    private Button button_2;

    @FXML
    private Button button_3;

    @FXML
    private Button button_4;

    @FXML
    private Button button_5;

    @FXML
    private Button button_6;

    @FXML
    private Button button_7;

    @FXML
    private Button button_8;

    @FXML
    private Button button_9;
    
    @FXML
    private Button userpage_gameCannotSendCommentButton;

    @FXML
    private AnchorPane userpage_gamePageBottomAnchor;

    @FXML
    private ScrollPane userpage_scrollpage;
    
    @FXML
    private Label userscreenpage_showScoreLabel;
    
    @FXML
    private TextArea userpage_gamePageMakeComment;
 
    @FXML
    private TableView<CommentTable> userpage_gameCommentTableView;

    @FXML
    private TableColumn<CommentTable, String> userpage_gamePageCommentColumn;

    @FXML
    private TableColumn<CommentTable, Integer> userpage_gamePageScoreColumn;

    @FXML
    private TableColumn<CommentTable, String> userpage_gamePageUsernameColumn;
   
    @FXML
    private Label userpage_gameAverageScoreLabel;
    
    @FXML
    private AnchorPane userpage_gamePageImageAnchor;

   

    private AlertMessage alert2 = new AlertMessage();
    
	ObservableList<GamesTable> mylist;
	
	private Alert alert;
    
    private Image image;
    
	
	Connection connect = null;
	ResultSet result = null;
	PreparedStatement prepare = null;
	Statement statement = null;
	
	public void switchToMainPage() {
		
		userpage_gameListTableView.setVisible(true);
		userpage_selectedGamePage.setVisible(false);
	}
	
	public void setGameDatas(int id) {
		
		String sql = "SELECT * FROM games WHERE id = '"
    			+ id + "'";
    	
    	connect = Database.connectDB();
		
    	try {
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		if(result.next()) {
    			userpage_gamePageGameName.setText(result.getString("game_name"));
    			userpage_gamePageReleaseDate.setText(result.getString("game_releaseDate"));
    			userpage_gamePageScore.setText(result.getString("game_score"));
    			userpage_gamePageSummary.setText(result.getString("game_summary"));
    			
    			
    			String url = result.getString("game_image");
    			
    			
    			if(url != null) {
    				Image image = new Image("File:"+url,150,200,false,true);
    				userpage_gamePageImageView.setImage(image);
    			}
    					
    			
    		}
    			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    	ObservableList<CommentTable> mylist5;
    	
    	
    	String sql2 = "SELECT * FROM combined_view WHERE game_name = '"
    			+ Data.temp_gameName + "'";
    	connect = Database.connectDB();
    	try {
    		
    		
    		prepare = connect.prepareStatement(sql2);
    		result = prepare.executeQuery();
    		
    		if(result.next()) {
    			
    			userpage_gamePageUsernameColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, String>("username"));
    			userpage_gamePageScoreColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, Integer>("user_score"));
    			userpage_gamePageCommentColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, String>("user_comment"));
    			
    			mylist5 = Database.getDataComments();
    			userpage_gameCommentTableView.setItems(mylist5);
    		}
    		
    		userpage_gameListTableView.setVisible(false);
			userpage_selectedGamePage.setVisible(true);
    		
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    	}
	}
	public void displayuserUsername() {
    	
    	String sql = "SELECT * FROM user WHERE username = '"
    			+ Data.user_username + "'";
    	
    	connect = Database.connectDB();
    	
    	try {
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		if(result.next()) {
    			
    			
    			String tempUsername = result.getString("username");
    			tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1);
    			userpage_usernameLabel.setText(tempUsername);
    			
    			
    		}
    			
    	}catch(Exception e){e.printStackTrace();}	
    	
    }
	
	public void logout() {
		
		try {
			
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure want to logout?");
			Optional<ButtonType> option = alert.showAndWait();
			
			if(option.get().equals(ButtonType.OK)) {
				
				userpage_logoutButton.getScene().getWindow().hide();
				
				
				Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
				Stage stage = new Stage();
				stage.setTitle("ScoreMaster");
				stage.setScene(new Scene(root));
				stage.show();						
			}									
		}catch(Exception e) {
			
			e.printStackTrace();
			
			
		}
			
	}
	public void buttonScore0() {Data.genScore = 0;}
	public void buttonScore1() {Data.genScore = 1;}
	public void buttonScore2() {Data.genScore = 2;}
	public void buttonScore3() {Data.genScore = 3;}
	public void buttonScore4() {Data.genScore = 4;}
	public void buttonScore5() {Data.genScore = 5;}
	public void buttonScore6() {Data.genScore = 6;}
	public void buttonScore7() {Data.genScore = 7;}
	public void buttonScore8(){Data.genScore = 8;}
	public void buttonScore9() {Data.genScore = 9;}
	public void buttonScore10() {Data.genScore = 10;}
	
	public void sendCommentDatas() {
		
		if( Data.genScore == null
				
    		|| userpage_gamePageMakeComment.getText().isEmpty()) {
    		
    		alert2.errorMessage("Please fill all blank fields.");
    		
    	}else {
    		
    		
    		connect = Database.connectDB();
    		try {
    			String insertData = "INSERT INTO comment"
						+ "(user_comment, username, game_id)"
						+ "VALUES(?,?,?)";
    			
    			String com = userpage_gamePageMakeComment.getText();
    			
				prepare = connect.prepareStatement(insertData);
				prepare.setString(1, com);
				prepare.setString(2, Data.user_username);
				prepare.setString(3, Data.temp_gameID);
				prepare.executeUpdate();
				
				String insertData2 = "INSERT INTO score"
						+ "(user_score, username, game_id)"
						+ "VALUES(?,?,?)";
				
				prepare = connect.prepareStatement(insertData2);
				prepare.setInt(1, Data.genScore);
				prepare.setString(2, Data.user_username);
				prepare.setString(3, Data.temp_gameID);
				

				prepare.executeUpdate();
				
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error message");
				alert.setHeaderText(null);
				alert.setContentText("Successfully added.");
				alert.showAndWait();
				

				try (PreparedStatement preparedStatement = connect.prepareStatement("UPDATE comment SET count = 1 WHERE game_id = ? AND username = ?")) {
				    preparedStatement.setString(1, Data.temp_gameID);
				    preparedStatement.setString(2, Data.user_username);

				    int affectedRows = preparedStatement.executeUpdate();

				    if (affectedRows > 0) {
				        
				    	userpage_gamePageSendCommentButton.setVisible(false);
				    }
				} catch (Exception e) {
				    e.printStackTrace();
				}
				
				ObservableList<CommentTable> mylist5;
				
				String sql2 = "SELECT * FROM combined_view WHERE game_name = '"
		    			+ Data.temp_gameName + "'";
		    	connect = Database.connectDB();
		    	try {
		    		
		    		
		    		prepare = connect.prepareStatement(sql2);
		    		result = prepare.executeQuery();
		    		
		    		if(result.next()) {
		    			
		    			userpage_gamePageUsernameColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, String>("username"));
		    			userpage_gamePageScoreColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, Integer>("user_score"));
		    			userpage_gamePageCommentColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, String>("user_comment"));
		    			
		    			mylist5 = Database.getDataComments();
		    			userpage_gameCommentTableView.setItems(mylist5);
		    		}
		    		
		    		userpage_gameListTableView.setVisible(false);
					userpage_selectedGamePage.setVisible(true);
		    		
		    	}catch(Exception e) {
		    		
		    		e.printStackTrace();
		    	}
				
    		}catch(Exception e) {
    			e.printStackTrace();	
    			
    		}
    		
    	
    		String sumQuery = "SELECT sum(game_score) AS totalScore FROM games WHERE game_id = ?";
    		try (PreparedStatement prepare = connect.prepareStatement(sumQuery)) {
    		    prepare.setString(1, Data.temp_gameID);

    		    try (ResultSet result = prepare.executeQuery()) {
    		        if (result.next()) {
    		            int currentTotalScore = result.getInt("totalScore");

    		            Data.totalScore = currentTotalScore + Data.genScore;

    		            String updateQuery = "UPDATE games SET game_score = ? WHERE game_id = ?";
    		            try (PreparedStatement update = connect.prepareStatement(updateQuery)) {
    		                update.setInt(1, Data.totalScore);
    		                update.setString(2, Data.temp_gameID);
    		                update.executeUpdate();
    		            }
    		        }
    		    }
    		} catch (Exception e) {
    		    e.printStackTrace();
    		}

    		String countQuery = "SELECT count(*) AS count FROM score WHERE game_id = ?";
    		try (PreparedStatement countStatement = connect.prepareStatement(countQuery)) {
    		    countStatement.setString(1, Data.temp_gameID);

    		    try (ResultSet countResultSet = countStatement.executeQuery()) {
    		        int rowCount = 0;
    		        if (countResultSet.next()) {
    		            rowCount = countResultSet.getInt("count");
    		        }

    		        double averageScore = (rowCount == 0) ? 0 : (double) Data.totalScore/ (rowCount);
    		        String averageScoreLabel = String.format("%.1f", averageScore);
    		        userpage_gameAverageScoreLabel.setText(averageScoreLabel);
    		        String total = String.valueOf(Data.totalScore);
    		        userpage_gamePageScore.setText(total);
    		    }
    		} catch (Exception countException) {
    		    countException.printStackTrace();
    		}
    	}
		userpage_gamePageMakeComment.setText("");
		
		
	}
	
	
	ImageView view = new ImageView();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		displayuserUsername();
		
			
		username_imageColumn.setCellValueFactory(cell -> {
		    GamesTable imageEntity = cell.getValue(); 
		    ImageView imageView = new ImageView(new Image("file:" + imageEntity.getImage()));
		    imageView.setFitWidth(72);
		    imageView.setFitHeight(90);
		    return new SimpleObjectProperty<>(imageView); 
		});
		
		userpage_gamePageButtonColumn.setCellValueFactory(cellData -> {
		    Button button = new Button("X");

		    
		    button.setOnAction(event -> { 
		        Integer Id = cellData.getValue().getId();
		        String game_ID = cellData.getValue().getGame_id();
		        String gameNAME = cellData.getValue().getGame_name();
		        setGameDatas(Id);
		        Data.temp_gameID = game_ID;
		        Data.temp_gameName = gameNAME;
		    
		        ObservableList<CommentTable> mylist7;
		        
		        userpage_gamePageUsernameColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, String>("username"));
    			userpage_gamePageScoreColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, Integer>("user_score"));
    			userpage_gamePageCommentColumn.setCellValueFactory(new PropertyValueFactory<CommentTable, String>("user_comment"));
    			
    			
    			String sumQuery = "SELECT sum(game_score) AS totalScore FROM games WHERE game_id = ?";
        		try (PreparedStatement prepare = connect.prepareStatement(sumQuery)) {
        		    prepare.setString(1, Data.temp_gameID);

        		    try (ResultSet result = prepare.executeQuery()) {
        		        if (result.next()) {
        		            int currentTotalScore = result.getInt("totalScore");

        		            Data.totalScore = currentTotalScore;
        		        }
        		    }
        		} catch (Exception e) {
        		    e.printStackTrace();
        		}

        		String countQuery = "SELECT count(*) AS count FROM score WHERE game_id = ?";
        		try (PreparedStatement countStatement = connect.prepareStatement(countQuery)) {
        		    countStatement.setString(1, Data.temp_gameID);

        		    try (ResultSet countResultSet = countStatement.executeQuery()) {
        		        int rowCount = 0;
        		        if (countResultSet.next()) {
        		            rowCount = countResultSet.getInt("count");
        		        }

        		        double averageScore = (rowCount == 0) ? 0 : (double) Data.totalScore/ (rowCount);
        		        String averageScoreLabel = String.format("%.1f", averageScore);
        		        userpage_gameAverageScoreLabel.setText(averageScoreLabel);
        		        String total = String.valueOf(Data.totalScore);
        		        userpage_gamePageScore.setText(total);
        		    }
        		} catch (Exception countException) {
        		    countException.printStackTrace();
        		}
    			
    			mylist7 = Database.getDataComments();
    			userpage_gameCommentTableView.setItems(mylist7);
    			
    			try {
    	            
    	            String query = "SELECT * FROM comment WHERE game_id = ? AND username = ? AND count = 1";
    	            PreparedStatement preparedStatement = connect.prepareStatement(query);
    	            preparedStatement.setString(1, Data.temp_gameID);
    	            preparedStatement.setString(2, Data.user_username);
    	            ResultSet resultSet = preparedStatement.executeQuery();

    	            if (resultSet.next()) {
    	            	userpage_gamePageSendCommentButton.setVisible(false);
    	            }else {
    	            	userpage_gamePageSendCommentButton.setVisible(true);
    	            }

    	            resultSet.close();
    	            preparedStatement.close();
    	            connect.close();
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        
    	    }
		    });

		    button.setPrefWidth(31);
		    button.setPrefHeight(31);
		    return new javafx.beans.property.SimpleObjectProperty<>(button);
		});
		 

		username_gameNameColumn.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_name"));
		userpage_releaseDateColumn.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_releaseDate"));
		userpage_gameTypeColumn.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_type"));
		
		
		userpage_gameScoreColumn.setCellValueFactory(new PropertyValueFactory<GamesTable, Integer>("game_score"));
		
		mylist = Database.getDataGames();
		userpage_gamesTableView.setItems(mylist);

	}
}