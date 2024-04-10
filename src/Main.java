import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Main extends Application {
    public static final int WIDTH = 600, HEIGHT = 400;
    Button doctor, nurse, patient;
    StackPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        root = new StackPane();
        root.setPadding(new Insets(40, 40, 40, 40));
        String buttonStyle = "-fx-background-color: #0000FF; " +  // Blue background
                            "-fx-text-fill: #000000; " +            // Black text color
                            "-fx-border-color: #000000; " +         // Black border color
                            "-fx-padding: 0.5cm;" +                 // Padding between text and border
                            "-fx-font-weight: bold;";              // Bold text

        TilePane box = new TilePane(Orientation.VERTICAL);
        box.setPadding(new Insets(20, 20, 20, 20));
        box.setAlignment(Pos.CENTER);
        box.setVgap(10.0);
        box.setStyle("-fx-border-color: blue; " +  // Blue border color
                    "-fx-border-width: 2px;");     // Border width
        
        patient = new Button("Patient Login");
        patient.setOnAction(new ButtonHandler());
        patient.setPrefWidth(150);
        patient.setPadding(new Insets(20, 20, 20, 20));
        patient.setStyle(buttonStyle);
        
        nurse = new Button("Nurse Login");
        nurse.setOnAction(new ButtonHandler());
        nurse.setPrefWidth(150);
        nurse.setPadding(new Insets(20, 20, 20, 20));
        nurse.setStyle(buttonStyle);

        doctor = new Button("Doctor Login");
        doctor.setOnAction(new ButtonHandler());
        doctor.setPrefWidth(150);
        doctor.setPadding(new Insets(20, 20, 20, 20));
        doctor.setStyle(buttonStyle);

        box.getChildren().addAll(new Label("Welcome to ________ Pediatric Doctor’s Office"), patient, nurse, doctor);
        root.getChildren().add(box);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Pediatric Doctor’s Office");
        stage.setScene(scene);
        stage.show();
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Object source = e.getSource();
            root.setPadding(new Insets(10, 10, 10, 10));
            try {
                // If the source is cancel or confirm button
                if (source == patient) {
                    root.getChildren().clear();
                    root.getChildren().addAll(new PatientLogin());
                } else if (source == nurse) {
                    root.getChildren().clear();
                    root.getChildren().add(new NurseView());
                } else if (source == doctor) {
                    root.getChildren().clear();
                    root.getChildren().add(new DoctorView());
                }
            } catch (Exception exception) {
                System.err.println("Internal Server Error");
                System.out.println("Internal Server Error");
            }
        }
    }
}