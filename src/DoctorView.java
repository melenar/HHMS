import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


class DoctorView extends VBox {
	String immunizationHistory, healthIssues, ongoingMedications, testResults, prescriptions, recommendations;
	
	
    DoctorView() {
    
    	BorderPane DoctorPane = new BorderPane();  
		Text DoctorTitle = new Text("Doctor's View");
		DoctorTitle.setX(550);
		DoctorTitle.setY(20);
		
		Text patientTitle = new Text("Patient:");
		patientTitle.setX(100);
		patientTitle.setY(100);
		
		Text HealthIssuesTitle = new Text("Health Issues:");
		HealthIssuesTitle.setX(100);
		HealthIssuesTitle.setY(230);
		
		Text OngoingMedicationsTitle = new Text("Ongoing Medications:");
		OngoingMedicationsTitle.setX(100);
		OngoingMedicationsTitle.setY(440);
		
		Text ImmunizationRecordsTitle = new Text("Immunization Records:");
		ImmunizationRecordsTitle.setX(100);
		ImmunizationRecordsTitle.setY(650);
		
		Text TestResultsTitle = new Text("Physical Test Results:");
		TestResultsTitle.setX(600);
		TestResultsTitle.setY(230);
		
		Text PrescriptionsTitle = new Text("Prescriptions:");
		PrescriptionsTitle.setX(600);
		PrescriptionsTitle.setY(440);
		
		Text RecommendationsTitle = new Text("Recommendations:");
		RecommendationsTitle.setX(600);
		RecommendationsTitle.setY(650);
		
		TextField HealthIssuesTextField = new TextField();
		TextField OngoingMedicationsTextField = new TextField();
		TextField ImmunizationHistoryTextField = new TextField();
		
		VBox PatientHistoryTextFields = new VBox();
		PatientHistoryTextFields.setSpacing(20);
		PatientHistoryTextFields.getChildren().addAll(HealthIssuesTextField, OngoingMedicationsTextField, ImmunizationHistoryTextField);
		
		
	    VBox.setMargin(HealthIssuesTextField, new Insets(250, 000, 20, 100)); 
	    HealthIssuesTextField.setPrefWidth(400);
	    VBox.setMargin(OngoingMedicationsTextField, new Insets(150, 000, 20, 100)); 
	    VBox.setMargin(ImmunizationHistoryTextField, new Insets(150, 000, 20, 100)); 
	    
	    
		TextField TestResultsTextField = new TextField();
		TextField RecommendationsTextField = new TextField();
		TextField PrescriptionsTextField = new TextField();
		
		
	    VBox VisitResultsTextFields = new VBox();
	    VisitResultsTextFields.setSpacing(20);
	    VisitResultsTextFields.getChildren().addAll(TestResultsTextField, RecommendationsTextField, PrescriptionsTextField);

	    
		
	    VBox.setMargin(TestResultsTextField, new Insets(250, 100, 20, 100));
	    TestResultsTextField.setPrefWidth(400);
	    VBox.setMargin(PrescriptionsTextField, new Insets(150, 100, 20, 100)); 
	    VBox.setMargin(RecommendationsTextField, new Insets(150, 100, 20, 100));
	    
	    
		Button sendButton = new Button("Send");
		VBox sendButtonVBox = new VBox();
		sendButtonVBox.getChildren().add(sendButton);
		VBox.setMargin(sendButton, new Insets(465, 00, 00, 00));
		sendButton.setOnAction(e -> {
			if (PrescriptionsTextField.getText().isEmpty()) {
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.ERROR);
				a.setContentText("Please fill out all fields");
				a.show();
				return;
			}
			File file = new File("src/files/PresciptionHistory.txt");
			try {
				FileOutputStream fos = new FileOutputStream(file);
				prescriptions = PrescriptionsTextField.getText() + "\n";
				fos.write(prescriptions.getBytes());
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText("Prescription Sent");
			a.show();
		});
		
	    
		Button submitButton = new Button("Submit");
		VBox submitButtonVBox = new VBox();
		submitButtonVBox.getChildren().add(submitButton);
		VBox.setMargin(submitButton, new Insets(0, 000, 000, 600));
		submitButton.setOnAction(e -> {
			if (HealthIssuesTextField.getText().isEmpty() || OngoingMedicationsTextField.getText().isEmpty()
					|| ImmunizationHistoryTextField.getText().isEmpty() || TestResultsTextField.getText().isEmpty()
					|| RecommendationsTextField.getText().isEmpty()) {
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.ERROR);
				a.setContentText("Please fill out all fields");
				a.show();
				return;
			}
			
			File file = new File("src/files/PatientHistory.txt");
			try {
				FileOutputStream fos = new FileOutputStream(file, true);
				immunizationHistory = ImmunizationHistoryTextField.getText() + "\n";
				healthIssues = HealthIssuesTextField.getText() + "\n";
				ongoingMedications = OngoingMedicationsTextField.getText()+"\n";
				testResults = TestResultsTextField.getText() + "\n";
				recommendations = RecommendationsTextField.getText() + "\n";
				fos.write(testResults.getBytes());
				fos.write(recommendations.getBytes());
				fos.write(immunizationHistory.getBytes());
				fos.write(healthIssues.getBytes());
				fos.write(ongoingMedications.getBytes());
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText("Patient Visit Submitted");
			a.show();
		});
		
	  
	    DoctorPane.setLeft(PatientHistoryTextFields);
	    DoctorPane.setCenter(VisitResultsTextFields);
	    DoctorPane.setRight(sendButtonVBox);
	    DoctorPane.setBottom(submitButtonVBox);
		DoctorPane.getChildren().addAll(DoctorTitle, patientTitle, HealthIssuesTitle, OngoingMedicationsTitle, 
										ImmunizationRecordsTitle, TestResultsTitle, PrescriptionsTitle, RecommendationsTitle);

    	this.getChildren().addAll(DoctorPane);
    	
    	
    }
}
