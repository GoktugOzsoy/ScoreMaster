package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	
	@FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginButton;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private ComboBox<?> login_selectUser;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_showPasswordCheck;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private CheckBox register_showPasswordCheck;

    @FXML
    private Button register_signUpButton;

    @FXML
    private TextField register_username;
    
    private AlertMessage alert = new AlertMessage();
    
    
    //database için lazım şeyler
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
    
    //giriş yap kısmı
    public void loginAccount() {
    	
    	if(login_username.getText().isEmpty()
    			|| login_password.getText().isEmpty()) {
    		
    		alert.errorMessage("Invalid username or password.");
    		
    	}else {
    		
    		String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND authority = ?"; 		
    		connect = Database.connectDB();
    		try {
    			
				if(!login_showPassword.isVisible()) {
				    				
    				if(!login_showPassword.getText().equals(login_password.getText())) {
    					
    						login_showPassword.setText(login_password.getText());	
    					}
				    					
	    			}else{
	    				
	    				if(!login_showPassword.getText().equals(login_password.getText())) {
	    					
	    					login_password.setText(login_showPassword.getText());	
	    				}	
	    			}
    			
    			
    			
    			prepare = connect.prepareStatement(sql);
    			prepare.setString(1, login_username.getText());
				prepare.setString(2, login_password.getText());
				prepare.setBoolean(3, true);
    			result = prepare.executeQuery();
    			// şifre ve kullanıcı adı doğruysa
    			if(result.next()) {
    				
    				Data.user_username = login_username.getText();
    				
    				alert.successMessage("Logged in successfully");
    				
    				//giriş yaptıktan sonra admin sayfasını göstermek için
    				Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
    				Stage stage = new Stage();
    				stage.setTitle("ScoreMaster");
    				stage.setScene(new Scene(root));
    				stage.show();
    				
    				//login sayfasını saklamak için
    				login_loginButton.getScene().getWindow().hide();
    				
    			//şifre ya da kullanıcı adı yanlışsa	
    			}else {
    				
    				alert.errorMessage("Incorrect username or password.");
    			}	
    		}catch(Exception e) {
    			e.printStackTrace();	
    			
    		}	
    	}
    }
    
    //giriş yap şifre göster kısmı
    public void loginShowPassword() {
    	
			if(login_showPasswordCheck.isSelected()) {
			    		
			    		login_showPassword.setText(login_password.getText());
			    		login_showPassword.setVisible(true);
			    		login_password.setVisible(false);
			    	}else {
			    		
			    		login_password.setText(login_showPassword.getText());
			    		login_showPassword.setVisible(false);
			    		login_password.setVisible(true);
			    	}
    	}
 
    
    //admin için kayıt kısmı
    public void registerAccount() {
    	//tüm boşlukları doldurmazsan hata mesajı
    	if(register_email.getText().isEmpty()
    			|| register_username.getText().isEmpty()
    			|| register_password.getText().isEmpty() ) {
    		alert.errorMessage("Please fill all blank feilds.");
    		
    	}else {
    		//doldurursan database e ekleme
    		String checkUsername = "SELECT * FROM user WHERE username = '"
    				+ register_username.getText() + "'";
    		
    		connect = Database.connectDB();
    		
    		try {
    			
    			//normal şekilde şifreyi yazdıktan sonra şifreyi göster yapıp değiştirince eski şifreyi yeni şifreyle aynı yapma
    			if(!register_showPasswordCheck.isSelected()) {
    				
    				if(!register_showPassword.getText().equals(register_password.getText())) {
    					
    					register_showPassword.setText(register_password.getText());	
    				}
    					
    			}else{
    				
    				if(!register_showPassword.getText().equals(register_password.getText())) {
    					
    					register_password.setText(register_showPassword.getText());	
    				}	
    			}
    			
    			prepare = connect.prepareStatement(checkUsername);
    			result = prepare.executeQuery();
    			
    			if(result.next()) {
    				
    				alert.errorMessage(register_username.getText()+" is already exist.");
    				
    				
    			//şifre 6 karakter olmazsa hata mesajı
    			}else if(register_password.getText().length()<6){
    				
    				alert.errorMessage("Invalid password, at least 6 characters needed.");
    		
    			}
    			else {
    				//her şey doğruysa database e admini kaydetme
    				String insertData = "INSERT INTO user (username, mail_address, password, authority) VALUES(?,?,?,?)";
    				
    				prepare = connect.prepareStatement(insertData);
    				prepare.setString(1, register_username.getText());
    				prepare.setString(2, register_email.getText());
    				prepare.setString(3, register_password.getText());
    				prepare.setBoolean(4, true);
    				
    				prepare.executeUpdate();
    				
    				
    				
    				alert.successMessage("Registered successfully.");
    				registerClear();
    				
    				
    				login_form.setVisible(true);
    				register_form.setVisible(false);
    				
    			}
    			
    		}catch(Exception e) {
    			
    			e.printStackTrace();
    		}
    	}
    }
    //kayıt olduktan sonra kullanılmak için kayıt olma boşluklarındaki karakterleri temizleme
    public void registerClear() {
    	
    	
    	register_email.clear();
    	register_username.clear();
    	register_password.clear();
    	register_showPassword.clear();	
    }
    
    //şifreyi göster kısmı
    public void registerShowPassword() {
    	
    	if(register_showPasswordCheck.isSelected()) {
    		
    		register_showPassword.setText(register_password.getText());
    		register_showPassword.setVisible(true);
    		register_password.setVisible(false);
    	}else {
    		
    		register_password.setText(register_showPassword.getText());
    		register_showPassword.setVisible(false);
    		register_password.setVisible(true);
    	}
    }
    //userlist kısmı
    public void userList() {
    	
    	List<String> listU = new ArrayList<>();
    	
    	for(String data : Users.user) {
    		
    		listU.add(data);
    	}
    	
    	ObservableList listData = FXCollections.observableList(listU);
    	
    	login_selectUser.setItems(listData);
    	
    	
    	
    	
    	
    	
    	
    }
    // userlist e basınca sayfa değiştirme kodları
    public void switchPage() {
    	
    	//admin portal seçilirse sample.fxml kısmına atıyor
    	if(login_selectUser.getSelectionModel().getSelectedItem() == "Admin Portal") {
    		
    		try {
    			
    			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
    				
    			Stage stage = new Stage();
    			
    			stage.setTitle("Score Master");
    			
    			stage.setScene(new Scene(root,330,550));
    			
    			stage.setResizable(false);
    			
    			stage.show();
    				
    		}catch(Exception e) {
    			
    			e.printStackTrace();	
    		}
    		//user portal seçilirse userpage.fxml kısmına atıyor
    	}else if(login_selectUser.getSelectionModel().getSelectedItem() == "User Portal") {
    		
    		
    		try {
    			
    			Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
    			
    			Stage stage = new Stage();
    			
    			stage.setTitle("Score Master");
    			
    			stage.setScene(new Scene(root,330,550));
    			
    			stage.setResizable(false);
    			
    			stage.show();
    				
    		}catch(Exception e) {
    			
    			e.printStackTrace();	
    		}
    	}
    	login_selectUser.getScene().getWindow().hide();
    }
    
    
    //kayıt olma ve giriş yapma ekranı arasında geçiy yapmak için
    @FXML
    void switchForm(ActionEvent event) {
    	
    	if(event.getSource() == login_registerHere) {
    		login_form.setVisible(false);
    		register_form.setVisible(true);
    	}else if(event.getSource() == register_loginHere) {
    		login_form.setVisible(true);
    		register_form.setVisible(false);
    	}

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	userList();
    }
	
}
