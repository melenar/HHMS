import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;


import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.control.ScrollBar;

class NurseView extends VBox {
    NurseView() {
    	System.out.print("Switched to Nurse View!");
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setVgap(10.0);
        //heading.getChildren().add(new Label("Nurse View"));

        
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(heading);
        HBox mainContainer = new HBox();
        
        createScrollBar(heading, mainContainer);
        
        VBox menuHolder = new VBox();
        drawPatientInfo(heading, menuHolder);
        drawVisitMenu(heading, menuHolder);
        
        //menuHolder.setTranslateX(-250);
        
        mainContainer.setTranslateX(-Main.WIDTH/2 + 500);
        mainContainer.getChildren().add(menuHolder);
        heading.getChildren().add(mainContainer);
        
    }

//needs search bar above scrollbar
    
    
public static void createScrollBar(TilePane scene, HBox mc) {
	//Scroll though list of names
	//names will just be Buttons that load file
	
	// just set to colored rects rn
	//holds search bar and name view
	VBox container = new VBox();
	VBox nameList = new VBox();
	
	container.setMaxWidth(400);
	container.setMaxHeight(600);
	//search bar stuff
	TextField search = new TextField();
	search.setPromptText("Enter Patient Name");
	//event when text is entered for live search feed
	search.textProperty().addListener((observable, oldValue, newValue) -> {
	    System.out.println(newValue);
	});
	
	container.getChildren().add(search);
	
	ScrollPane s1 = new ScrollPane();
	
	Rectangle rect1 = new Rectangle(200,200, Color.BLACK);
	Rectangle rect2 = new Rectangle(200,200, Color.WHITE);
	Rectangle rect3 = new Rectangle(200,200, Color.BLACK);
	Rectangle rect4 = new Rectangle(200,200, Color.WHITE);
	
	nameList.getChildren().add(rect1);
	nameList.getChildren().add(rect2);
	nameList.getChildren().add(rect3);
	nameList.getChildren().add(rect4);
	
	//s1.setTranslateX((-Main.WIDTH + 150)/2);
	s1.setPrefSize(300, Main.HEIGHT);
	//s1.setMaxSize(300, Main.HEIGHT);
	
	
	
	
	
	s1.setContent(nameList);
	s1.setHbarPolicy(ScrollBarPolicy.NEVER);
	
	
    container.getChildren().add(s1);
    mc.getChildren().add(container);
    
	System.out.print("test");
	
}

public static void drawPatientInfo(TilePane scene, VBox h) {
	//fix layout to match sizes
	HBox patientInfo = new HBox();
//	Image image = new Image("NurseJoyTempImage.png");
//	ImageView imageView = new ImageView(image);
//	imageView.setFitWidth(200);
//	imageView.setPreserveRatio(true);
	Rectangle imageView = new Rectangle(200,200, Color.BLACK);
	
	
	Label pInfo = new Label();
	pInfo.setLineSpacing(10);
	String name = "Joy";
	pInfo.setText("Name: " + name + "\n Sex: \n DOB \n Age: ");
	pInfo.setPrefWidth(200);
	
	BorderPane borderPane = new BorderPane();
	borderPane.setStyle("-fx-border-color: black;");
	
	borderPane.setCenter(pInfo);
	//patientInfo.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null , null)));
	patientInfo.getChildren().add(imageView);
	patientInfo.getChildren().add(borderPane);
	
	//patientInfo.getChildren().add(imageView2);
	
	//patientInfo.setTranslateX(-Main.WIDTH/2);
	//patientInfo.setTranslateY((-Main.HEIGHT + 200)/2);
	patientInfo.setPrefHeight(200);
	h.getChildren().add(patientInfo);
	System.out.print("Drawing Patient Info?");
	
}

public static void drawVisitMenu(TilePane scene, VBox h) {
	//fix layout to match sizes
	
	//Switch to info Boxes
	//add buttons
		HBox patientInfo = new HBox();
		
		Label pVisit = new Label();
		pVisit.setLineSpacing(10);
		String vList = "v1 \nv2 \nv3 \n";
		pVisit.setText("Previous Visits: \n" + vList);
		pVisit.setPrefWidth(200);
		
		BorderPane borderPane1 = new BorderPane();
		borderPane1.setStyle("-fx-border-color: black;");
		
		borderPane1.setCenter(pVisit);
		
		VBox newVisitC = new VBox();
		
		LocalDate today = LocalDate.now();
		DatePicker datePicker = new DatePicker(today);
		
		TextField reason = new TextField();
		
		Button addV = new Button("Add Visit +");
		addV.setOnAction(event -> {
			vitalsScreenHandler(scene);
			
		});
		
		Label nVisit = new Label();
		nVisit.setLineSpacing(10);
		nVisit.setText("Reason of Visit: ");
		nVisit.setPrefWidth(200);
		
		newVisitC.getChildren().add(datePicker);
		newVisitC.getChildren().add(nVisit);
		newVisitC.getChildren().add(reason);
		newVisitC.getChildren().add(addV);
		
		BorderPane borderPane2 = new BorderPane();
		borderPane2.setStyle("-fx-border-color: black;");
		
		borderPane2.setCenter(newVisitC);
		//patientInfo.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null , null)));
		patientInfo.getChildren().add(pVisit);
		patientInfo.getChildren().add(newVisitC);
		patientInfo.getChildren().add(borderPane1);
		patientInfo.getChildren().add(borderPane2);
		//patientInfo.getChildren().add(imageView2);
		
		//patientInfo.setTranslateX(-Main.WIDTH);
		//patientInfo.setTranslateY((-Main.HEIGHT + 600)/2);
		patientInfo.setPrefHeight(200);
		h.getChildren().add(patientInfo);
		
	
}

public static void vitalsScreenHandler (TilePane scene) {
	//clears children and "switches scenes"
	//Just clears and draws the new page basically
	scene.getChildren().clear();
	System.out.print("switched");
	
	HBox container = new HBox();
	scene.getChildren().add(container);
	patientInfo(container);
	patientVitals(container);
	patientHistory(container);
}

public static void patientInfo (HBox c) {
	//image
	//info
	//visit infoßß
	//finish button
	Button nextB = new Button("Finish!");
//	nextB.setOnAction(event -> {
//		root.get
//		
//	});
	
	VBox container = new VBox();
	
//	Image image = new Image("NurseJoyTempImage.png");
//	ImageView imageView = new ImageView(image);
//	imageView.setFitWidth(200);
//	imageView.setPreserveRatio(true);
	
	Rectangle imageView = new Rectangle(200,200, Color.BLACK);
	container.getChildren().add(imageView);
	
	Label pInfo = new Label();
	pInfo.setLineSpacing(10);
	String name = "Joy";
	pInfo.setText("Name: " + name + "\n Sex: \n DOB \n Age: ");
	pInfo.setPrefWidth(200);
	
	container.getChildren().add(pInfo);
	container.getChildren().add(nextB);
	
	c.getChildren().add(container);
	
}

public static void patientVitals (HBox c) {
	VBox labels = new VBox();
	VBox textBoxes = new VBox();
	
	
	
	HBox container = new HBox();
	//Vitals
	textBox("Vitals: ", labels, textBoxes);
	
	//over 12 checkbox

	//Weight
	textBox("Weight: ", labels, textBoxes);
	//Height
	textBox("Height: ", labels, textBoxes);
	//temperature
	textBox("Temperature: ", labels, textBoxes);
	//BP
	textBox("Blood Pressure: ", labels, textBoxes);
	
	CheckBox cb = new CheckBox("Patient Over 12?");
	textBoxes.getChildren().add(cb);
	

	
	bigTextBox("Allergies: ", textBoxes);
	bigTextBox("Health Concerns: ", textBoxes);
	
	container.getChildren().addAll(labels, textBoxes);
	c.getChildren().add(container);
	
}

public static void textBox (String s, VBox lb, VBox tb) {
	Label l = new Label(s);
	l.setPrefHeight(25);
	TextField t = new TextField();
	t.setPrefWidth(100);
	
	
	
	lb.getChildren().add(l);
	tb.getChildren().add(t);
}
public static void bigTextBox(String s, VBox tb) {
	Label l = new Label(s);
	TextArea textArea = new TextArea();
	textArea.setWrapText(true);
	textArea.setPrefSize(200, 100);
	tb.getChildren().addAll(l,textArea);
	
}
public static void patientHistory (HBox c) {
//Patient History
//Prescription
//Immunization

	VBox textBoxes = new VBox();
	bigTextBox("Patient History: ", textBoxes);
	bigTextBox("Previous Prescribed Medication: ", textBoxes);
	bigTextBox("Immunization History: ", textBoxes);
	
	c.getChildren().add(textBoxes); 
}
}

