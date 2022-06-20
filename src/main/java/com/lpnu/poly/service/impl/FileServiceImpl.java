package com.lpnu.poly.service.impl;

import com.amazonaws.HttpMethod;
import com.lpnu.poly.AWS.AWSS3Service;
import com.lpnu.poly.AWS.BucketName;
import com.lpnu.poly.entity.Post;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.PostRepository;
import com.lpnu.poly.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {

    private final AWSS3Service awss3Service;
    private final PostRepository postRepository;

    private static final String POST_NOT_EXIST = "Post doesn't exist";

    @Autowired
    public FileServiceImpl(AWSS3Service awss3Service, PostRepository postRepository) {
        this.awss3Service = awss3Service;
        this.postRepository = postRepository;
    }

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile file, String title) {
        Post post = postRepository.findByTitle(title).orElseThrow(()-> {
            throw new NotExistsException(POST_NOT_EXIST);
        });

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
        String fileName = date + file.getOriginalFilename();

        try {
            Files.copy(file.getInputStream(), Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        awss3Service.putObject(
                BucketName.BUCKET_NAME.getBucketName(),
                fileName,
                new File(fileName));

        try {
            Files.delete( Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setAvatarURI(fileName);
        return ResponseEntity.ok(fileName);
    }

    @Override
    public ResponseEntity<String> getFile(String title) {
        Post post = postRepository.findByTitle(title).orElseThrow(()-> {
            throw new NotExistsException(POST_NOT_EXIST);
        });
        return ResponseEntity.ok(awss3Service.generateURI(BucketName.BUCKET_NAME.getBucketName(),post.getAvatarURI(), HttpMethod.GET));
    }
}
