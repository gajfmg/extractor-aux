
package extractor;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
/**
 *
 * @author Gabriel
 */
public class Extractor {
	public static void main(String[] args) throws Exception {
		
		//criar a HttpURLConnection 
		HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/repos/gajfmg/GECID-Hierarquia-Ontologica/commits/master").openConnection();
                // Informações do Projeto https://api.github.com/repos/gajfmg/GECID-Hierarquia-Ontologica
		
                httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
		
		//Ler linha por linha
		StringBuilder responseSB = new StringBuilder();
		String line;
		while ( ( line = in.readLine() ) != null) {
			responseSB.append("\n" + line);
			//System.out.println(line);
		}
		in.close();
		
		//Strings
                //Captura nome 
		Arrays.stream(responseSB.toString().split("\"name\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> System.out.println("\n Nome do Committer: " +l));
               //Caputra Data
                Arrays.stream(responseSB.toString().split("\"date\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> System.out.println("\n Data do Commit: " +l));
                // Comentario
		 Arrays.stream(responseSB.toString().split("\"message\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> System.out.println("\n Comentário: " +l));
                
                 System.out.println("\n Arquivos Alterados: ");
                 //Arquivos Alterados
                 Arrays.stream(responseSB.toString().split("\"filename\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> System.out.println (l));
	
                
                //Soma de Strings como dados , neste caso coma das mudanças
                int total = Arrays.stream(responseSB.toString().split("\"changes\":")).skip(1).mapToInt(l -> Integer.parseInt(l.split(",")[0])).sum();
		System.out.println("\n Total de mudanças efetuadas: " + total);
		
	}
	
}
