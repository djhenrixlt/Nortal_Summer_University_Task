package com.assignment.su22.game.model;

import com.assignment.su22.game.enums.Symbols;
import lombok.*;

import java.util.Map;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BirdsMap {

   private Integer width;
   private Integer height;
   private Map<Symbols, Position> birdsPositions;
}
