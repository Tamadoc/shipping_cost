package se.lexicon.teri.shipping_cost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.teri.shipping_cost.entity.Box;
import se.lexicon.teri.shipping_cost.exception.ArgumentException;
import se.lexicon.teri.shipping_cost.repository.BoxRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BoxServiceImpl implements BoxService{

    BoxRepository boxRepository;

    @Autowired
    public void setBoxRepository(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    @Override
    public List<Box> getAll() {
        List<Box> boxes = new ArrayList<>();
        Iterator<Box> iterator = boxRepository.findAll().iterator();
        iterator.forEachRemaining(boxes::add);
        return boxes;
    }

    @Override
    public Box save(Box box) {
        if (box == null) throw new ArgumentException("Box should not be null");
        if (box.getId() != null) throw new ArgumentException("Box id should be null");

        return boxRepository.save(box);
    }
}
