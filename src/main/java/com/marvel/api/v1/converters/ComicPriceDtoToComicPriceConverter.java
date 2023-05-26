package com.marvel.api.v1.converters;

import com.marvel.domain.ComicPrice;
import com.marvel.api.v1.model.ComicPriceDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ComicPriceDtoToComicPriceConverter implements Converter<ComicPriceDTO, ComicPrice> {

    @Nullable
    @Override
    public ComicPrice convert(ComicPriceDTO comicPriceDTO) {
        if (comicPriceDTO == null)
            return null;

        return new ComicPrice()
                .setId(Long.valueOf(comicPriceDTO.getId()))
                .setPrice(comicPriceDTO.getPrice())
                .setType(comicPriceDTO.getType());
    }
}
