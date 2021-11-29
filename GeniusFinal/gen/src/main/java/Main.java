import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException, InterruptedException, JSONException, MalformedURLException {

        String search_term;

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter artist:");
        search_term = myObj.nextLine();
        try {
            URL url = new URL("https://genius.com/api/search/song?page=1&q=" + search_term);
            connection = (HttpURLConnection) url.openConnection();

            // Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
            connection.setReadTimeout(5000);

            // Test if the response from the server is successful
            int status = connection.getResponseCode();

            if (status >= 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());

            JSONObject obj = new JSONObject(responseContent.toString());
            JSONArray jsonArray = new JSONArray();
            int length = obj.length();
            jsonArray.put(obj);
            // System.out.println(obj.getJSONObject("response"));
            System.out.println(jsonArray);


            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(responseContent.toString());
            String prettyJsonString = gson.toJson(je);
            System.out.println(prettyJsonString);

            //System.out.println(responseContent.get);


        } finally {
            connection.disconnect();

        }
    }

}






