package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;

public class JBlock extends AbstractBlock<Boolean> implements Block<Boolean> {

	public JBlock() {
		blocks = new Boolean[][] {
				{ false, true, false, false, },
				{ false, true, false, false, },
				{ false, true, true,  false, },
				{ false, false,false, false, } };
	}

	//@Override
	public Color getColor() {
		return Config.JBLOCKCOLOUR;
	}
}
