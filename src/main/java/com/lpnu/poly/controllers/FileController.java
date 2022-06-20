package com.lpnu.poly.controllers;

import com.lpnu.poly.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/v1/")
    public ResponseEntity<String> getFile(@RequestParam("title")String title){
        return fileService.getFile(title);
    }

    @PostMapping("/v1/")
    public ResponseEntity<String> file(@RequestParam("file")MultipartFile file,
                                  @RequestParam("title")String title){
        return fileService.uploadFile(file,title);
    }

}
