package example.model.services;

import example.model.dao.RecomendacionDAO;
import example.model.entity.Recomendacion;
import example.model.entity.Usuario;

import java.util.List;

public class RecomendacionService {
    private final RecomendacionDAO recomendacionDAO = new RecomendacionDAO();

    public boolean saveRecomendacion(Recomendacion recomendacion) {
        if (recomendacion != null) {
            if (findRecomendacionById(recomendacion.getId()) == null) {
                recomendacionDAO.saveRecomendacion(recomendacion);
                return true;
            }
        }
        return false;
    }

    public Recomendacion findRecomendacionById(Integer id) {
        if (id == null) {
            return null;
        }
        return recomendacionDAO.findRecomendacionById(id);
    }

    public boolean deleteRecomendacion(Recomendacion recomendacionToDelete) {
        boolean eliminado = false;
        if (recomendacionToDelete == null || recomendacionToDelete.getId() == null) {
            return false;
        }
        Recomendacion recomendacionFinded = findRecomendacionById(recomendacionToDelete.getId());
        if (recomendacionFinded == null) {
            return false;
        }
        if (recomendacionDAO.deleteRecomendacion(recomendacionFinded)) {
            eliminado = true;
        }
        return eliminado;
    }

    public List<Recomendacion> findAllRecomendacionByUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId()<1) {
            return null;
        }
        return recomendacionDAO.findRecomendacionByUsuario(usuario.getId());
    }
}