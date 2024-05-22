import java.time.LocalDate;
import java.util.ArrayList;

import Biblioteca.Biblioteca;
import Biblioteca.MaterialBiblioteca.MaterialBiblioteca;
import utiles.*;
import Persona.*;

public class App {
    public static void main(String[] args) throws Exception {
        int diaActual = Integer.parseInt(LocalDate.now().toString().substring(5, 7));
        System.out.println(diaActual);
        Biblioteca biblioteca = new Biblioteca(new ArrayList<MaterialBiblioteca>(), new ArrayList<Persona>());
        mostrarMenu(biblioteca);
    }

    private static void mostrarMenu(Biblioteca biblioteca){
        int opcion = 0;
        while (true) {
            System.out.print("""
                1º Perfil de bibliotecario
                2º Perfil de usuario
                0º Salir

            Escriba la opción:  """);
            opcion = ES.leerEnteroPositivo();
            System.out.println();
            switch (opcion) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    biblioteca.iniciarSesionBibliotecario();
                    break;
                case 2:
                    biblioteca.iniciarSesionUsuario();
                    break;
                default:
                    System.out.print("Opción inválida, elige una de las existentes: ");
                    opcion = ES.leerEnteroPositivo();
                    break;
            }
        }
    }
}
