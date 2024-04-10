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
import java.io.FileOutputStream;
import javafx.scene.control.Alert;



class CreateAccount extends VBox {
	Button accountCreate;
	VBox root;
	
    CreateAccount() {
    	
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
        username.setTranslateY(-165);
        username.getChildren().addAll(usernameLabel, usernameField);
        
        //Password
        HBox password = new HBox();
        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();
        password.setAlignment(Pos.CENTER);
        password.setTranslateY(-150);
        password.getChildren().addAll(passwordLabel, passwordField);

        /**********************BUTTONS**********************/
        
        //Create Account
        accountCreate = new Button("Create Account");
        accountCreate.setPrefWidth(150);
        accountCreate.setTranslateY(35);
        accountCreate.setTranslateX(200);
        accountCreate.setPadding(new Insets(10, 10, 10, 10));

        
        accountCreate.setOnAction(e -> {
            System.out.println("Account Created");
            this.getChildren().clear();
            this.getChildren().add(new PatientLogin());
        });
        
        
        
        /**********************Engine**********************/
        //Instantiating the VBox
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(heading, accountCreate, username, password);
    }
   
}