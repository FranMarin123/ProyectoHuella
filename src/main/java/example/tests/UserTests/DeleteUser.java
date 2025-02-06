package example.tests.UserTests;

import example.model.entity.Usuario;
import example.model.services.UsuarioService;

public class DeleteUser {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        UsuarioService usuarioService = new UsuarioService();
        usuario.setEmail("miguel@gmail.com");
        System.out.println(usuarioService.deleteUser(usuario));
    }
}
