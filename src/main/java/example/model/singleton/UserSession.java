package example.model.singleton;

import example.model.entity.Usuario;

public class UserSession {
    private static UserSession _instance;
    private Usuario currentUsuario;

    private UserSession(Usuario usuario) {
        currentUsuario = usuario;
    }

    public static UserSession getInstance(Usuario usuario) {
        if (_instance == null) {
            _instance = new UserSession(usuario);
        }
        return _instance;
    }

    public static UserSession getInstance() {
        return _instance;
    }


    public boolean closeSession() {
        _instance = null;
        return true;
    }

    public Usuario getCurrentUsuario() {
        return currentUsuario;
    }
}
