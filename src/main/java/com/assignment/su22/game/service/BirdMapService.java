package com.assignment.su22.game.service;

import com.assignment.su22.game.enums.Symbols;
import com.assignment.su22.game.model.Position;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class BirdMapService {

    private ResourceService resourceService;
    private static final String PATH = "classpath:hummingbird_map.txt";

    // Get Birds locations from txt file
    public Map<Integer, List<String>> getBirdsMapsFromFile() {
        return resourceService.readFileToMap(PATH);
    }

    // set each bird coordinates on map
    public Map<Map<Integer, Symbols>, Position> setBirdPositions(Map<Integer, List<String>> birds, Symbols symbol1, Symbols symbol2) {
        int number = 1;
        Map<Map<Integer, Symbols>, Position> positions = new HashMap<>();
        for (int i = 0; i < birds.size(); i++) {
            for (int n = 0; n < birds.get(i).size(); n++) {
                if (birds.get(i).get(n).equals(symbol1.getValue())) {
                    positions.put(Map.of(number++,symbol1), new Position(i, n));
                }
                if (birds.get(i).get(n).equals(symbol2.getValue())) {
                    positions.put(Map.of(number++,symbol2), new Position(i, n));
                }
            }
        }
        return positions;
    }

    // TODO: move one step
    public Map<Integer, List<String>> moveOneStep(Map<Integer, List<String>> birds, List<Symbols> symbols,
                                                  Map<Map<Integer, Symbols>, Position> positions) {
        return Map.of();
    }

    //TODO: validate move first
    //TODO: validate move second
    // TODO: count iterations
}
