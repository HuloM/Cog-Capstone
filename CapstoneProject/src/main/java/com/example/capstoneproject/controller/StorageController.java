package com.example.capstoneproject.controller;

import com.example.capstoneproject.service.interfaces.StorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/getImage/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        Path filePath = storageService.load(filename);
        byte[] image = IOUtils.toByteArray(filePath.toUri());
        String mediaType = switch (filename.substring(filename.lastIndexOf(".") + 1)) {
            case "jpg", "jpeg" -> "image/jpeg";
            default -> "image/png";
        };
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .body(image);


    }
}
