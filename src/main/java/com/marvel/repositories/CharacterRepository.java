package com.marvel.repositories;

import com.marvel.domain.MarvelCharacter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<MarvelCharacter, Long> {

    Optional<MarvelCharacter> findById(Long id);

    Optional<MarvelCharacter> findByName(String name);

    @Query("SELECT DISTINCT m FROM MarvelCharacter m " +
            "WHERE m.modified >= :modifiedFrom AND m.modified <= :modifiedTo ")
    Page<MarvelCharacter> findAllByModifiedDateAndOrdered(@Param("modifiedFrom") LocalDateTime modifiedFrom,
                                                          @Param("modifiedTo") LocalDateTime modifiedTo,
                                                          Pageable pageable);

    @Query("SELECT DISTINCT m FROM MarvelCharacter m LEFT JOIN m.comics c " +
            "WHERE c.id = :comicId AND m.modified >= :modifiedFrom AND m.modified <= :modifiedTo ")
    Page<MarvelCharacter> findAllByComicIdAndBetweenModifiedDateAndOrdered(@Param("comicId") Long id,
                                                                           @Param("modifiedFrom") LocalDateTime modifiedFrom,
                                                                           @Param("modifiedTo") LocalDateTime modifiedTo,
                                                                           Pageable pageable);

    @Query("SELECT DISTINCT m FROM MarvelCharacter m JOIN m.comics c JOIN c.dates d " +
            "WHERE d.date >= :dateFrom AND d.date <= :dateTo AND c.id = :comicId ")
    Page<MarvelCharacter> findAllByComicIdAndBetweenDatesAndOrdered(@Param("dateFrom") LocalDateTime dateFrom,
                                                                    @Param("dateTo") LocalDateTime dateTo,
                                                                    @Param("comicId") Long comicId,
                                                                    Pageable pageable);


}
