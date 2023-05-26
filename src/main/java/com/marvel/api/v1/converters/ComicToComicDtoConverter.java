package com.marvel.api.v1.converters;

import com.marvel.api.v1.model.MarvelCharacterDTO;
import com.marvel.api.v1.model.ComicDTO;
import com.marvel.api.v1.model.ComicDateDTO;
import com.marvel.api.v1.model.ComicPriceDTO;
import com.marvel.domain.Comic;
import com.marvel.services.DateHelperService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComicToComicDtoConverter implements Converter<Comic, ComicDTO>, DateHelperService {

    private final CharacterToCharacterDtoConverter characterToCharacterDtoConverter;
    private final ComicDateToComicDateDtoConverter comicDateToComicDateDtoConverter;
    private final ComicPriceToComicPriceDtoConverter comicPriceToComicPriceDtoConverter;

    public ComicToComicDtoConverter(CharacterToCharacterDtoConverter characterToCharacterDtoConverter,
                                    ComicDateToComicDateDtoConverter comicDateToComicDateDtoConverter,
                                    ComicPriceToComicPriceDtoConverter comicPriceToComicPriceDtoConverter) {
        this.characterToCharacterDtoConverter = characterToCharacterDtoConverter;
        this.comicDateToComicDateDtoConverter = comicDateToComicDateDtoConverter;
        this.comicPriceToComicPriceDtoConverter = comicPriceToComicPriceDtoConverter;
    }

    @Override
    @Nullable
    public ComicDTO convert(Comic comic) {
        if (comic == null)
            return null;

        final List<MarvelCharacterDTO> characters = comic.getMarvelCharacters()
                .stream()
                .map(characterToCharacterDtoConverter::convert)
                .collect(Collectors.toList());

        final List<ComicPriceDTO> pricesDto = comic.getPrices()
                .stream()
                .map(comicPriceToComicPriceDtoConverter::convert)
                .collect(Collectors.toList());

        final List<ComicDateDTO> datesDto = comic.getDates()
                .stream()
                .map(comicDateToComicDateDtoConverter::convert)
                .collect(Collectors.toList());

        final ComicDTO comicDto = new ComicDTO();

        comicDto
                .setId(comic.getId())
                .setTitle(comic.getTitle())
                .setDescription(comic.getDescription())
                .setModified(parseLocalDateTimeFormatToString(comic.getModified()))
                .setFormat(comic.getFormat())
                .setPageCount(comic.getPageCount())
                .setThumbnailImageName(comic.getThumbnailImageName())
                .setFullImageName(comic.getFullImageName())
                .setDates(datesDto)
                .setPrices(pricesDto)
                .setMarvelCharacters(characters);

        return comicDto;
    }
}
