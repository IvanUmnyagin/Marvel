package com.marvel.controllers.v1.api;

import com.marvel.api.v1.model.ComicDTO;
import com.marvel.api.v1.model.MarvelCharacterDTO;
import com.marvel.api.v1.model.ModelDataWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;

import static com.marvel.services.DateHelperService.DATE_FORMAT;


@Api("MarvelCharacters Controller")
public interface CharacterControllerApi {

    @ApiOperation(value = "Это позволит получить список персонажей Marvel.")
    ModelDataWrapper<MarvelCharacterDTO> getCharacters(
            @ApiParam(value = "Уникальный идентификатор персонажа")
                    String comic_id,
            @ApiParam(value = "Номер страницы списка моделей, по умолчанию 0")
                    String number_page,
            @ApiParam(value = "Количество моделей на странице, по умолчанию 15")
                    String page_size,
            @ApiParam(value = "Упорядочите набор результатов по полю или полям. " +
                    "Добавить - к значению, отсортированному в порядке убывания.", allowableValues = "имя, -имя, измененное, -измененный")
                    String order_by,
            @ApiParam(value = "Дата изменения минимального значения или создания этого ресурса, формат:" + DATE_FORMAT)
                    String modified_start,
            @ApiParam(value = "Дата изменения или создания максимального значения этого ресурса, формат:" + DATE_FORMAT)
                    String modified_end);

    @ApiOperation(value = "Это позволит получить персонажа Marvel по идентификатору.")
    ModelDataWrapper<MarvelCharacterDTO> getCharacter(Long characterId);

    @ApiOperation(value = "При этом будет получен список комиксов Marvel по идентификатору персонажа.")
    ModelDataWrapper<ComicDTO> getComicsByCharacterId(Long characterId);

    @ApiOperation(value = "Это добавит нового персонажа Marvel.")
    ModelDataWrapper<MarvelCharacterDTO> addNewMarvelCharacter(MarvelCharacterDTO model,
                                                               BindingResult bindingResult);

    @ApiOperation(value = "Это обновит персонажа Marvel по идентификатору.")
    ModelDataWrapper<MarvelCharacterDTO> updateMarvelCharacter(MarvelCharacterDTO model,
                                                               Long characterId,
                                                               BindingResult bindingResult);

    @ApiOperation(value = "Это приведет к удалению персонажей Marvel по идентификатору.")
    ModelDataWrapper<MarvelCharacterDTO> updateMarvelCharacter(Long characterId);
}
