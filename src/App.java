import java.io.InputStream;
import java.net.URL;
import java.util.List;

// import br.com.alura.omnistream.service.json.JsonParser;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer a conexão HTTP e buscar os top 250 filmes

        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";

        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        String url = "http://localhost:8080/linguagens";
        
        // pegar só os dados que interessam (título, poster, classificação)
        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }
}
