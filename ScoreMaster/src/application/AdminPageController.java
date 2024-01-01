package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AdminPageController implements Initializable{
	
	
	@FXML
	private TextField adminpage_addSummaryText;
	
	@FXML
    private Button adminpage_addGameButton;

    @FXML
    private TextField adminpage_addGameIDText;

    @FXML
    private TextField adminpage_addGameNameText;

    @FXML
    private TextField adminpage_addGameReleaseDateText;

    @FXML
    private TextField adminpage_addGameTypeText;

    @FXML
    private Label adminpage_adminUsername;

    @FXML
    private Button adminpage_clearButton;

    @FXML
    private AnchorPane adminpage_editUsersBottomAnchor;

    @FXML
    private AnchorPane adminpage_commentsEditAnchor;

    @FXML
    private Button adminpage_deleteButton;

    @FXML
    private Button adminpage_editCommentsButton;

    @FXML
    private Button adminpage_editUsersButton;

    @FXML
    private AnchorPane adminpage_gameImportAnchor;

    @FXML
    private AnchorPane adminpage_gameListAnchor;

    @FXML
    private Button adminpage_giveAuthorityButton;

    @FXML
    private AnchorPane adminpage_imageAnchor;

    @FXML
    private Button adminpage_imageImportButton;

    @FXML
    private ImageView adminpage_imageView;

    @FXML
    private Button adminpage_logoutButton;

    @FXML
    private Button adminpage_mainButton;

    @FXML
    private AnchorPane adminpage_mainform;

    @FXML
    private AnchorPane adminpage_topAnchorPanel;
    
    @FXML
    private AnchorPane adminpage_deleteCommentTableAnchor;
    
    @FXML
    private AnchorPane adminpage_checkCommentTableAnchor;

    @FXML
    private Button adminpage_updateButton;
    
    @FXML
    private TableColumn<GamesTable, String> userpage_gameSummaryColumn;

    @FXML
    private TableColumn<UsersTable, String> adminpage_userEmailColumn;

    @FXML
    private TableColumn<UsersTable, Integer> adminpage_userIDcolumn;

    @FXML
    private AnchorPane adminpage_userListAnchor;

    @FXML
    private TableView<UsersTable> adminpage_userListTableView;

    @FXML
    private TableColumn<UsersTable, String> adminpage_usernameColumn;
    
    @FXML
    private AnchorPane adminpage_usersEditAnchor;

    @FXML
    private TableView<GamesTable> adminpage_gameTable;

    @FXML
    private TableColumn<GamesTable, String> userpage_gameID;

    @FXML
    private TableColumn<GamesTable, String> userpage_gameName;

    @FXML
    private TableColumn<GamesTable, String> userpage_gameReleaseDate;

    @FXML
    private TableColumn<GamesTable, Integer> userpage_gameScore;

    @FXML
    private TableColumn<GamesTable, String> userpage_gameType;
    
    @FXML
    private TableView<GametoCommentTable> adminpage_checkCommentTableView;
    
    @FXML
    private TableColumn<GametoCommentTable, String> adminpage_editCommentGameNameColumn;
    
    @FXML
    private TableColumn<GametoCommentTable, ImageView> adminpage_editCommentImageColumn;
    
    @FXML
    private TableColumn<CommenttoDelete, Integer> adminpage_editCommentScoreColumn;
    
    @FXML
    private TableColumn<GametoCommentTable, Button> adminpage_editCommentGoCommentButton;

    @FXML
    private TableView<CommenttoDelete> adminpage_deleteCommentTableview;
    
    @FXML
    private TableColumn<CommenttoDelete, String> adminpage_commentListUsername;

    @FXML
    private TableColumn<CommenttoDelete, String> adminpage_editCommentCommentColumn;

    @FXML
    private TableColumn<CommenttoDelete, Button> adminpage_editCommentDeleteButtonColumn;
    
  
    

    private Alert alert;
    
    private Image image;
    
    ObservableList<GamesTable> mylist3;
    ObservableList<UsersTable> mylist4;
    ObservableList<GametoCommentTable> mylist5;
    ObservableList<CommenttoDelete> mylist6;
	
	
	
	Connection connect = null;
	ResultSet result = null;
	PreparedStatement prepare = null;
	Statement statement = null;
	
	public void gamesAddButton() {
		
		if(adminpage_addGameNameText.getText().isEmpty()||
				adminpage_addGameIDText.getText().isEmpty()||
				adminpage_addGameReleaseDateText.getText().isEmpty()||
				adminpage_addGameTypeText.getText().isEmpty()||
				adminpage_addSummaryText.getText().isEmpty()||
				Data.path == null) {
			
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error message");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all blank fields");
			alert.showAndWait();	
		}else {
			
			String checkGameID = "SELECT game_id FROM games WHERE game_id = '"
					+ adminpage_addGameIDText.getText() + "'";
			
			connect = Database.connectDB();
			
			
			try {
				statement = connect.createStatement();
				result = statement.executeQuery(checkGameID);
				
				if(result.next()) {
					
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error message");
					alert.setHeaderText(null);
					alert.setContentText(adminpage_addGameIDText.getText() + "is already taken.");
					alert.showAndWait();

				}else {
					
					String insertData = "INSERT INTO games"
							+ "(game_id, game_name, game_releaseDate, game_score, game_type, game_image, game_summary)"
							+ "VALUES(?,?,?,?,?,?,?)";
					
					prepare = connect.prepareStatement(insertData);
					prepare.setString(1, adminpage_addGameIDText.getText());
					prepare.setString(2, adminpage_addGameNameText.getText());
					prepare.setString(3, adminpage_addGameReleaseDateText.getText());
					prepare.setInt(4, 0);
					prepare.setString(5, adminpage_addGameTypeText.getText());
					
					
					String path = Data.path;
					
					path = path.replace("\\", "\\\\");
					
					prepare.setString(6, path);
					prepare.setString(7, adminpage_addSummaryText.getText());
					
					Data.temp_gameName = adminpage_addGameNameText.getText();
					Data.temp_gameReleaseDate = adminpage_addGameReleaseDateText.getText();
					Data.temp_gameScore = 0;
					Data.temp_gameSummary = adminpage_addSummaryText.getText();
					Data.temp_gameID = adminpage_addGameIDText.getText();
					
					prepare.executeUpdate();
					
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully added.");
					alert.showAndWait();
					
					
					userpage_gameName.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_name"));
					userpage_gameReleaseDate.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_releaseDate"));
					userpage_gameType.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_type"));
					userpage_gameScore.setCellValueFactory(new PropertyValueFactory<GamesTable, Integer>("game_score"));
					userpage_gameID.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_id"));
					userpage_gameSummaryColumn.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_summary"));

					
					mylist3 = Database.getDataGames();
					adminpage_gameTable.setItems(mylist3);
					gameClearButton();
					
					
					
				}
						
			}catch(Exception e) {
				
				e.printStackTrace();
				
				
			}
			
		}
		
	}
	
	public void gameSelectData() {
		
		GamesTable gamestable = adminpage_gameTable.getSelectionModel().getSelectedItem();
		int num = adminpage_gameTable.getSelectionModel().getSelectedIndex();
		
		if((num-1) < -1) {
			return;
		}
		
		adminpage_addGameIDText.setText(gamestable.getGame_id());
		adminpage_addGameNameText.setText(gamestable.getGame_name());
		adminpage_addGameReleaseDateText.setText(gamestable.getGame_releaseDate());
		adminpage_addGameTypeText.setText(gamestable.getGame_type());
		adminpage_addSummaryText.setText(gamestable.getGame_summary());
		
		Data.path = gamestable.getImage();
		Data.id = gamestable.getId();
		
		String path = "File:" + gamestable.getImage();
		
		image = new Image(path, 150, 200, false, true);
		adminpage_imageView.setImage(image);
			
	}
	
	public void gameDeleteButton() {
		
		if(Data.id == 0) {
			
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error message");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all blank fields");
			alert.showAndWait();	
		}else {
			
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure want to delete this game ?");
			Optional<ButtonType> option = alert.showAndWait();
			
			if(option.get().equals(ButtonType.OK)) {
				
				String deleteData = "DELETE  FROM games WHERE id= " + Data.id;
				
				
				String sql = "SELECT * FROM user WHERE username = '"
		    			+ Data.user_username + "'";
		    	
				
				try {
					
					prepare = connect.prepareStatement(deleteData);
					prepare.executeUpdate();
					
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully deleted.");
					alert.showAndWait();
					
					userpage_gameName.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_name"));
					userpage_gameReleaseDate.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_releaseDate"));
					userpage_gameType.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_type"));
					userpage_gameScore.setCellValueFactory(new PropertyValueFactory<GamesTable, Integer>("game_score"));
					userpage_gameID.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_id"));
					userpage_gameSummaryColumn.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_summary"));
					
					mylist3 = Database.getDataGames();
					adminpage_gameTable.setItems(mylist3);
					
					gameClearButton();
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
					
			}else {
				
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error message");
				alert.setHeaderText(null);
				alert.setContentText("Cancelled.");
				alert.showAndWait();		
			}	
		}	
	}
	
	public void giveAuthority() {
		
	
		
		try {
				
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure want to authorize this user ?");
			Optional<ButtonType> option3 = alert.showAndWait();
				
				
			if(option3.get().equals(ButtonType.OK)) {
				
				UsersTable userstable = adminpage_userListTableView.getSelectionModel().getSelectedItem();
				int num = adminpage_userListTableView.getSelectionModel().getSelectedIndex();
		
				if((num-1) < -1) {
					return;
				}
				
				Data.user_id = userstable.getUser_id();
				
				String giveAuthority = "UPDATE user SET authority = '1' WHERE user_id = " + Data.user_id;
			
				
				prepare = connect.prepareStatement(giveAuthority);
				
				prepare.executeUpdate();
				
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Successfully updated.");
				alert.showAndWait();
				
				
				
				adminpage_userIDcolumn.setCellValueFactory(new PropertyValueFactory<UsersTable, Integer>("user_id"));
				adminpage_usernameColumn.setCellValueFactory(new PropertyValueFactory<UsersTable, String>("username"));
				adminpage_userEmailColumn.setCellValueFactory(new PropertyValueFactory<UsersTable, String>("mail_address"));
				
				mylist4 = Database.getDataUsers();
				adminpage_userListTableView.setItems(mylist4);
				
				
			}else {
				
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Cancelled.");
				alert.showAndWait();	
			}	
		}catch(Exception e) {
				e.printStackTrace();
			}
	}	
		
	public void gameUpdateButton() {
		
		if(adminpage_addGameNameText.getText().isEmpty()||
				adminpage_addGameIDText.getText().isEmpty()||
				adminpage_addGameReleaseDateText.getText().isEmpty()||
				adminpage_addGameTypeText.getText().isEmpty()||
				adminpage_addSummaryText.getText().isEmpty()||
				Data.path == null||
				Data.id == 0) {
			
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error message");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all blank fields");
			alert.showAndWait();	
		}else {
			
			String path = Data.path;
			path = path.replace("\\", "\\\\");
			
			String updateData = "UPDATE games SET " +
                    "game_id = '" + adminpage_addGameIDText.getText() + "', " +
                    "game_name = '" + adminpage_addGameNameText.getText() + "', " +
                    "game_type = '" + adminpage_addGameTypeText.getText() + "', " +
                    "game_releaseDate = '" + adminpage_addGameReleaseDateText.getText() + "', " +
                    "game_image = '" + path + "', " +
                    "game_summary = '" + adminpage_addSummaryText.getText() +"' "+
                    "WHERE id = " + Data.id;
			
			connect = Database.connectDB();
			
			try {
				
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Are you sure want to update the game id ");
				Optional<ButtonType> option = alert.showAndWait();
				
				
				if(option.get().equals(ButtonType.OK)) {
					
					prepare = connect.prepareStatement(updateData);
					
					prepare.executeUpdate();
					
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully updated.");
					alert.showAndWait();
					
					
					
					userpage_gameName.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_name"));
					userpage_gameReleaseDate.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_releaseDate"));
					userpage_gameType.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_type"));
					userpage_gameID.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_id"));
					
					mylist3 = Database.getDataGames();
					adminpage_gameTable.setItems(mylist3);
					gameClearButton();
					
					
				}else {
					
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Cancelled.");
					alert.showAndWait();
					
					
					
				}
				
				
				
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
					
					
			
			
			
			
		}
		
		
		
		
	}
	
	public void gameClearButton() {
		
		adminpage_addGameIDText.setText("");
		adminpage_addGameNameText.setText("");
		adminpage_addGameReleaseDateText.setText("");
		adminpage_addGameTypeText.setText("");
		adminpage_addSummaryText.setText("");
		Data.path = "";
		Data.id = 0;
		adminpage_imageView.setImage(null);
		
		
	}
	
	public void gameImageImportButton() {
		
		FileChooser openFile = new FileChooser();
		openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File","*png","*jpg"));
		
		File file = openFile.showOpenDialog(adminpage_mainform.getScene().getWindow());
		
		if(file != null) {
			
			Data.path = file.getAbsolutePath();
			
			image = new Image(file.toURI().toString(),150,200,false,true);
			
			adminpage_imageView.setImage(image);
		}
		
	}
	
	public void logout() {
		
		try {
			
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure want to logout?");
			Optional<ButtonType> option = alert.showAndWait();
			
			if(option.get().equals(ButtonType.OK)) {
				
				adminpage_logoutButton.getScene().getWindow().hide();
				
				
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
	
	public void displayadminUsername() {
    	
    	String sql = "SELECT * FROM user WHERE username = '"
    			+ Data.user_username + "'";
    	
    	connect = Database.connectDB();
    	
    	try {
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		if(result.next()) {
    			
    			
    			String tempUsername = result.getString("username");
    			tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1);
    			adminpage_adminUsername.setText(tempUsername);
    			
    			
    		}
    			
    	}catch(Exception e){e.printStackTrace();}	
    	
    }
    
	
	public void switchForm(ActionEvent event) {
		
		if(event.getSource() == adminpage_mainButton) {
			adminpage_gameListAnchor.setVisible(true);
			adminpage_usersEditAnchor.setVisible(false);
			adminpage_commentsEditAnchor.setVisible(false);


		
			
			
		}else if(event.getSource() == adminpage_editUsersButton) {
			adminpage_usersEditAnchor.setVisible(true);
			adminpage_commentsEditAnchor.setVisible(false);
			adminpage_gameListAnchor.setVisible(false);

			
			
			
				
		}else if(event.getSource() == adminpage_editCommentsButton) {
			adminpage_commentsEditAnchor.setVisible(true);
			adminpage_gameListAnchor.setVisible(false);
			adminpage_usersEditAnchor.setVisible(false);
			}
		
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		displayadminUsername();
		
		userpage_gameName.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_name"));
		userpage_gameReleaseDate.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_releaseDate"));
		userpage_gameType.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_type"));
		userpage_gameScore.setCellValueFactory(new PropertyValueFactory<GamesTable, Integer>("game_score"));
		userpage_gameID.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_id"));
		userpage_gameSummaryColumn.setCellValueFactory(new PropertyValueFactory<GamesTable, String>("game_summary"));
		
		mylist3 = Database.getDataGames();
		adminpage_gameTable.setItems(mylist3);
		
		
		adminpage_userIDcolumn.setCellValueFactory(new PropertyValueFactory<UsersTable, Integer>("user_id"));
		adminpage_usernameColumn.setCellValueFactory(new PropertyValueFactory<UsersTable, String>("username"));
		adminpage_userEmailColumn.setCellValueFactory(new PropertyValueFactory<UsersTable, String>("mail_address"));
		
		mylist4 = Database.getDataUsers();
		adminpage_userListTableView.setItems(mylist4);
		
		ObservableList<UsersTable> mylist6 = Database.getDataUsers(); 
		adminpage_userListTableView.setItems(mylist6);
		

		adminpage_editCommentImageColumn.setCellValueFactory(cell -> {
		    GametoCommentTable imageEntity = cell.getValue(); 
		    ImageView imageView = new ImageView(new Image("file:" + imageEntity.getGame_image()));
		    imageView.setFitWidth(72);
		    imageView.setFitHeight(90);
		    return new SimpleObjectProperty<>(imageView); 
		});

		adminpage_editCommentGameNameColumn.setCellValueFactory(new PropertyValueFactory<GametoCommentTable, String>("game_name"));

		adminpage_editCommentGoCommentButton.setCellValueFactory(cellData -> {
		    Button button = new Button("");

		    button.setOnAction(event -> { 
		    	
		    	GametoCommentTable gamename = cellData.getValue();
		    	
		    	Data.temp_gameName = gamename.getGame_name();
		    	
		    	String gameName = cellData.getValue().getGame_name();

		        
		        adminpage_commentListUsername.setCellValueFactory(new PropertyValueFactory<>("usernameS"));
		        adminpage_editCommentCommentColumn.setCellValueFactory(new PropertyValueFactory<>("user_comment"));
		        

		        
		        adminpage_editCommentDeleteButtonColumn.setCellValueFactory(cell2Data -> {
		            Button deleteButton = new Button("X");
		            deleteButton.setOnAction(event3 -> {
		            	
		            	CommenttoDelete username = cell2Data.getValue();
		            	String us = username.getUsernameS();
		            	
		            	
		            	CommenttoDelete comment = cell2Data.getValue();
		            	
		            	String com = comment.getUser_comment();
		            	
		            	CommenttoDelete score = cell2Data.getValue();
		            	Integer sc = score.getUser_score();
		            	
		            	
		          	
		            	String deleteData2 = "DELETE FROM comment WHERE user_comment = '"+ com + "'";

		            	try {
							
							prepare = connect.prepareStatement(deleteData2);
							prepare.executeUpdate();
							
							
											
						}catch(Exception e) {
							e.printStackTrace();
						}
		            	
		            	String deleteData3 = "DELETE FROM score WHERE username = ? AND user_score = ?";
		            	try (PreparedStatement preparedStatement = connect.prepareStatement(deleteData3)) {
		            	    preparedStatement.setString(1, us);
		            	    preparedStatement.setInt(2, sc);
		            	    preparedStatement.executeUpdate();
		            	    
		            	    alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error message");
							alert.setHeaderText(null);
							alert.setContentText("Successfully deleted.");
							alert.showAndWait();
		            	} catch (Exception e) {
		            	    e.printStackTrace();
		            	}
		            	
		            	
		            });

		            deleteButton.setPrefWidth(15);
		            deleteButton.setPrefHeight(15);
		            return new SimpleObjectProperty<>(deleteButton);
		        });

		        
		        adminpage_deleteCommentTableview.setItems(Database.getGameComments(gameName));
	
		    });

		    button.setPrefWidth(70);
		    button.setPrefHeight(70);
		    
		    return new javafx.beans.property.SimpleObjectProperty<>(button);
		});

		mylist5 = Database.getImageButtonGames();
		adminpage_checkCommentTableView.setItems(mylist5);

	}
}