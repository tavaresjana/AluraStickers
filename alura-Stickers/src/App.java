import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // *fazer uma conexão HTTP e buscar os top 250 filmes*

        // 1º cria uma variavel com a URL/Chave/Endereço do que vamos consumir
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); // ali na frente estou usando o var, mas poderia ser também o "HttpClient"
        var request = HttpRequest.newBuilder(endereco).GET().build(); // ali na frente estou usando o var, mas poderia ser também o "HttpRequest"
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // o BodyHandlers é uma classe vai criar a maneira de ler os dados
        String body = response.body();

        //System.out.println(body); para verificar o txt armazenado no body

        // *extrair só os dados que interessam (tipo, poster e classificação)*
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //System.out.println(listaDeFilmes.size()); para verificar o tamanho da lista que deve retornar 250 - ok
        //System.out.println(listaDeFilmes.get(0)); para verificar o item na posição 0

        // *exibir e manipular os dados*

        //foreach
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
