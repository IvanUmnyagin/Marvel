package com.marvel.api.v1.converters;

import com.marvel.domain.ComicDate;
import com.marvel.api.v1.model.ComicDateDTO;
import com.marvel.services.DateHelperService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ComicDateToComicDateDtoConverter implements Converter<ComicDate, ComicDateDTO>, DateHelperService {

    @Override
    @Nullable
    public ComicDateDTO convert(ComicDate comicDate) {
        if (comicDate == null)
            return null;

        return new ComicDateDTO()
                .setId(comicDate.getId().toString())
                .setDate(parseLocalDateTimeFormatToString(comicDate.getDate()))
                .setType(comicDate.getType());
    }
}
