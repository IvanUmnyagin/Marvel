package com.marvel.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ComicDateDTO {

    @ApiModelProperty(value = " The unique ID of the comic date resource.", required = true)
    private String id;

    @ApiModelProperty(value = "A description of the date (e.g. on sale date, FOC date)", required = true)
    @NotBlank
    private String type;

    @ApiModelProperty(value = "Format date is 'yyyy-MM-dd hh:mm:ss'", required = true)
    @NotBlank
    @Length(max = 19, min = 19)
    private String date;
}
