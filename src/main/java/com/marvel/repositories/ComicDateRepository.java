package com.marvel.repositories;

import com.marvel.domain.ComicDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicDateRepository extends JpaRepository<ComicDate, Long> {
}
