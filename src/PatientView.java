import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;
import java.util.*;


class PatientView extends VBox {
    ViewUI view = new ViewUI();
	
	PatientView() {
            this.setPadding(new Insets(10, 10, 10, 10));
            // TilePane heading = new TilePane(Orientation.VERTICAL);
            // heading.setPadding(new Insets(20, 20, 20, 20));
            // heading.setAlignment(Pos.CENTER);
            // heading.setVgap(10.0);
            // heading.getChildren().add(new Label("Nurse View"));
            // this.getChildren().addAll(view.authenticate(this));
            System.out.println(view.authenticate(this, 2));
    }
    
    public static void startUI(VBox root, int id) {
        // Button edit, logout;
        // Scanner scnr = new Scanner(System.in);
    	// //Heading
        // TilePane heading = new TilePane(Orientation.VERTICAL);
        // heading.setPadding(new Insets(20, 20, 20, 20));
        // heading.setAlignment(Pos.CENTER);
        // heading.setTranslateY(-75);
        // heading.setVgap(10.0);
        // heading.getChildren().add(new Label("Patient Portal"));

        
		// /**********************Text Boxes**********************/
        
        // //Chat
        // HBox chatBox = new HBox();
        // Label chat = new Label("Chat: ");
        // TextField patientChat = new TextField();
        // chatBox.setAlignment(Pos.CENTER);
        // chatBox.setTranslateY(500);
        // chatBox.setTranslateX(400);
        // chatBox.getChildren().addAll(chat, patientChat);


        // /**********************BUTTONS**********************/
        
        // //View/Edit Info
        // edit = new Button("View/Edit Info");
        // edit.setPrefWidth(100);
        // edit.setTranslateY(450);
        // edit.setTranslateX(0);
        // edit.setPadding(new Insets(10, 10, 10, 10));
        
        // edit.setOnAction(e -> {
        //     root.getChildren().clear();
        //     // root.getChildren().add(new InfoPage());
        // });
        
        
        // logout = new Button("Logout");
        // logout.setPrefWidth(100);
        // logout.setTranslateY(475);
        // logout.setTranslateX(0);
        // logout.setPadding(new Insets(10, 10, 10, 10));
        
		// logout.setOnAction(e -> {
		// 	root.getChildren().clear();
		// 	// root.getChildren().add(new PatientLogin());
		// });
        
        // Labels for patient contact information
        Label phoneLabel = new Label("Phone Number:");
        Label emailLabel = new Label("Email:");

        // Text fields for displaying and editing patient contact information
        TextField phoneTextField = new TextField();
        TextField emailTextField = new TextField();

        // Labels for patient details
        Label insuranceLabel = new Label("Insurance Information:");
        Label pharmacyLabel = new Label("Pharmacy Information:");

        // Sample patient details (replace with actual data)
        Label insuranceInfoLabel = new Label("Insurance Company: XYZ, Policy Number: 12345");
        Label pharmacyInfoLabel = new Label("Pharmacy Name: ABC Pharmacy, Phone: 123-456-7890");

        // Label for visit summaries
        Label visitSummaryLabel = new Label("Visit Summary:");

        // ListView for displaying visit summaries
        ListView<String> visitSummaryListView = new ListView<>();

        // Text area for sending messages to the doctor/nurse
        TextArea messageTextArea = new TextArea();
        messageTextArea.setPromptText("Enter your message...");

        // Button for sending messages
        Button sendButton = new Button("Send");

        // Label for replies from doctor/nurse
        Label replyLabel = new Label("Replies:");

        // ListView to display replies from doctor/nurse
        ListView<String> replyListView = new ListView<>();

        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Adding components to the layout
        grid.add(phoneLabel, 0, 0);
        grid.add(phoneTextField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailTextField, 1, 1);
        grid.add(insuranceLabel, 0, 2);
        grid.add(insuranceInfoLabel, 1, 2);
        grid.add(pharmacyLabel, 0, 3);
        grid.add(pharmacyInfoLabel, 1, 3);
        grid.add(visitSummaryLabel, 0, 4);
        grid.add(visitSummaryListView, 1, 4);
        grid.add(messageTextArea, 0, 5, 2, 1);
        grid.add(sendButton, 0, 6);
        grid.add(replyLabel, 0, 7);
        grid.add(replyListView, 1, 7);
        
        /**********************Engine**********************/
        //Instantiating the VBox
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(grid);
    }
   
}