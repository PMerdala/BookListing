package com.example.pmerdala.booklisting;

import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

public interface RawDataProvider<T> {

    public T fetchData(Properties properties) throws RawDataProviderException;
}
