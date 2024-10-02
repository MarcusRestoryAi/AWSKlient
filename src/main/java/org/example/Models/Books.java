package org.example.Models;

public class Books {

    private long id;

    public Books() {
    }

    public Books(String title, String ISBN) {
        this.title = title;
        this.ISBN = ISBN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Author getAuthor() {
        return author;
    }
    //kommentar

    public void setAuthor(Author author) {
        this.author = author;
    }

    private String title;

    private String ISBN;

    private Author author;
}
