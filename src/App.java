
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String var[] = LerArquivo();
        String texto = gerarString(var);
        gerarArquivo(texto);
    }

    public static String[] LerArquivo() throws IOException{
        String retorno[] = new String[4];
        Path caminho = Paths.get("src/x.txt");
        List<String> lista = Files.readAllLines(caminho, StandardCharsets.UTF_8);

        for(int i = 0; i < 4; i++){
            retorno[i] = lista.get(i);
        }

        return retorno;
    }

    public static String gerarString(String[] textos) throws IOException {
        String retorno = "";
        Integer contador = Integer.parseInt(textos[3]);
        String atual = textos[0];

        for (int i = 0; i < contador; i++) {
            Integer tamanho = atual.length();
            for(int j = 0; j < tamanho; j++)
            {
                if(atual.charAt(j) == 'A'){
                    retorno += textos[1];
                }
                if(atual.charAt(j) == 'B'){
                    retorno += textos[2];
                }
            }
            atual = retorno;
        }
        return retorno;
    }

    public static void gerarArquivo(String texto) throws IOException{
        
        FileWriter arq = new FileWriter("src//Resultado.html");
        PrintWriter arquivo = new PrintWriter(arq);
        arquivo.println("<!DOCTYPE html>");
        arquivo.println("<html>");
        arquivo.println("<head>");
        arquivo.println("</head>");
        arquivo.println("<body>");
        arquivo.println("<svg height='10000' width='10000'>");
        
        Integer x = 2000;
        Integer y = 2000;

        Integer angulo = 0;

        Integer tamanhoTexto = texto.length();
        
        for (int i = 0; i < tamanhoTexto; i++) {
        
            char c = texto.charAt(i);

            if(c == 'A'){

                angulo = angulo + 120;
                
                if(angulo >= 360){
                    angulo = 0;
                }
            }
            else if(c == 'B'){
                angulo = angulo - 120;
                
                if(angulo < 0 ){
                    angulo = 240;
                }
            }
            
            if(angulo == 0){
                arquivo.println("<line x1='" + x + "' y1=" + y + " x2=" + (x + 120)  + " y2=" + (y) + " style='stroke:rgb(0,0,0);stroke-width:2' />");
                x = x + 120;
            }
            else if(angulo == 120){
                arquivo.println("<line x1='" + x + "' y1=" + y + " x2=" + (x - 60)  + " y2=" + (y - 80) + " style='stroke:rgb(0,0,0);stroke-width:2' />");
                x = x - 60;
                y = y - 80;
            }
            else if(angulo == 240){
                arquivo.println("<line x1='" + x + "' y1=" + y + " x2=" + (x - 60)  + " y2=" + (y + 80) + " style='stroke:rgb(0,0,0);stroke-width:2' />");
                x = x - 60;
                y = y + 80;
            }
        }
    
        arquivo.println("</svg>");
        arquivo.println("</body>");
        arquivo.println("</html>");
        arq.close();
    }
}

