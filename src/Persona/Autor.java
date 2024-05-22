package Persona;

public class Autor extends Persona{
    
    private int librosEscritos;

    public Autor(String nombre, String apellidos, String fechaNacimiento, String contrasena, int librosEscritos) {
        super(nombre, apellidos, fechaNacimiento, contrasena);
        this.librosEscritos = librosEscritos;
    }

    @Override
    public String toString() {
        return super.toString() + " | Libros escritos: " + librosEscritos;
    }

    public int getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(int librosEscritos) {
        this.librosEscritos = librosEscritos;
    }
    
}
