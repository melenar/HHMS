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


class PatientView extends VBox {
	Button edit, logout;
	VBox root;
	Scanner scnr = new Scanner(System.in);
	
	PatientView() {
    	
    	//Heading
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-75);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("Patient Portal"));

        
		/**********************Text Boxes**********************/
        
        //Chat
        HBox chatBox = new HBox();
        Label chat = new Label("Chat: ");
        TextField patientChat = new TextField();
        chatBox.setAlignment(Pos.CENTER);
        chatBox.setTranslateY(500);
        chatBox.setTranslateX(400);
        chatBox.getChildren().addAll(chat, patientChat);


        /**********************BUTTONS**********************/
        
        //View/Edit Info
        edit = new Button("View/Edit Info");
        edit.setPrefWidth(100);
        edit.setTranslateY(450);
        edit.setTranslateX(0);
        edit.setPadding(new Insets(10, 10, 10, 10));
        
        edit.setOnAction(e -> {
            this.getChildren().clear();
            this.getChildren().add(new InfoPage());
        });
        
        
        logout = new Button("Logout");
        logout.setPrefWidth(100);
        logout.setTranslateY(475);
        logout.setTranslateX(0);
        logout.setPadding(new Insets(10, 10, 10, 10));
        
		logout.setOnAction(e -> {
			this.getChildren().clear();
			this.getChildren().add(new PatientLogin());
		});
        
        
        
        
        /**********************Engine**********************/
        //Instantiating the VBox
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(heading, chatBox, edit, logout);
    }
   
}