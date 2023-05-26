package com.marvel.bootstrap;

import com.marvel.domain.Comic;
import com.marvel.domain.ComicDate;
import com.marvel.domain.ComicPrice;
import com.marvel.domain.MarvelCharacter;
import com.marvel.repositories.CharacterRepository;
import com.marvel.repositories.ComicDateRepository;
import com.marvel.repositories.ComicPriceRepository;
import com.marvel.repositories.ComicRepository;
import com.marvel.services.DateHelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent>, DateHelperService {       //данные начальной загрузки

    private final LocalDateTime nowDateTime = LocalDateTime.now();
    private final LocalDateTime prevDateTime = LocalDateTime.of(1995, 3, 25, 12, 55);
    private final LocalDateTime prevDateTimeTwo = LocalDateTime.of(2023, 4, 28, 10, 40);
    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;
    private final ComicDateRepository comicDateRepository;
    private final ComicPriceRepository comicPriceRepository;

//    @Value("${upload.path}")
//    private String uploadPath;

    public BootstrapData(CharacterRepository characterRepository,
                         ComicRepository comicRepository,
                         ComicDateRepository comicDateRepository,
                         ComicPriceRepository comicPriceRepository) {
        this.comicDateRepository = comicDateRepository;
        this.comicPriceRepository = comicPriceRepository;
        this.characterRepository = characterRepository;
        this.comicRepository = comicRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.warn("start loading data");
        if (comicRepository.count() == 0)
            loadComics();
        log.warn("load data success");
/*
        File uploadDir = new File(uploadPath);
        uploadDir.mkdir();
        log.warn(uploadPath);*/
    }

    private List<MarvelCharacter> getCharacters() {
        List<MarvelCharacter> characters = new ArrayList<>();

        characters.add(new MarvelCharacter()
                .setName("Groot")
                .setDescription("super strong tree")
                .setModified(nowDateTime)
                .setThumbnailImageName("Image1.jpg")
                .setFullImageName("Image2.jpg"));

        characters.add(new MarvelCharacter()
                .setName("Thanos")
                .setDescription("super strong villain")
                .setModified(nowDateTime)
                .setThumbnailImageName("Image43jpg")
                .setFullImageName("Image63jpg"));

        characters.add(new MarvelCharacter()
                .setName("Red Hulk")
                .setDescription("red NEGODIAY")
                .setModified(nowDateTime)
                .setThumbnailImageName("Image21jpg")
                .setFullImageName("Image25jpg"));

        characters.add(new MarvelCharacter()
                .setName("Hulk")
                .setDescription("green hero")
                .setModified(nowDateTime)
                .setThumbnailImageName("Image73jpg")
                .setFullImageName("Image23412jpg"));

        return characters;
    }

    public void loadComics() {      //загрузка комиксов

        List<MarvelCharacter> listCharacters = characterRepository.saveAll(getCharacters());

        Set<MarvelCharacter> charactersTwo = new HashSet<MarvelCharacter>() {{
            add(listCharacters.get(0));
            add(listCharacters.get(1));
            add(listCharacters.get(2));
        }};
        Set<MarvelCharacter> charactersOne = new HashSet<MarvelCharacter>() {{
            add(listCharacters.get(2));
            add(listCharacters.get(3));
        }};


        Comic newComic = new Comic()
                .setTitle("this is new comic")
                .setDescription("just nice comic")
                .setModified(prevDateTime)
                .setFormat("A4")
                .setPageCount(30)
                .setThumbnailImageName("Image23123sdfgjpg")
                .setFullImageName("Image23jpg123123")
                .setMarvelCharacters(charactersOne);

        newComic.addComicDate(new ComicDate().setType("hard").setDate(prevDateTime));
        newComic.addComicDate(new ComicDate().setType("very izi").setDate(prevDateTimeTwo));
        newComic.addComicDate(new ComicDate().setType("very hm").setDate(LocalDateTime.now()));
        newComic.addComicPrice(new ComicPrice().setType("1 price").setPrice(BigDecimal.valueOf(33.5)));
        newComic.addComicPrice(new ComicPrice().setType("2 price").setPrice(BigDecimal.valueOf(33.5)));
        newComic.addComicPrice(new ComicPrice().setType("print price").setPrice(BigDecimal.valueOf(15)));

        Comic oldComic = new Comic()
                .setTitle("this is old comic")
                .setDescription("normal comic")
                .setModified(prevDateTime)
                .setFormat("A3")
                .setPageCount(30)
                .setThumbnailImageName("Image12312323jpg")
                .setFullImageName("Image2zfgasfg3jpg")
                .setMarvelCharacters(charactersTwo);

        oldComic.addComicDate(new ComicDate().setType("hard").setDate(prevDateTime));
        oldComic.addComicDate(new ComicDate().setType("very ok").setDate(prevDateTimeTwo));
        oldComic.addComicDate(new ComicDate().setType("very easy").setDate(LocalDateTime.now()));

        oldComic.addComicPrice(new ComicPrice().setType("3qwe price").setPrice(BigDecimal.valueOf(3.5)));
        oldComic.addComicPrice(new ComicPrice().setType("5as price").setPrice(BigDecimal.valueOf(31.5)));
        oldComic.addComicPrice(new ComicPrice().setType("print price").setPrice(BigDecimal.valueOf(1)));

        comicRepository.save(oldComic);
        comicRepository.save(newComic);

        //comicPriceRepository.saveAll(prices);
        //comicDateRepository.saveAll(dates);


        comicRepository.flush();
        }
        }
