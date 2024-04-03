```java
package com.example.invoiceprocessor;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GoogleSheetsService {

    private Sheets service;
    private String spreadsheetId;

    public GoogleSheetsService(Sheets service, String spreadsheetId) {
        this.service = service;
        this.spreadsheetId = spreadsheetId;
    }

    public void updateSpreadsheet(Invoice invoice) {
        try {
            ValueRange response = this.service.spreadsheets().values()
                    .get(spreadsheetId, "Sheet1")
                    .execute();

            List<List<Object>> values = response.getValues();

            if (values == null || values.isEmpty()) {
                addRow(invoice);
            } else {
                boolean updated = false;
                for (List row : values) {
                    if (row.get(0).equals(invoice.getInvoiceNumber())) {
                        updateRow(invoice, values.indexOf(row) + 1);
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    addRow(invoice);
                }
            }
        } catch (IOException e) {
            ErrorLogger.logError("ERROR", e.getMessage());
        }
    }

    private void addRow(Invoice invoice) throws IOException {
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(
                        invoice.getInvoiceNumber(),
                        invoice.getInvoiceDate(),
                        invoice.getTotalAmount()
                )
        );

        ValueRange body = new ValueRange()
                .setValues(values);
        this.service.spreadsheets().values()
                .append(spreadsheetId, "Sheet1", body)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }

    private void updateRow(Invoice invoice, int rowIndex) throws IOException {
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(
                        invoice.getInvoiceNumber(),
                        invoice.getInvoiceDate(),
                        invoice.getTotalAmount()
                )
        );

        ValueRange body = new ValueRange()
                .setValues(values);
        this.service.spreadsheets().values()
                .update(spreadsheetId, "Sheet1!A" + rowIndex + ":C" + rowIndex, body)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }
}
```