package com.marvel.api.v1.converters;

import com.marvel.api.v1.model.MarvelCharacterDTO;
import com.marvel.domain.MarvelCharacter;
import com.marvel.services.DateHelperService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CharacterToCharacterDtoConverter implements Converter<MarvelCharacter, MarvelCharacterDTO>, DateHelperService {

    @Override
    @Nullable
    public MarvelCharacterDTO convert(MarvelCharacter character) {
        if (character == null)
            return null;

        final MarvelCharacterDTO characterDTO = new MarvelCharacterDTO();

        characterDTO
                .setId(character.getId())
                .setName(character.getName())
                .setDescription(character.getDescription())
                .setModified(parseLocalDateTimeFormatToString(character.getModified()))
                .setThumbnailImageName(character.getThumbnailImageName())
                .setFullImageName(character.getFullImageName());

        return characterDTO;
    }
}
