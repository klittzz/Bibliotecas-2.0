package Biblioteca.MaterialBiblioteca;
import Persona.*;

public class Libro extends Libreria{
    
    private Autor autor;
    private String fechaPublicacion;

    public Libro(String titulo, Autor autor, String fechaPublicacion) {
        super(titulo);
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.id = (this.autor.getNombre().length() >= 3)?(autor.getNombre().substring(0,3)).toUpperCase() : ((this.autor.getNombre().length() == 2)? "-" + autor.getNombre().toUpperCase() : "--" + autor.getNombre().toUpperCase()) + fechaPublicacion.substring(0,4) + fechaPublicacion.substring(5,7) + fechaPublicacion.substring(8,10);
    }

    public String toString() {
        return super.toString() + " | Autor" + autor + " | Fecha de publicacion: " + fechaPublicacion + " | Estado: " + ((estado)?"Prestado":"No prestado");
    }

    public Persona getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }
    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
}
