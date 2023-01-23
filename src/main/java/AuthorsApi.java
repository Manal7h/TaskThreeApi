import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AuthorsApi {
	public static void AuthorsApi() throws IOException, InterruptedException{
		String url = "jdbc:sqlserver://localhost:1433;databaseName=ThreeApi;encrypt=true;trustServerCertificate=true";
		String user = "sa";
	    String pass = "root";
	    
	    HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC"))
	            .build();// fetching the API
	    
	    
	    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	    
//	    System.out.println("API JSON :" +response.body());
	    String uglyJsonString=response.body();
	    
	   
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();//
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(uglyJsonString);
		String jsonString = gson.toJson(jsonElement);
		System.out.println(jsonString);
		
		
		
		
		
		
		
		
		
	    
	    
		
		
		
	}
}
