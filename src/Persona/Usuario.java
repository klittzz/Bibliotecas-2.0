package Persona;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import Biblioteca.MaterialBiblioteca.MaterialBiblioteca;
import utiles.Aa;
import utiles.ES;

public class Usuario extends Persona{
    
    private int idUsuario;
    private static int contador = 1;
    private Queue<MaterialBiblioteca> libroPrestado = new LinkedList<MaterialBiblioteca>();

    public Usuario(String nombre, String apellidos, String fechaNacimiento, String contrasena) {
        super(nombre, apellidos, fechaNacimiento, contrasena);
        this.idUsuario = contador;
        Usuario.contador++;
    }

    public ArrayList<MaterialBiblioteca> cogerPrestadoLibro(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca){
        ArrayList<MaterialBiblioteca> librosDisponibles = new ArrayList<>();
        int opcion;
        System.out.println("Elige que libro quieres coger prestado: ");
        System.out.println();
        for (MaterialBiblioteca materialBiblioteca : lisMaterialBiblioteca) if(materialBiblioteca.mostrarEstado() == false) librosDisponibles.add(materialBiblioteca);
        for (MaterialBiblioteca materialBiblioteca : librosDisponibles) Aa.bonito(materialBiblioteca.toString());
        opcion = ES.leerEnteroPositivo();
        while(opcion > librosDisponibles.size()){
            System.out.print("Opcion inválida, escriba una opción válida:");
            opcion = ES.leerEnteroPositivo();
        }
        cambiarEstado1(lisMaterialBiblioteca, librosDisponibles.get(opcion-1));
        libroPrestado.offer(librosDisponibles.get(opcion-1));
        return lisMaterialBiblioteca;
    }

    private void cambiarEstado1 (ArrayList<MaterialBiblioteca> lisMaterialBiblioteca, MaterialBiblioteca libroEscogido){
        for (int i = 0; i < lisMaterialBiblioteca.size(); i++) if(libroEscogido == lisMaterialBiblioteca.get(i)) lisMaterialBiblioteca.get(i).prestar();
    }

    public ArrayList<MaterialBiblioteca> dejarLibroPrestado(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca){
        int cantidad;
        ArrayList<MaterialBiblioteca> librosParaDevolver = new ArrayList<MaterialBiblioteca>();
        System.out.print("¿Cuántos libros quieres devolver?");
        cantidad = ES.leerEnteroPositivo();
        while(cantidad > libroPrestado.size()){
            System.out.print("Opcion inválida, escriba una opción válida:");
            cantidad = ES.leerEnteroPositivo();
        }
        for (int i = 0; i < cantidad; i++) {
            librosParaDevolver.add(libroPrestado.remove());
        }
        System.out.println("Estos son lo libros que vas devolver.");
        for (MaterialBiblioteca materialBiblioteca : librosParaDevolver) Aa.bonito(materialBiblioteca.toString());
        System.out.println("Ordenando los libros...");
        Collections.sort(librosParaDevolver);
        System.out.println("Libros ordenados: ");
        for (MaterialBiblioteca materialBiblioteca : librosParaDevolver) Aa.bonito(materialBiblioteca.toString());
        cambiarEstado2(lisMaterialBiblioteca, librosParaDevolver);
        librosParaDevolver.clear();
        return lisMaterialBiblioteca;
    }

    private void cambiarEstado2 (ArrayList<MaterialBiblioteca> lisMaterialBiblioteca, ArrayList<MaterialBiblioteca> librosParaDevolver){
        for (int i = 0; i < librosParaDevolver.size(); i++) {
            for (int j = 0; j < lisMaterialBiblioteca.size(); j++) {
                if(librosParaDevolver.get(i) == lisMaterialBiblioteca.get(j)) {
                    lisMaterialBiblioteca.get(j).devolver();
                }
            }
        }
    }

    public void verLibrosCogidos(){
        if(libroPrestado.size() == 0) System.out.println("No ha recogido ningún libro.");
        else for (MaterialBiblioteca materialBiblioteca : libroPrestado) Aa.bonito(materialBiblioteca.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " | ID: " + idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Queue<MaterialBiblioteca> getLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(Queue<MaterialBiblioteca> libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

        
}
