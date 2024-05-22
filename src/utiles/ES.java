package utiles;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ES {

    static Scanner sc = new Scanner(System.in);
    
    public static int leerEnteroPositivo(){
        int entero = -1;
        while (entero == -1) {
            try{
                entero = sc.nextInt();
                if (entero<0) throw new InputMismatchException(); // Para que sea positivo
            } catch(InputMismatchException ime){
                System.out.println("¡ERROR: Debe introducir un número positivo!");
                entero =-1;
            } finally {
                sc.nextLine();
            }
        }
        return entero;
    }

    public static boolean leerSINO(){
        while (true) {
            String texto = sc.nextLine().toLowerCase();
            if (texto.length()==1)
                if (texto.charAt(0)=='s') return true;
                else if (texto.charAt(0)=='n') return false;
            System.out.println("El resultado solo puede ser 'S' o 'N':");
        }
    }

    public static String leerCadena(){
        return sc.nextLine();
    }
}
