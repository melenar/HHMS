import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;
import java.util.*;


class DoctorView extends VBox {
	ViewUI view = new ViewUI();
    static String patientID;
    static Admin system = new Admin();

    DoctorView() {
		this.setPadding(new Insets(10, 10, 10, 10));
        System.out.println(view.authenticate(this, 3));
	}

	public static void startUI(VBox root, String id) {
		patientID = id;
		// Heading label
        Label headingLabel = new Label("Patient Health Assessment");

        // Labels for patient details
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        Label genderLabel = new Label("Gender:");
        Label immunizationLabel = new Label("Immunization:");

        // Sample patient details (replace with actual data)
        Label nameValueLabel = new Label("John Doe");
        Label ageValueLabel = new Label("30");
        Label genderValueLabel = new Label("Male");
        Label immunizationValueLabel = new Label("Up to date");

        // Labels for doctor's findings and prescription
        Label findingsLabel = new Label("Doctor's Findings:");
        Label prescriptionLabel = new Label("Prescription:");

        // Text areas for doctor to enter findings and prescription
        TextArea findingsTextArea = new TextArea();
        TextArea prescriptionTextArea = new TextArea();

        // Button for submitting doctor's findings and prescription
        Button submitButton = new Button("Submit");
		submitButton.setOnAction(e -> {
            if (!findingsTextArea.getText().isEmpty() && !prescriptionTextArea.getText().isEmpty()) {
                save(findingsTextArea.getText(), prescriptionTextArea.getText());
				sendPrescription(prescriptionTextArea.getText());
            }
        });

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
                communicationListView.getItems().add("Doctor: " + message); // Add doctor's message to the list view
				system.saveMessage(message, "Doctor", patientID);
                messageTextField.clear(); // Clear the message text field
            }
        });

        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Adding components to the layout
        grid.add(headingLabel, 0, 0, 2, 1);

        // Patient details
        grid.add(nameLabel, 0, 1);
        grid.add(nameValueLabel, 1, 1);
        grid.add(ageLabel, 0, 2);
        grid.add(ageValueLabel, 1, 2);
        grid.add(genderLabel, 0, 3);
        grid.add(genderValueLabel, 1, 3);
        grid.add(immunizationLabel, 0, 4);
        grid.add(immunizationValueLabel, 1, 4);

        // Doctor's findings and prescription
        grid.add(findingsLabel, 0, 5);
        grid.add(findingsTextArea, 1, 5);
        grid.add(prescriptionLabel, 0, 6);
        grid.add(prescriptionTextArea, 1, 6);
        grid.add(submitButton, 1, 7);

		VBox communicationBox = new VBox(5, new Label("Messaging Portal"), communicationListView, new HBox(messageTextField, sendButton));
        grid.add(communicationBox, 2, 1, 1, 7);

		// Text area for patient history
        TextArea patientHistoryTextArea = new TextArea();
        patientHistoryTextArea.setEditable(false); // Make it read-only

		// Patient history
        grid.add(new Label("Patient History:"), 0, 8);
        grid.add(patientHistoryTextArea, 0, 9, 3, 1);

		root.getChildren().addAll(grid);//, new Separator(), patientHistoryTableView);
    }

	static void save(String findingsTextArea, String prescriptionTextArea) {
        String path = patientID + "_PatientInfo.txt";
        try {
            // Create file if it doesn't exist
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Open file in append mode
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            String text = "Notes:\n" + findingsTextArea + "\nPrescription:\n" + prescriptionTextArea;

            // Append text to file
            bw.write(text);
            bw.newLine(); // Move to the next line

            // Close the resources
            bw.close();
            fw.close();
        } catch (IOException e1) { e1.printStackTrace(); }
    }

	static void sendPrescription(String prescriptionTextArea) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Prescription Sent");
        alert.setHeaderText(null);
        alert.setContentText(prescriptionTextArea);
        alert.showAndWait();
	}
}
