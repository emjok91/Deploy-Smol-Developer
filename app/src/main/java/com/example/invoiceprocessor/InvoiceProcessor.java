```java
package com.example.invoiceprocessor;

import com.google.api.services.drive.model.File;
import java.util.List;

public class InvoiceProcessor {

    private GoogleDriveService driveService;
    private GoogleSheetsService sheetsService;
    private OCRService ocrService;
    private ErrorLogger errorLogger;
    private String processedFolderId;

    public InvoiceProcessor(GoogleDriveService driveService, GoogleSheetsService sheetsService, OCRService ocrService, ErrorLogger errorLogger, String processedFolderId) {
        this.driveService = driveService;
        this.sheetsService = sheetsService;
        this.ocrService = ocrService;
        this.errorLogger = errorLogger;
        this.processedFolderId = processedFolderId;
    }

    public void processInvoices() {
        try {
            List<File> files = driveService.getDriveFiles();
            for (File file : files) {
                if (isImageFile(file)) {
                    String ocrText = ocrService.getOCRText(file);
                    Invoice invoice = InvoiceParser.parseInvoice(ocrText);
                    if (invoice != null) {
                        updateSpreadsheet(invoice);
                        driveService.moveFile(file, processedFolderId);
                    }
                }
            }
        } catch (Exception e) {
            errorLogger.logError("ERROR", e.getMessage());
        }
    }

    private boolean isImageFile(File file) {
        String mimeType = file.getMimeType();
        return mimeType.equals("image/jpeg") || mimeType.equals("image/png");
    }

    private void updateSpreadsheet(Invoice invoice) {
        String spreadsheetId = sheetsService.getSpreadsheet();
        if (spreadsheetId == null) {
            spreadsheetId = sheetsService.createSpreadsheet();
        }
        if (sheetsService.rowExists(spreadsheetId, invoice.getInvoiceNumber())) {
            sheetsService.updateRow(spreadsheetId, invoice);
        } else {
            sheetsService.addRow(spreadsheetId, invoice);
        }
    }
}
```