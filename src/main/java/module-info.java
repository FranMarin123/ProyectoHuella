module example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires kernel;
    requires layout;

    opens example to javafx.fxml;
    exports example;

    opens example.model.entity to org.hibernate.orm.core;
    exports example.controllers;
    opens example.controllers to javafx.fxml;
}
