package com.shubham.aws.S3.service;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

public interface IS3Service {

    public int uploadToS3(String filename, byte[] fileBytes, String bucket);

    public S3ObjectInputStream downloadFileFromS3(String filename, String bucket);
}
