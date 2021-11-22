import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    // private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://genius.p.rapidapi.com/artists/16775/songs"))
                .header("x-rapidapi-host", "genius.p.rapidapi.com")
                .header("x-rapidapi-key", "f2f9cc3f95msh34c0949210b8835p1e4273jsncb114eb50412")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }

    public static String parse(String responseBody) {
        JSONArray songs = new JSONArray(responseBody);
        for (int i = 0; i < songs.length(); i++) {
            JSONObject song = songs.getJSONObject(i);
            int id = song.getInt("id");
            String title = song.getString("full_title");
            System.out.println(title);
        }
        return null;
    }
}


