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

class InfoPage extends VBox {
	Button edit, save;	
	Alert a;
	
	InfoPage() {
		a = new Alert(Alert.AlertType.NONE);
    	
    	//Heading
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-75);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("User Information"));

        
		/**********************Text Boxes**********************/
        
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
        Label ageLabel = new Label("Date  of Birth: ");
        TextField ageField = new TextField();
        ageBox.setTranslateY(-105);
        ageBox.setTranslateX(-8);
        ageBox.setAlignment(Pos.CENTER);
        ageBox.getChildren().addAll(ageLabel, ageField);

        /**********************BUTTONS**********************/
        
        //Save Info
        save = new Button("Save");
        save.setPrefWidth(75);
        save.setTranslateY(100);
        save.setTranslateX(200);
        
        
        save.setOnAction(e -> {
            System.out.println("Information Saved");
            a.setAlertType(Alert.AlertType.CONFIRMATION);
            a.setContentText("Do you want to save this information?");
            a.showAndWait();
            
            //Confirmation to save information
			if (a.getResult().getText().equals("OK")) {
				a.setAlertType(Alert.AlertType.INFORMATION);
				a.setContentText("Information saved");
				a.showAndWait();
			
			} else {
				a.setAlertType(Alert.AlertType.WARNING);
				a.setContentText("Information not saved");
				a.showAndWait();
			}
			
			
            this.getChildren().clear();
            this.getChildren().add(new PatientView());
        });
        save.setPadding(new Insets(10, 10, 10, 10));
        
        
        /**********************Engine**********************/
        //Instantiating the VBox
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(heading, save, firstName, lastName, genderBox, phoneBox, ageBox);
    }
   
}