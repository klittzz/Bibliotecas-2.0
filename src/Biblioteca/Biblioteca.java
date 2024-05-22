package Biblioteca;
import java.time.LocalDate;
import java.util.ArrayList;

import Biblioteca.MaterialBiblioteca.Libro;
import Biblioteca.MaterialBiblioteca.MaterialBiblioteca;
import Biblioteca.MaterialBiblioteca.Revista;
import Persona.*;
import utiles.Aa;
import utiles.ES;

public class Biblioteca{

    private ArrayList<MaterialBiblioteca> lisMaterialBiblioteca;
    private ArrayList<Persona> listaPersonas;
    MaterialBiblioteca r1 = new Revista("Primera revista", "wergweg", 1);
    MaterialBiblioteca r2 = new Revista("Segunda revista", "whdfhdfhger5g", 1);
    Autor a1 = new Autor("Evaristo", "Lopez", "12/12/2", null, 1);
    Autor a2 = new Autor("Pito", "Guti", "11/12/31", null, 3);
    MaterialBiblioteca l1 = new Libro("Primer revista", a1, "2024/12/1");
    MaterialBiblioteca l2 = new Libro("Primer revista", a2, "1/1/1");
    Usuario u1 = new Usuario("1","1","1","1");
    Bibliotecario b1 = new Bibliotecario("1","1","1","1");
    

    public Biblioteca(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca, ArrayList<Persona> listaPersonas) {
        this.lisMaterialBiblioteca = lisMaterialBiblioteca;
        this.listaPersonas = listaPersonas;
        // listaPersonas.add(a1);
        // listaPersonas.add(a2);
        // listaPersonas.add(u1);
        // listaPersonas.add(b1);
        // lisMaterialBiblioteca.add(l1);
        // lisMaterialBiblioteca.add(l2);
        // lisMaterialBiblioteca.add(r1);
        // lisMaterialBiblioteca.add(r2);
    }

    // Este método permite iniciar sesion para acceder al menu del bibliotecario

    public void iniciarSesionBibliotecario(){
        ArrayList<Bibliotecario> listaBibliotecario = new ArrayList<Bibliotecario>();
        for (Persona bibliotecario : listaPersonas) if(bibliotecario instanceof Bibliotecario) listaBibliotecario.add((Bibliotecario) bibliotecario);
        if (listaBibliotecario.size() == 0) anadirPersona(1);
        Bibliotecario bibliotecario = (Bibliotecario) buscarBibliotecario();
        if(bibliotecario != null)mostrarMenuBibliotecario(bibliotecario);
    }

    // Este método busca al bibliotecario que el usuario quiere escoger

    public Bibliotecario buscarBibliotecario(){
        int eleccion;
        int i = 0;
        ArrayList<Bibliotecario> listaBibliotecarios = new ArrayList<Bibliotecario>();
        for (Persona bibliotecario2 : listaPersonas) if (bibliotecario2 instanceof Bibliotecario) listaBibliotecarios.add((Bibliotecario) bibliotecario2);
        System.out.println("Elige a que bibliotecario quieres coger, del número del 1 al " + listaBibliotecarios.size());
        for (Persona bibliotecario : listaBibliotecarios) Aa.bonito("Nombre: " + bibliotecario.getNombre());
        eleccion = ES.leerEnteroPositivo();
        while(eleccion > listaBibliotecarios.size()){
            System.out.print("Elección incorrecta, escriba un valor correcto: ");
            eleccion = ES.leerEnteroPositivo();
        }
        for (; i < listaPersonas.size(); i++) {
            if(listaBibliotecarios.get(eleccion-1) == listaPersonas.get(i)){
                i = confirmacionPersona(i);
                if(i == 1999999999){
                    return null;
                } else return (Bibliotecario) listaPersonas.get(i);
            }
        }
        return null;
    }

    // Este método muestra el menu del bibliotecario

