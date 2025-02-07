package example;

import example.controllers.Controller;
import example.model.entity.Actividad;
import example.model.entity.Huella;
import example.model.services.ActividadService;
import example.model.services.HuellaService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrarHabitoController extends Controller implements Initializable {
    public ChoiceBox<String> choiceBox=new ChoiceBox<>();
    public TextField valor;
    public TextField unidad;
    public DatePicker fecha;
    private final ActividadService actividadService=new ActividadService();
    private final HuellaService huellaService=new HuellaService();

    @Override
    public void onOpen(Object input) throws IOException {
        List<Actividad> actividades=actividadService.findAllActividades();
        for (Actividad actividad:actividades) {
            choiceBox.getItems().add(actividad.getNombre());
        }
        choiceBox.setOnAction(event -> unidadSelect());
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarFiltroNumerico();
    }

    public void registerBoton(){
        Huella huella=new Huella();
        if (choiceBox.getValue()!=null && !choiceBox.getValue().isEmpty() && valor.getText()!=null
                && fecha.getValue()!=null && !valor.getText().isEmpty()) {
            huella.setIdActividad(actividadService.findActividadByNombre(choiceBox.getValue()));
            huella.setUnidad(unidad.getText());
            huella.setFecha(fecha.getValue());
            huella.setValor(new BigDecimal(valor.getText()));
            huella.setIdUsuario(UserSession.getInstance().getCurrentUsuario());
            huellaService.saveHuella(huella);
            try {
                App.currentController.changeScene(Scenes.LOGGED, null);
            } catch (IOException e) {
            }
        }
    }

    private void configurarFiltroNumerico() {
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                valor.setText(oldValue);
            }
        });
    }

    public void unidadSelect(){
        if (choiceBox.getValue()!=null && !choiceBox.getValue().isEmpty()) {
            List<Actividad> actividades = actividadService.findAllActividades();
            for (Actividad actividad : actividades) {
                if (choiceBox.getValue().equals(actividad.getNombre())) {
                    unidad.setText(actividad.getIdCategoria().getUnidad());
                }
            }
        }
    }

    public void salirClick() {
        try {
            App.currentController.changeScene(Scenes.LOGGED, null);
        } catch (IOException e) {
        }
    }
}
