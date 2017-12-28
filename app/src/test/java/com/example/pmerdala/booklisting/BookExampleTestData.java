package com.example.pmerdala.booklisting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by merdala on 2017-12-28.
 */

public class BookExampleTestData {

    private final static String description = "Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps";
    private final static  String author = "Marziah Karch";
    private final static String id = "6tLAyQLSzG0C";
    private final static String title = "Android for Work";
    private final static String subtitle = "Productivity for Professionals";
    private final static String publishedDateString = "2010-09-01";
    private final static String imageUrl = "http://books.google.com/books/content?id=6tLAyQLSzG0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
    private final static String linkUrl = "https://www.googleapis.com/books/v1/volumes/6tLAyQLSzG0C";
    private final static String publisher = "Apress";
    private final static String isbn13 = "9781430230007";
    private final static String isbn10 = "1430230002";
    private final static List<String> authors = new ArrayList<>();
    private static Calendar publishedDate = Calendar.getInstance();

    static{
        authors.clear();
        authors.add(author);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            publishedDate.setTime(dateFormat.parse(publishedDateString));
        } catch (ParseException e) {
            e.printStackTrace();
            publishedDate =null;
        }

    }

    public static List<String> getAuthors(){
        return new ArrayList<>(authors);
    }

    public static Calendar getPublishedDate(){
        if (publishedDate==null){
            throw new NullPointerException("nie ustawiono daty dla publishedDate");
        }
        return (Calendar) publishedDate.clone();
    }


    public static String getDescription() {
        return description;
    }

    public static String getAuthor() {
        return author;
    }

    public static String getId() {
        return id;
    }

    public static String getTitle() {
        return title;
    }

    public static String getSubtitle() {
        return subtitle;
    }

    public static String getPublishedDateString() {
        return publishedDateString;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    public static String getLinkUrl() {
        return linkUrl;
    }

    public static String getPublisher() {
        return publisher;
    }

    public static String getIsbn13() {
        return isbn13;
    }

    public static String getIsbn10() {
        return isbn10;
    }

    public static Book getBook(){
        Book book = new Book(getId(),getTitle(),getSubtitle(),getAuthors(),getDescription(),getImageUrl(),getLinkUrl(),getPublisher(),getPublishedDate(),getIsbn13(),getIsbn10());
        return book;
    }
}
