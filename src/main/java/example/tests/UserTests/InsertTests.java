package example.tests.UserTests;

import example.model.entity.Usuario;
import example.model.dao.UsuarioDAO;

import java.time.LocalDate;

public class InsertTests {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@gmail.com");
        usuario.setPassword("1234");
        usuario.setFechaRegistro(LocalDate.now().toString());
        UsuarioDAO.saveUser(usuario);
    }
}
