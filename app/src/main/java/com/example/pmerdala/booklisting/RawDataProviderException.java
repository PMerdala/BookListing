package com.example.pmerdala.booklisting;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * Created by merdala on 2017-12-28.
 */

public class RawDataProviderException extends Exception {
    public RawDataProviderException() {
    }

    public RawDataProviderException(String message) {
        super(message);
    }

    public RawDataProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RawDataProviderException(Throwable cause) {
        super(cause);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public RawDataProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
