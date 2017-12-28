package com.example.pmerdala.booklisting;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by merdala on 2017-12-28.
 */

public class BookDataProviderException extends Exception {
    public BookDataProviderException() {
    }

    public BookDataProviderException(String message) {
        super(message);
    }

    public BookDataProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDataProviderException(Throwable cause) {
        super(cause);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public BookDataProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
