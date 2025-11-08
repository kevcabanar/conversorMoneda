
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException  {
        String continuarEjecucion="0";
        String monedaIngreso = "";
        String monedaSalida = "";
        double monto;
        double cambio;

        while (!continuarEjecucion.equals("7")) {
            Respuesta respuestas = Conversor.eleccionUserMenu();
            continuarEjecucion = respuestas.opcion;
            if (!continuarEjecucion.equals("7")) {
                //Definir la URL
                monedaIngreso = respuestas.monedaIngreso;
                monedaSalida = respuestas.monedaSalida;
                monto= respuestas.monto;
                // original "https://v6.exchangerate-api.com/v6/b5f92db1cdd21a302ef9ad5d/latest/USD";
                String url = "https://v6.exchangerate-api.com/v6/b5f92db1cdd21a302ef9ad5d/latest/"+ monedaIngreso;
                cambio = obtenerTasa(url,monedaSalida);
                double montoFinal= cambio*monto;
                System.out.println("Por "+monto+" " +monedaIngreso +"--Obtendra: "+ montoFinal +" "+ monedaSalida);
            }else{
                //el valor es 7 = saldremos de la aplicacion
                System.out.println("Tenga un buen dia lo esperamos en otra oportunidad: ");
            }
        }





    }

    public static double obtenerTasa(String urlFinal, String monedaSalida) throws IOException, InterruptedException {
        //Código omitido
        HttpClient cliente = HttpClient.newHttpClient();

        //Crear la solicitud
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(urlFinal))
                .GET()
                .build();
        //Enviar la solicitud y obtener la respuesta
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        //Mostrar el JSON recibido
        //Conversión a JSON
        JsonElement elemento = JsonParser.parseString(respuesta.body());
        JsonObject objectRoot = elemento.getAsJsonObject();

        JsonObject conversionRates = objectRoot.getAsJsonObject("conversion_rates");
        double tasa = conversionRates.get(monedaSalida).getAsDouble();

        return tasa;
    }
}