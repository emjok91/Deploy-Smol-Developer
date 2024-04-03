1. Shared Variables: 
   - "invoiceNumber", "invoiceDate", "totalAmount" are shared between InvoiceProcessor, InvoiceParser, GoogleSheetsService, and Invoice classes.
   - "driveService", "sheetsService", "ocrService" are shared between MainActivity and InvoiceProcessor classes.
   - "processedFolderId" is shared between GoogleDriveService and InvoiceProcessor classes.

2. Data Schemas: 
   - "Invoice" class is a data schema shared across InvoiceProcessor, InvoiceParser, and GoogleSheetsService classes.

3. ID Names of DOM Elements: 
   - "activity_main" is the ID of the main layout file used in MainActivity.java.

4. Message Names: 
   - "ERROR" is a message name used in ErrorLogger class.

5. Function Names: 
   - "processInvoice", "parseInvoice", "logError", "moveFile", "updateSpreadsheet" are function names shared across multiple classes.
   - "onCreate" is a lifecycle method in MainActivity.java.
   - "getOCRText", "getDriveFiles", "getSpreadsheet", "createSpreadsheet", "updateRow", "addRow" are function names in OCRService, GoogleDriveService, and GoogleSheetsService classes respectively.

6. Dependencies: 
   - Google Drive API and Google Sheets API are shared dependencies across GoogleDriveService and GoogleSheetsService classes.
   - OCR library (like Tesseract) is a shared dependency in OCRService class.
   - Android SDK and its related dependencies are shared across all the classes and in build.gradle file.