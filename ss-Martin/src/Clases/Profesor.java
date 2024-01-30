package Clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor extends Usuario {

    private static boolean profesorRegistrado = false;  // Variable para verificar si ya hay un profesor registrado

    public Profesor(int cedula, String usuario, String contrasena, String tipo) {
        super(cedula, usuario, contrasena, tipo);  // Indicamos que es profesor
        if (profesorRegistrado) {
            throw new IllegalArgumentException("Ya hay un profesor registrado. No se puede registrar otro.");
        }
        profesorRegistrado = true;
    }

    private String generarUsuarioUnico(String nombreUsuario) {
        // Lógica para generar un nombre de usuario único
        return nombreUsuario.toLowerCase().replaceAll("\\s+", "");
    }

    @Override
    public boolean validarContrasena(String contrasena) {
        // Lógica de validación de contraseña para profesor
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public void agregarEstudianteACurso(Estudiante estudiante, Curso curso) {
        if (curso != null && estudiante != null) {
            curso.agregarEstudiante(estudiante);
            System.out.println("Estudiante añadido al curso exitosamente.");
        } else {
            System.out.println("Error al añadir estudiante al curso. Verifica que el curso y el estudiante no sean nulos.");
        }
    }

    // Otros métodos específicos para Profesor si es necesario
}
