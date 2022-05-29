package com.lpnu.poly;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.io.IOException;

public class PolyApplication {

    private static final AWSCredentials credentials;
    private static String bucketName;

    static {
        //put your accesskey and secretkey here
        credentials = new BasicAWSCredentials(
                "accessKey",
                "secretKey"
        );
    }
    public static void main(String[] args) throws IOException {
        //set-up the client
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        AWSS3Service awsService = new AWSS3Service(s3client);

        bucketName = "bucketName";

        //uploading object
        awsService.putObject(
                bucketName,
                "test2.txt",
                new File("/Users/temp/Documents/PolTech/test2.txt")
        );

        //downloading an object
        S3Object s3object = awsService.getObject(bucketName, "test.txt");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/temp/Documents/PolTech/test.txt"));

        //deleting an object
        awsService.deleteObject(bucketName, "test.txt");

    }

//    @SpringBootApplication
//    public class PolyApplication {
//
//        public static void main(String[] args) {
//            SpringApplication.run(PolyApplication.class, args);
//        }
//
//    }
}