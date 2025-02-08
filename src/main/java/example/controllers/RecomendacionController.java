package example.controllers;

import example.App;
import example.model.entity.Recomendacion;
import example.model.services.RecomendacionService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecomendacionController extends Controller implements Initializable {
    public Label recomendacionLabel;
    private final RecomendacionService recomendacionService = new RecomendacionService();

    @Override
    public void onOpen(Object input) throws IOException {
        List<Recomendacion> recomendaciones=recomendacionService.findAllRecomendacionByUsuario(UserSession.getInstance().getCurrentUsuario());
        StringBuilder mostrar=new StringBuilder();
        for (Recomendacion recomendacion : recomendaciones) {
            mostrar.append(recomendacion.getDescripcion()).append("\n");
        }
        recomendacionLabel.setText(mostrar.toString());
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void salirClick() {
        try {
            App.currentController.changeScene(Scenes.LOGGED, null);
        } catch (IOException e) {
        }
    }
}
