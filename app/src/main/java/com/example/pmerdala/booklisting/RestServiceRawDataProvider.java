package com.example.pmerdala.booklisting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

public class RestServiceRawDataProvider implements RawDataProvider<String> {

    public final static String URL_PROPERTY = "Url";
    public final static String CONNECTION_TIMEOUT_PROPERTY = "Connection timeout";
    public final static String FETCH_TIMEOUT_PROPERTY = "Fetch timeout";
    public final static int DEFAULT_CONNECTION_TIMEOUT = 15000;
    public final static int DEFAULT_FETCH_TIMEOUT = 10000;

    @Override
    public String fetchData(Properties properties)  throws RawDataProviderException{
        ProviderOption option = getOption(properties);
        String data = fetchRawData(option);
        return data;
    }

    private ProviderOption getOption(Properties properties) throws RawDataProviderException{
        if (properties==null){
            throw new RawDataProviderException("Do RestServiceRawDataProvider.fetchData należy przekazać properties z conajmniej jedną własnością " + URL_PROPERTY);
        }
        String urlString = properties.getProperty(URL_PROPERTY);
        if (urlString == null) {
            throw new RawDataProviderException("Do RestServiceRawDataProvider.fetchData należy przekazać property " + URL_PROPERTY);
        }
        String timeoutString = properties.getProperty(CONNECTION_TIMEOUT_PROPERTY, "" + DEFAULT_CONNECTION_TIMEOUT);
        int connectionTimeout = Integer.valueOf(timeoutString);
        timeoutString = properties.getProperty(FETCH_TIMEOUT_PROPERTY, "" + DEFAULT_FETCH_TIMEOUT);
        int fetchTimeout = Integer.valueOf(timeoutString);
        return new ProviderOption(urlString,connectionTimeout,fetchTimeout);
    }

    private String fetchRawData(ProviderOption option) throws RawDataProviderException {
        URL url = prepareUrl(option);
        URLConnection connection = prepareConnection(url, option);
        BufferedReader in = prepareReader(connection,option);
        String data = fetchRawData(in,option);
        return data;
    }

    private URLConnection prepareConnection(URL url, ProviderOption option) throws RawDataProviderException {
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            throw new RawDataProviderException("problem z otwarciem połączenia connection z RestService url=" + url.toString(),e);
        }
        connection.setConnectTimeout(option.connectionTimeout);
        connection.setReadTimeout(option.fetchTimeout);
        return connection;
    }

    private BufferedReader prepareReader(URLConnection connection,ProviderOption option) throws RawDataProviderException {
        try {
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            throw new RawDataProviderException("problem z pobraniem InputStream dla connection z RestService url=" + option.url,e);
        }
    }

    private String fetchRawData(BufferedReader in,ProviderOption option) throws RawDataProviderException {
        StringBuilder sb = new StringBuilder();
        String s;
        try {
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            throw new RawDataProviderException("problem z pobraniem danych z RestService url=" + option.url,e);
        }
        return sb.toString();

    }


    private URL prepareUrl(ProviderOption option) throws RawDataProviderException {
        try {
            return new URL(option.url);
        } catch (MalformedURLException e) {
           throw new RawDataProviderException("niepoprawny url=" + option.url,e);
        }
    }

    private class ProviderOption{
        final String url;
        final int connectionTimeout;
        final int fetchTimeout;

        public ProviderOption(String url, int connectionTimeout, int fetchTimeout) {
            this.url = url;
            this.connectionTimeout = connectionTimeout;
            this.fetchTimeout = fetchTimeout;
        }
    }

}
