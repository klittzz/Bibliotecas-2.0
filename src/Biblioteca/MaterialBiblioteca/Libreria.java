package Biblioteca.MaterialBiblioteca;

public class Libreria implements MaterialBiblioteca{

    protected String titulo;
    protected String id;
    protected boolean estado;

    public Libreria(String titulo) {
        this.titulo = titulo;
        this.estado = false;
    }

    @Override
    public void actualizarRevista() {
    }

    @Override
    public int compareTo(MaterialBiblioteca o) {
        return this.titulo.compareTo(o.recogerTitulo());
    }

    @Override
    public boolean mostrarEstado() {
        return isEstado();
    }

    @Override
    public String recogerTitulo(){
        return getTitulo();
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | ID: " + id;
    }

    public void prestar(){
        setEstado(true);
    }

    public void devolver(){
        setEstado(false);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}