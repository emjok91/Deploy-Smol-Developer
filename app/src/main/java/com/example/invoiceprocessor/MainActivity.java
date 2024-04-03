```java
package com.example.invoiceprocessor;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private InvoiceProcessor invoiceProcessor;
    private GoogleDriveService driveService;
    private GoogleSheetsService sheetsService;
    private OCRService ocrService;
    private ErrorLogger errorLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            driveService = new GoogleDriveService();
            sheetsService = new GoogleSheetsService();
            ocrService = new OCRService();
            errorLogger = new ErrorLogger();

            invoiceProcessor = new InvoiceProcessor(driveService, sheetsService, ocrService, errorLogger);
            invoiceProcessor.startProcessing();
        } catch (Exception e) {
            errorLogger.logError("ERROR", "Failed to initialize services", e);
        }
    }
}
```