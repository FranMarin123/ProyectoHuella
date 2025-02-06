package example.model.services;

import example.model.dao.CategoriaDAO;
import example.model.entity.Categoria;

public class CategoriaService {
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public boolean saveCategoria(Categoria categoria) {
        if (categoria != null && categoria.getNombre() != null && !categoria.getNombre().isEmpty()) {
            if (findCategoriaById(categoria.getId()) == null) {
                categoriaDAO.saveCategoria(categoria);
                return true;
            }
        }
        return false;
    }

    public Categoria findCategoriaById(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return categoriaDAO.findCategoriaById(id);
    }

    public Categoria findCategoriaByNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        return categoriaDAO.findCategoriaByNombre(nombre);
    }
}