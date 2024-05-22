package Biblioteca.MaterialBiblioteca;

public interface MaterialBiblioteca extends Comparable<MaterialBiblioteca>{

    public boolean mostrarEstado();

    public void actualizarRevista();

    public int compareTo(MaterialBiblioteca o);

    public String recogerTitulo();
    
    public void prestar();

    public void devolver();

    public String toString();
}
