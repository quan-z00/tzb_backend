package com.tzb.backend.admin.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.tzb.backend.admin.service.FileService;
import com.tzb.backend.common.annotation.ExcludeResultWrapper;
import com.tzb.backend.common.annotation.ResultWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author 29002
 * @since 2024/5/15
 */
@CrossOrigin
@RestController
@ResultWrapper
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;


    @SaIgnore
    @PutMapping("/file/img")
    public Object upload(@RequestAttribute MultipartFile file, HttpServletRequest request) throws IOException {
        return fileService.uploadImage(file, request);
    }

    @SaIgnore
    @PutMapping("/file/imgs")
    public Object upload(@RequestAttribute MultipartFile[] files, HttpServletRequest request) {
        //TODO 待优化
        return Arrays.stream(files).map(file -> {
            try {
                return fileService.uploadImage(file, request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @ExcludeResultWrapper
    @GetMapping("/img/{fileName}")
    public Object loadImage(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        return fileService.loadImage(fileName, request);
    }
}
