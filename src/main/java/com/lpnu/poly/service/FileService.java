package com.lpnu.poly.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ResponseEntity<String> uploadFile(MultipartFile file, String title);

    ResponseEntity<String> getFile(String title);
}
