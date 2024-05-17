module com.example.angvanz {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires spring.security.core;
    requires java.desktop;

    opens com.example.angvanz to javafx.fxml;
    exports com.example.angvanz;

    opens com.example.angvanz.domain;
    opens com.example.angvanz.repository;
    opens com.example.angvanz.service;
}