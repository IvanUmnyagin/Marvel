package com.marvel.controllers.v1;

import com.marvel.api.v1.model.ModelDataWrapper;
import com.marvel.controllers.v1.api.ImageControllerApi;
import com.marvel.services.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.marvel.controllers.v1.ImageController.BASE_URL;

@Api("Image controller")
@Slf4j
@RestController
@RequestMapping(BASE_URL)
public class ImageController implements ImageControllerApi {

    public static final String BASE_URL = "/v1/public/images";

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ApiOperation(value = "This will get image file by name")
    @GetMapping(value = "/get/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageWithMediaType(@PathVariable String name) throws IOException {

        return imageService.getImageByName(name);
    }

    @ApiOperation(value = "This will upload image file on server and return model with generated new file name.")
    @RequestMapping(method = RequestMethod.POST, value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelDataWrapper<Object> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        log.warn("check");

        ModelDataWrapper<Object> responseModel = new ModelDataWrapper<>();

        try {
            String fileName = imageService.uploadImage(file);
            responseModel.setCode(200).setStatus("all ok, file save with name: " + fileName);
        } catch (IOException e) {
            return responseModel.setCode(500).setStatus("image not upload : " + e.getMessage());
        }

        return responseModel;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IOException.class})
    public ModelDataWrapper<Object> handleNotFound(Exception exception) {
        log.error(exception.getMessage());

        ModelDataWrapper<Object> dataWrapper = new ModelDataWrapper<>();

        dataWrapper.setCode(HttpStatus.NOT_FOUND.value());
        dataWrapper.setStatus(exception.getMessage());

        return dataWrapper;
    }
}
