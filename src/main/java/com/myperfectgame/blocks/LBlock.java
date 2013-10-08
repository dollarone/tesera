package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;

public class LBlock extends AbstractBlock implements Block {

	public LBlock() {
		blocks = new boolean[][] { 
				{ false, false, true, false, },
				{ false, false, true, false, },
				{ false, true,  true, false, },
				{ false, false,false, false, } };
	}

	@Override
	public Color getColor() {
		return Config.LBLOCKCOLOUR;
	}
	
}
