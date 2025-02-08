package example.controllers;

import example.App;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginController extends Controller implements Initializable {
    @Override
    public void onOpen(Object input) throws IOException {
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void editPerfClick(){
        try {
            App.currentController.changeScene(Scenes.EDIT,null);
        } catch (IOException e) {
        }
    }

    public void registraHuellasClick(){
        try {
            App.currentController.changeScene(Scenes.REGISTERHUELLA,null);
        } catch (IOException e) {
        }
    }

    public void registraHabitosClick(){
        try {
            App.currentController.changeScene(Scenes.REGISTERHABITO,null);
        } catch (IOException e) {
        }
    }

    public void verImpactoClick(){
        try {
            App.currentController.changeScene(Scenes.IMPACTO,null);
        } catch (IOException e) {
        }
    }

    public void recomendacionesClick(){
        try {
            App.currentController.changeScene(Scenes.RECOMENDACION,null);
        } catch (IOException e) {
        }
    }

    public void seguimientoClick(){
        try {
            App.currentController.changeScene(Scenes.GRAFICA,null);
        } catch (IOException e) {
        }
    }

    public void salirClick(){
        try {
            App.currentController.changeScene(Scenes.PRINCIPAL,null);
            UserSession.getInstance().closeSession();
        } catch (IOException e) {
        }
    }
}
