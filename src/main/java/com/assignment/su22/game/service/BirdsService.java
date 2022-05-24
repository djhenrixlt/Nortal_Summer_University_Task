package com.assignment.su22.game.service;

import com.assignment.su22.game.enums.Square;
import com.assignment.su22.game.enums.Symbols;
import com.assignment.su22.game.model.Bird;
import com.assignment.su22.game.model.BirdsMap;
import com.assignment.su22.game.model.Position;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class BirdsService {
    private final BirdsMap mapSize = BirdsMap.builder().height(2).width(3).build();
    private BirdMapService birdMapService;


    // Set map squares occupation types in 2D map
    public Map<Integer, List<Square>> getSquaresState() {
        return birdMapService.getBirdsMapsFromFile().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, line -> setSquaresStateInLine(line.getValue())));
    }
    // Bean for logs
    @Bean
    public Integer birdMovement() {

        Map<Integer, List<String>> birdsMap1 = birdMapService.getBirdsMapsFromFile();
        Map<Integer, List<String>> birdsMap = new HashMap<>(birdsMap1);
        Map<Integer, List<Square>> mapOfMovement = getSquaresState();

        log.info("GAME MAP:");
        birdsMap.forEach((key3, value3) -> log.info(String.valueOf(value3)));

        // Building Bird Objects
        Bird bird1 = buildBirds("BIRD_1", Symbols.ARROW, 0, 1);
        Bird bird2 = buildBirds("BIRD_2", Symbols.GREATER, 0, 2);
        Bird bird3 = buildBirds("BIRD_3", Symbols.ARROW, 1, 0);
        Bird bird4 = buildBirds("BIRD_4", Symbols.ARROW, 1, 2);
        Bird bird5 = buildBirds("BIRD_5", Symbols.GREATER, 2, 0);


        int i = 0;
        while (true) {
            log.info("STEP " + (i + 1));
            // BIRD_2 MOVE
            if (setIfStatement(bird2.getPosition().getY() == mapSize.getWidth(), mapOfMovement, 0, 0, Square.FREE, bird2)) {
                // set map is occupied
                mapOfMovement.get(0).set(bird2.getPosition().getY(), Square.FREE);
                mapOfMovement.get(0).set(0, Square.TAKEN);
                // set bird on map
                birdsMap.get(0).set(bird2.getPosition().getY(), Symbols.COMMA.getValue());
                birdsMap.get(0).set(0, Symbols.GREATER.getValue());
                // set birds position
                bird2.setPosition(new Position(0, bird2.getPosition().getY()));

            } else if (setIfStatement(bird2.getPosition().getY() < mapSize.getWidth(), mapOfMovement, 0, bird2.getPosition().getY() + 1, Square.FREE, bird2)) {
                // set map is occupied
                mapOfMovement.get(0).set(bird2.getPosition().getY(), Square.FREE);
                mapOfMovement.get(0).set(bird2.getPosition().getY() + 1, Square.TAKEN);
                // set bird on map
                birdsMap.get(0).set(bird2.getPosition().getY(), Symbols.COMMA.getValue());
                birdsMap.get(0).set(bird2.getPosition().getY() + 1, Symbols.GREATER.getValue());
                // set birds position
                bird2.setPosition(new Position(bird2.getPosition().getY(), bird2.getPosition().getY() + 1));
            } else {
                bird2.setMoving(false);
            }
            // BIRD_5 MOVE
            if (setIfStatement(bird5.getPosition().getY() < mapSize.getWidth(), mapOfMovement, 2, bird5.getPosition().getY() + 1, Square.FREE, bird5)) {
                // set map is occupied
                mapOfMovement.get(2).set(bird5.getPosition().getY(), Square.FREE);
                mapOfMovement.get(2).set(bird5.getPosition().getY(), Square.TAKEN);
                // set bird on map
                birdsMap.get(2).set(bird5.getPosition().getY(), Symbols.COMMA.getValue());
                birdsMap.get(2).set(bird5.getPosition().getY() + 1, Symbols.GREATER.getValue());
                // set birds position
                bird5.setPosition(new Position(bird5.getPosition().getY(), bird5.getPosition().getY() + 1));
            } else if (setIfStatement(bird5.getPosition().getY() < mapSize.getWidth(), mapOfMovement, 2, bird5.getPosition().getY() + 1, Square.EMPTY, bird5)) {
                //set map is occupied
                mapOfMovement.get(2).set(bird5.getPosition().getY(), Square.FREE);
                mapOfMovement.get(2).set(bird5.getPosition().getY() + 2, Square.TAKEN);
                // set bird on map
                birdsMap.get(2).set(bird5.getPosition().getY(), Symbols.COMMA.getValue());
                birdsMap.get(2).set(bird5.getPosition().getY() + 2, Symbols.GREATER.getValue());
                // set birds position
                bird5.setPosition(new Position(bird5.getPosition().getY(), bird5.getPosition().getY() + 2));
            } else {
                bird5.setMoving(false);
            }

            // BIRD_1 MOVE
            if (setIfStatement2(mapOfMovement, bird1, Square.EMPTY, 2, Square.EMPTY)) {
                bird1.setMoving(false);
            }

            // BIRD_3 Move
            if (setIfStatement(mapOfMovement.get(1).get(bird3.getPosition().getY()).equals(Square.TAKEN), mapOfMovement, 2, bird3.getPosition().getY(), Square.FREE, bird3)) {
                // set map is occupied
                mapOfMovement.get(1).set(bird3.getPosition().getY(), Square.FREE);
                mapOfMovement.get(2).set(bird3.getPosition().getY(), Square.TAKEN);
                // set bird on map
                birdsMap.get(1).set(bird3.getPosition().getY(), Symbols.COMMA.getValue());
                birdsMap.get(2).set(bird3.getPosition().getY(), Symbols.ARROW.getValue());
                // set birds position
                bird3.setPosition(new Position(bird3.getPosition().getY() + 1, bird3.getPosition().getY()));

            } else if (setIfStatement2(mapOfMovement, bird3, Square.FREE, 1, Square.TAKEN)) {
                bird3.setMoving(false);
            }

            // BIRD_4 MOVE
            if (setIfStatement2(mapOfMovement, bird4, Square.FREE, 1, Square.TAKEN)) {
                bird4.setMoving(false);
            }

            // Setting Log to console to check if works
            log.info("Map: ");
            birdsMap.forEach((key, value) -> log.info(String.valueOf(value)));

            if (Stream.of(bird2, bird5, bird3, bird4, bird1).anyMatch(bird -> !bird.isMoving())) {
                break;
            }
            i++;
        }
        log.info(("NUMBER OF ITTERATIONS: " + (i + 1)));
        return i;
    }

    private boolean setIfStatement2(Map<Integer, List<Square>> mapOfMovement, Bird bird, Square square1, int key, Square square2) {
        return mapOfMovement.get(1).get(bird.getPosition().getY() + 1).equals(square1)
                && mapOfMovement.get(key).get(bird.getPosition().getY() + 1).equals(square2);
    }

    private boolean setIfStatement(boolean bird2, Map<Integer, List<Square>> mapOfMovement, int key, int key1, Square free, Bird bird21) {
        return bird2
                && mapOfMovement.get(key).get(key1).equals(free)
                && bird21.isMoving();
    }

    // Building Bird
    private Bird buildBirds(String bird, Symbols symbol, int x, int y) {
        return Bird.builder().name(bird).symbol(symbol).moving(true).position(new Position(x, y)).build();
    }
    // Setting square State in Line
    private List<Square> setSquaresStateInLine(List<String> list) {
        List<Square> squares = new ArrayList<>();
        for (String s : list) {
            if (s.equals(Symbols.COMMA.getValue())) {
                squares.add(Square.FREE);
            } else if (s.equals(Symbols.HOLE.getValue())) {
                squares.add(Square.EMPTY);
            } else {
                squares.add(Square.TAKEN);
            }
        }
        return squares;
    }
}
