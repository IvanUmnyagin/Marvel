package com.marvel.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ModelDataContainer<T> {

    @ApiModelProperty(value = "This is the page number of the list of models, default 0", required = false)
    private Integer numberPage;

    @ApiModelProperty(value = "This is the number of models per page, default 15", required = false)
    private Integer pageSize;

    @ApiModelProperty(value = "This is a model tag, number of models in the result", required = false)
    private Integer count;

    @ApiModelProperty(value = "This is the list of models, or result", required = false)
    private List<T> results = new ArrayList<>();

}
