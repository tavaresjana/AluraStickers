import java.io.File;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    // dentro dessa classe precisamos de um metódo criar(). no fim nosso objetivo é
    // que a saida seja uma imagem-sticker

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // *leitura da imagem*
        // InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream inputStream = new
        // URL("https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // *criar nova imagem em memória com transparência e com tamanho novo*
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT); // vai criar uma
                                                                                                      // nova imagem
                                                                                                      // vazia que o
                                                                                                      // fundo é
                                                                                                      // transparente e
                                                                                                      // estamos
                                                                                                      // colocando
                                                                                                      // dentro do
                                                                                                      // novaImagem

        // *copiar a imagem original para nova imagem (em memória)*
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // *configurar a fonte*
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // *escrever uma frase na nova imagem*
        graphics.drawString("FILME TOP", 100, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

    /*
     * Esse main estava sendo usado para testes dos InputStream de leitura de imagem
     * que também estão comentados.
     * public static void main(String[] args) throws Exception {
     * var geradora = new GeradoraDeFigurinhas();
     * geradora.cria(); }
     */
}