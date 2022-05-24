package com.assignment.su22.game.validator;

import com.assignment.su22.game.model.Bird;
import com.assignment.su22.game.model.BirdsMap;
import org.springframework.stereotype.Component;

@Component
public class BirdValidator implements Validator{
    @Override
    public boolean validate(Bird bird, BirdsMap birdsMap) {
        return false;
    }

    @Override
    public void setMapValidator(Integer map) {
        //TODO:  Implement in the Future Validator and make loook more simple
    }

    @Override
    public boolean validateMap(Integer map) {
        return false;
    }
}
