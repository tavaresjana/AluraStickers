import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // *fazer uma conexão HTTP e buscar os dados da API selecionada*. Primeiro cria uma variavel com a URL/Chave/Endereço do que vamos consumir

       // String url = "https://alura-imdb-api.herokuapp.com/movies";
       // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

         String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
         ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        var http = new ClienteHttp();
        String json = http.BuscaDados(url);

        // *exibir e manipular os dados*
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        // for
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            var nomeArquivo = "saida/"+ conteudo.getTitulo() + ".png"; //para ir pro diretório de saída, pois antes estava tdo na home

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
