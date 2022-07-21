import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//aperte shift + alt + o para importar tudo de uma vez. mas é necessário checar se os imports foram feitos corretamente, pois pode puxar um bibli diferente

public class JsonParser {

    //esse Pattern é pra você representar uma expressão regular no Java
    //expressão regular vai passar um código em cima do texto e tentar pegar padrões do texto
    //essa REGEX vai extrair as informações-padrões que estão dentro dos colchetes do json/body
    //essa REGEX eliminou a parte de itens, o error msg e pegou somente a lista de filmes que a gente quer recuperar
    //.*\[(.+)\\].* dentro dos parenteses é que chamamos de grupo de captura. É comum usarmos uma classe que alguém fez ou uma biblioteca de parser de Json (Jacson?)
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    // public indicando que poderá ser acessado de qualquer lugar
    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributosItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosItem.put(atributo, valor);
            }

            dados.add(atributosItem);
        }

        return dados;
    }

}