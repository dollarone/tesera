package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class TBlock extends AbstractBlock<Image> implements Block<Image> {

	public TBlock() {

        blocks = new Image[][] {
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, } };

        try {
            SpriteSheet spriteSheet = new SpriteSheet( new Image("resources/tblock.png"), 30, 30);
            blocks[0][1] = spriteSheet.getSprite(0, 0);
            blocks[1][1] = spriteSheet.getSprite(1, 0);
            blocks[2][1] = spriteSheet.getSprite(2, 0);
            blocks[1][2] = spriteSheet.getSprite(1, 1); // top of the T
        }
        catch(SlickException e) {}
	}

	@Override
	public Color getColor() {
		return Config.TBLOCKCOLOUR;
	}
}
