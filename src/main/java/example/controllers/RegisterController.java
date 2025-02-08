package example.controllers;

import example.App;
import example.Utilities.JavaFXUtils;
import example.model.entity.Usuario;
import example.model.services.UsuarioService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterController extends Controller implements Initializable {
    public TextField nombre;
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

    public void registerClick(){
        Usuario usuario = new Usuario();
        Usuario usuarioFinded;
        if (email !=null && !email.getText().isEmpty() && password!=null && !password.getText().isEmpty()
                && nombre!=null && !nombre.getText().isEmpty()) {
            usuario.setContraseña(password.getText());
            usuario.setEmail(email.getText());
            usuario.setNombre(nombre.getText());
            usuario.setFechaRegistro(LocalDate.now());
            usuarioFinded=usuarioService.findUser(email.getText());
            if(usuarioFinded==null){
                usuarioService.saveUser(usuario);
                UserSession.getInstance(usuario);
                JavaFXUtils.showConfirmAlert("USUARIO CREADO", ("El usuario ha sido creado correctamente, bienvenido "+usuario.getNombre()));
                try {
                    App.currentController.changeScene(Scenes.LOGGED,null);
                } catch (IOException e) {
                }
            }else {
                JavaFXUtils.showErrorAlert("ERROR","El usuario ya existe");
            }
        }else {
            JavaFXUtils.showErrorAlert("ERROR","Por favor completa todos los campos");
        }
    }

    public void salirClick(){
        System.exit(0);
    }
}
