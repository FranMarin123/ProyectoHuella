package example.tests.UserTests;

import example.model.entity.Usuario;
import example.model.dao.UsuarioDAO;
import example.model.services.UsuarioService;

import java.time.LocalDate;

public class InsertTests {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        UsuarioService usuarioService = new UsuarioService();
        usuario.setNombre("Miguel");
        usuario.setEmail("miguel@gmail.com");
        usuario.setContraseña("1234");
        usuario.setFechaRegistro(LocalDate.now());
        usuarioService.saveUser(usuario);
    }
}
