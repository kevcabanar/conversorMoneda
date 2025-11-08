import com.google.gson.Gson;

import java.lang.classfile.instruction.SwitchCase;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Conversor {

    public static Respuesta eleccionUserMenu() {
        Scanner entrada = new Scanner(System.in);
        String mensajeInicial = """
                ******************************************************
                Sa bienvenido/a al Conversor de Moneda =]
                1) Dolar =>> Peso argentino
                2) Peso argentino =>> Dolar
                3) Dolar =>> Real brasileño
                4) Real brasileño =>> Dolar
                5) Dolar =>> Peso Colombiano
                6) Peso colombiano =>> Dolar
                7) salir
                Elija una opcion valida:
                ******************************************************
                """;
        System.out.println(mensajeInicial);
        String opcionElegida = entrada.nextLine();
        Respuesta respuesta;
        if (opcionElegida.equals("7")) {
             respuesta = new Respuesta(opcionElegida," "," ",0.0);
        }else{
            String[] monedasElegidas= obtenerMonedasElegidas(opcionElegida);

            System.out.println("¿Cuando es el monto que deseas cambiar?");
            Double monto = Double.parseDouble(entrada.nextLine());
             respuesta = new Respuesta(opcionElegida,monedasElegidas[0],monedasElegidas[1],monto);
        }

        return respuesta;
    }

    private static String[] obtenerMonedasElegidas(String opcionElegida) {
        String monedaIngreso= "";
        String monedaSalida = "";
        switch(opcionElegida) {
            case "1":
                monedaIngreso= "USD";
                monedaSalida="ARS";
                break;
            case "2":
                monedaIngreso= "ARS";
                monedaSalida="USD";
                break;
            case "3":
                monedaIngreso= "USD";
                monedaSalida="BRL";
                break;
            case "4":
                monedaIngreso= "BRL";
                monedaSalida="USD";
                break;
            case "5":
                monedaIngreso= "USD";
                monedaSalida="COP";
                break;
            case "6":
                monedaIngreso= "COP";
                monedaSalida="USD";
                break;
        }
        return new String[]{monedaIngreso, monedaSalida};
    }
}

class Respuesta {
    String opcion;
    String monedaIngreso;
    String monedaSalida;
    double monto;

    Respuesta(String opcion,String monedaIngreso, String monedaSalida,Double monto) {
        this.opcion = opcion;
        this.monedaIngreso = monedaIngreso;
        this.monedaSalida = monedaSalida;
        this.monto = monto;
    }
}
