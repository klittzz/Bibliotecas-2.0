package utiles;

public class Aa {

    public static void bonito(String texto){
        String lineas = "------";
        for (int i = 0; i < texto.length(); i++) {
            lineas += "-";
        }
        System.out.println(Ansi.MAGENTA + lineas + Ansi.WHITE);        
        System.out.println(Ansi.MAGENTA + "-- " + Ansi.WHITE + texto + Ansi.MAGENTA + " --" + Ansi.WHITE);
        System.out.println(Ansi.MAGENTA + lineas + Ansi.WHITE);

    }
}
