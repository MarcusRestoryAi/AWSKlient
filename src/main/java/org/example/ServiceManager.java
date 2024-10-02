package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.example.Models.Books;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceManager {

    //Skapa en CloseableHTTPClinet object
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void sendGetRequest(String uri, int id) throws IOException, ParseException {
        String newUri = uri + String.format("/%d", id);

        sendGetRequest(newUri);
    }

    public static void sendGetRequest(String uri) throws IOException, ParseException {
        //Skapa en HTTP GET object
        HttpGet request = new HttpGet(uri);

        //Skapa ett objekt för att hantera responsen
        CloseableHttpResponse response = httpClient.execute(request);

        //Kontrollera att response lyckas
        if (response.getCode() != 200) {
            System.out.println(String.format("Något har gått fel! Statuskod: %d", response.getCode()));
            return;
        }

        //Packa upp payload från response
        HttpEntity entity = response.getEntity();

        //Skriva ut PayloadData till Console
        //System.out.println(EntityUtils.toString(entity));

        //Skapa ett objekt av ObjectMapper klassen
        ObjectMapper mapper = new ObjectMapper();

        //Save response to a string
        String jsonResp = EntityUtils.toString(entity);

        try {
            ArrayList<Books> books = mapper.readValue(jsonResp, new TypeReference<ArrayList<Books>>(){});

            //Anvädn en ForEach loop för att skriva ut Books's titles
            for (Books book : books) {
                System.out.println(String.format("%s skriven av %s", book.getTitle(), book.getAuthor().getName()));
            }
        } catch (Exception e) {
            Books book = mapper.readValue(jsonResp, new TypeReference<Books>() {
            });

            //Anvädn en ForEach loop för att skriva ut Books's titles
            System.out.println(String.format("%s skriven av %s", book.getTitle(), book.getAuthor().getName()));
        }

    }

    public static void sendPostBookRequest(String uri, Books book) throws IOException, ParseException {
        //Skapa ett HTTPPOST object
        HttpPost request = new HttpPost(uri);

        //Skapa ett objekt av ObjectMapper klassen
        ObjectMapper mapper = new ObjectMapper();

        StringEntity jsonPayload = new StringEntity(mapper.writeValueAsString(book), ContentType.APPLICATION_JSON);

        //Koppla payload till POST-requesr
        request.setEntity(jsonPayload);

        //Skapa ett objekt för att hantera responsen
        CloseableHttpResponse response = httpClient.execute(request);

        //Kontrollera att response lyckas
        if (response.getCode() != 200) {
            System.out.println(String.format("Något har gått fel! Statuskod: %d", response.getCode()));
            return;
        }

        //Packa upp payload från response
        HttpEntity entity = response.getEntity();

        //Skriva ut PayloadData till Console
        //System.out.println(EntityUtils.toString(entity));

        //Save response to a string
        String jsonResp = EntityUtils.toString(entity);

        Books respBook = mapper.readValue(jsonResp, new TypeReference<Books>() {
        });

        //Anvädn en ForEach loop för att skriva ut Books's titles
        System.out.println(String.format("Boken %s har blivit sparad med id %d", respBook.getTitle(), respBook.getId()));
    }
}
