package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;

public class ZBlock extends AbstractBlock<Boolean> implements Block<Boolean> {

	public ZBlock() {
		blocks = new Boolean[][] {
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
	public Boolean[][] rotateClockWise() {
		if(blocks[2][1] == true) {
			return super.rotateCounterClockWise();
		}
		else return super.rotateClockWise();
	}

	@Override
	public Boolean[][] rotateCounterClockWise() {
		if(blocks[1][2] == true) {
			return super.rotateClockWise();
		}
		else return super.rotateCounterClockWise();
	}

}
