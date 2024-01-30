package Clases;

import java.util.Date;
import java.util.List;

public class CuestionarioAlumno {
    private Estudiante alumno;
    private Cuestionario cuestionario;
    private double puntajeObtenido;
    private List<Respuesta> respuestas;
    private Date fechaRealizacion;

    public CuestionarioAlumno(Estudiante alumno, Cuestionario cuestionario, List<Respuesta> respuestas, Date fechaRealizacion) {
        this.alumno = alumno;
        this.cuestionario = cuestionario;
        this.respuestas = respuestas;
        this.fechaRealizacion = fechaRealizacion;
        calcularPuntaje();
    }

    public Estudiante getAlumno() {
        return alumno;
    }

    public void setAlumno(Estudiante alumno) {
        this.alumno = alumno;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public double getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(int puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public void calcularPuntaje() {
        int totalPreguntas = this.cuestionario.getPreguntas().size();
        double puntajePorPregunta = (double) this.cuestionario.getPuntaje() / totalPreguntas;
        double puntajeObtenido = 0;

        for (Respuesta respuesta : this.respuestas) {
            if (respuesta.esCorrecta()) {
                puntajeObtenido += puntajePorPregunta;
            }
        }

        this.puntajeObtenido = puntajeObtenido;
    }
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
}