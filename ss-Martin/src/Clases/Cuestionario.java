package Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cuestionario {
    private String nombre;
    private int puntaje;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Pregunta> preguntas;

    public Cuestionario(String nombre, int puntaje, Date fechaInicio, Date fechaFin) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.preguntas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    public boolean estaDisponible() {
        Date fechaActual = new Date();
        return fechaActual.after(fechaInicio) && fechaActual.before(fechaFin);
    }
}