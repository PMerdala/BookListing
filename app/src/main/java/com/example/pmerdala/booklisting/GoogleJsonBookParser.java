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

    private Book parserBook(JSONObject jsonBook) throws BookParserException{
        String step = "start parserBook ";
        Book book = null;
        try {
            step = "before get id ";
            String id = jsonBook.getString("id");
            step = "before get volumeInfo ";
            JSONObject jsonVolumeInfo = jsonBook.getJSONObject("volumeInfo");
            step = "before get title ";
            String title= jsonVolumeInfo.getString("title");
            step = "before get subtitle ";
            String subtitle= jsonVolumeInfo.getString("subtitle");
            step = "before get description ";
            String description= jsonVolumeInfo.getString("description");
            step = "before get publisher ";
            String publisher= jsonVolumeInfo.getString("publisher");
            step = "before get authors ";
            ArrayList<String> authors = getAuthors(jsonVolumeInfo.getJSONArray("authors"));
            step = "before get publishedDate ";
            Calendar publishedDate=getPublishedDate(jsonVolumeInfo.getString("publishedDate"));
            step = "before get imageLinks ";
            String imageUrl= getImageUrl(jsonVolumeInfo.getJSONObject("imageLinks"));
            step = "before get industryIdentifiers ";
            JSONArray isbns = jsonVolumeInfo.getJSONArray("industryIdentifiers");
            step = "before get ISBN_13 ";
            String isbn13= getIsbn(isbns,"ISBN_13");
            step = "before get ISBN_10 ";
            String isbn10= getIsbn(isbns,"ISBN_10");
            step = "before get selfLink ";
            String linkUrl= jsonBook.getString("selfLink");
            step = "before new Book ";
            book = new Book(id,title,subtitle,authors,description,imageUrl,linkUrl,publisher,publishedDate,isbn13,isbn10);

        } catch (JSONException e) {
            throw new BookParserException(step + "problem z parsowaniem " ,e);
        }
        return book;
    }

    private String getImageUrl(JSONObject jsonImages) throws BookParserException{
        String step = "start getImageUrl ";
        try {
            return jsonImages.getString("smallThumbnail");
        } catch (JSONException e) {
            throw new BookParserException(step + "problem z parsowaniem " ,e);
        }

    }

    private ArrayList<String> getAuthors(JSONArray jsonAuthors) throws BookParserException{
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

    private Calendar getPublishedDate(String dateString)throws BookParserException{
        DateFormat dataParser = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = dataParser.parse(dateString);
        } catch (ParseException e) {
            throw new BookParserException("problem z parsowaniem daty="+dateString ,e);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
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
