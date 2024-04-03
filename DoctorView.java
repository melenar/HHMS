import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

class DoctorView extends VBox {
    DoctorView() {
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("Doctor View"));

        
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(heading);
    }
}