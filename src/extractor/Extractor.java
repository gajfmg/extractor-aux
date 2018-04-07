
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
		//criar a HttpURLConnection 
		HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/repos/gajfmg/web-app/commits").openConnection();
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
               System.out.println(responseSB);
               JSONObject my_obj = new JSONObject(responseSB);
               System.out.println(my_obj);
               
               String nome = my_obj.getString("nome");
              
		
                System.out.println("nome: " + nome);
                
            
    }
    
}
