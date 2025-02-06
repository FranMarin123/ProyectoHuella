package example.model.services;

import example.model.dao.HuellaDAO;
import example.model.entity.Huella;

public class HuellaService {
    private final HuellaDAO huellaDAO = new HuellaDAO();

    public boolean saveHuella(Huella huella) {
        if (huella != null) {
            if (findHuellaById(huella.getId()) == null) {
                huellaDAO.saveHuella(huella);
                return true;
            }
        }
        return false;
    }

    public Huella findHuellaById(Integer id) {
        if (id == null) {
            return null;
        }
        return huellaDAO.findHuellaById(id);
    }

    public boolean deleteHuella(Huella huellaToDelete) {
        boolean eliminado = false;
        if (huellaToDelete == null || huellaToDelete.getId() == null) {
            return false;
        }
        Huella huellaFinded = findHuellaById(huellaToDelete.getId());
        if (huellaFinded == null) {
            return false;
        }
        if (huellaDAO.deleteHuella(huellaFinded)) {
            eliminado = true;
        }
        return eliminado;
    }
}
