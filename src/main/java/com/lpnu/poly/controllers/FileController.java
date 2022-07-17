package com.lpnu.poly.controllers;

import com.lpnu.poly.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/v1/post/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<String> getPostAvatar(@RequestParam("title")String title){
        return fileService.getPostFile(title);
    }

    @GetMapping("/v1/user/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<String> getUserAvatar(@RequestParam("email")String email){
        return fileService.getUserFile(email);
    }

    @PostMapping("/v1/post/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<String> uploadPostAvatar(@RequestParam("file")MultipartFile file,
                                  @RequestParam("title")String title){
        return fileService.uploadPostFile(file,title);
    }

    @PostMapping("/v1/user/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<String> uploadUserAvatar(@RequestParam("file")MultipartFile file,
                                  @RequestParam("email")String email){
        return fileService.uploadUserFile(file,email);
    }

}
