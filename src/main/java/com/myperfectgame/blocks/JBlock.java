package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class JBlock extends AbstractBlock<Image> implements Block<Image> {

	public JBlock() {
        blocks = new Image[][] {
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, } };

        try {
            SpriteSheet spriteSheet = new SpriteSheet( new Image("resources/1by1blue.png"), 30, 30);
            blocks[0][2] = spriteSheet.getSprite(0, 0);
            blocks[1][2] = spriteSheet.getSprite(0, 0);
            blocks[2][2] = spriteSheet.getSprite(0, 0);
            blocks[2][1] = spriteSheet.getSprite(0, 0);
        }
        catch(SlickException e) {}

    }

	//@Override
	public Color getColor() {
		return Config.JBLOCKCOLOUR;
	}
}
