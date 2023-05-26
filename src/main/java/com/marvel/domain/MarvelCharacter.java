package com.marvel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.web.JsonPath;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class MarvelCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime modified;

    private String thumbnailImageName;
    private String fullImageName;

    @ManyToMany(mappedBy = "marvelCharacters",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<Comic> comics = new HashSet<>();

    public void setComic(Comic comic) {
        if (comic != null) {
            this.comics.add(comic);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarvelCharacter)) return false;
        MarvelCharacter character = (MarvelCharacter) o;
        return Objects.equals(id, character.id) &&
                Objects.equals(name, character.name) &&
                Objects.equals(description, character.description) &&
                Objects.equals(modified, character.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, modified);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", modified=" + modified;
    }
}