    public void mostrarMenuBibliotecario(Bibliotecario bibliotecario){
        int opcion = 0;
        boolean flag;
        while (true) {
            System.out.print("""
                1º Añadir material
                2º Quitar material
                3º Añadir autor
                4º Actualizar edición de revista
                5º Acceder al menu de administrador
                0º Volver al menu inicial

            Escriba la opción:  """);
            opcion = ES.leerEnteroPositivo();
            System.out.println();
            switch (opcion) {
                case 0:
                    return;
                case 1:
                    System.out.println("""
                            1º Añadir libro
                            2º Añadir revista
                        
                        Escriba la opción:    """);
                        opcion = ES.leerEnteroPositivo();
                        flag = false;
                        while(!flag){
                            switch (opcion) {
                                case 1:
                                    bibliotecario.anadirLibro(lisMaterialBiblioteca, listaPersonas);
                                    flag = true;
                                    break;
                                case 2:
                                    bibliotecario.anadirRevista(lisMaterialBiblioteca);
                                    flag = true;
                                    break;
                                default:
                                    System.out.print("Opción inválida, elige una de las existentes: ");
                                    opcion = ES.leerEnteroPositivo();
                                    break;
                            }
                        }
                    break;
                case 2:
                    if (!comprobarExistencias()) { 
                        System.out.println("No hay material registrado en la base de datos.");
                    } else bibliotecario.quitarMaterial(lisMaterialBiblioteca);
                    break;
                case 3:
                    bibliotecario.anadirAutor(listaPersonas);
                    break;
                case 4:
                    if(!comprobarRevistas()) System.out.println("No existen libros dentro de la base de datos.");
                    else bibliotecario.actualizarRevista(lisMaterialBiblioteca);
                    break;
                case 5 :
                    String contrasena = "";
                    System.out.print("Introduzca la contraseña de administrador:");
                    contrasena = ES.leerCadena();
                    for (int i = 2; i > -1; i--) {
                        if(contrasena.equals(Bibliotecario.getContrasenaAdmin())){
                            mostrarMenuAdmin();
                            break;
                        } else {
                            System.out.println("La contraseña es incorrecta, te quedan " + (i+1) + " intentos: ");
                            contrasena = ES.leerCadena();
                        }
                    }
                    break;
                default:
                    System.out.print("Opción inválida, elige una de las existentes: ");
                    opcion = ES.leerEnteroPositivo();
                    break;
            }
        }
    }

    // Este método permite acceder al menu de administrador, accesible por el menu del bibliotecario

    public void mostrarMenuAdmin(){
        int opcion = 0;
        while (true) {
            System.out.print("""
                1º Añadir bibliotecario
                2º Quitar bibliotecario
                3º Cambiar contraseña de administrador
                0º Volver al menu anterior

            Escriba la opción:  """);
            opcion = ES.leerEnteroPositivo();
            System.out.println();
            switch (opcion) {
                case 0:
                    return;
                case 1:
                    anadirPersona(1);
                    break;
                case 2:
                    listaPersonas.remove(buscarBibliotecario());
                    break;
                case 3:
                    String contrasena;
                    System.out.print("Introduzca la nueva contraseña: ");
                    contrasena = ES.leerCadena();
                    Bibliotecario.setContrasenaAdmin(contrasena);
                    break;
                default:
                    System.out.print("Opción inválida, elige una de las existentes: ");
                    opcion = ES.leerEnteroPositivo();
                    break;
            }
        }
    }

    // Este método permite añadir a una persona, usuario o bibliotecario

    public void anadirPersona (int tipoPersona){
        String nombre, apellidos, fechaNacimiento = "", contrasena;
        System.out.print("Escribe el nombre: ");
        nombre = ES.leerCadena();
        System.out.print("Escribe sus apellidos: ");
        apellidos = ES.leerCadena();
        fechaNacimiento = fecha(fechaNacimiento);
        System.out.print("Introduzca la contraseña: ");
        contrasena = ES.leerCadena();
        boolean opcion = true;
        while(opcion){
            System.out.println("¿Estas seguro de que esta va a ser tu contraseña, escribe una s o una n?");
            opcion = ES.leerSINO();
            if(opcion){
                break;
            }else if(!opcion){
                System.out.print("Escribe la nueva contraseña:");
                contrasena = ES.leerCadena();
            }
        }
        if(tipoPersona == 1) this.listaPersonas.add(new Bibliotecario(nombre, apellidos, fechaNacimiento, contrasena));
        if(tipoPersona == 2) this.listaPersonas.add(new Usuario(nombre, apellidos, fechaNacimiento, contrasena));
    }

    // Este método permite iniciar sesión al usuario

    public void iniciarSesionUsuario(){
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        for (Persona usuarios : listaPersonas) if(usuarios instanceof Usuario) listaUsuarios.add((Usuario) usuarios);
        if (listaUsuarios.size() == 0) anadirPersona(2);
        Usuario usuario = (Usuario) buscarUsuario();
        if(usuario != null)mostrarMenuUsuario(usuario);
    }

    // Este método busca al usuario que quiera el usuario de la aplicación

    private Usuario buscarUsuario(){
        int eleccion;
        int i = 0;
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        for (Persona persona : listaPersonas) if (persona instanceof Usuario) listaUsuarios.add((Usuario) persona);
        System.out.println("Elige a que usuario quieres coger, del número del 1 al " + listaUsuarios.size());
        for (Persona persona2 : listaUsuarios) Aa.bonito("Nombre: " + persona2.getNombre());
        System.out.println("0 - Añadir usuario");
        eleccion = ES.leerEnteroPositivo();
        if(eleccion == 100){ 
            anadirPersona(2);
            return null;
        }
        while(eleccion > listaUsuarios.size()){
            System.out.print("Elección incorrecta, escriba un valor correcto: ");
            eleccion = ES.leerEnteroPositivo();
        }
        
        for (; i < listaPersonas.size(); i++) {
            if(listaUsuarios.get(eleccion-1) == listaPersonas.get(i)){
                i = confirmacionPersona(i);
                if(i == 1999999999){
                    return null;
                } else return (Usuario) listaPersonas.get(i);
            }
        }
        return null;
    }   

