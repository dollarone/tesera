package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;

public class OBlock extends AbstractBlock<Boolean> implements Block<Boolean> {

	public OBlock() {
		blocks = new Boolean[][] {
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
