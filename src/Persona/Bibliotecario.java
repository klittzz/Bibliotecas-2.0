package Persona;
import java.time.LocalDate;
import java.util.ArrayList;
import Biblioteca.MaterialBiblioteca.Libro;
import Biblioteca.MaterialBiblioteca.MaterialBiblioteca;
import Biblioteca.MaterialBiblioteca.Revista;
import utiles.Aa;
import utiles.ES;

public class Bibliotecario extends Persona{

    private static String contrasenaAdmin = "1234abcd";

    public Bibliotecario(String nombre, String apellidos, String fechaNacimiento, String contrasena) {
        super(nombre, apellidos, fechaNacimiento, contrasena); 
       }

    //Este método agrega libros a la lista de material

    public ArrayList<MaterialBiblioteca> anadirLibro(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca, ArrayList<Persona> listaPersonas){
        String titulo;
        Autor autor;
        String fechaPublicacion = "";
        System.out.print("Escribe el título: ");
        titulo = ES.leerCadena();
        autor = buscarAutor(listaPersonas);
        fechaPublicacion = fecha(fechaPublicacion);
        lisMaterialBiblioteca.add(new Libro(titulo, autor, fechaPublicacion));
        return lisMaterialBiblioteca;
    }

    //Este método agrega revistas a la lista de material

    public ArrayList<MaterialBiblioteca> anadirRevista(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca){
        String titulo;
        String editor;
        System.out.print("Escribe el título: ");
        titulo = ES.leerCadena();
        System.out.println("Escribe la editorial: ");
        editor = ES.leerCadena();
        lisMaterialBiblioteca.add(new Revista(titulo, editor, 1));
        return lisMaterialBiblioteca;
    }

    //Este método elimina material de la lista

    public ArrayList<MaterialBiblioteca> quitarMaterial(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca){
        int opcion = 0;
        for (MaterialBiblioteca materialBiblioteca : lisMaterialBiblioteca) Aa.bonito(materialBiblioteca.toString());
        System.out.print("Elija del 1 al " + lisMaterialBiblioteca.size() + ", según el orden dado, el autor requerido: ");
        opcion = ES.leerEnteroPositivo();
        while(opcion > lisMaterialBiblioteca.size()){
            System.out.println("Opcion inválida, escriba una válida");
            opcion = ES.leerEnteroPositivo();
        }
        lisMaterialBiblioteca.remove(opcion-1);
        return lisMaterialBiblioteca;
    }

    //Este método comprueba si existen autores en la lista de personas

    private boolean comprobarAutores(ArrayList<Persona> listaPersonas){
        ArrayList<Autor> listaAutores = new ArrayList<Autor>();
        for (Persona autores : listaPersonas) if(autores instanceof Autor) listaAutores.add((Autor) autores);
        if(listaAutores.size() == 0) return false;
        return true;
    }

    //Este método busca permite buscar autores de la lista de personas, o crear un autor nuevo

    public Autor buscarAutor(ArrayList<Persona> listaPersonas){
        if (!comprobarAutores(listaPersonas)) return anadirAutor(listaPersonas);
        int opcion;
        boolean flag1 = false;
        while (!flag1) {
            System.out.println("""
                1º Autor ya registrado.
                2º Autor nuevo
                
            Escriba la opción:    """);
            opcion = ES.leerEnteroPositivo();
            switch (opcion) {
                case 1:
                    return elegirAutor(listaPersonas);
                case 2:
                    return anadirAutor(listaPersonas);
                default:
                    System.out.print("Opción no válida: ");
                    opcion = ES.leerEnteroPositivo();
                    break;
            }
        }
        return null;
    }

    //Esta método permite elegir un autor de la lista de personas

    public Autor elegirAutor(ArrayList<Persona> listaPersonas){
        int opcion = 0;
        ArrayList<Autor> listaAutores = new ArrayList<Autor>();
        System.out.println("La lista de autores ya registrados es la siguiente: ");
        for (Persona autor : listaPersonas) if(autor instanceof Autor) listaAutores.add((Autor) autor);
        for (Autor autores : listaAutores) Aa.bonito(autores.toString());
        System.out.print("Elija del 1 al " + listaAutores.size() + ", según el orden dado, el autor requerido: ");
        opcion = ES.leerEnteroPositivo();
        while(opcion > listaAutores.size()){
            System.out.println("Opcion inválida, escriba una válida");
            opcion = ES.leerEnteroPositivo();
        }
        return listaAutores.get(opcion-1);
    }

    //Esta método permite crear un autor

