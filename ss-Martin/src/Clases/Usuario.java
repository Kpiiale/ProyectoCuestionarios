package Clases;

public class Usuario {
    private int cedula;
    private String usuario;
    private String contrasena;
    private String tipo;

    public Usuario(int cedula, String usuario, String contrasena, String tipo) {
        this.cedula = cedula;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String rol) {
        this.tipo = tipo;
    }
    public boolean validarContrasena(String contrasena) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return contrasena.matches(regex);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", cedula=" + cedula +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

}