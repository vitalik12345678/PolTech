package com.lpnu.poly.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ResponseEntity<String> uploadPostFile(MultipartFile file, String title);

    String getPostFile(String title);

    ResponseEntity<String> uploadUserFile(MultipartFile file,String email);

    String getUserFile(String email);
    
}
