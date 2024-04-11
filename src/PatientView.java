import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;
import java.util.*;


class PatientView extends VBox {
    ViewUI view = new ViewUI();
    static String patientID;
    static Admin system = new Admin();
	
	PatientView() {
            this.setPadding(new Insets(10, 10, 10, 10));
            System.out.println(view.authenticate(this, 2));
    }
     
    public static void startUI(VBox root, String id) {    
        patientID = id;    
        // Labels for patient contact information
        Label nameLabel = new Label("Name:");
        Label phoneLabel = new Label("Phone Number:");

        // Text fields for displaying and editing patient contact information
        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Enter new Phone number");

        // Labels for patient details
        Label insuranceLabel = new Label("Insurance Information:");
        Label pharmacyLabel = new Label("Pharmacy Information:");

        // Sample patient details (replace with actual data)
        Label insuranceInfoLabel = new Label("Insurance Company: XYZ, Policy Number: 12345");
        Label pharmacyInfoLabel = new Label("Pharmacy Name: ABC Pharmacy, Phone: 123-456-7890");

        // Label for visit summaries
        Label visitSummaryLabel = new Label("Visit Summary:");

        // ListView for nurse-patient communication
        String messages = system.loadMessages(patientID);
        ListView<String> communicationListView = new ListView<>();
        communicationListView.getItems().add(messages);
        communicationListView.setPrefHeight(100); // Set preferred height

        // Text field for entering messages
        TextField messageTextField = new TextField();
        messageTextField.setPromptText("Enter your message...");

        // Button for sending messages
        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            String message = messageTextField.getText().trim();
            if (!message.isEmpty()) {
                communicationListView.getItems().add("Patient: " + message); // Add nurse's message to the list view
                system.saveMessage(message, "Patient", patientID);
                messageTextField.clear(); // Clear the message text field
            }
        });

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
        grid.add(nameLabel, 0, 0);
        // grid.add(phoneTextField, 1, 0);
        grid.add(phoneLabel, 0, 1);
        grid.add(phoneTextField, 1, 1);
        grid.add(insuranceLabel, 0, 2);
        grid.add(insuranceInfoLabel, 1, 2);
        grid.add(pharmacyLabel, 0, 3);
        grid.add(pharmacyInfoLabel, 1, 3);
        grid.add(visitSummaryLabel, 0, 4);
        grid.add(communicationListView, 1, 4);
        grid.add(messageTextField, 0, 5, 2, 1);
        grid.add(sendButton, 0, 6);
        grid.add(replyLabel, 0, 7);
        grid.add(replyListView, 1, 7);
        
        /**********************Engine**********************/
        //Instantiating the VBox
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(grid);
    }
   
}