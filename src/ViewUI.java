import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;
import java.util.*;

class ViewUI{
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
        heading.getChildren().add(new Label("Patient Login"));

        /**********************Text Boxes**********************/
        
        //Patient ID
        Label patientId = new Label("Patient ID: ");
        TextField patientIdField = new TextField();

        HBox authBox = new HBox();
        authBox.setAlignment(Pos.CENTER);
        authBox.setTranslateY(-175);
        authBox.getChildren().addAll(patientId, patientIdField);

        /**********************BUTTONS**********************/
        
        //Login
        login = new Button("Login");
        login.setPrefWidth(75);
        login.setTranslateY(72);
        login.setAlignment(Pos.CENTER);
        login.setTranslateX(725);
        login.setPadding(new Insets(10, 10, 10, 10)); 

        //Create Account
        accountCreate = new Button("Create Account");
        accountCreate.setPrefWidth(150);
        accountCreate.setAlignment(Pos.CENTER);
        accountCreate.setTranslateY(35);
        accountCreate.setTranslateX(400);
        accountCreate.setPadding(new Insets(10, 10, 10, 10));
        
		login.setOnAction(e -> {
			String id = patientIdField.getText();
			try {
			Scanner scnr = new Scanner(new File("files/patientID_patientInfo.txt"));
			    while (scnr.hasNextLine()) {
                    String line = scnr.nextLine();
					if (id.equals(line.split(" ")[0])) {
                        // isLogged = true;
                        // a = new Alert(Alert.AlertType.NONE);
                        // a.setAlertType(Alert.AlertType.INFORMATION);
                        // a.setContentText("Login Successful!");
                        // a.showAndWait();

                        //To swtich to the Nurse or Patient view after authentication
                        root.getChildren().clear();
                        // startUI(root);
                        if (viewChoice == 1) NurseView.startUI(root);
                        else PatientView.startUI(root);
                        break;
                    // } else {
                    //     a = new Alert(Alert.AlertType.NONE);
                    //     a.setAlertType(Alert.AlertType.ERROR);
                    //     a.setContentText("Incorrect Password");
                    //     a.show();
                    //     root.getChildren().clear();
					// 		return root;
                    //     break;
                    }
                } scnr.close();
			} catch (FileNotFoundException e1) { e1.printStackTrace(); }
		});
        
        accountCreate.setOnAction(e -> {
            root.getChildren().clear();
            createAccount(root, viewChoice);
        });
        
        
        /**********************Engine**********************/                
        //Instantiating the VBox
        // if (isLogged) {
        //     root.getChildren().clear();
        //     System.out.println("jfns");
        // }
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(heading, login, accountCreate, authBox);
        return true;
        // return root;
    }

    public void createAccount(VBox root, int viewChoice) {
        Button accountCreate;
        String userID, userPass, firstSub, lastSub, dobSub, fileContent;
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
        firstName.setTranslateY(-165);
        firstName.getChildren().addAll(first, firstnameField);
        
        //Last Name
        HBox lastName = new HBox();
        Label lastNameLabel = new Label("Last Name: ");
        TextField lastNameField = new TextField();
        lastName.setTranslateY(-150);
        lastName.setAlignment(Pos.CENTER);
        lastName.getChildren().addAll(lastNameLabel, lastNameField);
        
        //Gender
        HBox genderBox = new HBox();
        Label gender = new Label("Gender: ");
        TextField genderField = new TextField();
        genderBox.setTranslateY(-135);
        genderBox.setTranslateX(10);
        genderBox.setAlignment(Pos.CENTER);
        genderBox.getChildren().addAll(gender, genderField);
        
        //Phone Number
        HBox phoneBox = new HBox();
        Label phoneLabel = new Label("Phone Number: ");
        TextField phoneField = new TextField();
        phoneBox.setTranslateY(-120);
        phoneBox.setTranslateX(-13);
        phoneBox.setAlignment(Pos.CENTER);
        phoneBox.getChildren().addAll(phoneLabel, phoneField);
        
        //Date of Birth
        HBox ageBox = new HBox();
        Label ageLabel = new Label("Age: ");
        TextField ageField = new TextField();
        ageBox.setTranslateY(-105);
        ageBox.setTranslateX(-8);
        ageBox.setAlignment(Pos.CENTER);
        ageBox.getChildren().addAll(ageLabel, ageField);

        /**********************BUTTONS**********************/
        
        //Create Account
        accountCreate = new Button("Create Account");
        accountCreate.setPrefWidth(150);
        accountCreate.setTranslateY(85);
        accountCreate.setAlignment(Pos.CENTER);
        accountCreate.setTranslateX(575);
        accountCreate.setPadding(new Insets(10, 10, 10, 10));
        
        //Back Button
        // Button back = new Button("Back");
        // back.setPrefWidth(75);
        // back.setTranslateY(0);
        // back.setAlignment(Pos.CENTER);
        // back.setTranslateX(415);
        // back.setPadding(new Insets(10, 10, 10, 10));
		// back.setOnAction(e -> {
		// 	this.getChildren().clear();
		// 	this.getChildren().add(new PatientLogin());
		// });
        
        accountCreate.setOnAction(e -> {
        	//Check if fields are empty
			if (firstnameField.getText().isEmpty() || lastNameField.getText().isEmpty() || genderField.getText().isEmpty() || phoneField.getText().isEmpty() || ageField.getText().isEmpty()) {
				a.setAlertType(Alert.AlertType.ERROR);
				a.setContentText("Please fill out all fields.");
				a.show();
			} else {
            //     File file = new File("src/files/patientID_patientInfo.txt");
            //     fileContent = "\n" + usernameField.getText() + " " + passwordField.getText();

            //     //check if the name already exists
            //     try {
            //         Scanner scnr = new Scanner(new FileInputStream(file));
            //         while (scnr.hasNextLine()) {
            //             String line = scnr.nextLine();
            //             if (line.contains(userID)) {
            //                 a.setAlertType(Alert.AlertType.ERROR);
            //                 a.setContentText("Username already exists.");
            //                 a.show();
            //                 return;
            //             }
            //         } scnr.close();
            //     } catch (FileNotFoundException e1) { e1.printStackTrace(); }
                
            //     //Write to file
            //     try { fos = new FileOutputStream(file, true); } 
            //     catch (FileNotFoundException e1) { e1.printStackTrace(); }
                
            //     try {
            //         fos.write(fileContent.getBytes());
            //         fos.close();
            //     } catch (IOException e1) { e1.printStackTrace(); }
                int patientID = system.newPatient(firstnameField.getText(), lastNameField.getText(), genderField.getText(), Long.parseLong(phoneField.getText()), Integer.parseInt(ageField.getText()));

                root.getChildren().clear();
                if (viewChoice == 1) NurseView.startUI(root);
                else PatientView.startUI(root);
			}
        });
        
        /**********************Engine**********************/
        //Instantiating the VBox
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(heading, firstName, lastName, genderBox, phoneBox, ageBox, accountCreate); //back);
    }
}