package example.controllers;

import example.App;
import example.model.entity.Huella;
import example.model.services.HuellaService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ImpactoController extends Controller implements Initializable {
    @FXML
    public DatePicker fechaInicio;

    @FXML
    public DatePicker fechaFin;

    @FXML
    public Label impacto;

    private final HuellaService huellaService=new HuellaService();

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fechaInicio.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (fechaFin.getValue() != null && fechaFin.getValue().isBefore(newValue)) {
                    fechaFin.setValue(newValue);
                }
                fechaFin.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isBefore(newValue));
                    }
                });
            }
        });

        fechaFin.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (fechaInicio.getValue() != null && fechaInicio.getValue().isAfter(newValue)) {
                    fechaInicio.setValue(newValue);
                }
                fechaInicio.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(newValue));
                    }
                });
            }
        });
    }

    public void verImpactoClick(){
        List<Huella> huellas=null;
        StringBuilder mostrar= new StringBuilder("IMPACTO ENTRE LAS FECHAS INDICADAS:\n");
        BigDecimal valorHuella;
        BigDecimal fEmision;
        BigDecimal valorImpacto;
        if (fechaInicio!=null && fechaInicio.getValue()!=null && fechaFin!=null && fechaFin.getValue()!=null){
            huellas=huellaService.findAllHuellasBetweenDates(UserSession.getInstance().getCurrentUsuario(), fechaInicio.getValue(), fechaFin.getValue());
            for (Huella huella : huellas) {
                valorHuella=huella.getValor();
                fEmision=huella.getIdActividad().getIdCategoria().getFactorEmision();
                valorImpacto=valorHuella.multiply(fEmision);
                mostrar.append("Huella: ").append(huella.getIdActividad().getNombre()).append(" Impacto: ").append(valorImpacto).append("\n");
            }
            impacto.setText(mostrar.toString());
        }
    }

    public void salirClick() {
        try {
            App.currentController.changeScene(Scenes.LOGGED, null);
        } catch (IOException e) {
        }
    }
}
