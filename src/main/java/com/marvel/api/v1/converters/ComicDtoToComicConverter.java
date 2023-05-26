package com.marvel.api.v1.converters;

import com.marvel.api.v1.model.ComicDTO;
import com.marvel.domain.MarvelCharacter;
import com.marvel.domain.Comic;
import com.marvel.domain.ComicDate;
import com.marvel.domain.ComicPrice;
import com.marvel.services.DateHelperService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class ComicDtoToComicConverter implements Converter<ComicDTO, Comic>, DateHelperService {

    private final CharacterDtoToCharacterConverter characterDtoToCharacterConverter;
    private final ComicDateDtoToComicDateConverter comicDateDtoToComicDateConverter;
    private final ComicPriceDtoToComicPriceConverter comicPriceDtoToComicPriceConverter;

    public ComicDtoToComicConverter(CharacterDtoToCharacterConverter characterDtoToCharacterConverter,
                                    ComicDateDtoToComicDateConverter comicDateDtoToComicDateConverter,
                                    ComicPriceDtoToComicPriceConverter comicPriceDtoToComicPriceConverter) {
        this.characterDtoToCharacterConverter = characterDtoToCharacterConverter;
        this.comicDateDtoToComicDateConverter = comicDateDtoToComicDateConverter;
        this.comicPriceDtoToComicPriceConverter = comicPriceDtoToComicPriceConverter;
    }

    @Override
    @Nullable
    public Comic convert(ComicDTO comicDto) {
        if (comicDto == null)
            return null;

        final Set<MarvelCharacter> characters = comicDto.getMarvelCharacters()
                .stream()
                .map(characterDtoToCharacterConverter::convert)
                .collect(Collectors.toSet());

        final List<ComicPrice> prices = comicDto.getPrices()
                .stream()
                .map(comicPriceDtoToComicPriceConverter::convert)
                .collect(Collectors.toList());

        final List<ComicDate> dates = comicDto.getDates()
                .stream()
                .map(comicDateDtoToComicDateConverter::convert)
                .collect(Collectors.toList());

        final Comic comic = new Comic();

        comic
                .setId(comicDto.getId())
                .setTitle(comicDto.getTitle())
                .setDescription(comicDto.getDescription())
                .setModified(parseStringDateFormatToLocalDateTime(comicDto.getModified()))
                .setFormat(comicDto.getFormat())
                .setPageCount(comicDto.getPageCount())
                .setThumbnailImageName(comicDto.getThumbnailImageName())
                .setFullImageName(comicDto.getFullImageName())
                .setDates(dates)
                .setPrices(prices)
                .setMarvelCharacters(characters);

        return comic;
    }
}
