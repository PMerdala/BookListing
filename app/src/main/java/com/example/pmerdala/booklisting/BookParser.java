package com.example.pmerdala.booklisting;

import java.util.List;
import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

public interface BookParser<T> {

    List<Book> listBookParser(T input, Properties properties) throws BookParserException;
}
