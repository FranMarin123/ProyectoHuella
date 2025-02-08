package example.tests.recomendacion;

import example.model.dao.RecomendacionDAO;
import example.model.entity.Recomendacion;

import java.util.List;

public class FindByUserTest {
    public static void main(String[] args) {
        RecomendacionDAO dao = new RecomendacionDAO();
        List<Recomendacion> recomendaciones=dao.findRecomendacionByUsuario(5);
        for (Recomendacion recomendacion : recomendaciones) {
            System.out.println(recomendacion.getDescripcion());
        }
    }
}
