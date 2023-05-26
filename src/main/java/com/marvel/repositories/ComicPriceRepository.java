package com.marvel.repositories;

import com.marvel.domain.ComicPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicPriceRepository extends JpaRepository<ComicPrice, Long> {
}
