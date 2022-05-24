package com.assignment.su22.game.service;


import com.assignment.su22.game.enums.Symbols;
import com.assignment.su22.game.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BirdMapServiceTest {

    private static final String PATH = "classpath:hummingbird_map.txt";

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private BirdMapService mapService;

    @Test
    void setBirdPositions(){
        Map<Integer, List<String>> birds = resourceService.readFileToMap(PATH);

        Map<Map<Integer, Symbols>, Position> positions = mapService.setBirdPositions(birds, Symbols.GREATER, Symbols.ARROW);

        assertEquals(5, positions.size());

    }

}
