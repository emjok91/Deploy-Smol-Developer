```java
package com.example.invoiceprocessor;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GoogleDriveService {

    private Drive driveService;
    private String processedFolderId;

    public GoogleDriveService(Drive driveService, String processedFolderId) {
        this.driveService = driveService;
        this.processedFolderId = processedFolderId;
    }

    public List<File> getDriveFiles(String folderId) throws IOException {
        FileList result = driveService.files().list()
                .setQ("'" + folderId + "' in parents and trashed = false")
                .setFields("files(id, name, mimeType)")
                .execute();
        return result.getFiles();
    }

    public boolean isImageFile(File file) {
        List<String> imageMimeTypes = Arrays.asList("image/jpeg", "image/png");
        return imageMimeTypes.contains(file.getMimeType());
    }

    public void moveFile(File file) throws IOException {
        File updatedFile = new File();
        updatedFile.setParents(Arrays.asList(processedFolderId));
        driveService.files().update(file.getId(), updatedFile).execute();
    }
}
```