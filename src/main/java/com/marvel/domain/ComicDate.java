package com.marvel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class ComicDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Comic.class)
    @JoinColumn(name = "comic_id")
    private Comic comic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComicDate)) return false;
        ComicDate comicDate = (ComicDate) o;
        return Objects.equals(getId(), comicDate.getId()) &&
                Objects.equals(getType(), comicDate.getType()) &&
                Objects.equals(getDate(), comicDate.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDate());
    }
}
