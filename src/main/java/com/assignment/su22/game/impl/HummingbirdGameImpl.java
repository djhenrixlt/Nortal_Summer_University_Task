package com.assignment.su22.game.impl;

import java.io.*;


import com.assignment.su22.game.service.BirdsService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.assignment.su22.game.HummingbirdGame;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HummingbirdGameImpl implements HummingbirdGame {

	private BirdsService birdsService;

	@Override
	public int hummingbirdGame(Resource resource) throws IOException {

		return birdsService.birdMovement();
	}
}
