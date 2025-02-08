package example.controllers;

import example.App;
import example.Utilities.JavaFXUtils;
import example.model.services.UsuarioService;
import example.model.singleton.UserSession;
import example.view.Scenes;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController extends Controller implements Initializable {
    public TextField nombre;
    public TextField email;
    public PasswordField password;
    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    public void onOpen(Object input) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void editClick() {
        boolean comp = false;
        if (nombre != null && !nombre.getText().isEmpty()) {
            UserSession.getInstance().getCurrentUsuario().setNombre(nombre.getText());
            comp = true;
        }
        if (email != null && !email.getText().isEmpty()) {
            UserSession.getInstance().getCurrentUsuario().setEmail(email.getText());
            comp = true;
        }
        if (password != null && !password.getText().isEmpty()) {
            UserSession.getInstance().getCurrentUsuario().setContraseña(password.getText());
            comp = true;
        }
        if (comp) {
            JavaFXUtils.showConfirmAlert("CAMBIO COMPLETADO", "Se ha modificado su usuario correctamente");
            try {
                App.currentController.changeScene(Scenes.LOGGED, null);
                usuarioService.updateUser(UserSession.getInstance().getCurrentUsuario());
            } catch (IOException e) {
            }
        }else {
            JavaFXUtils.showErrorAlert("ERROR", "No se ha modificado su usuario");
        }
    }

    public void salirClick() {
        try {
            App.currentController.changeScene(Scenes.LOGGED, null);
        } catch (IOException e) {
        }
    }
}
