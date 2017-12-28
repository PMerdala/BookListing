package com.example.pmerdala.booklisting;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

public class RestServiceRawDataProviderTest implements JsonTestData{



    @Test
    public void testFetchDataFromGoogle() throws RawDataProviderException,JSONException{
        RestServiceRawDataProvider dataProvider = new RestServiceRawDataProvider();
        Properties prop = new Properties();
        prop.setProperty(RestServiceRawDataProvider.URL_PROPERTY,"https://www.googleapis.com/books/v1/volumes?q=Android&maxResults=1");
        String data = dataProvider.fetchData(prop);
        JSONObject root = new JSONObject(data);
        Assert.assertNotNull(root);
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
