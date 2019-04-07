package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class dialog {

	
	public static void SaveInfdialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText("We succefully saved the picture.");

		alert.showAndWait();
	}
	public static void unSaveInfdialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText("Something went wrong,we failed save the picture.Try again..");

		alert.showAndWait();
	}
	
	public static void errorMsg(String errormsg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText("Something went wrong,"+errormsg+".Try again..");

		alert.showAndWait();
	}
	public static boolean confirm() {
		boolean answer;	
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Clear all");
		alert.setContentText("Are you sure to delete everything on the picture?");
		Optional<ButtonType> result=alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			  answer=true;
			}
		else {
			answer=false;
		}
		return answer;	
	}
	
}
