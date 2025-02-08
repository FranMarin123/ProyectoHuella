package example.Utilities;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import example.model.entity.Huella;
import example.model.entity.Recomendacion;
import example.model.services.RecomendacionService;
import example.model.singleton.UserSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {
    private final RecomendacionService recomendacionService=new RecomendacionService();
    private final List<Recomendacion> recomendaciones=recomendacionService.findAllRecomendacionByUsuario(UserSession.getInstance().getCurrentUsuario());

    public void generarPDF(String ruta, List<Huella> huellas) {
        try {
            File file = new File(ruta);
            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("REPORTE DE HUELLA DE CARBONO DE: "+UserSession.getInstance().getCurrentUsuario().getEmail()).setBold().setFontSize(16));
            document.add(new Paragraph("\n"));
            for (Huella huella : huellas) {
            document.add(new Paragraph("-Actividad: " + huella.getIdActividad().getNombre()));
            document.add(new Paragraph("    Valor: " + huella.getValor() + " " + huella.getUnidad()));
            document.add(new Paragraph("    Fecha: " + huella.getFecha()));
            }
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Recomendaciones: "));
            for (Recomendacion recomendacion : recomendaciones) {
                document.add(new Paragraph(recomendacion.getDescripcion()));
            }
            document.close();
        } catch (FileNotFoundException e) {
        }
    }
}
