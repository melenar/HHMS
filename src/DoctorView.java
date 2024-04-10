package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


class DoctorView extends VBox {
    DoctorView() {
    
    	BorderPane DoctorPane = new BorderPane();  
		Text DoctorTitle = new Text("Doctor's View");
		DoctorTitle.setX(550);
		DoctorTitle.setY(20);
		
		Text PatientHistoryTitle = new Text("Patient History:");
		PatientHistoryTitle.setX(100);
		PatientHistoryTitle.setY(100);
		
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
		PatientHistoryTextFields.getChildren().add(HealthIssuesTextField);
		PatientHistoryTextFields.getChildren().add(OngoingMedicationsTextField);
		PatientHistoryTextFields.getChildren().add(ImmunizationHistoryTextField);
		
	    VBox.setMargin(HealthIssuesTextField, new Insets(250, 000, 20, 100)); 
	    HealthIssuesTextField.setPrefWidth(400);
	    VBox.setMargin(OngoingMedicationsTextField, new Insets(150, 000, 20, 100)); 
	    VBox.setMargin(ImmunizationHistoryTextField, new Insets(150, 000, 20, 100)); 
	    
		TextField TestResultsTextField = new TextField();
		TextField RecommendationsTextField = new TextField();
		TextField PrescriptionsTextField = new TextField();
		
	    VBox VisitResultsTextFields = new VBox();
	    VisitResultsTextFields.setSpacing(20);
	    VisitResultsTextFields.getChildren().add(TestResultsTextField);
	    VisitResultsTextFields.getChildren().add(PrescriptionsTextField);
	    VisitResultsTextFields.getChildren().add(RecommendationsTextField);
	    
		
	    VBox.setMargin(TestResultsTextField, new Insets(250, 100, 20, 100));
	    TestResultsTextField.setPrefWidth(400);
	    VBox.setMargin(PrescriptionsTextField, new Insets(150, 100, 20, 100)); 
	    VBox.setMargin(RecommendationsTextField, new Insets(150, 100, 20, 100));
	    
		Button SendButton = new Button("Send");
		VBox SendButtonVBox = new VBox();
		SendButtonVBox.getChildren().add(SendButton);
		VBox.setMargin(SendButton, new Insets(465, 00, 00, 00));
	    
		Button SubmitButton = new Button("Submit");
		VBox SubmitButtonVBox = new VBox();
		SubmitButtonVBox.getChildren().add(SubmitButton);
		VBox.setMargin(SubmitButton, new Insets(0, 000, 000, 600));
	  
	    DoctorPane.setLeft(PatientHistoryTextFields);
	    DoctorPane.setCenter(VisitResultsTextFields);
	    DoctorPane.setRight(SendButtonVBox);
	    DoctorPane.setBottom(SubmitButtonVBox);
		DoctorPane.getChildren().add(DoctorTitle);
		DoctorPane.getChildren().add(PatientHistoryTitle);
		DoctorPane.getChildren().add(HealthIssuesTitle);
		DoctorPane.getChildren().add(OngoingMedicationsTitle);
		DoctorPane.getChildren().add(ImmunizationRecordsTitle);
		DoctorPane.getChildren().add(TestResultsTitle);
		DoctorPane.getChildren().add(PrescriptionsTitle);
		DoctorPane.getChildren().add(RecommendationsTitle);
		
    	this.getChildren().addAll(DoctorPane);
    	
    	
    }
}
