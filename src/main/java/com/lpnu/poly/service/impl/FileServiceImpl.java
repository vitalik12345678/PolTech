package com.lpnu.poly.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.lpnu.poly.AWS.AWSS3Service;
import com.lpnu.poly.AWS.BucketName;
import com.lpnu.poly.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {

    private final AWSS3Service awss3Service;

    @Autowired
    public FileServiceImpl(AWSS3Service awss3Service) {
        this.awss3Service = awss3Service;
    }

    @Override
    public ResponseEntity<?> uploadFile() {

        awss3Service.putObject(
                BucketName.BUCKET_NAME.getBucketName(),
                "jopa.txt",
                new File("D:\\poly\\jopa.txt")
        );

        //downloading an object
        S3Object s3object = awss3Service.getObject(BucketName.BUCKET_NAME.getBucketName(), "jopa.txt");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File("D:\\poly\\src\\main\\resources\\DAYN.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //deleting an object
        awss3Service.deleteObject(BucketName.BUCKET_NAME.getBucketName(), "jopa.txt");
        return null;
    }
}
