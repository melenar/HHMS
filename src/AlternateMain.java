package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
                 
        	//Creates the 4 panes used 
		BorderPane MainPane = new BorderPane();
		BorderPane PatientPane = new BorderPane();
		BorderPane NursePane = new BorderPane();
		BorderPane DoctorPane = new BorderPane();
		
		Stage stage = new Stage();
		
		//Creates the 4 scenes used
		Scene MainUI = new Scene(MainPane, 1200, 800);
		Scene PatientUI = new Scene(PatientPane, 1200, 800);
		Scene NurseUI = new Scene(NursePane, 1200, 800);
		Scene DoctorUI = new Scene(DoctorPane, 1200, 800);
		
		//Sets title of MainUI page
		Text title = new Text();
		title.setText("Welcome to Pediatric Doctor's Office");
		title.setX(500);
		title.setY(50);
		
		//Creates buttons that lead to the other pages on MainUI page
		Button PatientButton = new Button("Patient View");
		PatientButton.setOnAction(e -> stage.setScene(PatientUI));
		Button NurseButton = new Button("Nurse View");
		NurseButton.setOnAction(e -> stage.setScene(NurseUI));
		Button DoctorButton = new Button("Doctor View");
		DoctorButton.setOnAction(e -> stage.setScene(DoctorUI));
		
		//Move buttons to the correct location on MainUI page
		VBox buttons = new VBox();
		buttons.setSpacing(20);
		buttons.getChildren().add(PatientButton);
		buttons.getChildren().add(NurseButton);
		buttons.getChildren().add(DoctorButton);
			
	   	VBox.setMargin(PatientButton, new Insets(300, 300, 20, 550));  
	    	VBox.setMargin(NurseButton, new Insets(20, 20, 20, 550)); 
	    	VBox.setMargin(DoctorButton, new Insets(20, 20, 20, 550));  
		
	    	//Adds everything to MainUI
		MainPane.setCenter(buttons);		
		MainPane.getChildren().add(title);
		
		stage.setScene(MainUI);
		stage.show();
		
	}		
}
