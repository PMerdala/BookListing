package com.example.pmerdala.booklisting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

public class GoogleJsonBookParser implements BookParser<String> {
    @Override
    public List<Book> listBookParser(String input, Properties properties) throws BookParserException {
        String step = "start listBookParser ";
        if (input==null){
            throw new BookParserException(step +" problem z parsowaniem input json is null");
        }
        JSONObject root;
        try {
            root = new JSONObject(input);
            return parserBooks(root.getJSONArray("items"));
        } catch (JSONException e) {
            throw new BookParserException(step +" problem z parsowaniem input json=" + input,e);
        }
    }

    private  List<Book> parserBooks(JSONArray jsonBooks)throws BookParserException{
        ArrayList<Book> books = new ArrayList<>();
        String step = "get items array ";
        try {
            int size = jsonBooks.length();
            step += "size=" + size + " ";
            for (int i=0;i<size;i++){
                step = "get item(" + i +") ";
                JSONObject item = jsonBooks.getJSONObject(i);
                Book book = parserBook(item);
                books.add(book);
            }
        } catch (JSONException e) {
            throw new BookParserException(step + "problem z parsowaniem " ,e);
        }
        return books;
    }

    private String parserStringItem(JSONObject element,String itemName) throws JSONException{
        String item = null;
        if (element.has(itemName) && !element.isNull(itemName)) {
            item = element.getString(itemName);
        }
        return item;
    }

    private JSONObject getJSONObjectIfExists(JSONObject element,String itemName)throws JSONException{
        JSONObject item = null;
        if (element.has(itemName) && !element.isNull(itemName)) {
            item = element.getJSONObject(itemName);
        }
        return item;
    }

    private JSONArray getJSONArrayIfExists(JSONObject element,String itemName)throws JSONException{
        JSONArray item = null;
        if (element.has(itemName) && !element.isNull(itemName)) {
            item = element.getJSONArray(itemName);
        }
        return item;
    }

    private Book parserBook(JSONObject jsonBook) throws BookParserException{
        String step = "start parserBook ";
        Book book = null;
        try {
            step = "before get id ";
            String id = jsonBook.getString("id");
            step = "before get volumeInfo id=" + id;
            JSONObject jsonVolumeInfo = jsonBook.getJSONObject("volumeInfo");
            step = "before get title id=" + id;
            String title= jsonVolumeInfo.getString("title");
            step = "before get subtitle id=" + id;
            String subtitle = parserStringItem(jsonVolumeInfo,"subtitle");
            step = "before get description id=" + id;
            String description= parserStringItem(jsonVolumeInfo,"description");
            step = "before get publisher id=" + id;
            String publisher= parserStringItem(jsonVolumeInfo,"publisher");
            step = "before get authors id=" + id;
            ArrayList<String> authors = getAuthors(getJSONArrayIfExists(jsonVolumeInfo,"authors"));
            step = "before get publishedDate id=" + id;
            String publishedDate=parserStringItem(jsonVolumeInfo,"publishedDate");
            step = "before get imageLinks id=" + id;
            String imageUrl= getImageUrl(getJSONObjectIfExists(jsonVolumeInfo,"imageLinks"));
            step = "before get industryIdentifiers id=" + id;
            JSONArray isbns = getJSONArrayIfExists(jsonVolumeInfo,"industryIdentifiers");
            String isbn13 = null;
            String isbn10 =null;
            if (isbns!=null) {
                step = "before get ISBN_13 id=" + id;
                isbn13 = getIsbn(isbns, "ISBN_13");
                step = "before get ISBN_10 id=" + id;
                isbn10 = getIsbn(isbns, "ISBN_10");
            }
            step = "before get selfLink id=" + id;
            String linkUrl= jsonBook.getString("selfLink");
            step = "before new Book id=" + id;
            book = new Book(id,title,subtitle,authors,description,imageUrl,linkUrl,publisher,publishedDate,isbn13,isbn10);

        } catch (JSONException e) {
            throw new BookParserException(step + "problem z parsowaniem " ,e);
        }
        return book;
    }

    private String getImageUrl(JSONObject jsonImages) throws BookParserException{
        String step = "start getImageUrl ";
        if (jsonImages==null) return null;
        try {
            return parserStringItem(jsonImages,"smallThumbnail");
        } catch (JSONException e) {
            throw new BookParserException(step + "problem z parsowaniem " ,e);
        }

    }

    private ArrayList<String> getAuthors(JSONArray jsonAuthors) throws BookParserException{
        if (jsonAuthors==null){
            return null;
        }
        ArrayList<String> authors = new ArrayList<>();
        String step = "start getAuthors ";
        try {
            int size = jsonAuthors.length();
            for (int i = 0; i<size;i++){
                String author= jsonAuthors.getString(i);
                authors.add(author);
            }
        } catch (JSONException e) {
            throw new BookParserException(step + "problem z parsowaniem " ,e);
        }
        return authors;
    }

    private String getIsbn(JSONArray jsonIsbns,String type) throws BookParserException{
        String step = "start getIsbn type=" + type;
        try {
            int size = jsonIsbns.length();
            for (int i = 0; i<size;i++){
                JSONObject isbn= jsonIsbns.getJSONObject(i);
                if (type.equals(isbn.getString("type"))){
                    return isbn.getString("identifier");
                }
            }
        } catch (JSONException e) {
            throw new BookParserException(step + "problem z parsowaniem " ,e);
        }
        return null;
    }
}
