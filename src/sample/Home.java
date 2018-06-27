package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by VISHAL-PC on 4/17/2017.
 */
public class Home implements Initializable
{

    @FXML
    public Button bfcfs,bsjf,bljf,bana;

    public void btfcfs() throws IOException
    {
        Stage stage;
        Parent root;
        stage=(Stage) bfcfs.getScene().getWindow();
        root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("First Come First Serve");
        stage.setScene(scene);
        stage.show();
    }

    public void btsjf() throws IOException
    {
        Stage stage;
        Parent root;
        stage=(Stage) bsjf.getScene().getWindow();
        root= FXMLLoader.load(getClass().getResource("sample3.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Shortest Job First");
        stage.setScene(scene);
        stage.show();
    }

    public void btljf() throws IOException
    {
        Stage stage;
        Parent root;
        stage=(Stage) bljf.getScene().getWindow();
        root= FXMLLoader.load(getClass().getResource("sample4.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Largest Job First");
        stage.setScene(scene);
        stage.show();
    }

    public void btana() throws IOException
    {
        Stage stage;
        Parent root;
        stage=(Stage) bana.getScene().getWindow();
        root= FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Analysis");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
