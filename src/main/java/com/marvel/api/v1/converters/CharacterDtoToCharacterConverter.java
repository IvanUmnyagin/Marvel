package com.marvel.api.v1.converters;

import com.marvel.api.v1.model.MarvelCharacterDTO;
import com.marvel.domain.MarvelCharacter;
import com.marvel.services.DateHelperService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CharacterDtoToCharacterConverter implements Converter<MarvelCharacterDTO, MarvelCharacter>, DateHelperService {

    @Override
    @Nullable
    public MarvelCharacter convert(MarvelCharacterDTO characterDto) {
        if (characterDto == null)
            return null;

        final MarvelCharacter character = new MarvelCharacter();

        character
                .setId(characterDto.getId())
                .setName(characterDto.getName())
                .setDescription(characterDto.getDescription())
                .setModified(parseStringDateFormatToLocalDateTime(characterDto.getModified()))
                .setThumbnailImageName(characterDto.getThumbnailImageName())
                .setFullImageName(characterDto.getFullImageName());

        return character;
    }
}
