
package Clases;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class ListaUsuarios {

    public List<Usuario> usuarios;
    public ListaUsuarios() {
        usuarios=new ArrayList<>();
        usuarios.add(new Usuario("Martin Sebastian","Fuentes Escobar",
                1729253367,"Martin170503","martin123","Matutino: 07:00-12:00","Estudiante"));
        usuarios.add(new Usuario("A B","F E",
                1729253368,"Lartin170503","lartin123","Todos","Profesor"));
    }
    //------------------------------VERIFICACIONES------------------------------------------------
    public boolean verificarContra(String usuario,String contrasena) throws Exception {
        for (Usuario u : usuarios) {
            if ((u.getUsuario().equals(usuario)&&u.getContrasena().equals(contrasena))){
                return true;
            }else {
                throw new Exception("ERROR EN EL INICIO DE SESION");
            }
        }
        return false;
    }
    public boolean verificarRol(String rol){
        if (rol.equals("Profesor")){
            return true;
        }else {
            return false;
        }
    }
    //-----------------------------------VALIDACIONES-----------------------------------------
    public boolean verificarID(int cedula){
        int i =0;
        int s=usuarios.size()-1;
        int c=0;
        while((i<=s)){
            c=(i+s)/2;
            Usuario aux=usuarios.get(c);
            if(cedula==aux.getCedula()){
                return false;
            } else if(cedula<aux.getCedula()){
                s=c-1;
            }else{
                i=c+1;
            }
        }
        return true;
    }

    public boolean esUsuarioUnico(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return false; // Se encontró un paquete con el mismo código.
            }
        }
        return true; // No se encontró ningún paquete con ese código, es único.
    }
    public boolean validarUsuario(String usuario) {
        boolean tieneLetras = false;
        boolean tieneNumeros = false;
        boolean tieneEspacio = false;

        // Check if the password has a minimum length of 5 characters
        if (usuario.length() < 5) {
            return false;
        }

        for (char caracter : usuario.toCharArray()) {
            if (Character.isWhitespace(caracter)) {
                tieneEspacio = true; // La contraseña no debe contener espacios
            }
            if (Character.isLetter(caracter)) {
                tieneLetras = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumeros = true;
            }

            // If the password contains both letters and numbers, it meets the requirements
            if (tieneLetras && tieneNumeros && !tieneEspacio) {
                return true;
            }
        }

        return false;
    }
    public boolean parametroVacio(String parametro){
        if(!parametro.equals("")){
            return true;
        }else{
            return false;
        }
    }
    public boolean actualizar(Usuario dato) {

        for (Usuario u : usuarios) {
            if (u.getCedula()==dato.getCedula()) {
                u.setNombre(dato.getNombre());
                u.setApellido(dato.getApellido());
                u.setRol(dato.getRol());
                u.setCedula(dato.getCedula());
                u.setUsuario(dato.getUsuario());
                u.setContrasena(dato.getContrasena());
                return true;
            }
        }
        return false;
    }
    public boolean validarContrasena(String contrasena) {
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspacio = false;

        if (contrasena.length() < 8) {
            return false; // La contraseña no tiene al menos 8 caracteres
        }

        for (char caracter : contrasena.toCharArray()) {
            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            } else if (Character.isWhitespace(caracter)) {
                tieneEspacio = true;
            }

            if (tieneMayuscula && tieneNumero && !tieneEspacio) {
                return true; // La contraseña cumple con todos los requisitos
            }
        }

        return false; // La contraseña no cumple con los requisitos
    }
    public void eliminarUsuarioPorCedula(int cedula) throws Exception {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCedula()==cedula) {
                usuarios.remove(i);
                return;
            }
        }
        throw new Exception("Usuario no encontrado con la cedula: " + cedula);
    }
    public void agregarUsuarioSiEsUnico(Usuario nuevoUsuario) throws Exception {
        if(validarUsuario(nuevoUsuario.getUsuario())){
            if (verificarID(nuevoUsuario.getCedula())&&esUsuarioUnico(nuevoUsuario.getUsuario())) {
                usuarios.add(nuevoUsuario);
            } else {
                throw new Exception("Ya existe el Usuario o cedula en el registro");
            }
        }else{
            throw new Exception("El usuario es invalido");
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}


