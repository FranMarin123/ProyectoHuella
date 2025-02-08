package example.controllers;

import example.App;
import example.view.Scenes;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimeraPantallaController extends Controller implements Initializable {
    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void iniciarSesionClick(){
        try {
            App.currentController.changeScene(Scenes.LOGIN,null);
        } catch (IOException e) {
        }
    }

    public void registrarClick(){
        try {
            App.currentController.changeScene(Scenes.REGISTER,null);
        } catch (IOException e) {
        }
    }

    public void salirClick(){
        System.exit(0);
    }
}
