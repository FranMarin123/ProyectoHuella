package example.model.services;

import example.model.dao.HabitoDAO;
import example.model.entity.Habito;
import example.model.entity.HabitoId;

public class HabitoService {
    private final HabitoDAO habitoDAO = new HabitoDAO();

    public boolean saveHabito(Habito habito) {
        if (habito != null) {
            //if (findHabitoById(habito.getId()) == null) {
                habitoDAO.saveHabito(habito);
                return true;
            //}
        }
        return false;
    }

    public Habito findHabitoById(HabitoId id) {
        if (id == null) {
            return null;
        }
        return habitoDAO.findHabitoById(id);
    }

    public boolean deleteHabito(Habito habitoToDelete) {
        boolean eliminado = false;
        if (habitoToDelete == null || habitoToDelete.getId() == null) {
            return false;
        }
        Habito habitoFinded = findHabitoById(habitoToDelete.getId());
        if (habitoFinded == null) {
            return false;
        }
        if (habitoDAO.deleteHabito(habitoFinded)) {
            eliminado = true;
        }
        return eliminado;
    }
}