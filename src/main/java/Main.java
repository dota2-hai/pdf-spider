

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {



    private final Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(final Stage stage) {
        stage.setTitle("翻译");
        stage.getIcons().add(new Image("plant-01.png"));
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("打开文档");
        final TextField documentPathTextField = new TextField();
        final Button submitBtn = new Button("翻译");
        final TextField translate = new TextField();
        documentPathTextField.setPromptText("输入文档位置");
        documentPathTextField.setMinWidth(500);


        final Label label = new Label();
        label.setText("翻译进度：");

        final ProgressBar pb= new ProgressBar();
        pb.setProgress(0);
        pb.setMinWidth(500);

        final ProgressIndicator pin = new ProgressIndicator();
        pin.setProgress(0);



//        final Button openMultipleButton = new Button("Open Pictures...");

        openButton.setOnAction(
                (final ActionEvent e) -> {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
//                        openFile(file);
                        documentPathTextField.setText(file.getAbsolutePath());
                    }
                });



        submitBtn.setOnAction((final ActionEvent e) -> {
            try {
//                new Thread().start();
                TmtClientUtil.translateTxt(documentPathTextField.getText(),"utf-8");
//                TmtClientUtil.processIn=0;



            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

//        openMultipleButton.setOnAction(
//                (final ActionEvent e) -> {
//                    configureFileChooser(fileChooser);
//                    List<File> list
//                            = fileChooser.showOpenMultipleDialog(stage);
//                    if (list != null) {
//                        list.stream().forEach((file) -> {
//                            openFile(file);
//                        });
//                    }
//                });

        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openButton, 0, 1);
        GridPane.setConstraints(documentPathTextField, 0, 2);
        GridPane.setConstraints(submitBtn, 0, 3);
//
//        GridPane.setConstraints(label, 0, 4);
//        GridPane.setConstraints(pb, 0, 5);
//        GridPane.setConstraints(pin, 1, 5);
//        GridPane.setConstraints(openMultipleButton, 1, 1);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton,documentPathTextField,submitBtn/*, openMultipleButton*/);
//        inputGridPane.getChildren().addAll(label,pb,pin);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        stage.setScene(new Scene(rootGroup));
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All TXT", "*.txt")
        );
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
}
