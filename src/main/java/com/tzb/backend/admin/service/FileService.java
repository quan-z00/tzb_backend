package com.tzb.backend.admin.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 29002
 * @since 2024/5/15
 */
public interface FileService {
    String uploadImage(MultipartFile file, HttpServletRequest request) throws IOException;

    Object loadImage(String fileName, HttpServletRequest request) throws IOException;

}
