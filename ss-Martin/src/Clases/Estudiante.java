package Clases;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Usuario {
    private static List<Estudiante> listaEstudiantes = new ArrayList<>();
    private int intentosCuestionario;
    private boolean haRespondidoCuestionario;

    public Estudiante(int cedula, String usuario, String contrasena, String tipo) {
        super(cedula, usuario, contrasena, tipo);  // Indicamos que no es profesor
        this.intentosCuestionario = 0;
        this.haRespondidoCuestionario = false;
        listaEstudiantes.add(this);
    }

    public int getIntentosCuestionario() {
        return intentosCuestionario;
    }

    public void setIntentosCuestionario(int intentosCuestionario) {
        this.intentosCuestionario = intentosCuestionario;
    }

    public void incrementarIntentosCuestionario() {
        intentosCuestionario++;
    }

    public void marcarCuestionarioRespondido() {
        haRespondidoCuestionario = true;
    }

    public boolean haRespondidoCuestionario() {
        return haRespondidoCuestionario;
    }

    public static List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public static Estudiante buscarEstudiante(int cedula, String contrasena) {
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getCedula()==cedula && estudiante.getContrasena().equals(contrasena)) {
                return estudiante;
            }
        }
        return null;
    }
}

