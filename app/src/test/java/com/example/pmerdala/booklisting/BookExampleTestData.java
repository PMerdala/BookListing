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
    private final static String imageUrl = "http://books.google.com/books/content?id=6tLAyQLSzG0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
    private final static String linkUrl = "https://www.googleapis.com/books/v1/volumes/6tLAyQLSzG0C";
    private final static String publisher = "Apress";
    private final static String isbn13 = "9781430230007";
    private final static String isbn10 = "1430230002";
    private final static List<String> authors = new ArrayList<>();
    private static String publishedDate = "2010-09-01";

    static{
        authors.clear();
        authors.add(author);

    }

    public static Calendar getCalendarFromString(String dateString,String pattern){
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            calendar.setTime(dateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
            calendar =null;
        }
        return calendar;
    }

    public static List<String> getAuthors(){
        return new ArrayList<>(authors);
    }

    public static String getPublishedDate(){
        return publishedDate;
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
    public static Book getBookWithoutTitle(){
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Vladimir Silva");
        String desc = "In the last few years, Android has progressed with the debut of better fonts, new User Interface and Experience (UI/UX) APIs, tablet considerations, multi-touch capabilities, multi-tasking, faster performance, improved battery management techniques, and now Google TV Android Apps for the Android game app developer repertoire. With actionable real-world source, Pro Android Games, Second Edition shows you how to build more sophisticated and addictive Android games, by leveraging the power of these recent advancements found in the new Android Jelly Beans development platform as well as those you've counted on in earlier releases. Multi-touch code gives these games and their players dynamic input and exchange ability, for a more realistic arcade game experience. Faster and better performance offers game players a more seamless, fun arcade experience like never before on Android. There is also improved native C/C++ integration with Android's NDK as well, which makes coding, compiling, and converting both productive and efficient with gains in app performance. Pro Android Games, Second Edition features the following improvements: Updates to the latest version of the Android SDK, NKD, plus the latest Eclipse IDE. Greater focus on tablets the ever changing device resolutions, and hardware specs. Native game development and hardware accelerated graphics. Bigger and Better Real World Engines, such as Quake I and II Plus an oldie from the previous edition: Doom Coverage of the new smart TV APIs (Google TV), UI, UX, multi-touch and multi-tasking features available with Android Jelly Bean. A look into the future with augmented reality Advanced techniques for improving your game playing experience including better multi-tasking, improved performance optimization, battery management and more. A \\\"Quake 3D\\\"-like game app case study You’ll definitely have fun, and perhaps you’ll even make some money. Enjoy! What you’ll learn Key advanced Android gaming techniques using the new Android SDK and NDK How to add and integrate multi-touch How to use Bluetooth controllers (Zeemote) More gaming tricks and tips, such as hybrid 3D graphics with OpenGL and JNI How to port, augment a 3D shooter \\\"Doom\\\"-like game app using OpenGL How to build a 3D shooter game like \\\"Quake\\\" and \\\"Quake II\\\" How and where to best deploy these game apps Who this book is for This book is for savvy Android app developers who are looking for professional or advanced techniques for porting, augmenting and building 3D game apps that are complex, fun and lucrative. Table of Contents Welcome to Android Gaming Gaming Tricks for Phones or Tablets More Gaming Tricks with OpenGL and JNI Efficient Graphics and Portability with OpenGL ES 2.0 3D Shooters for Doom 3D Shooters for Quake 3D Shooters for Quake II Fun With Bluetooth Controllers A Look Into the Future: Augmented Reality and Google TV Deployment and Compilation Tips";
        Book book = new Book("Z94TEkJfCE0C","Pro Android Games",null,authors,desc,"http://books.google.com/books/content?id=Z94TEkJfCE0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api","https://www.googleapis.com/books/v1/volumes/Z94TEkJfCE0C","Apress","2012-10-10","9781430247975","1430247975");
        return book;
    }
}