    public Autor anadirAutor(ArrayList<Persona> listaPersonas){
        String nombre;
        String apellidos;
        String fechaPublicacion = "";
        System.out.print("Introduzca el nombre del autor: ");
        nombre = ES.leerCadena();
        System.out.print("Introduzca el apellido del autor: ");
        apellidos = ES.leerCadena();
        fechaPublicacion = fecha(fechaPublicacion);
        Autor autor = (Autor)new Autor(nombre, apellidos, fechaPublicacion, null, 0);
        listaPersonas.add(autor);
        return autor;
    }

    //Este método permite introducir la fecha de forma correcta

    public String fecha(String fechaPublicacion){
        int fecha;
        int fechaAux = 0;
        int diaActual = Integer.parseInt(LocalDate.now().toString().substring(5, 7));
        System.out.print("A continuación introducirá su fecha, introduzca el año en formato AAAA: ");
        fecha = ES.leerEnteroPositivo();
        while(fecha > 2024){
            System.out.print("Año introducido de forma incorrecta, introduzca un año correcto: ");
            fecha = ES.leerEnteroPositivo();
        }
        if(fecha < 10){            
            fechaPublicacion += "000" + String.valueOf(fecha) + "-";
            fechaAux = fecha;
            fecha = 10000;
        }
        if(fecha < 100){
            fechaPublicacion += "00" + String.valueOf(fecha) + "-";
            fechaAux = fecha;
            fecha = 10000;
        }
        if(fecha < 1000){
            fechaPublicacion += "0" + String.valueOf(fecha) + "-";
            fechaAux = fecha;
        } 
        if(fecha < 2025 && fecha > 999) { 
            fechaPublicacion += String.valueOf(fecha) + "-";
            fechaAux = fecha;
        }
        fecha = fechaAux;
        System.out.print("Escriba el mes , formato MM: ");
        if(fecha == 2024){
            fecha = ES.leerEnteroPositivo();
            while(fecha < 0 || fecha > diaActual){
                System.out.print("Mes introducido de forma incorrecta, introduzca un mes correcto: ");
                fecha = ES.leerEnteroPositivo();
            }
        } else{
            fecha = ES.leerEnteroPositivo();
            while(fecha < 0 || fecha > 12){
                System.out.print("Mes introducido de forma incorrecta, introduzca un mes correcto: ");
                fecha = ES.leerEnteroPositivo();
            }
        }
        if(fecha < 10) fechaPublicacion += "0" + String.valueOf(fecha) + "-";
        else fechaPublicacion += String.valueOf(fecha) + "-";
        System.out.print("Escriba el día , formato DD: ");
        fecha = ES.leerEnteroPositivo();
        diaActual = Integer.parseInt(LocalDate.now().toString().substring(8, 10));
        String mesActual = LocalDate.now().toString().substring(0, 7);
        if(fechaPublicacion.substring(0, 7).equals(mesActual)){
            while(fecha < 0 || fecha > diaActual){
                System.out.print("Mes introducido de forma incorrecta, introduzca un mes correcto: ");
                fecha = ES.leerEnteroPositivo();
            }
        } else {
            while(fecha < 0 || fecha > 31){
                System.out.print("Mes introducido de forma incorrecta, introduzca un mes correcto: ");
                fecha = ES.leerEnteroPositivo();
            }
        }
        if(fecha < 10) fechaPublicacion += "0" + String.valueOf(fecha);
        else fechaPublicacion += String.valueOf(fecha);
        return fechaPublicacion;
    }

    //Este método actualiza el numero de edición de una revista dentro de la lista de materiales

    public void actualizarRevista(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca) {
        int opcion = 0;
        ArrayList<MaterialBiblioteca> revista = new ArrayList<>();
        for (MaterialBiblioteca materialBiblioteca : lisMaterialBiblioteca) if(materialBiblioteca instanceof Revista) revista.add(materialBiblioteca);
        System.out.println("Estas son las revistas de la biblioteca: ");
        for (MaterialBiblioteca materialBiblioteca : revista) Aa.bonito(materialBiblioteca.toString());
        opcion = ES.leerEnteroPositivo();
        while(opcion > revista.size()){
            System.out.println("Opcion inválida, escriba una válida");
            opcion = ES.leerEnteroPositivo();
        }
        for (int i = 0; i < lisMaterialBiblioteca.size(); i++) {
            if(lisMaterialBiblioteca.get(i) == revista.get(opcion-1)) lisMaterialBiblioteca.get(i).actualizarRevista();
        }   
    }
    
    public static String getContrasenaAdmin() {
        return contrasenaAdmin;
    }

    public static void setContrasenaAdmin(String contrasenaAdmin) {
        Bibliotecario.contrasenaAdmin = contrasenaAdmin;
    }
}
