package com.marvel.api.v1.converters;

import com.marvel.domain.ComicPrice;
import com.marvel.api.v1.model.ComicPriceDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ComicPriceToComicPriceDtoConverter implements Converter<ComicPrice, ComicPriceDTO> {

    @Nullable
    @Override
    public ComicPriceDTO convert(ComicPrice comicPrice) {
        if (comicPrice == null)
            return null;

        return new ComicPriceDTO()
                .setId(comicPrice.getId().toString())
                .setPrice(comicPrice.getPrice())
                .setType(comicPrice.getType());
    }
}
