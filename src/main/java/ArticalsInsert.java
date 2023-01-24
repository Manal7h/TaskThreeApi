import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ArticalsInsert {
	public static void ArticalsInsert() throws IOException, InterruptedException, Exception {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=ThreeApi;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		// TODO Auto-generated method stub
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://api.nytimes.com/svc/mostpopular/v2/shared/1/facebook.json?api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC"))
				.build();// fetching the API

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println("API JSON :" + response.body());
		String uglyJsonString = response.body();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();//
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(uglyJsonString);
		String jsonString = gson.toJson(jsonElement);
		System.out.println(jsonString);

		ArticalsData ApiJson = gson.fromJson(jsonString, ArticalsData.class);// FOR ETCH

		Connection con = null;
		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, user, pass);
		for (Results x : ApiJson.results) {

			String source = x.getSource();
			String section = x.getSection();
			String subsection = x.getSubsection();
			String type = x.getType();
			String title = x.getTitle();

			String SQLqueryForInserting = "insert into Articals values('" + source + "' ,'" + section + "', '"
					+ subsection + "','" + type + "' , '" + title + "' )";

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
