package com.marvel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private String format;
    private Integer pageCount;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime modified;

    private String thumbnailImageName;
    private String fullImageName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comic", fetch = FetchType.LAZY)
    private List<ComicDate> dates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comic", fetch = FetchType.LAZY)
    private List<ComicPrice> prices = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "comic_marvel_characters",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "marvel_characters_id"))
    private Set<MarvelCharacter> marvelCharacters = new TreeSet<>();

    public void addComicDate(ComicDate comicDate) {
        if (dates == null) {
            dates = new ArrayList<>();
        }
        comicDate.setComic(this);
        dates.add(comicDate);
    }

    public void addComicPrice(ComicPrice comicPrice){
        if (prices == null) {
            prices = new ArrayList<>();
        }
        comicPrice.setComic(this);
        prices.add(comicPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comic)) return false;
        Comic comic = (Comic) o;
        return Objects.equals(getId(), comic.getId()) &&
                Objects.equals(getTitle(), comic.getTitle()) &&
                Objects.equals(getDescription(), comic.getDescription()) &&
                Objects.equals(getModified(), comic.getModified()) &&
                Objects.equals(getFormat(), comic.getFormat()) &&
                Objects.equals(getPageCount(), comic.getPageCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getModified(), getFormat(), getPageCount());
    }

    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", modified=" + modified +
                ", format='" + format + '\'' +
                ", pageCount=" + pageCount +
                ", dates=" + dates +
                ", prices=" + prices +
                ", characters=" + marvelCharacters +
                '}';
    }
}


