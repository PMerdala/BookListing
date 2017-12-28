package com.example.pmerdala.booklisting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

public class RestServiceRawDataProviderTest implements JsonTestData{



    @Test
    public void testFetchDataFromGoogle() throws RawDataProviderException{
        RestServiceRawDataProvider dataProvider = new RestServiceRawDataProvider();
        Properties prop = new Properties();
        prop.setProperty(RestServiceRawDataProvider.URL_PROPERTY,"https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1");
        String data = dataProvider.fetchData(prop);
        // usunięcie ilości wszystkich książek która się zmienia
        String firstString  = ", \"totalItems\": ";
        int firstPos = data.indexOf(firstString)+ firstString.length();
        int secondPos = data.indexOf(", \"items\":");
        data = data.substring(0,firstPos) +"603" +data.substring(secondPos);
        Assert.assertEquals(jsonString,data);
    }

    @Test(expected = RawDataProviderException.class)
    public void testFetchDataPropertiesNull() throws RawDataProviderException{
        RestServiceRawDataProvider dataProvider = new RestServiceRawDataProvider();
        dataProvider.fetchData(null);
    }
    @Test(expected = RawDataProviderException.class)
    public void testFetchDataWithoutUrl() throws RawDataProviderException{
        RestServiceRawDataProvider dataProvider = new RestServiceRawDataProvider();
        Properties prop = new Properties();
        dataProvider.fetchData(prop);
    }
}
