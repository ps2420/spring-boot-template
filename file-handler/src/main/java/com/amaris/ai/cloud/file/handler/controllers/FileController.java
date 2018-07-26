package com.amaris.ai.cloud.file.handler.controllers;

import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping("/uploadFile")
  public UploadFileResponse uploadFile(final @RequestParam("file") MultipartFile file) {
    final String fileName = fileStorageService.storeFile(file);
    return new UploadFileResponse(fileName, "", file.getContentType(), file.getSize());
  }

  @RequestMapping(value = "/health", method = RequestMethod.GET)
  public Response health() {
    return Response.ok("200").build();
  }

}
