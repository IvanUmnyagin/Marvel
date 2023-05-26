package com.marvel.api.v1.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QueryComicModel {

    private Long comicId;
    private Integer numberPage;
    private Integer pageSize;
    private String title;
    private String dateStart;
    private String dateEnd;
    private String orderBy;

}
