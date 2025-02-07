package example.model.services;

import example.model.dao.ActividadDAO;
import example.model.entity.Actividad;

import java.util.List;

public class ActividadService {
    private final ActividadDAO actividadDAO = new ActividadDAO();

    public boolean saveActividad(Actividad actividad) {
        if (actividad != null && actividad.getNombre() != null && !actividad.getNombre().isEmpty()) {
            if (findActividadById(actividad.getId()) == null) {
                actividadDAO.saveActividad(actividad);
                return true;
            }
        }
        return false;
    }

    public Actividad findActividadById(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return actividadDAO.findActividadById(id);
    }

    public Actividad findActividadByNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        return actividadDAO.findActividadByNombre(nombre);
    }

    public boolean deleteActividad(Actividad actividadToDelete) {
        boolean eliminado = false;
        if (actividadToDelete == null || actividadToDelete.getId() == null) {
            return false;
        }
        Actividad actividadFinded = findActividadById(actividadToDelete.getId());
        if (actividadFinded == null) {
            return false;
        }
        if (actividadDAO.deleteActividad(actividadFinded)) {
            eliminado = true;
        }
        return eliminado;
    }

    public List<Actividad> findAllActividades() {
        return actividadDAO.findAllActividad();
    }
}
