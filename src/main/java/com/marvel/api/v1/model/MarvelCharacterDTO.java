package com.marvel.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.marvel.services.DateHelperService.DATE_FORMAT;

@Getter
@Setter
@Accessors(chain = true)
public class MarvelCharacterDTO {

    @ApiModelProperty(value = " The unique ID of the character resource.", required = true)
    private Long id;

    @ApiModelProperty(value = "This is the character's name", required = true)
    @NotBlank(message = "You must specify a name")
    private String name;

    @ApiModelProperty(value = "This is the character's discription", required = true)
    @NotBlank(message = "You must specify a discription")
    private String description;

    @ApiModelProperty(value = "This is the date when the character was changed or added, " +
            "and is generated automatically. Format date is 'yyyy-MM-dd hh:mm:ss'", required = false)
    @Length(max = 19, min = 19)
    private String modified;

    @ApiModelProperty(value = "This is the name of the mini image on the server", required = false)
    private String thumbnailImageName;

    @ApiModelProperty(value = "This is the name of the full image on the server", required = false)
    private String fullImageName;

    public MarvelCharacterDTO() {
        this.modified = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
