package com.myperfectgame;

import org.newdawn.slick.Color;

public class OBlock extends AbstractBlock implements Block {

	public OBlock() {
		blocks = new boolean[][] { 
		{ false, false, false, false, },
		{ false,  true, true,  false, },
		{ false,  true, true,  false, },
		{ false, false, false, false, } };
	}

	@Override
	public Color getColor() {
		return Config.OBLOCKCOLOUR;
	}
}
