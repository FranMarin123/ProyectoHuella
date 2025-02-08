package example.view;

public enum Scenes {
    BASE("base.fxml"),
    PRINCIPAL("principal.fxml"),
    LOGIN("loginScreen.fxml"),
    REGISTER("registerScreen.fxml"),
    LOGGED("afterLoginScreen.fxml"),
    EDIT("editProfileScreen.fxml"),
    REGISTERHUELLA("registrarHuella.fxml"),
    REGISTERHABITO("registrarHabito.fxml"),
    IMPACTO("verImpacto.fxml"),
    RECOMENDACION("recomendacion.fxml"),
    GRAFICA("grafica.fxml"),
    ;

    private String path;

    Scenes(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }
}
