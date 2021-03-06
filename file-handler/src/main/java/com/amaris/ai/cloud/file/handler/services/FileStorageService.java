package com.amaris.ai.cloud.file.handler.services;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.amaris.ai.cloud.file.handler.configuration.FileStorageProperties;
import com.amaris.ai.cloud.file.handler.exception.FileStorageException;
import com.amaris.ai.cloud.file.handler.exception.MyFileNotFoundException;

@Service
public class FileStorageService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageService.class);

  @Autowired
  private FileStorageProperties fileStorageProperties;

  private Path fileStorageLocation;
  private Path fileDownloadLocation;

  @PostConstruct
  private void postConstruct() {
    try {
      this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
      Files.createDirectories(this.fileStorageLocation);
      
      this.fileDownloadLocation = Paths.get(fileStorageProperties.getDownloadDir()).toAbsolutePath().normalize();
      Files.createDirectories(this.fileDownloadLocation);
    } catch (Exception ex) {
      throw new RuntimeException("Could not create the directory where the uploaded files will be stored." + ex, ex);
    }
  }

  public String storeFile(final MultipartFile file) {
    final String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      if (fileName.contains("..")) {
        throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
      }
      final Path targetLocation = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (Exception ex) {
      LOGGER.error("Could not store file " + fileName + ". Please try again!" + ex, ex);
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!" + ex, ex);
    }
  }

  public Resource loadFileAsResource(final String fileName) {
    try {
      final Path filePath = this.fileDownloadLocation.resolve(fileName).normalize();
      final Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new MyFileNotFoundException("File not found " + fileName);
      }
    } catch (MalformedURLException ex) {
      throw new MyFileNotFoundException("File not found " + fileName, ex);
    }
  }

}
