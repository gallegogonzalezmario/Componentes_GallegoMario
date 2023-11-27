module es.ieslosmontecillos.componentes_gallegomario {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.componentes_gallegomario to javafx.fxml;
    exports es.ieslosmontecillos.componentes_gallegomario;
}