    // Este método permite confirmar la persona que quiere iniciar sesión a través de la contraseña

    private int confirmacionPersona(int i){
        int j = 2;
        System.out.println("¿Este eres tú?\n" + listaPersonas.get(i).toString());
        boolean flag = ES.leerSINO();
        if(flag){
            String contrasena = "";
            System.out.print("Introduzca su contraseña:");
            contrasena = ES.leerCadena();
            for (; j > -1; j--) {
                if(contrasena.equals(listaPersonas.get(i).getContraseña())){
                    flag = false;
                    break;
                } else {
                    System.out.println("La contraseña es incorrecta, te quedan " + (j+1) + " intentos: ");
                    contrasena = ES.leerCadena();
                }
            }
            if(j == -1){
                System.out.println("Has terminado todos tus intentos.");
                i = 1999999999;
            }
        } else i = 1999999999;
        return i;
    }

    // Este método muestra el menu del usuario

    public void mostrarMenuUsuario(Usuario usuario) {
        int opcion = 0;
        while (true) {
            System.out.print("""
                1º Coger libros
                2º Devolver libros
                3º Ver libros
                4º Ver autores
                5º Ver libros que has cogido
                0º Salir

            Escriba la opción:  """);
            opcion = ES.leerEnteroPositivo();
            System.out.println();
            switch (opcion) {
                case 0:
                    return;
                case 1:
                    if(!comprobarDisponibilidad()) System.out.println("No existen libros dentro de la base de datos.");
                    else usuario.cogerPrestadoLibro(lisMaterialBiblioteca);
                    break;
                case 2:
                    if(usuario.getLibroPrestado().size() == 0) System.out.println("No has recogido ningún libro de esta biblioteca.");
                    else usuario.dejarLibroPrestado(lisMaterialBiblioteca);
                    break;
                case 3:
                    if(!comprobarExistencias()) System.out.println("No existen libros dentro de la base de datos.");
                    else mostrarLibreria();
                    break;
                case 4:
                    mostrarAutores();
                    break;
                case 5:
                    usuario.verLibrosCogidos();
                    break;
                default:
                    System.out.print("Opción inválida, elige una de las existentes: ");
                    opcion = ES.leerEnteroPositivo();
                    break;
            }
        }
    }

    // Este método muestra la libreria de al biblioteca

    public void mostrarLibreria() {
        for (MaterialBiblioteca materialBiblioteca : lisMaterialBiblioteca) Aa.bonito(materialBiblioteca.toString());
    }

    // Este método muestra los autores registrados en la lista de usuario

    public void mostrarAutores(){
        int contador = 0;
        for (Persona autor2 : listaPersonas) if(autor2 instanceof Autor) contador++;
        if (contador != 0) {
            for (Persona autor : listaPersonas) if(autor instanceof Autor) Aa.bonito(autor.toString());
            System.out.println();
        } else System.out.println("No exiten autores registrados acutalmente.");
        System.out.println();
    }

    // Este método permite introducir de forma correcta la fecha de nacimiento y/o publicación

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
                System.out.print("Día introducido de forma incorrecta, introduzca un mes correcto: ");
                fecha = ES.leerEnteroPositivo();
            }
        }
        if(fecha < 10) fechaPublicacion += "0" + String.valueOf(fecha);
        else fechaPublicacion += String.valueOf(fecha);
        return fechaPublicacion;
    }

    // Este método permite comprobar las existencia de libros en la biblioteca

    private boolean comprobarExistencias(){
        if(lisMaterialBiblioteca.size() == 0) return false;
        return true;
    }

    // Este método permite comprobar la disponibilidad de libros en la biblioteca

    private boolean comprobarDisponibilidad(){
        int contador = 0;
        for (MaterialBiblioteca materialBiblioteca : lisMaterialBiblioteca) if(materialBiblioteca.mostrarEstado() == false) contador++;
        if(contador > 0) return true;
        return false;
    }

    // Este método permite comprobar si hay revistas en la lista

    private boolean comprobarRevistas(){
        int contador = 0;
        for (MaterialBiblioteca materialBiblioteca : lisMaterialBiblioteca) if(materialBiblioteca instanceof Revista) contador++;
        if(contador > 0) return true;
        return false;
    }

    public ArrayList<MaterialBiblioteca> getLisMaterialBiblioteca() {
        return lisMaterialBiblioteca;
    }

    public void setLisMaterialBiblioteca(ArrayList<MaterialBiblioteca> lisMaterialBiblioteca) {
        this.lisMaterialBiblioteca = lisMaterialBiblioteca;
    }

    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    
    
}
