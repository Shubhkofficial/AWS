package com.shubham.aws.S3.pojo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UploadBody {

    private String filename;
    private String bucket;
    private MultipartFile file;
    

}
