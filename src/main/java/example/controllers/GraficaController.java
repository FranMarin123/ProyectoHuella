package example.controllers;

import example.App;
import example.Utilities.JavaFXUtils;
import example.Utilities.PDFGenerator;
import example.model.entity.Huella;
import example.model.services.HuellaService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GraficaController extends Controller implements Initializable {
    private final PDFGenerator pdfGenerator = new PDFGenerator();
    private final HuellaService huellaService = new HuellaService();

    public BarChart<String, Number> barChart;

    @Override
    public void onOpen(Object input) throws IOException {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Impacto");
        List<Huella> huellas = huellaService.findAllHuellasForUsuario(UserSession.getInstance().getCurrentUsuario());
        BigDecimal totalTransporte = new BigDecimal(0);
        BigDecimal totalEnergia = new BigDecimal(0);
        BigDecimal totalAlimentacion = new BigDecimal(0);
        BigDecimal totalResiduos = new BigDecimal(0);
        BigDecimal totalAgua = new BigDecimal(0);
        for (Huella huella : huellas) {
            if (huella.getIdActividad().getIdCategoria().getId() == 1) {
                totalTransporte=totalTransporte.add(huella.getValor().multiply(huella.getIdActividad().getIdCategoria().getFactorEmision()));
            } else if (huella.getIdActividad().getIdCategoria().getId() == 2) {
                totalEnergia=totalEnergia.add(huella.getValor().multiply(huella.getIdActividad().getIdCategoria().getFactorEmision()));
            } else if (huella.getIdActividad().getIdCategoria().getId() == 3) {
                totalAlimentacion=totalAlimentacion.add(huella.getValor().multiply(huella.getIdActividad().getIdCategoria().getFactorEmision()));
            } else if (huella.getIdActividad().getIdCategoria().getId() == 4) {
                totalResiduos=totalResiduos.add(huella.getValor().multiply(huella.getIdActividad().getIdCategoria().getFactorEmision()));
            } else if (huella.getIdActividad().getIdCategoria().getId() == 5) {
                totalAgua=totalAgua.add(huella.getValor().multiply(huella.getIdActividad().getIdCategoria().getFactorEmision()));
            }
        }
        series.getData().add(new XYChart.Data<>("Transporte", totalTransporte.doubleValue()));
        series.getData().add(new XYChart.Data<>("Energia", totalEnergia.doubleValue()));
        series.getData().add(new XYChart.Data<>("Alimentacion", totalAlimentacion.doubleValue()));
        series.getData().add(new XYChart.Data<>("Residuos", totalResiduos.doubleValue()));
        series.getData().add(new XYChart.Data<>("Agua", totalAgua.doubleValue()));
        barChart.getData().add(series);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void generarPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            pdfGenerator.generarPDF(file.getAbsolutePath(), huellaService.findAllHuellasForUsuario(UserSession.getInstance().getCurrentUsuario()));
            JavaFXUtils.showConfirmAlert("PDF GENERADO", "El PDF ha sido generado exitosamente");
        }
    }

    public void salirClick() {
        try {
            App.currentController.changeScene(Scenes.LOGGED, null);
        } catch (IOException e) {
        }
    }
}
