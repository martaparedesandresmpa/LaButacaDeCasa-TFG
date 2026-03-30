package modelo;

public class contenido {

    public String titulo;
    public String tipo;
    public String genero;
    public int anio;
    public String sinopsis;
    public String trailer;
    public String imagen;

    public contenido(String titulo, String tipo, String genero, int anio, String sinopsis, String trailer, String imagen) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.genero = genero;
        this.anio = anio;
        this.sinopsis = sinopsis;
        this.trailer = trailer;
        this.imagen = imagen;
    }
}