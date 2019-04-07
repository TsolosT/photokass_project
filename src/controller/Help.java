package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Help implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private Button close;
	
	public void close(ActionEvent event) {
		 Stage stage = (Stage) close.getScene().getWindow();
		    // do what you have to do
		    stage.close();
	}
}
