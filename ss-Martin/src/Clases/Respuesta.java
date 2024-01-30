package Clases;

public class Respuesta {
    private boolean esCorrecta;
    private String texto;

    public Respuesta(boolean esCorrecta, String texto) {
        this.esCorrecta = esCorrecta;
        this.texto = texto;
    }

    public boolean esCorrecta() {
        return esCorrecta;
    }

    public String getTexto() {
        return texto;
    }


}
