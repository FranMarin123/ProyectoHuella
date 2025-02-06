package example;

import example.controllers.Controller;
import example.view.Scenes;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends Controller implements Initializable {
    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void registerClick(){
        try {
            App.currentController.changeScene(Scenes.LOGGED,null);
        } catch (IOException e) {
        }
    }

    public void salirClick(){
        System.exit(0);
    }
}