//Screen 1
//Search Bar
//Scroll bar through names in alphabetic order

//pfp display
//general information
//previous visits
//new visit section

//Screen 2
//Patient information
//pfp, general, visit
//Vitals tab
//History tab

//class NurseView extends VBox {
//    ViewUI view = new ViewUI();
//    static int patientID;
//
//    NurseView() {
//        this.setPadding(new Insets(10, 10, 10, 10));
//        // TilePane heading = new TilePane(Orientation.VERTICAL);
//        // heading.setPadding(new Insets(20, 20, 20, 20));
//        // heading.setAlignment(Pos.CENTER);
//        // heading.setVgap(10.0);
//        // heading.getChildren().add(new Label("Nurse View"));
//        // this.getChildren().addAll(view.authenticate(this));
//        System.out.println(view.authenticate(this, 1));
//    }
//
//    public static void display(VBox root) {
//        // Labels for known allergies and health concerns
//        Label allergiesLabel = new Label("Known Allergies:");
//        Label healthConcernsLabel = new Label("Health Concerns:");
//
//        // Text areas for entering patient data
//        TextArea allergiesTextArea = new TextArea();
//        TextArea healthConcernsTextArea = new TextArea();
//
//        // Button for submitting patient data
//        Button submitButton = new Button("Submit");
//
//        // ListView for nurse-patient communication
//        ListView<String> communicationListView = new ListView<>();
//        communicationListView.setPrefHeight(100); // Set preferred height
//
//        // Text field for entering messages
//        TextField messageTextField = new TextField();
//        messageTextField.setPromptText("Enter your message...");
//
//        // Button for sending messages
//        Button sendButton = new Button("Send");
//        sendButton.setOnAction(e -> {
//            String message = messageTextField.getText().trim();
//            if (!message.isEmpty()) {
//                communicationListView.getItems().add("Nurse: " + message); // Add nurse's message to the list view
//                messageTextField.clear(); // Clear the message text field
//            }
//        });
//
//        // Layout setup
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20, 20, 20, 20));
//        grid.setVgap(10);
//        grid.setHgap(10);
//
//        // Adding components to the layout
//        grid.add(allergiesLabel, 0, 0);
//        grid.add(allergiesTextArea, 1, 0);
//        grid.add(healthConcernsLabel, 0, 1);
//        grid.add(healthConcernsTextArea, 1, 1);
//        grid.add(submitButton, 1, 2);
//
//        // Communication section
//        VBox communicationBox = new VBox(5, new Label("Communication"), communicationListView, new HBox(messageTextField, sendButton));
//        grid.add(communicationBox, 2, 0, 1, 3);
//
//        // TableView for displaying patient history
//        TableView<String> patientHistoryTableView = new TableView<>();
//        TableColumn<String, String> historyColumn = new TableColumn<>("Patient History");
//        patientHistoryTableView.getColumns().add(historyColumn);
//
//        root.getChildren().addAll(grid, new Separator(), patientHistoryTableView);
//    }
//
//    public static void startUI1(VBox root, int id) {
//        patientID = id;
//        //Heading
//        TilePane heading = new TilePane(Orientation.VERTICAL);
//        heading.setPadding(new Insets(20, 20, 20, 20));
//        heading.setAlignment(Pos.CENTER);
//        heading.setTranslateY(-75);
//        heading.setVgap(10.0);
//        heading.getChildren().add(new Label("Nurse View"));
//
//        // Creating labels
//        Label weightLabel = new Label("Weight (kg):");
//        Label heightLabel = new Label("Height (cm):");
//        Label temperatureLabel = new Label("Body Temperature (°C):");
//        Label bloodPressureLabel = new Label("Blood Pressure (mmHg):");
//
//        // Creating text fields for data entry
//        TextField weightField = new TextField();
//        TextField heightField = new TextField();
//        TextField temperatureField = new TextField();
//        TextField bloodPressureField = new TextField();
//
//        Button submitButton = new Button("Submit");
//        submitButton.setOnAction(e -> {
//            // Retrieve data from text fields
//            double weight = Double.parseDouble(weightField.getText());
//            double height = Double.parseDouble(heightField.getText());
//            double temperature = Double.parseDouble(temperatureField.getText());
//            double bloodPressure = Double.parseDouble(bloodPressureField.getText());
//
//            saveVitals(weight, height, temperature, bloodPressure);
//            root.getChildren().clear();
//            display(root);
//        });
//
//        // Creating radio button
//        RadioButton radioButton = new RadioButton("Confirm: The age of the patient is over 12 years");
//        radioButton.setSelected(false); // Initially not selected
//
//        // Creating toggle group
//        ToggleGroup toggleGroup = new ToggleGroup();
//        radioButton.setToggleGroup(toggleGroup);
//        submitButton.setDisable(true);
//
//        radioButton.setOnAction(e -> {
//            if (radioButton.isSelected()) {
//                submitButton.setDisable(false); // Enable submit button if radio button selected
//            } else {
//                submitButton.setDisable(true); // Disable submit button if radio button not selected
//            }
//        });
//
//        // Creating a layout
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(20, 20, 20, 20));
//        grid.setVgap(10);
//        grid.setHgap(10);
//
//        // Adding components to the layout
//        grid.add(weightLabel, 0, 0);
//        grid.add(weightField, 1, 0);
//        grid.add(heightLabel, 0, 1);
//        grid.add(heightField, 1, 1);
//        grid.add(temperatureLabel, 0, 2);
//        grid.add(temperatureField, 1, 2);
//        grid.add(bloodPressureLabel, 0, 3);
//        grid.add(bloodPressureField, 1, 3);
//        grid.add(radioButton, 0, 4);
//        grid.add(submitButton, 1, 5);
//
//        root.setPadding(new Insets(10, 10, 10, 10));
//        root.getChildren().addAll(heading, grid);//, accountCreate, username, password, back);
//    }
//
//    static void saveVitals(double weight, double height, double temperature, double bloodPressure) {
//        String path = String.valueOf(patientID) + "_PatientInfo.txt";
//        try {
//            // Create file if it doesn't exist
//            File file = new File(path);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            // Open file in append mode
//            FileWriter fw = new FileWriter(file, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            
//            String text = "Vist: " + (new Date())
//                        + "\nWeight: " + weight
//                        + "\nHeight: " + height
//                        + "\nTemperature: " + temperature
//                        + "\nBlood Pressure: " + bloodPressure;
//
//            // Append text to file
//            bw.write(text);
//            bw.newLine(); // Move to the next line
//
//            // Close the resources
//            bw.close();
//            fw.close();
//        } catch (IOException e) {
//            System.err.println("Internal Server Error");
//            System.out.println("Internal Server Error");
//        }
//    }
//    
//    public static void startUI(VBox root, int id) {
//    	System.out.print("Nurse Now");
//    }
//}
