package com.example.transactiontracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageController {
    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable("imageName") String imageName) {
        FileSystemResource inputStream = new FileSystemResource("./TransactionTrackerSpringBoot/assets/images/" + imageName);
//        FileSystemResource inputStream = new FileSystemResource("/usr/src/app/assets/images/" + imageName);
        return new ResponseEntity<>(inputStream, HttpStatus.OK);
    }
}
