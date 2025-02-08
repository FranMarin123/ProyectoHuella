package example.controllers;

import example.App;
import example.Utilities.JavaFXUtils;
import example.model.entity.Actividad;
import example.model.entity.Habito;
import example.model.entity.HabitoId;
import example.model.services.ActividadService;
import example.model.services.HabitoService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrarHabitoController extends Controller implements Initializable {
    public ChoiceBox<String> choiceBox=new ChoiceBox<>();
    public TextField valor;
    public ChoiceBox<String> tipo=new ChoiceBox<>();
    public DatePicker fecha;
    private final ActividadService actividadService=new ActividadService();
    private final HabitoService habitoService=new HabitoService();

    @Override
    public void onOpen(Object input) throws IOException {
        List<Actividad> actividades=actividadService.findAllActividades();
        for (Actividad actividad:actividades) {
            choiceBox.getItems().add(actividad.getNombre());
        }
        tipo.getItems().addAll("Diario","Semanal","Mensual","Anual");
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarFiltroNumerico();
    }

    public void registerBoton(){
        Habito habito=new Habito();
        HabitoId habitoId=new HabitoId();
        if (choiceBox.getValue()!=null && !choiceBox.getValue().isEmpty() && valor.getText()!=null
                && fecha.getValue()!=null && !valor.getText().isEmpty() && tipo.getValue()!=null
                && !tipo.getValue().isEmpty()) {
            habito.setId(habitoId);
            habito.setIdActividad(actividadService.findActividadByNombre(choiceBox.getValue()));
            habito.setFrecuencia(Integer.parseInt(valor.getText()));
            habito.setTipo(tipo.getValue());
            habito.setIdUsuario(UserSession.getInstance().getCurrentUsuario());
            habito.setUltimaFecha(fecha.getValue());
            habitoService.saveHabito(habito);
            JavaFXUtils.showConfirmAlert("HABITO CREADO","El habito se ha registrado exitosamente");
            try {
                App.currentController.changeScene(Scenes.LOGGED, null);
            } catch (IOException e) {
            }
        }else {
            JavaFXUtils.showErrorAlert("ERROR","Error al registrar el habito");
        }
    }

    private void configurarFiltroNumerico() {
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                valor.setText(oldValue);
            }
        });
    }

    public void salirClick() {
        try {
            App.currentController.changeScene(Scenes.LOGGED, null);
        } catch (IOException e) {
        }
    }
}
