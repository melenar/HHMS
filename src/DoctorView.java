import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;
import java.util.*;


class DoctorView extends VBox {
	String immunizationHistory, healthIssues, ongoingMedications, testResults, prescriptions, recommendations;
	
	
    DoctorView() {

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

        // ListView for patient-doctor communication
        ListView<String> communicationListView = new ListView<>();
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

		this.getChildren().addAll(grid);//, new Separator(), patientHistoryTableView);
    
    	// BorderPane DoctorPane = new BorderPane();  
		// Text DoctorTitle = new Text("Doctor's View");
		// DoctorTitle.setX(500);
		// DoctorTitle.setY(20);
		
		// Text patientTitle = new Text("Patient:");
		// patientTitle.setX(100);
		// patientTitle.setY(100);
		
		// Text HealthIssuesTitle = new Text("Health Issues:");
		// HealthIssuesTitle.setX(100);
		// HealthIssuesTitle.setY(230);
		
		// Text OngoingMedicationsTitle = new Text("Ongoing Medications:");
		// OngoingMedicationsTitle.setX(100);
		// OngoingMedicationsTitle.setY(400);
		
		// Text ImmunizationRecordsTitle = new Text("Immunization Records:");
		// ImmunizationRecordsTitle.setX(100);
		// ImmunizationRecordsTitle.setY(560);
		
		// Text TestResultsTitle = new Text("Physical Test Results:");
		// TestResultsTitle.setX(600);
		// TestResultsTitle.setY(230);
		
		// Text PrescriptionsTitle = new Text("Prescriptions:");
		// PrescriptionsTitle.setX(600);
		// PrescriptionsTitle.setY(400);
		
		// Text RecommendationsTitle = new Text("Recommendations:");
		// RecommendationsTitle.setX(600);
		// RecommendationsTitle.setY(560);
		
		// TextField HealthIssuesTextField = new TextField();
		// TextField OngoingMedicationsTextField = new TextField();
		// TextField ImmunizationHistoryTextField = new TextField();
		
		// VBox PatientHistoryTextFields = new VBox();
		// PatientHistoryTextFields.setSpacing(20);
		// PatientHistoryTextFields.getChildren().addAll(HealthIssuesTextField, OngoingMedicationsTextField, ImmunizationHistoryTextField);
		
		
	    // VBox.setMargin(HealthIssuesTextField, new Insets(250, 000, 20, 100)); 
	    // HealthIssuesTextField.setPrefWidth(400);
	    // VBox.setMargin(OngoingMedicationsTextField, new Insets(100, 000, 20, 100)); 
	    // VBox.setMargin(ImmunizationHistoryTextField, new Insets(100, 000, 20, 100)); 
	    
	    
		// TextField TestResultsTextField = new TextField();
		// TextField RecommendationsTextField = new TextField();
		// TextField PrescriptionsTextField = new TextField();
		
		
	    // VBox VisitResultsTextFields = new VBox();
	    // VisitResultsTextFields.setSpacing(20);
	    // VisitResultsTextFields.getChildren().addAll(TestResultsTextField, RecommendationsTextField, PrescriptionsTextField);

	    
		
	    // VBox.setMargin(TestResultsTextField, new Insets(250, 100, 20, 100));
	    // TestResultsTextField.setPrefWidth(400);
	    // VBox.setMargin(PrescriptionsTextField, new Insets(100, 100, 20, 100)); 
	    // VBox.setMargin(RecommendationsTextField, new Insets(100, 100, 20, 100));
	    
	    
		// Button sendButton = new Button("Send");
		// VBox sendButtonVBox = new VBox();
		// sendButtonVBox.getChildren().add(sendButton);
		// VBox.setMargin(sendButton, new Insets(415, 00, 00, 00));
		// sendButton.setOnAction(e -> {
		// 	if (PrescriptionsTextField.getText().isEmpty()) {
		// 		Alert a = new Alert(AlertType.NONE);
		// 		a.setAlertType(AlertType.ERROR);
		// 		a.setContentText("Please fill out all fields");
		// 		a.show();
		// 		return;
		// 	}
		// 	File file = new File("src/files/PresciptionHistory.txt");
		// 	try {
		// 		FileOutputStream fos = new FileOutputStream(file);
		// 		prescriptions = PrescriptionsTextField.getText() + "\n";
		// 		fos.write(prescriptions.getBytes());
		// 		fos.close();
		// 	} catch (IOException e1) {
		// 		e1.printStackTrace();
		// 	}
			
		// 	Alert a = new Alert(AlertType.NONE);
		// 	a.setAlertType(AlertType.INFORMATION);
		// 	a.setContentText("Prescription Sent");
		// 	a.show();
		// });
		
	    
		// Button submitButton = new Button("Submit");
		// VBox submitButtonVBox = new VBox();
		// submitButtonVBox.getChildren().add(submitButton);
		// VBox.setMargin(submitButton, new Insets(0, 000, 000, 600));
		// submitButton.setOnAction(e -> {
		// 	if (HealthIssuesTextField.getText().isEmpty() || OngoingMedicationsTextField.getText().isEmpty()
		// 			|| ImmunizationHistoryTextField.getText().isEmpty() || TestResultsTextField.getText().isEmpty()
		// 			|| RecommendationsTextField.getText().isEmpty()) {
		// 		Alert a = new Alert(AlertType.NONE);
		// 		a.setAlertType(AlertType.ERROR);
		// 		a.setContentText("Please fill out all fields");
		// 		a.show();
		// 		return;
		// 	}
			
		// 	File file = new File("src/files/PatientHistory.txt");
		// 	try {
		// 		FileOutputStream fos = new FileOutputStream(file, true);
		// 		immunizationHistory = ImmunizationHistoryTextField.getText() + "\n";
		// 		healthIssues = HealthIssuesTextField.getText() + "\n";
		// 		ongoingMedications = OngoingMedicationsTextField.getText()+"\n";
		// 		testResults = TestResultsTextField.getText() + "\n";
		// 		recommendations = RecommendationsTextField.getText() + "\n";
		// 		fos.write(testResults.getBytes());
		// 		fos.write(recommendations.getBytes());
		// 		fos.write(immunizationHistory.getBytes());
		// 		fos.write(healthIssues.getBytes());
		// 		fos.write(ongoingMedications.getBytes());
		// 		fos.close();
		// 	} catch (IOException e1) {
		// 		e1.printStackTrace();
		// 	}
			
		// 	Alert a = new Alert(AlertType.NONE);
		// 	a.setAlertType(AlertType.INFORMATION);
		// 	a.setContentText("Patient Visit Submitted");
		// 	a.show();
		// });
		
	  
	    // DoctorPane.setLeft(PatientHistoryTextFields);
	    // DoctorPane.setCenter(VisitResultsTextFields);
	    // DoctorPane.setRight(sendButtonVBox);
	    // DoctorPane.setBottom(submitButtonVBox);
		// DoctorPane.getChildren().addAll(DoctorTitle, patientTitle, HealthIssuesTitle, OngoingMedicationsTitle, 
		// 								ImmunizationRecordsTitle, TestResultsTitle, PrescriptionsTitle, RecommendationsTitle);

    	// this.getChildren().addAll(DoctorPane);    	
    }
}
