package com.marvel.services;

import com.marvel.api.v1.model.ModelDataWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String uploadImage(MultipartFile file) throws IOException;

    byte[] getImageByName(String name) throws IOException;
}
