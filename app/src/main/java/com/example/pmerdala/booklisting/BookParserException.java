package com.example.pmerdala.booklisting;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by merdala on 2017-12-28.
 */

public class BookParserException extends Exception {
    public BookParserException() {
    }

    public BookParserException(String message) {
        super(message);
    }

    public BookParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookParserException(Throwable cause) {
        super(cause);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public BookParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
