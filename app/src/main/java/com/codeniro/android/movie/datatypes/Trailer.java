package com.codeniro.android.movie.datatypes;

/**
 * Created by Dupree on 12/05/2017.
 */

public class Trailer{
    private String id;
    private String key;
    private String author;
    private String content;
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    private String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
