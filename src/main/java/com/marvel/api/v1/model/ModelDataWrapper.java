package com.marvel.api.v1.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ModelDataWrapper<T> {

    @ApiModelProperty(value = "This is response code (200, 400, 404 etc)", required = false)
    private Integer code;

    @ApiModelProperty(value = "Description of the response status ('bad request parameters', 'image not found' etc)",
            required = false)
    private String status;

    @ApiModelProperty(value = "This is container for result data")
    private ModelDataContainer<T> data;

    public ModelDataWrapper() {
        this.code = 200;
        this.status = "all right";
    }
}
