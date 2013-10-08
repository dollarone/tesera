package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;

public class SBlock extends AbstractBlock implements Block {

	public SBlock() {
		blocks = new boolean[][] { 
		{ false, false, true,  false, },
		{ false, true,  true,  false, },
		{ false, true,  false, false, },
		{ false, false, false, false, } };
	}

	@Override
	public Color getColor() {
		return Config.SBLOCKCOLOUR;
	}
	@Override
	public boolean[][] rotateClockWise() {
		if(blocks[3][1] == true) {
			return super.rotateCounterClockWise();
		}
		else return super.rotateClockWise();
	}

	@Override
	public boolean[][] rotateCounterClockWise() {
		if(blocks[1][3] == true) {
			return super.rotateClockWise();
		}
		else return super.rotateCounterClockWise();
	}

}
