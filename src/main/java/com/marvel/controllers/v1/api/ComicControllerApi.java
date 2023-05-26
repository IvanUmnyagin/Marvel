package com.marvel.controllers.v1.api;

import com.marvel.api.v1.model.ComicDTO;
import com.marvel.api.v1.model.MarvelCharacterDTO;
import com.marvel.api.v1.model.ModelDataWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import static com.marvel.services.DateHelperService.DATE_FORMAT;

@Api("Comic controller")
public interface ComicControllerApi {

    @ApiOperation(value = "This will get a list of comics.")
    ModelDataWrapper<ComicDTO> getComics(
            @ApiParam(value = "Page number of the list of models, default 0")
                    String number_page,
            @ApiParam(value = "The number of models per page, default 15")
                    String page_size,
            @ApiParam(value = "Filter by title comic")
                    String title,
            @ApiParam(value = "Start date of the first date range event, format: " + DATE_FORMAT)
                    String date_start,
            @ApiParam(value = "End date of the first date range event, format: " + DATE_FORMAT)
                    String date_end,
            @ApiParam(value = "Order the result set by a field or fields. " +
                    "Add - to the value sort in descending order.", allowableValues = "title, -title, modified, -modified")
                    String order_by);

    @ApiOperation(value = "This will get comic by id.")
    ModelDataWrapper<ComicDTO> getComic(Long comicId);

    @ApiOperation(value = "This will get a list of Marvel characters by comic id.")
    ModelDataWrapper<MarvelCharacterDTO> getCharactersByComicId(
            @PathVariable String comicId,
            @ApiParam(value = "Page number of the list of models, default 0")
                    String number_page,
            @ApiParam(value = "The number of models per page, default 15")
                    String page_size,
            @ApiParam(value = "Order the result set by a field or fields. " +
                    "Add - to the value sort in descending order.", allowableValues = "name, -name, modified, -modified")
                    String order_by,
            @ApiParam(value = "Start date of the first date range event, format: " + DATE_FORMAT)
                    String date_start,
            @ApiParam(value = "End date of the first date range event, format: " + DATE_FORMAT)
                    String date_end);

    @ApiOperation(value = "This will add new comic.")
    ModelDataWrapper<ComicDTO> addNewComic(ComicDTO model, BindingResult bindingResult);

    @ApiOperation(value = "This will update comic by id.")
    ModelDataWrapper<ComicDTO> updateComic(ComicDTO model, Long comicId, BindingResult bindingResult);

    @ApiOperation(value = "This will delete comic by id.")
    ModelDataWrapper<ComicDTO> deleteComicById(Long comicId);
}

