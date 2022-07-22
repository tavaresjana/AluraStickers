import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {
    public String BuscaDados(String url) {

        try {

            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient(); // ali na frente estou usando o var, mas poderia ser também o "HttpClient"
            var request = HttpRequest.newBuilder(endereco).GET().build(); // ali na frente estou usando o var, mas poderia ser também o "HttpRequest"
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // o BodyHandlers é uma classe vai criar a maneira de ler os dados
            String body = response.body();

            return body;

        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }
}
