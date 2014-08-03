package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class LBlock extends AbstractBlock<Image> implements Block<Image> {

	public LBlock() {

        blocks = new Image[][] {
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, } };

        try {
            SpriteSheet spriteSheet = new SpriteSheet( new Image("resources/lblock.png"), 30, 30);
            blocks[0][1] = spriteSheet.getSprite(0, 0);
            blocks[1][1] = spriteSheet.getSprite(1, 0);
            blocks[2][1] = spriteSheet.getSprite(2, 0);
            blocks[0][2] = spriteSheet.getSprite(2, 1);
        }
        catch(SlickException e) {}
	}

	@Override
	public Color getColor() {
		return Config.LBLOCKCOLOUR;
	}
	
}
