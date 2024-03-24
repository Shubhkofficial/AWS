package com.shubham.aws.S3.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Service
public class S3Service implements IS3Service {

    @Autowired
    private AmazonS3 s3Client;

    public int uploadToS3(String filename, byte[] fileBytes, String bucket) {
        File file = new File(filename);

        try {
            OutputStream o = new FileOutputStream(file);
            o.write(fileBytes);
            s3Client.putObject(bucket, filename, file);
            return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public S3ObjectInputStream downloadFileFromS3(String filename, String bucket) {
        S3Object obj = s3Client.getObject(bucket, filename);
        S3ObjectInputStream content = obj.getObjectContent();
        return content;
    }

}
