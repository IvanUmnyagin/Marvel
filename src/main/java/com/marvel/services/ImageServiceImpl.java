package com.marvel.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    public String uploadImage(MultipartFile file) throws IOException {
        if (file != null) {
            log.info("creating path");

            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            File uploadDir = new File(uploadPath + fileName);
            uploadDir.getParentFile().mkdirs();

            log.warn(uploadDir.getAbsolutePath());

            if (uploadDir.createNewFile()) {
                log.info("file path created");

                FileOutputStream fileOutputStream = new FileOutputStream(uploadDir);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
            } else
                throw new IOException("file not created, what is it??");

            return fileName;
        } else
            throw new IOException("file is NULL");
    }

    @Override
    public byte[] getImageByName(String name) throws IOException {
        File imageFile = new File(uploadPath + name);
        FileInputStream fileInputStream = new FileInputStream(imageFile);

        //InputStream in = getClass().getResourceAsStream(uploadPath + name);
        return IOUtils.toByteArray(fileInputStream);
    }
}
