package example;

import example.controllers.Controller;
import example.model.entity.Usuario;
import example.model.services.UsuarioService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    public TextField email;
    public PasswordField password;

    private final UsuarioService usuarioService=new UsuarioService();

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginClick(){
        Usuario usuario = new Usuario();
        Usuario usuarioFinded;
        if (email !=null && !email.getText().isEmpty() && password!=null && !password.getText().isEmpty()) {
            usuario.setContraseña(password.getText());
            usuario.setEmail(email.getText());
            usuarioFinded=usuarioService.findUser(email.getText());
            if(usuarioFinded!=null && usuarioFinded.getEmail().equals(usuario.getEmail())
                    && usuarioFinded.getContraseña().equals(usuario.getContraseña())){
                UserSession.getInstance(usuarioFinded);
                try {
                    App.currentController.changeScene(Scenes.LOGGED,null);
                } catch (IOException e) {
                }
            }
        }
    }

    public void salirClick(){
        System.exit(0);
    }
}
