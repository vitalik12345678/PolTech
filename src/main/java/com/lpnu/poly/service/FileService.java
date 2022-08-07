package com.lpnu.poly.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadPostFile(MultipartFile file, String title);

    String getPostFile(String title);

    String getPostFile(Long id);

    String uploadUserFile(MultipartFile file, String email);

    String getUserFile(String email);

}
