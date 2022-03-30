package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class ImageController {

    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable("imageName") String imageName) throws IOException {
        ByteArrayResource inputStream = new ByteArrayResource(
                Files.readAllBytes(Paths.get(
                        "./TransactionTrackerSpringBoot/assets/images/" + imageName
                )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }

}
