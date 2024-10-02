package org.example;

import org.apache.hc.core5.http.ParseException;
import org.example.Models.Author;
import org.example.Models.Books;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello world!");

        //https://api.wheretheiss.at/v1/satellites/25544
        //http://molndalbooks-env.eba-uxef3rik.eu-north-1.elasticbeanstalk.com/books

        //ServiceManager.sendGetRequest("https://api.wheretheiss.at/v1/satellites/25544");
        //ServiceManager.sendGetRequest("http://molndalbooks-env.eba-uxef3rik.eu-north-1.elasticbeanstalk.com/books");
        //ServiceManager.sendGetRequest("http://molndalbooks-env.eba-uxef3rik.eu-north-1.elasticbeanstalk.com/books", 4);


        //Skapa en Book och Author objekt
        Author myAuth = new Author("Tolkien", 84);
        Books myBook = new Books("MyStory", "3434");

        //Koppla myAuth till prop i myBook
        //myBook.setId(Long.parseLong("0"));
        myAuth.setId(Long.parseLong("1"));
        myBook.setAuthor(myAuth);

        //SKicka data till PostReq
        ServiceManager.sendPostBookRequest("http://molndalbooks-env.eba-uxef3rik.eu-north-1.elasticbeanstalk.com/books", myBook);
    }
}