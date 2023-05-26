package com.marvel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class ComicPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Comic.class)
    @JoinColumn(name = "comic_id")
    private Comic comic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComicPrice)) return false;
        ComicPrice that = (ComicPrice) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getPrice());
    }
}
