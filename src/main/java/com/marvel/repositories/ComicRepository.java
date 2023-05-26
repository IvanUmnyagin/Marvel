package com.marvel.repositories;

import com.marvel.domain.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ComicRepository extends JpaRepository<Comic, Long> {

    Optional<Comic> findById(Long id);

    @Query("SELECT DISTINCT c FROM Comic c JOIN c.dates d " +
            "WHERE d.date >= :dateFrom AND d.date <= :dateTo AND c.title = :title ")
    Page<Comic> findAllByTitleAndBetweenDatesAndOrdered(@Param("dateFrom") LocalDateTime dateFrom,
                                                        @Param("dateTo") LocalDateTime dateTo,
                                                        @Param("title") String title,
                                                        Pageable pageable);

    @Query("SELECT DISTINCT c FROM Comic c JOIN c.dates d " +
            "WHERE d.date >= :dateFrom AND d.date <= :dateTo ")
    Page<Comic> findAllBetweenDatesAndOrdered(@Param("dateFrom") LocalDateTime dateFrom,
                                              @Param("dateTo") LocalDateTime dateTo,
                                              Pageable pageable);

    /*
    @Query("SELECT m FROM comic c JOIN c.dates d LEFT JOIN c.marvelCharacters m " +
            "WHERE d.date >= :dateFrom AND d.date <= :dateTo AND c.id = :comicId ")
    Page<MarvelCharacter> findAllByComicIdAndBetweenDatesAndOrdered(@Param("dateFrom") LocalDateTime dateFrom,
                                                                    @Param("dateTo") LocalDateTime dateTo,
                                                                    @Param("comicId") Long comicId,
                                                                    Pageable pageable);
    */
}

