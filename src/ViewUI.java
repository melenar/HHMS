import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;
import java.util.*;

class ViewUI {
    boolean isLogged = false;
    Admin system = new Admin();

    public boolean authenticate(VBox root, int viewChoice) {
        TilePane heading;
        Button login, accountCreate;

      //Heading
        heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-65);
        heading.setVgap(10.0);

	//create a new label with Patient Login
        Label patientLoginTitle = new Label("Patient Login");
        patientLoginTitle.setStyle("-fx-font-size: 24px; " +
        			  "-fx-font-weight: bold;");
        
        //add label to heading tilePane
        heading.getChildren().add(patientLoginTitle);

        /**********************Text Boxes**********************/
        
        //Patient ID
        Label patientId = new Label("Patient ID: ");
	patientId.setStyle("-fx-font-weight: bold;");
        TextField patientIdField = new TextField();
        
        patientId.setTranslateY(50);
        patientIdField.setAlignment(Pos.CENTER);
        patientIdField.setTranslateY(50);

        HBox authBox = new HBox();
        authBox.setAlignment(Pos.CENTER);
        authBox.setTranslateY(-175);
        authBox.getChildren().addAll(patientId, patientIdField);

        /**********************BUTTONS**********************/
        
        //Login
        login = new Button("Login");
        login.setPrefWidth(75);
        login.setAlignment(Pos.CENTER);
        login.setPadding(new Insets(10, 10, 10, 10)); 

        //Create Account
        accountCreate = new Button("Create Account");
        accountCreate.setPrefWidth(150);
        accountCreate.setAlignment(Pos.CENTER);
        accountCreate.setPadding(new Insets(10, 10, 10, 10));
        
		login.setOnAction(e -> {
			String id = patientIdField.getText();
			try {
			Scanner scnr = new Scanner(new File("files/IdRecords.txt"));
			    while (scnr.hasNextLine()) {
                    String line = scnr.nextLine();
					if (id.equals(line.split(" ")[0])) {
                        //To swtich to the Nurse or Patient view after authentication
                        root.getChildren().clear();
                        if (viewChoice == 1) System.out.print("switching to nurse"); //NurseView.startUI(root, patientID);
                        else if (viewChoice == 2) PatientView.startUI(root, id);
                        else DoctorView.startUI(root, id);
                        break;
                    }
                } scnr.close();
			} catch (FileNotFoundException e1) { e1.printStackTrace(); }
		});
	    
        
        accountCreate.setOnAction(e -> {
            root.getChildren().clear();
            createAccount(root, viewChoice);
        });

	//create a hBox for the buttons
        HBox buttonsBox = new HBox(20);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(accountCreate, login);
        
        /**********************Engine**********************/                
        //Instantiating the VBox
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(heading, authBox, buttonsBox);
        return true;
    }

    public void createAccount(VBox root, int viewChoice) {
        Button accountCreate;
        //String userID, userPass, firstSub, lastSub, dobSub, fileContent;
		Alert a = new Alert(Alert.AlertType.NONE);

        //Heading
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-75);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("Create an Account"));

		//**********************Text Boxes**********************/
        
        //First Name
        HBox firstName = new HBox();
        Label first = new Label("First Name: ");
        TextField firstnameField = new TextField();
        firstName.setAlignment(Pos.CENTER);
        firstName.setTranslateY(-80);
        firstName.getChildren().addAll(first, firstnameField);
        
        //Last Name
        HBox lastName = new HBox();
        Label lastNameLabel = new Label("Last Name: ");
        TextField lastNameField = new TextField();
        lastName.setTranslateY(-60);
        lastName.setAlignment(Pos.CENTER);
        lastName.getChildren().addAll(lastNameLabel, lastNameField);
        
        //Gender
        HBox genderBox = new HBox();
        Label gender = new Label("Gender: ");
        TextField genderField = new TextField();
        genderBox.setTranslateY(-40);
        genderBox.setTranslateX(10);
        genderBox.setAlignment(Pos.CENTER);
        genderBox.getChildren().addAll(gender, genderField);
        
        //Phone Number
        HBox phoneBox = new HBox();
        Label phoneLabel = new Label("Phone Number: ");
        TextField phoneField = new TextField();
        phoneBox.setTranslateY(-20);
        phoneBox.setTranslateX(-10);
        phoneBox.setAlignment(Pos.CENTER);
        phoneBox.getChildren().addAll(phoneLabel, phoneField);
        //Date of Birth
        HBox dobBox = new HBox();
        Label dobLabel = new Label("Birth Date: ");
        TextField dobField = new TextField();
        dobBox.setTranslateY(-0);
        dobBox.setTranslateX(3);
        dobBox.setAlignment(Pos.CENTER);
        dobBox.getChildren().addAll(dobLabel, dobField);

        /**********************BUTTONS**********************/
        
        //Create Account
        accountCreate = new Button("Create Account");
        accountCreate.setPrefWidth(150);
        accountCreate.setTranslateY(85);
        accountCreate.setAlignment(Pos.CENTER);
        accountCreate.setTranslateX(575);
        accountCreate.setPadding(new Insets(10, 10, 10, 10));
        
        accountCreate.setOnAction(e -> {
        	//Check if fields are empty
			if (firstnameField.getText().isEmpty() || lastNameField.getText().isEmpty() || genderField.getText().isEmpty() || phoneField.getText().isEmpty() || dobField.getText().isEmpty()) {
				a.setAlertType(Alert.AlertType.ERROR);
				a.setContentText("Please fill out all fields.");
				a.show();
			} else {
                String patientID = system.newPatient(firstnameField.getText(), lastNameField.getText(), genderField.getText(), Long.parseLong(phoneField.getText()), dobField.getText());
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Account created successfully! Your patient ID is " + patientID);
                a.showAndWait();

                root.getChildren().clear();
                if (viewChoice == 1) System.out.print("switching to nurse"); //NurseView.startUI(root, patientID);
                else if (viewChoice == 2) PatientView.startUI(root, patientID);
                else DoctorView.startUI(root, patientID);
			}
        });
        
        /**********************Engine**********************/
        //Instantiating the VBox
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(heading, firstName, lastName, genderBox, phoneBox, dobBox, accountCreate); //back);
    }


}

