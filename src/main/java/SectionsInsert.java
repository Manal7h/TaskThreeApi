import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SectionsInsert {
	public static void SectionsInsert() throws IOException,InterruptedException, Exception{
		String url = "jdbc:sqlserver://localhost:1433;databaseName=ThreeApi;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		// TODO Auto-generated method stub
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC"))
				.build();// fetching the API
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println("API JSON :" + response.body());
		String uglyJsonString = response.body();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();//
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(uglyJsonString);
		String jsonString = gson.toJson(jsonElement);
		System.out.println(jsonString);

		SectionsData ApiJson = gson.fromJson(jsonString, SectionsData.class);// FOR ETCH


		
		for (Docs x : ApiJson.response.docs) {
			String source = x.getSource();
			String document_type = x.getDocument_type();
			String news_desk = x.getNews_desk();
			String section_name = x.getSection_name();
			String subsection_name = x.getSubsection_name();

			String SQLqueryForInserting = "insert into Sections values('" + source + "' ,'" + document_type + "', '"
					+ news_desk + "','" + section_name + "' , '" + subsection_name + "' )";
			
		Connection con = null;
		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, user, pass);
		
			System.out.print(SQLqueryForInserting);

			try {

				Statement st = con.createStatement();

				// Executing query
				int m = st.executeUpdate(SQLqueryForInserting);
				if (m >= 0)
					System.out.println("Inserted successfully : " + SQLqueryForInserting);
				else
					System.out.println("Insertion failed");

				// Closing the connections
				con.close();

			} catch (Exception ex) {
				// Display message when exceptions occurs
				System.err.println(ex);
			}

		}
	}
}
