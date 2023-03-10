import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainController {

    public Label textFieldSearch;

    public TextField textFieldArtist;

    @FXML
    private TextField artistSearchText;

    @FXML
    private Button generateArtist;

    private static HttpURLConnection connection;

    @FXML

    void generateArtist(ActionEvent event) throws MalformedURLException, IOException {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        String search_term = textFieldArtist.getText(); //Obtains input from text field to use in the API search

        try {
            URL url = new URL("https://genius.com/api/search/song?page=1&q=" + search_term);
            connection = (HttpURLConnection) url.openConnection();

            //Setting up request to obtain JSON data from API with the "GET" request method
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);//
            connection.setReadTimeout(5000);

            //Testing response from server
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

            JSONObject obj = new JSONObject(responseContent.toString()); //Turns response from API into JSONObject that is used to retrieve other objects and arrays

            JSONObject obj1 = obj.getJSONObject("response"); //Pulling response JSONObject from original API data
            JSONArray obj1_array = obj1.getJSONArray("sections"); //Pulling JSONArray sections from the response object
            JSONObject obj2 = obj1_array.getJSONObject(0); //Pulling index 0 JSONObject that contains the hits array
            JSONArray obj2_array = obj2.getJSONArray("hits"); //Pulling hits array that contains top 10 results from search

            //For loop that loops through each result to pull the titles of the top 10 hits, and prints them
            for (int i = 0; i < obj2_array.length(); i++) {
                JSONObject obj3 = obj2_array.getJSONObject(i); //Pulling each object index for each result
                JSONObject obj4 = obj3.getJSONObject("result"); //Pulling each result from each index

                JOptionPane.showMessageDialog(null, obj4.getString("full_title"), "Top 10 Songs", 1); //Prints each title from the results obtained
            }

        } finally { //Disconnects connection to API server when search task is completed
            connection.disconnect();

        }

    }


    }


