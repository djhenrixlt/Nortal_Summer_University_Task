package com.assignment.su22.game.model;

import com.assignment.su22.game.enums.Symbols;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Bird {
   private String name;
   private Symbols symbol;
   private boolean moving;
   private Position position;


}
