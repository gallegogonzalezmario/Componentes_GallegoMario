package es.ieslosmontecillos.componentes_gallegomario;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CampoTextoBoton extends HBox {
    @FXML
    private TextField textField;

    public CampoTextoBoton() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "campo-texto-boton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }

    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }

    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>() {
        @Override
        protected void invalidated(){
            setEventHandler(ActionEvent.ACTION, get());
        }
        @Override
        public Object getBean() {
            return CampoTextoBoton.this;
        }

        @Override
        public String getName() {
            return "onAction";
        }
    };

    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty(){
        return onAction;
    }
    public final void setOnAction(EventHandler<ActionEvent> handler){onActionProperty().set(handler);}
    public final EventHandler<ActionEvent> getOnAction(){return onActionProperty().get();}
}