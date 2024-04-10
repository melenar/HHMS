import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

import javax.swing.text.View;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.io.*;

class NurseView extends VBox{

    ViewUI view = new ViewUI();

    NurseView() {
        this.setPadding(new Insets(10, 10, 10, 10));
        // TilePane heading = new TilePane(Orientation.VERTICAL);
        // heading.setPadding(new Insets(20, 20, 20, 20));
        // heading.setAlignment(Pos.CENTER);
        // heading.setVgap(10.0);
        // heading.getChildren().add(new Label("Nurse View"));
        // this.getChildren().addAll(view.authenticate(this));
        System.out.println(view.authenticate(this, 1));
    }

    public static void startUI(VBox root) {
        VBox UI = new VBox();
        //Heading
        TilePane heading = new TilePane(Orientation.VERTICAL);
        heading.setPadding(new Insets(20, 20, 20, 20));
        heading.setAlignment(Pos.CENTER);
        heading.setTranslateY(-75);
        heading.setVgap(10.0);
        heading.getChildren().add(new Label("Nurse View"));

        

        Button edit = new Button("View/Edit Info");
        edit.setPrefWidth(100);
        // edit.setTranslateY(450);
        edit.setTranslateX(0);
        edit.setPadding(new Insets(10, 10, 10, 10));

        UI.getChildren().addAll(heading, edit);

        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(UI);//, accountCreate, username, password, back);
    }

}