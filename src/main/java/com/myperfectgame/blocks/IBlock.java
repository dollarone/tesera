package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;

public class IBlock extends AbstractBlock implements Block {

	public IBlock() {
		blocks = new boolean[][] { 
		{ false, false, true, false, },
		{ false, false, true, false, },
		{ false, false, true, false, },
		{ false, false, true, false, } };
	}

	@Override
	public Color getColor() {
		return Config.IBLOCKCOLOUR;
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
