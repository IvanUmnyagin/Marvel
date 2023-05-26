package com.marvel.services;

import com.marvel.api.v1.model.ComicDTO;
import com.marvel.api.v1.model.MarvelCharacterDTO;
import com.marvel.api.v1.model.QueryCharacterModel;
import com.marvel.api.v1.model.ModelDataContainer;

public interface CharacterService {

    ModelDataContainer<MarvelCharacterDTO> getCharactersByModel(QueryCharacterModel model);

    ModelDataContainer<MarvelCharacterDTO> getCharacterById(Long id);

    ModelDataContainer<ComicDTO> getComicsByCharacterId(Long characterId);

    ModelDataContainer<MarvelCharacterDTO> saveMarvelCharacterDto(MarvelCharacterDTO model);

    ModelDataContainer<MarvelCharacterDTO> updateMarvelCharacterById(Long characterId, MarvelCharacterDTO model);

    void deleteCharacterById(Long characterId);
}
