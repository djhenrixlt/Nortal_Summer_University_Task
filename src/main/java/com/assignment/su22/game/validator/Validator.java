package com.assignment.su22.game.validator;

import com.assignment.su22.game.model.Bird;
import com.assignment.su22.game.model.BirdsMap;

public interface Validator {

    boolean validate(Bird bird, BirdsMap birdsMap);

    void  setMapValidator(Integer map);

    boolean validateMap(Integer map);

}
