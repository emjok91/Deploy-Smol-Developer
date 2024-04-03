```java
package com.example.invoiceprocessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceParser {

    private static final Pattern INVOICE_NUMBER_PATTERN = Pattern.compile("Invoice No: (\\d+)");
    private static final Pattern INVOICE_DATE_PATTERN = Pattern.compile("Date: (\\d{2}/\\d{2}/\\d{4})");
    private static final Pattern TOTAL_AMOUNT_PATTERN = Pattern.compile("Total: \\$(\\d+\\.\\d{2})");

    public Invoice parseInvoice(String ocrText) {
        try {
            String invoiceNumber = extractInvoiceNumber(ocrText);
            String invoiceDate = extractInvoiceDate(ocrText);
            double totalAmount = extractTotalAmount(ocrText);

            return new Invoice(invoiceNumber, invoiceDate, totalAmount);
        } catch (Exception e) {
            ErrorLogger.logError("Error parsing invoice: " + e.getMessage());
            return null;
        }
    }

    private String extractInvoiceNumber(String ocrText) {
        Matcher matcher = INVOICE_NUMBER_PATTERN.matcher(ocrText);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("Invoice number not found");
        }
    }

    private String extractInvoiceDate(String ocrText) {
        Matcher matcher = INVOICE_DATE_PATTERN.matcher(ocrText);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("Invoice date not found");
        }
    }

    private double extractTotalAmount(String ocrText) {
        Matcher matcher = TOTAL_AMOUNT_PATTERN.matcher(ocrText);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            throw new IllegalArgumentException("Total amount not found");
        }
    }
}
```