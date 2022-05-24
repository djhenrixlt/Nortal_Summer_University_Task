package com.assignment.su22.game.service;


import com.assignment.su22.game.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ResourceServiceTest {

    private static final String PATH = "classpath:hummingbird_map.txt";

    @Autowired
    private ResourceService resourceService;

    @Test
    void readFileToMap(){
        Map<Integer, List<String>> getmap = resourceService.readFileToMap(PATH);
        assertEquals(3,3);
    }


}
