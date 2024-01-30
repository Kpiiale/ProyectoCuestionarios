package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static List<Curso> listaCursos = new ArrayList<>();
    private static List<CuestionarioAlumno> listaCuestionariosAlumno = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("---------Cuestionario---------");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Crear curso");
            System.out.println("4. Crear cuestionario");
            System.out.println("5. Realizar cuestionario");
            System.out.println("6. añadir a un estudiante a un curso");
            System.out.println("7. Salir");
            op = Integer.parseInt(sc.next());

            switch (op) {
                case 1:
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("Ingrese su cédula:");
                    int cedula = Integer.parseInt(scanner.nextLine());

                    System.out.println("Ingrese su contraseña:");
                    String contrasena = scanner.nextLine();

                    System.out.println("Ingrese su usuario:");
                    String usuario = scanner.nextLine();

                    System.out.println("Escriba su rol (Estudiante, Profesor");
                    String tipo = scanner.nextLine();

                    try {
                        if (tipo.equals("Profesor")) {
                            Profesor profesor = new Profesor(cedula, usuario, contrasena, tipo);
                            listaUsuarios.add(profesor);  // Agregar profesor a la lista de usuarios
                            System.out.println("Profesor registrado correctamente.");
                        } else {
                            Estudiante estudiante = new Estudiante(cedula, usuario, contrasena, tipo);
                            Estudiante.getListaEstudiantes().add(estudiante);  // Agregar estudiante a la lista de estudiantes
                            System.out.println("Estudiante registrado correctamente.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al crear el usuario: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (!hayEstudiantesRegistrados()) {
                        System.out.println("No hay estudiantes registrados. Por favor, registre a un estudiante primero.");
                        return;
                    }

                    Scanner loginScanner = new Scanner(System.in);


                    System.out.println("Ingrese su usuario:");
                    String loginUsuario = loginScanner.nextLine();

                    System.out.println("Ingrese su contraseña:");
                    String loginContrasena = loginScanner.nextLine();

                    Estudiante estudianteIngresado = realizarInicioSesionEstudiante(loginUsuario, loginContrasena);

                    if (estudianteIngresado != null) {
                        System.out.println("Inicio de sesión exitoso como estudiante. ");
                    } else {
                        System.out.println("Error en el inicio de sesión. Usuario o contraseña incorrectos.");
                    }
                    break;

                case 3:
                    // Opción para crear curso
                    crearCurso();
                    break;

                case 4:
                    // Opción para crear cuestionario
                    crearCuestionario();
                    break;

                case 5:
                    // Opción para realizar cuestionario
                    realizarCuestionario();
                    break;

                case 6:

                    añadirEstudianteACurso();
                    break;
            }
        } while (op != 7);
    }

    private static boolean hayEstudiantesRegistrados() {
        return !Estudiante.getListaEstudiantes().isEmpty();
    }

    private static Estudiante realizarInicioSesionEstudiante(String usuario, String contrasena) {
        for (Estudiante estudiante : Estudiante.getListaEstudiantes()) {
            if (estudiante.getUsuario().equals(usuario) && estudiante.getContrasena().equals(contrasena)) {
                return estudiante;
            }
        }
        return null;
    }

    private static void crearCuestionario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del cuestionario:");
        String nombreCuestionario = scanner.nextLine();

        System.out.println("Ingrese el puntaje del cuestionario:");
        int puntajeCuestionario = Integer.parseInt(scanner.nextLine());

        // Ingreso de fecha de inicio
        System.out.println("Ingrese la fecha de inicio del cuestionario (en formato dd/MM/yyyy):");
        String fechaInicioStr = scanner.nextLine();
        Date fechaInicio = parsearFecha(fechaInicioStr);

        // Ingreso de fecha de fin
        System.out.println("Ingrese la fecha de fin del cuestionario (en formato dd/MM/yyyy):");
        String fechaFinStr = scanner.nextLine();
        Date fechaFin = parsearFecha(fechaFinStr);

        Cuestionario cuestionario = new Cuestionario(nombreCuestionario, puntajeCuestionario, fechaInicio, fechaFin);

        char agregarPregunta;
        do {
            // Crear pregunta y agregar respuestas
            Pregunta pregunta = crearPregunta();

            // Agregar pregunta al cuestionario
            cuestionario.agregarPregunta(pregunta);

            System.out.print("¿Agregar otra pregunta? (S/N): ");
            agregarPregunta = scanner.nextLine().toUpperCase().charAt(0);
        } while (agregarPregunta == 'S');

        // Agregar el cuestionario a un curso específico (puedes elegir la lógica adecuada aquí)
        Curso curso = obtenerCurso(); // Puedes implementar un método para seleccionar un curso
        curso.agregarCuestionario(cuestionario);

        System.out.println("Cuestionario creado y asignado al curso exitosamente.");
    }

    // Método para parsear una cadena de fecha a un objeto Date
    private static Date parsearFecha(String fechaStr) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            return formatoFecha.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Error al parsear la fecha. Ingrese la fecha en el formato correcto.");
            return null;
        }
    }

    private static Pregunta crearPregunta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el enunciado de la pregunta:");
        String enunciadoPregunta = scanner.nextLine();

        Pregunta pregunta = new Pregunta(enunciadoPregunta);

        char agregarRespuesta;
        do {
            // Crear respuesta y agregarla a la pregunta
            Respuesta respuesta = crearRespuesta();

            // Agregar respuesta a la pregunta
            pregunta.agregarRespuesta(respuesta);

            System.out.print("¿Agregar otra respuesta? (S/N): ");
            agregarRespuesta = scanner.nextLine().toUpperCase().charAt(0);
        } while (agregarRespuesta == 'S');

        return pregunta;
    }

    private static Respuesta crearRespuesta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el texto de la respuesta:");
        String textoRespuesta = scanner.nextLine();

        System.out.print("¿Es correcta? (S/N): ");
        boolean esCorrecta = scanner.nextLine().equalsIgnoreCase("S");

        return new Respuesta(esCorrecta, textoRespuesta);
    }

    private static void realizarCuestionario() {
        Scanner scanner = new Scanner(System.in);

        // Obtener el estudiante que realizará el cuestionario
        System.out.println("Ingrese su cédula como estudiante:");
        int cedulaEstudiante = Integer.parseInt(scanner.nextLine());

        Estudiante estudiante = buscarEstudiantePorCedula(cedulaEstudiante);

        if (estudiante != null) {
            // Obtener el curso al que pertenece el estudiante
            Curso cursoEstudiante = obtenerCursoPorEstudiante(estudiante);

            if (cursoEstudiante != null) {
                // Verificar si el estudiante está inscrito en el curso
                if (cursoEstudiante.estudianteInscrito(estudiante)) {
                    // Obtener la lista de cuestionarios disponibles para el estudiante en el curso
                    List<Cuestionario> cuestionariosDisponibles = cursoEstudiante.getCuestionariosDisponibles();

                    if (!cuestionariosDisponibles.isEmpty()) {
                        // Mostrar los cuestionarios disponibles al estudiante
                        System.out.println("Cuestionarios disponibles para el curso:");
                        for (int i = 0; i < cuestionariosDisponibles.size(); i++) {
                            System.out.println((i + 1) + ". " + cuestionariosDisponibles.get(i).getNombre());
                        }

                        // Solicitar al estudiante que elija un cuestionario
                        System.out.print("Seleccione el número del cuestionario que desea realizar: ");
                        int opcionCuestionario = Integer.parseInt(scanner.nextLine()) - 1;

                        if (opcionCuestionario >= 0 && opcionCuestionario < cuestionariosDisponibles.size()) {
                            // Obtener el cuestionario seleccionado
                            Cuestionario cuestionarioSeleccionado = cuestionariosDisponibles.get(opcionCuestionario);

                            // Realizar el cuestionario
                            double nota = realizarCuestionarioEstudiante(estudiante, cuestionarioSeleccionado);

                            // Mostrar la nota obtenida por el estudiante
                            System.out.println("Su nota en el cuestionario es: " + nota);
                        } else {
                            System.out.println("Opción no válida. Saliendo del cuestionario.");
                        }
                    } else {
                        System.out.println("No hay cuestionarios disponibles para este curso.");
                    }
                } else {
                    System.out.println("No estás inscrito en este curso. No puedes realizar cuestionarios.");
                }
            } else {
                System.out.println("Curso no encontrado.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    // Método para obtener el curso al que pertenece un estudiante
    private static Curso obtenerCursoPorEstudiante(Estudiante estudiante) {
        for (Curso curso : listaCursos) {
            if (curso.estudianteInscrito(estudiante)) {
                return curso;
            }
        }
        return null;
    }

    // Método para buscar un estudiante por su cédula
    private static Estudiante buscarEstudiantePorCedula(int cedula) {
        for (Estudiante estudiante : Estudiante.getListaEstudiantes()) {
            if (estudiante.getCedula()==cedula) {
                return estudiante;
            }
        }
        return null;
    }

    // Método para que un estudiante realice un cuestionario y retorne la nota obtenida
    private static double realizarCuestionarioEstudiante(Estudiante estudiante, Cuestionario cuestionario) {
        Scanner scanner = new Scanner(System.in);
        List<Respuesta> respuestas = new ArrayList<>();

        System.out.println("Responde las siguientes preguntas:");
        for (Pregunta pregunta : cuestionario.getPreguntas()) {
            System.out.println(pregunta.getEnunciado());

            for (int i = 0; i < pregunta.getRespuestas().size(); i++) {
                System.out.println((i + 1) + ". " + pregunta.getRespuestas().get(i).getTexto());
            }

            System.out.print("Ingrese el número de la respuesta correcta: ");
            int opcion = Integer.parseInt(scanner.nextLine()) - 1;

            if (opcion >= 0 && opcion < pregunta.getRespuestas().size()) {
                Respuesta respuestaSeleccionada = pregunta.getRespuestas().get(opcion);
                respuestas.add(respuestaSeleccionada);
            } else {
                System.out.println("Opción no válida. La pregunta se marcará como incorrecta.");
                respuestas.add(null); // Marcar la pregunta como incorrecta si la opción no es válida
            }
        }

        // Calcular el puntaje total del cuestionario
        int puntajeTotal = cuestionario.getPuntaje(); // Puntaje total del cuestionario

        // Calcular el puntaje obtenido por el estudiante
        int puntajeObtenido = 0;

        for (Respuesta respuesta : respuestas) {
            if (respuesta != null && respuesta.esCorrecta()) {
                puntajeObtenido += puntajeTotal / cuestionario.getPreguntas().size(); // Sumar la proporción del puntaje de cada pregunta
            }
        }

        // Calcular el puntaje proporcional
        double puntajeProporcional = (double) puntajeObtenido / puntajeTotal * 10; // Escalando a una escala de 10

        return puntajeProporcional;
    }


    private static int calcularPuntaje(List<Respuesta> respuestasAlumno) {
        int puntaje = 0;

        for (Respuesta respuesta : respuestasAlumno) {
            if (respuesta.esCorrecta()) {
                puntaje += 1; // Sumar 1 al puntaje por cada respuesta correcta
            }
        }

        return puntaje;
    }

    private static Curso obtenerCurso() {
        Scanner scanner = new Scanner(System.in);

        // Mostrar la lista de cursos disponibles
        System.out.println("Cursos disponibles:");

        for (int i = 0; i < listaCursos.size(); i++) {
            Curso curso = listaCursos.get(i);
            System.out.println((i + 1) + ". " + curso.getNombre());
        }

        // Solicitar al usuario que elija un curso
        System.out.print("Seleccione el número del curso: ");
        int opcionCurso = Integer.parseInt(scanner.nextLine()) - 1;

        if (opcionCurso >= 0 && opcionCurso < listaCursos.size()) {
            return listaCursos.get(opcionCurso);
        } else {
            System.out.println("Opción no válida. Curso no encontrado.");
            return null;
        }
    }

    private static void crearCurso() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del curso:");
        String nombreCurso = scanner.nextLine();

        System.out.println("Ingrese el horario del curso:");
        String horarioCurso = scanner.nextLine();

        Curso nuevoCurso = new Curso(nombreCurso, horarioCurso);
        listaCursos.add(nuevoCurso);

        System.out.println("Curso creado exitosamente.");
    }

    private static void añadirEstudianteACurso() {
        Scanner scanner = new Scanner(System.in);

        // Impresión de depuración: Lista de profesores
        System.out.println("Lista de profesores:");
        for (Usuario usuario : listaUsuarios) {
            if (usuario instanceof Profesor) {
                System.out.println("Cédula: " + usuario.getCedula());
            }
        }

        // Obtener el profesor que realizará la acción
        System.out.println("Ingrese su cédula como profesor:");
       int cedulaProfesor = Integer.parseInt(scanner.nextLine());

        // Impresión de depuración: Cédula ingresada por el usuario
        System.out.println("Cédula ingresada: " + cedulaProfesor);

        Profesor profesor = buscarProfesorPorCedula(cedulaProfesor);

        if (profesor != null) {
            // Obtener el curso al que se añadirá el estudiante
            Curso curso = obtenerCurso();

            if (curso != null) {
                // Obtener el estudiante que se añadirá al curso
                System.out.println("Ingrese la cédula del estudiante:");
                int cedulaEstudiante = Integer.parseInt(scanner.nextLine());

                // Impresión de depuración: Cédula ingresada por el usuario
                System.out.println("Cédula del estudiante ingresada: " + cedulaEstudiante);

                Estudiante estudiante = buscarEstudiantePorCedula(cedulaEstudiante);

                if (estudiante != null) {
                    // Añadir estudiante al curso
                    profesor.agregarEstudianteACurso(estudiante, curso);
                } else {
                    System.out.println("Estudiante no encontrado.");
                }
            } else {
                System.out.println("Curso no encontrado.");
            }
        } else {
            // Impresión de depuración: Cédula ingresada y lista de profesores
            System.out.println("Profesor no encontrado. Cédula ingresada: " + cedulaProfesor);
        }
    }

    // Método para buscar un profesor por su cédula
    private static Profesor buscarProfesorPorCedula(int cedula) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario instanceof Profesor && usuario.getCedula()==cedula) {
                return (Profesor) usuario;
            }
        }
        return null;
    }

    private static void visualizarCuestionarioEstudiante() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar la cédula del estudiante
        System.out.println("Ingrese la cédula del estudiante:");
        int cedulaEstudiante = Integer.parseInt(scanner.nextLine());

        // Buscar al estudiante por su cédula
        Estudiante estudiante = buscarEstudiantePorCedula(cedulaEstudiante);

        if (estudiante != null) {
            // Mostrar los cuestionarios realizados por el estudiante
            System.out.println("Cuestionarios realizados por el estudiante:");
            List<CuestionarioAlumno> cuestionariosRealizados = new ArrayList<>();
            for (CuestionarioAlumno cuestionarioAlumno : listaCuestionariosAlumno) {
                if (cuestionarioAlumno.getAlumno().getCedula()==cedulaEstudiante) {
                    cuestionariosRealizados.add(cuestionarioAlumno);
                    System.out.println("- " + cuestionarioAlumno.getCuestionario().getNombre());
                }
            }

            // Si el estudiante ha realizado cuestionarios
            if (!cuestionariosRealizados.isEmpty()) {
                System.out.print("Seleccione el número del cuestionario para ver sus respuestas: ");
                int opcionCuestionario = Integer.parseInt(scanner.nextLine()) - 1;

                if (opcionCuestionario >= 0 && opcionCuestionario < cuestionariosRealizados.size()) {
                    CuestionarioAlumno cuestionarioSeleccionado = cuestionariosRealizados.get(opcionCuestionario);

                    // Mostrar las respuestas del estudiante en el cuestionario seleccionado
                    System.out.println("Respuestas del cuestionario \"" + cuestionarioSeleccionado.getCuestionario().getNombre() + "\":");
                    for (Respuesta respuesta : cuestionarioSeleccionado.getRespuestas()) {
                        System.out.println("- " + respuesta.getTexto() + " (Correcta: " + respuesta.esCorrecta() + ")");
                    }

                    // Mostrar el puntaje obtenido y la fecha de realización del cuestionario
                    System.out.println("Puntaje obtenido: " + cuestionarioSeleccionado.getPuntajeObtenido());
                    System.out.println("Fecha de realización: " + cuestionarioSeleccionado.getFechaRealizacion());
                } else {
                    System.out.println("Opción no válida. Saliendo del proceso.");
                }
            } else {
                System.out.println("El estudiante no ha realizado cuestionarios.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}