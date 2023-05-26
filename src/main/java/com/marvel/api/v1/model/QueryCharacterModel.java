package com.marvel.api.v1.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QueryCharacterModel {

    private Integer numberPage;
    private Integer pageSize;
    private Long comicId;
    private String modifiedDateStart;
    private String modifiedDateEnd;
    private String orderBy;

}
