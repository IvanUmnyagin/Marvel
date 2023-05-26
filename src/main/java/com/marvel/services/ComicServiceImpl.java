package com.marvel.services;

import com.marvel.api.v1.converters.CharacterToCharacterDtoConverter;
import com.marvel.api.v1.converters.ComicDtoToComicConverter;
import com.marvel.api.v1.converters.ComicToComicDtoConverter;
import com.marvel.api.v1.model.*;
import com.marvel.domain.Comic;
import com.marvel.domain.MarvelCharacter;
import com.marvel.exceptions.NotValidParametersException;
import com.marvel.exceptions.CharacterNotFoundException;
import com.marvel.exceptions.ComicNotFoundException;
import com.marvel.repositories.CharacterRepository;
import com.marvel.repositories.ComicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComicServiceImpl implements ComicService, DateHelperService {

    private final ComicRepository comicRepository;
    private final ComicToComicDtoConverter comicToDtoConverter;
    private final CharacterRepository characterRepository;
    private final CharacterToCharacterDtoConverter characterToDtoConverter;
    private final ComicDtoToComicConverter dtoToComicConverter;

    public ComicServiceImpl(ComicRepository comicRepository,
                            ComicToComicDtoConverter comicToDtoConverter,
                            CharacterRepository characterRepository,
                            CharacterToCharacterDtoConverter characterToDtoConverter,
                            ComicDtoToComicConverter dtoToComicConverter) {
        this.comicRepository = comicRepository;
        this.comicToDtoConverter = comicToDtoConverter;
        this.characterRepository = characterRepository;
        this.characterToDtoConverter = characterToDtoConverter;
        this.dtoToComicConverter = dtoToComicConverter;
    }

    private Page<Comic> getComicPageByModel(QueryComicModel model) {
        Sort sort;

        if (model.getOrderBy().equals("title"))
            sort = Sort.by("title").ascending();
        else if (model.getOrderBy().equals("-title"))
            sort = Sort.by("title").descending();
        else if (model.getOrderBy().equals("modified"))
            sort = Sort.by("modified").ascending();
        else
            sort = Sort.by("modified").descending();

        Pageable pageable = PageRequest.of(model.getNumberPage(), model.getPageSize(), sort);

        Page<Comic> comics;
        try {
            if (model.getTitle() == null) {
                comics = comicRepository.findAllBetweenDatesAndOrdered(
                        parseStringDateFormatToLocalDateTime(model.getDateStart()),
                        parseStringDateFormatToLocalDateTime(model.getDateEnd()),
                        pageable);
            } else {
                comics = comicRepository.findAllByTitleAndBetweenDatesAndOrdered(
                        parseStringDateFormatToLocalDateTime(model.getDateStart()),
                        parseStringDateFormatToLocalDateTime(model.getDateEnd()),
                        model.getTitle(),
                        pageable);
            }
        } catch (DateTimeParseException e) {
            throw new NotValidParametersException("Bad parameter, the date must be in the format: " + DATE_FORMAT);
        }

        return comics;
    }

    private Page<MarvelCharacter> getCharactersPageByModel(QueryCharacterModel model) {

        Sort sort;

        if (model.getOrderBy().equals("name"))
            sort = Sort.by("name").ascending();
        else if (model.getOrderBy().equals("-name"))
            sort = Sort.by("name").descending();
        else if (model.getOrderBy().equals("modified"))
            sort = Sort.by("modified").ascending();
        else
            sort = Sort.by("modified").descending();

        Pageable pageable = PageRequest.of(model.getNumberPage(), model.getPageSize(), sort);

        Page<MarvelCharacter> pageCharacters;
        try {
            pageCharacters = characterRepository.findAllByComicIdAndBetweenDatesAndOrdered(
                    parseStringDateFormatToLocalDateTime(model.getModifiedDateStart()),
                    parseStringDateFormatToLocalDateTime(model.getModifiedDateEnd()),
                    model.getComicId(),
                    pageable);

            pageCharacters.stream().peek(System.out::println);
        } catch (DateTimeParseException e) {
            throw new NotValidParametersException("Bad parameter, the date must be in the format: " + DATE_FORMAT);
        }

        return pageCharacters;
    }

    @Override
    @Transactional
    public ModelDataContainer<MarvelCharacterDTO> getCharactersByModel(QueryCharacterModel model) {
        try {
            List<MarvelCharacterDTO> charactersDto = getCharactersPageByModel(model)
                    .stream()
                    .map(characterToDtoConverter::convert)
                    .collect(Collectors.toList());

            if (charactersDto.isEmpty())
                throw new CharacterNotFoundException("Characters not found");

            return new ModelDataContainer<MarvelCharacterDTO>()
                    .setResults(charactersDto)
                    .setCount(charactersDto.size())
                    .setNumberPage(model.getNumberPage())
                    .setPageSize(model.getPageSize());
        } catch (Exception e) {
            throw new CharacterNotFoundException("Characters not found");
        }
    }

    @Override
    @Transactional
    public ModelDataContainer<ComicDTO> getComics(QueryComicModel model) {
        try {
            List<ComicDTO> comics = getComicPageByModel(model)
                    .stream()
                    .map(comicToDtoConverter::convert)
                    .collect(Collectors.toList());

            if (comics.size() == 0)
                throw new ComicNotFoundException("Comics not found");

            ModelDataContainer<ComicDTO> responseModel = new ModelDataContainer<>();
            responseModel
                    .setResults(comics)
                    .setCount(comics.size())
                    .setNumberPage(model.getNumberPage())
                    .setPageSize(model.getPageSize());;

            return responseModel;
        } catch (Exception e) {
            throw new ComicNotFoundException("Comics not found");
        }
    }

    @Override
    @Transactional
    public ModelDataContainer<ComicDTO> getComicById(Long comicId) {
        Optional<Comic> optionalComic = comicRepository.findById(comicId);

        if (optionalComic.isPresent()) {
            ModelDataContainer<ComicDTO> responseModel = new ModelDataContainer<>();
            responseModel.getResults().add(comicToDtoConverter.convert(optionalComic.get()));

            return responseModel;
        } else
            throw new ComicNotFoundException("Comic with id:" + comicId + " not found");
    }

    @Override
    @Transactional
    public ModelDataContainer<ComicDTO> saveComicDto(ComicDTO model) {

        try {
            if (model == null)
                throw new IllegalArgumentException();
            Comic result = comicRepository
                    .saveAndFlush(dtoToComicConverter.convert(model).setModified(LocalDateTime.now()));
            ModelDataContainer<ComicDTO> responseModel = new ModelDataContainer<>();
            responseModel.getResults().add(comicToDtoConverter.convert(result));

            return responseModel;
        } catch (IllegalArgumentException e) {
            throw new NotValidParametersException("Not valid data for Comic");
        }
    }

    @Override
    public ModelDataContainer<ComicDTO> updateComicById(Long comicId, ComicDTO model) {
        model.setId(comicId);

        return this.saveComicDto(model);
    }

    @Override
    public void deleteComicById(Long comicId) {
        Optional<Comic> optionalComic = comicRepository.findById(comicId);

        if (optionalComic.isPresent()) {
            comicRepository.delete(optionalComic.get());
        } else
            throw new CharacterNotFoundException("comic with id: " + comicId + " not found");
    }
}
