package se.lexicon.teri.shipping_cost.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.teri.shipping_cost.entity.Box;

public interface BoxRepository extends CrudRepository<Box, String> {

}
