package com.amaris.ai.cloud.file.handler.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.amaris.ai.cloud.file.handler.models.UploadFileResponse;
import com.amaris.ai.cloud.file.handler.services.FileStorageService;

@RestController
public class FileController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping("/uploadFile")
  public UploadFileResponse uploadFile(final @RequestParam("file") MultipartFile file) {
    final String fileName = fileStorageService.storeFile(file);
    return new UploadFileResponse(fileName, "", file.getContentType(), file.getSize());
  }
 
  @GetMapping("/downloadFile")
  public ResponseEntity<Resource> downloadFile(final @RequestParam("fileName") String fileName, HttpServletRequest request) {
    final Resource resource = fileStorageService.loadFileAsResource(fileName);

    String contentType = "application/octet-stream";
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      LOGGER.error("Could not determine file type." + ex, ex);
    }

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
  }

}
