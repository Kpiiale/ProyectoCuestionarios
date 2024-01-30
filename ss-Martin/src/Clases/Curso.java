package Clases;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nombre;
    private String horario;
    private List<Cuestionario> cuestionarios;

    private List<Estudiante> listaEstudiantes;

    public Curso(String nombre, String horario) {
        this.nombre = nombre;
        this.horario = horario;
        this.cuestionarios = new ArrayList<>();
        this.listaEstudiantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public List<Cuestionario> getCuestionarios() {
        return cuestionarios;
    }

    public void agregarCuestionario(Cuestionario cuestionario) {
        cuestionarios.add(cuestionario);
    }

    public List<Cuestionario> getCuestionariosDisponibles() {
        List<Cuestionario> cuestionariosDisponibles = new ArrayList<>();

        for (Cuestionario cuestionario : cuestionarios) {
            if (cuestionario.estaDisponible()) {
                cuestionariosDisponibles.add(cuestionario);
            }
        }

        return cuestionariosDisponibles;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (estudiante != null) {
            listaEstudiantes.add(estudiante);
        }
    }

    public boolean estudianteInscrito(Estudiante estudiante) {
        return listaEstudiantes.contains(estudiante);
    }
}
