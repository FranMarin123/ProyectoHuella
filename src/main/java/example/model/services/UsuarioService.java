package example.model.services;

import example.model.dao.UsuarioDAO;
import example.model.entity.Usuario;
import org.hibernate.Transaction;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO=new UsuarioDAO();

    public boolean saveUser(Usuario usuario) {
        if (usuario!=null && usuario.getEmail()!=null && !usuario.getEmail().isEmpty()
                && usuario.getContraseña()!=null && !usuario.getContraseña().isEmpty()) {
            if (findUser(usuario.getEmail()) == null) {
                usuarioDAO.saveUser(usuario);
                return true;
            }
        }
        return false;
    }

    public Usuario findUser(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        if (usuarioDAO.findUser(email) != null) {
            return usuarioDAO.findUser(email);
        }
        return null;
    }

    public boolean deleteUser(Usuario usuarioToDelete) {
        boolean eliminado = false;
        if (usuarioToDelete.getEmail() == null || usuarioToDelete.getEmail().isEmpty()) {
            return false;
        }
        Usuario usuarioFinded = findUser(usuarioToDelete.getEmail());
        if (usuarioFinded == null) {
            return false;
        }
        if (usuarioDAO.deleteUser(usuarioFinded)){
            eliminado = true;
        }
        return eliminado;
    }

    public boolean updateUser(Usuario usuarioToUpdate) {
        if (usuarioToUpdate == null || usuarioToUpdate.getEmail() == null || usuarioToUpdate.getEmail().isEmpty()) {
            return false;
        }
        /*Usuario usuarioFinded = findUser(usuarioToUpdate.getEmail());
        if (usuarioFinded == null) {
            return false;
        }*/
        return usuarioDAO.updateUser(usuarioToUpdate);
    }
}
