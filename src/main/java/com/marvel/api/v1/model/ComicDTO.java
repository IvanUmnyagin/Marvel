package com.marvel.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.marvel.services.DateHelperService.DATE_FORMAT;
import static com.marvel.services.DateHelperService.DATE_FORMAT_LENGTH;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ComicDTO {

    private Long id;

    @ApiModelProperty(value = "Title of the comic", required = true)
    @NotBlank
    private String title;

    @ApiModelProperty(value = "Description of the comic", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(value = "This is the date when the comic was changed or added, " +
            "and is generated automatically. Format date is 'yyyy-MM-dd hh:mm:ss'", required = false)
    @NotBlank
    @Length(max = 19, min = 19)
    private String modified;

    @ApiModelProperty(value = "The publication format of the comic e.g. comic, hardcover, trade paperback",
            required = true)
    @NotBlank
    private String format;

    @ApiModelProperty(value = "The number of story pages in the comic.", required = true)
    @Min(1)
    @Max(1000)
    private Integer pageCount;

    @ApiModelProperty(value = "This is the name of the mini image on the server", required = false)
    private String thumbnailImageName;

    @ApiModelProperty(value = "This is the name of the full image on the server", required = false)
    private String fullImageName;

    @ApiModelProperty(value = "A list of key dates for this comic.", required = false)
    private List<ComicDateDTO> dates = new ArrayList<>();

    @ApiModelProperty(value = "A list of prices for this comic")
    private List<ComicPriceDTO> prices = new ArrayList<>();

    @ApiModelProperty(value = "These are the characters that are present in this comic", required = false)
    private List<MarvelCharacterDTO> marvelCharacters = new ArrayList<>();
}


