package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


//mesaj kısmını oluşturma
public class AlertMessage {
	
	private Alert alert;
	
	
//hata mesajı	
	public void errorMessage(String message) {
		
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

//başarı mesajı
	public void successMessage(String message) {
		
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
//doğrulama mesajı
	public boolean confirmationMessage(String message) {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		Optional<ButtonType> option = alert.showAndWait();
		
			if(option.get().equals(ButtonType.OK)) {
				return true;
			}else {
				return false;
			}
		
	}
			
	
	

}