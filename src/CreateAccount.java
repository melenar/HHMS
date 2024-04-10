import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.control.Alert;



class CreateAccount extends VBox {
	Button accountCreate;
	FileOutputStream fos;
	VBox root;
	String userID, userPass, firstSub, lastSub, dobSub, fileContent;
	
    CreateAccount() {
		Alert a = new Alert(Alert.AlertType.NONE);

    	
    	//Heading
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-75);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("Create an Account"));

        
		/**********************Text Boxes**********************/
        
        //Username
        HBox username = new HBox();
        Label usernameLabel = new Label("Username: ");
        TextField usernameField = new TextField();
        username.setAlignment(Pos.CENTER);
        username.setTranslateY(-140);
        username.getChildren().addAll(usernameLabel, usernameField);
        
        //Password
        HBox password = new HBox();
        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();
        password.setAlignment(Pos.CENTER);
        password.setTranslateY(-125);
        password.getChildren().addAll(passwordLabel, passwordField);

        /**********************BUTTONS**********************/
        
        //Create Account
        accountCreate = new Button("Create Account");
        accountCreate.setPrefWidth(150);
        accountCreate.setTranslateY(85);
        accountCreate.setAlignment(Pos.CENTER);
        accountCreate.setTranslateX(575);
        accountCreate.setPadding(new Insets(10, 10, 10, 10));
        
        //Back Button
        Button back = new Button("Back");
        back.setPrefWidth(75);
        back.setTranslateY(0);
        back.setAlignment(Pos.CENTER);
        back.setTranslateX(415);
        back.setPadding(new Insets(10, 10, 10, 10));
		back.setOnAction(e -> {
			this.getChildren().clear();
			this.getChildren().add(new PatientLogin());
		});
        

        
        accountCreate.setOnAction(e -> {
        	
        	//Check if fields are empty
			if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) { //Checks if fields are left empty
				a.setAlertType(Alert.AlertType.ERROR);
				a.setContentText("Please fill out all fields.");
				a.show();
			} else {
				userID = usernameField.getText();
				//Checks if the username is 8 characters long
				if (userID.length() != 8) {
					a.setAlertType(Alert.AlertType.ERROR);
					a.setContentText("Username must be 8 characters long.");
					a.show();
				}

				//Splits the username into three parts for validation
				firstSub = userID.substring(0, 3).toLowerCase();
				lastSub = userID.substring(3,6).toLowerCase();
				dobSub = userID.substring(6, 8);

				//Checks if the username is in the correct format
				if (!firstSub.matches("[a-z]+") || !lastSub.matches("[a-z]+") || !dobSub.matches("[0-9]+")) {
					a.setAlertType(Alert.AlertType.ERROR);
					a.setContentText(
							"Username must be in the format: first three letters of first name, first three letters of last name, and last two digits of date of birth.");
					a.show();
				} else {
					File file = new File("src/files/patientID_patientInfo.txt");
					fileContent = "\n" + usernameField.getText() + " " + passwordField.getText();

					//check if the name already exists
					try {
						Scanner scnr = new Scanner(new FileInputStream(file));
						while (scnr.hasNextLine()) {
							String line = scnr.nextLine();
							if (line.contains(userID)) {
								a.setAlertType(Alert.AlertType.ERROR);
								a.setContentText("Username already exists.");
								a.show();
								return;
							}
						} scnr.close();
					} catch (FileNotFoundException e1) { e1.printStackTrace(); }
					
					//Write to file
					try { fos = new FileOutputStream(file, true); } 
					catch (FileNotFoundException e1) { e1.printStackTrace(); }
					
					try {
						fos.write(fileContent.getBytes());
						fos.close();
					} catch (IOException e1) { e1.printStackTrace(); }
				}				
				this.getChildren().clear();
				this.getChildren().add(new PatientLogin());
			}
        });
        
        
        
        /**********************Engine**********************/
        //Instantiating the VBox
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(heading, accountCreate, username, password, back);
    }
   
}