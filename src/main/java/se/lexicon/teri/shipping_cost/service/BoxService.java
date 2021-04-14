package se.lexicon.teri.shipping_cost.service;

import se.lexicon.teri.shipping_cost.entity.Box;

import java.util.List;

public interface BoxService {

    List<Box> getAll();
    Box save(Box box);
}
