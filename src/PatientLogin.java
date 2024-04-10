import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.Alert;

class PatientLogin extends VBox {
	Button login, accountCreate;
	VBox root;
	FileInputStream fis;
	File file = new File("src/files/patientID_patientInfo.txt");
	String userName, userPass;
	Alert a;

	
    PatientLogin() {

    	//Heading
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-65);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("Patient Login"));

        
		/**********************Text Boxes**********************/
        
        //Username
        HBox username = new HBox();
        Label usernameLabel = new Label("Username: ");
        TextField usernameField = new TextField();
        username.setAlignment(Pos.CENTER);
        username.setTranslateY(-175);
        username.getChildren().addAll(usernameLabel, usernameField);
        
        //Password
        HBox password = new HBox();
        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();
        password.setAlignment(Pos.CENTER);
        password.setTranslateY(-150);
        password.getChildren().addAll(passwordLabel, passwordField);

        /**********************BUTTONS**********************/
        
        //Login
        Button login = new Button("Login");
        login.setPrefWidth(75);
        login.setTranslateY(72);
        login.setAlignment(Pos.CENTER);
        login.setTranslateX(725);
        login.setPadding(new Insets(10, 10, 10, 10)); 

        
		login.setOnAction(e -> {
			userName = usernameField.getText();
			userPass = passwordField.getText();
			
			try {
				Scanner scnr = new Scanner(new FileInputStream(file));
				
				while (scnr.hasNextLine()) {
					String line = scnr.nextLine();
					if (userName.equals(line.split(" ")[0])) {
						if (userPass.equals(line.split(" ")[1])) {
							a = new Alert(Alert.AlertType.NONE);
							a.setAlertType(Alert.AlertType.INFORMATION);
							a.setContentText("Login Successful!");
							a.showAndWait();
							this.getChildren().clear();
							this.getChildren().add(new PatientView());
							break;
						} else {
							a = new Alert(Alert.AlertType.NONE);
							a.setAlertType(Alert.AlertType.ERROR);
							a.setContentText("Incorrect Password");
							a.show();
							break;
						}

					} 
				} scnr.close();
			} catch (FileNotFoundException e1) { e1.printStackTrace(); }
		});
        
		
		
		
        //Create Account
        Button accountCreate = new Button("Create Account");
        accountCreate.setPrefWidth(150);
        accountCreate.setAlignment(Pos.CENTER);
        accountCreate.setTranslateY(35);
        accountCreate.setTranslateX(400);
        accountCreate.setPadding(new Insets(10, 10, 10, 10));

        
        accountCreate.setOnAction(e -> {
            this.getChildren().clear();
            this.getChildren().add(new CreateAccount());
            });
        
        
        /**********************Engine**********************/                
        //Instantiating the VBox
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(heading, login, accountCreate, username, password);
    } 
}