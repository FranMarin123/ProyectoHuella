package example.tests.ActividadTests;

import example.model.entity.Actividad;
import example.model.services.ActividadService;

import java.util.List;

public class ListarTest {
    public static void main(String[] args) {
        ActividadService actividadService = new ActividadService();
        List<Actividad> actividades=actividadService.findAllActividades();
        for (Actividad actividad : actividades) {
            System.out.println(actividad.getIdCategoria().getUnidad());
        }
    }
}
