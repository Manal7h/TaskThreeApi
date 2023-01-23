import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SectionsApi {
	public static void SectionsApi() throws IOException,InterruptedException{
	
	String url = "jdbc:sqlserver://localhost:1433;databaseName=ThreeApi;encrypt=true;trustServerCertificate=true";
	String user = "sa";
    String pass = "root";
    
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC"))
            .build();// fetching the API
    
    
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    //take the API and save it into response variable 
    
//    System.out.println("API JSON :" +response.body());
    String uglyJsonString=response.body();//response.body is the api, we stored the api into new variable called uglyJsonString
    
    
    
    
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();// gson is a library 
	JsonParser jsonParser = new JsonParser();// will change the JSOn to String 
	JsonElement jsonElement = jsonParser.parse(uglyJsonString);
	String jsonString = gson.toJson(jsonElement);
	System.out.println(jsonString);
	
}
}