package com.codegym.service;

import com.codegym.model.Promotion;
import java.util.Optional;


public interface IPromotionService {

    Iterable<Promotion> findAll();

    Optional<Promotion> findById(Long id);

    void save(Promotion promotion);

    void remove(Long id);
}
