```java
package com.example.invoiceprocessor;

import android.graphics.Bitmap;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

public class OCRService {

    private static final String TAG = "OCRService";
    private TessBaseAPI tessBaseAPI;

    public OCRService(String dataPath, String language) {
        tessBaseAPI = new TessBaseAPI();
        if (!tessBaseAPI.init(dataPath, language)) {
            Log.e(TAG, "Could not initialize TessBaseAPI");
        }
    }

    public String getOCRText(Bitmap bitmap) {
        tessBaseAPI.setImage(bitmap);
        return tessBaseAPI.getUTF8Text();
    }

    public void onDestroy() {
        if (tessBaseAPI != null) {
            tessBaseAPI.end();
        }
    }
}
```