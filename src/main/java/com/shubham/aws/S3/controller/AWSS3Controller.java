package com.shubham.aws.S3.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.shubham.aws.S3.service.S3Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AWSS3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam String filename, @RequestParam String bucket,
            @RequestParam MultipartFile file) throws IOException {
        int response = s3Service.uploadToS3(filename, file.getBytes(), bucket);
        if (response == 1) {
            return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("File Uploaded Err", HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<String> download(@RequestParam String filename, @RequestParam String bucket) {
        S3ObjectInputStream response = s3Service.downloadFileFromS3(filename, bucket);
        if (response != null) {
            System.out.println(response);
            return new ResponseEntity<>("File Downloaded Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("File Download Err", HttpStatus.OK);
    }

}
