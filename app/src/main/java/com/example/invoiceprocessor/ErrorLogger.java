```java
package com.example.invoiceprocessor;

import android.util.Log;

public class ErrorLogger {

    private static final String TAG = "InvoiceProcessor";

    public static void logError(String message, Exception e) {
        Log.e(TAG, message, e);
    }

    public static void logError(String message) {
        Log.e(TAG, message);
    }
}
```