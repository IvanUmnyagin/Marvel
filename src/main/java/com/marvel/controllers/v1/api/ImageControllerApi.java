package com.marvel.controllers.v1.api;

import com.marvel.api.v1.model.ModelDataWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api("Image controller")
public interface ImageControllerApi {

    @ApiOperation(value = "Это позволит получить файл изображения по имени")
    byte[] getImageWithMediaType(
            @ApiParam(value = "Название изображения") String name
    ) throws IOException;

    @ApiOperation(value = "Это загрузит файл изображения на сервер и вернет модель с сгенерированным новым именем файла.")
    ModelDataWrapper<Object> uploadImage(MultipartFile file) throws IOException;

}
