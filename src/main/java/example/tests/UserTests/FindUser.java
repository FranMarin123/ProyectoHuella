package example.tests.UserTests;

import example.model.dao.UsuarioDAO;
import example.model.entity.Usuario;
import example.model.services.UsuarioService;

public class FindUser {
    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();
        Usuario usuario = service.findUser("miguel@gmail.com");
        System.out.println(usuario.getId()+" "+usuario.getNombre()+" "+usuario.getContraseña()+" "+usuario.getFechaRegistro());
    }
}
