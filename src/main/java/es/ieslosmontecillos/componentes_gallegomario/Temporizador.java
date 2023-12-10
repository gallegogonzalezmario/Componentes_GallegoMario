package es.ieslosmontecillos.componentes_gallegomario;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class Temporizador extends Label {
    private IntegerProperty tiempo = new SimpleIntegerProperty(0);
    private Timeline tm = new Timeline();

    public void iniciaTemporizador(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("temporizador.fxml"));
        loader.setRoot(this);

        loader.setController(this);
        setText("Esperando indicación");
        tm.setOnFinished(event -> {
            Temporizador.this.setText("El temporizador ha terminado.");
        });

        tiempo.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(tiempo.get()/3600 > 0)
                    Temporizador.this.setText("Quedan " + (tiempo.get()/3600) + " horas " + ((tiempo.get()%3600)/60) + " minutos " + (tiempo.get()%60) + " segundos");
                else if(tiempo.get()/60 > 0)
                    Temporizador.this.setText("Quedan " + (tiempo.get()/60) + " minutos " + (tiempo.get()%60) + " segundos");
                else
                    Temporizador.this.setText("Quedan " + (tiempo.get()%60) + " segundos");
            }
        });

        try {
            loader.load();
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    public void comienzaTemporizador(){
        tm.stop();
        if(tiempo.get() != 0) {
            KeyValue kv = new KeyValue(tiempo, 0);
            KeyFrame kf = new KeyFrame(Duration.millis(tiempo.get() * 1000), kv);

            tm.getKeyFrames().add(kf);
            tm.play();
        } else {
            setText("Debe introducir un valor mayor de 0");
        }
    }


    public void pausarTemporizador(){
        tm.pause();
    }

    public void resumirTemporizador(){
        tm.play();
    }

    public void setTiempo(int tiempo){
        this.tiempo.set(tiempo);
    }
    public int getTiempo(){ return tiempo.get(); }
}
