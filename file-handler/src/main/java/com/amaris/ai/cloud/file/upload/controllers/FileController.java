package com.amaris.ai.cloud.file.upload.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.amaris.ai.cloud.file.upload.models.UploadFileResponse;
import com.amaris.ai.cloud.file.upload.services.FileStorageService;

@RestController
public class FileController {

  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping("/upload")
  public UploadFileResponse upload(@RequestParam(name = "domain", defaultValue = "") String domain,
      final @RequestParam("file") MultipartFile file) {
    final String fileName = fileStorageService.storeFile(file);
    // final String fileDownloadUri =
    // ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
    return new UploadFileResponse(fileName, "", file.getContentType(), file.getSize());
  }

}
