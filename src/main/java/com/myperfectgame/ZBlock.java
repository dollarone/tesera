package com.myperfectgame;

import org.newdawn.slick.Color;

public class ZBlock extends AbstractBlock implements Block {

	public ZBlock() {
		blocks = new boolean[][] { 
		{ false, true,  false, false, },
		{ false, true,  true,  false, },
		{ false, false, true,  false, },
		{ false, false, false, false, } };
	}

	@Override
	public Color getColor() {
		return Config.ZBLOCKCOLOUR;
	}
	@Override
	public boolean[][] rotateClockWise() {
		if(blocks[2][1] == true) {
			return super.rotateCounterClockWise();
		}
		else return super.rotateClockWise();
	}

	@Override
	public boolean[][] rotateCounterClockWise() {
		if(blocks[1][2] == true) {
			return super.rotateClockWise();
		}
		else return super.rotateCounterClockWise();
	}

}
