import javafx.scene.control.Button;
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
        
        mainContainer.getChildren().add(menuHolder);
        heading.getChildren().add(mainContainer);
        
    }

//needs search bar above scrollbar
public void createScrollBar(TilePane scene, HBox mc) {
	//Scroll though list of names
	//names will just be Buttons that load file
	
	// just set to colored rects rn
	//holds search bar and name view
	VBox container = new VBox();
	VBox nameList = new VBox();
	
	container.setMaxWidth(200);
	container.setMaxHeight(400);
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

public void drawPatientInfo(TilePane scene, VBox h) {
	//fix layout to match sizes
	HBox patientInfo = new HBox();
	Image image = new Image("NurseJoyTempImage.png");
	ImageView imageView = new ImageView(image);
	imageView.setFitWidth(200);
	imageView.setPreserveRatio(true);
	
	
	
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
	
}

public void drawVisitMenu(TilePane scene, VBox h) {
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

public void vitalsScreenHandler (TilePane scene) {
	//clears children and "switches scenes"
	//Just clears and draws the new page basically
	scene.getChildren().clear();
	System.out.print("switched");
	
	HBox container = new HBox();
	scene.getChildren().add(container);
	patientInfo(container);
}

public void patientInfo (HBox c) {
	//image
	//info
	//visit info
	//finish button
	VBox container = new VBox();
	
	Image image = new Image("NurseJoyTempImage.png");
	ImageView imageView = new ImageView(image);
	imageView.setFitWidth(200);
	imageView.setPreserveRatio(true);
	
	container.getChildren().add(imageView);
	
	Label pInfo = new Label();
	pInfo.setLineSpacing(10);
	String name = "Joy";
	pInfo.setText("Name: " + name + "\n Sex: \n DOB \n Age: ");
	pInfo.setPrefWidth(200);
	
	container.getChildren().add(pInfo);
	
	
	c.getChildren().add(container);
	
}

public void patientVitals (HBox c) {}

public void patientHistory (HBox c) {}
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

