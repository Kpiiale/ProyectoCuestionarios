package Clases;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private String enunciado;
    private List<Respuesta> respuestas;

    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
        this.respuestas = new ArrayList<>();
    }

    public String getEnunciado() {
        return enunciado;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
