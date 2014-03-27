package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;

public class TBlock extends AbstractBlock<Boolean> implements Block<Boolean> {

	public TBlock() {
		blocks = new Boolean[][] {
		{ false, false, true, false, },
		{ false, true,  true, false, },
		{ false, false, true, false, },
		{ false, false, false,false, } };
	}

	@Override
	public Color getColor() {
		return Config.TBLOCKCOLOUR;
	}
}
