package com.bookmyrooms.main.utilities;

import com.bookmyrooms.main.exceptions.ResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

public class FileHandler {

    public static String uploadImage(String path, MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String extension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = fileName.substring(dotIndex);
            fileName = fileName.substring(0, dotIndex);
        }
        String newFileName = fileName + "_" + UUID.randomUUID() + extension;

        Path uploadPath = Path.of(path);
        Path filePath = uploadPath.resolve(newFileName);

        if(!Files.exists(uploadPath)){
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newFileName;
    }

    public static ResponseEntity<Resource> serveImage(String path , String fileName){
        try{
            Path filePath = Path.of(path).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists() && resource.isReadable()){
                return ResponseEntity.ok()
                        .contentType(
                                MediaTypeFactory.getMediaType(resource)
                                        .orElse(MediaType.APPLICATION_OCTET_STREAM)
                        )
                        .body(resource);
            }else {
                throw new ResourceNotFoundException("Image","Name",fileName);
            }
        } catch (MalformedURLException e) {
                throw new ResourceNotFoundException("Image","Name",fileName);
        }
    }
}
