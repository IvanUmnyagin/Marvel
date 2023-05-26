package com.marvel.services;

import com.marvel.api.v1.model.*;

public interface ComicService {

    ModelDataContainer<ComicDTO> getComics(QueryComicModel model);

    ModelDataContainer<ComicDTO> getComicById(Long comicId);

    ModelDataContainer<MarvelCharacterDTO> getCharactersByModel(QueryCharacterModel model);

    ModelDataContainer<ComicDTO> saveComicDto(ComicDTO model);

    ModelDataContainer<ComicDTO> updateComicById(Long comicId, ComicDTO model);

    void deleteComicById(Long comicId);
}
