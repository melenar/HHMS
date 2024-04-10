import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;
import java.util.*;

class NurseView extends VBox {
    ViewUI view = new ViewUI();
    static String patientID;
    static Admin system = new Admin();

    NurseView() {
        this.setPadding(new Insets(10, 10, 10, 10));
        System.out.println(view.authenticate(this, 1));
    }

    public static void display(VBox root) {
        // Labels for known allergies and health concerns
        Label allergiesLabel = new Label("Known Allergies:");
        Label healthConcernsLabel = new Label("Health Concerns:");

        // Text areas for entering patient data
        TextArea allergiesTextArea = new TextArea();
        TextArea healthConcernsTextArea = new TextArea();

        // Button for submitting patient data
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            if (!allergiesTextArea.getText().isEmpty() && !healthConcernsTextArea.getText().isEmpty()) {
                save(allergiesTextArea.getText(), healthConcernsTextArea.getText());
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
                communicationListView.getItems().add("Nurse: " + message); // Add nurse's message to the list view
                system.saveMessage(message, "Nurse", patientID);
                messageTextField.clear(); // Clear the message text field
            }
        });

        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Adding components to the layout
        grid.add(allergiesLabel, 0, 0);
        grid.add(allergiesTextArea, 1, 0);
        grid.add(healthConcernsLabel, 0, 1);
        grid.add(healthConcernsTextArea, 1, 1);
        grid.add(submitButton, 1, 2);

        // Communication section
        VBox communicationBox = new VBox(5, new Label("Communication"), communicationListView, new HBox(messageTextField, sendButton));
        grid.add(communicationBox, 2, 0, 1, 3);

        // TableView for displaying patient history
        TableView<String> patientHistoryTableView = new TableView<>();
        TableColumn<String, String> historyColumn = new TableColumn<>("Patient History");
        patientHistoryTableView.getColumns().add(historyColumn);

        root.getChildren().addAll(grid, new Separator(), patientHistoryTableView);
    }

    public static void startUI(VBox root, String id) {
        patientID = id;
        //Heading
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-75);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("Nurse View"));

        // Creating labels
        Label weightLabel = new Label("Weight (kg):");
        Label heightLabel = new Label("Height (cm):");
        Label temperatureLabel = new Label("Body Temperature (°C):");
        Label bloodPressureLabel = new Label("Blood Pressure (mmHg):");

        // Creating text fields for data entry
        TextField weightField = new TextField();
        TextField heightField = new TextField();
        TextField temperatureField = new TextField();
        TextField bloodPressureField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Retrieve data from text fields
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            double temperature = Double.parseDouble(temperatureField.getText());
            double bloodPressure = Double.parseDouble(bloodPressureField.getText());

            saveVitals(weight, height, temperature, bloodPressure);
            root.getChildren().clear();
            display(root);
        });

        // Creating radio button
        RadioButton radioButton = new RadioButton("Confirm: The age of the patient is over 12 years");
        radioButton.setSelected(false); // Initially not selected

        // Creating toggle group
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton.setToggleGroup(toggleGroup);
        submitButton.setDisable(true);

        radioButton.setOnAction(e -> {
            if (radioButton.isSelected()) {
                submitButton.setDisable(false); // Enable submit button if radio button selected
            } else {
                submitButton.setDisable(true); // Disable submit button if radio button not selected
            }
        });

        // Creating a layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Adding components to the layout
        grid.add(weightLabel, 0, 0);
        grid.add(weightField, 1, 0);
        grid.add(heightLabel, 0, 1);
        grid.add(heightField, 1, 1);
        grid.add(temperatureLabel, 0, 2);
        grid.add(temperatureField, 1, 2);
        grid.add(bloodPressureLabel, 0, 3);
        grid.add(bloodPressureField, 1, 3);
        grid.add(radioButton, 0, 4);
        grid.add(submitButton, 1, 5);

        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(heading, grid);//, accountCreate, username, password, back);
    }

    static void saveVitals(double weight, double height, double temperature, double bloodPressure) {
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
            
            String text = "Vist: " + (new Date())
                        + "\nWeight: " + weight
                        + "\nHeight: " + height
                        + "\nTemperature: " + temperature
                        + "\nBlood Pressure: " + bloodPressure;

            // Append text to file
            bw.write(text);
            bw.newLine(); // Move to the next line

            // Close the resources
            bw.close();
            fw.close();
        } catch (IOException e1) { e1.printStackTrace(); }
    }

    static void save(String allergies, String healthConcerns) {
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
            
            String text = "\nAlergies:\n" + allergies + "\nHealth Concerns:\n" + healthConcerns;

            // Append text to file
            bw.write(text);
            bw.newLine(); // Move to the next line

            // Close the resources
            bw.close();
            fw.close();
        } catch (IOException e1) { e1.printStackTrace(); }
    }
}