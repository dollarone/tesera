package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ZBlock extends AbstractBlock<Image> implements Block<Image> {

	public ZBlock() {

        blocks = new Image[][] {
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, } };

        try {
            SpriteSheet spriteSheet = new SpriteSheet( new Image("resources/1by1red.png"), 30, 30);
            blocks[0][1] = spriteSheet.getSprite(0, 0);
            blocks[1][1] = spriteSheet.getSprite(0, 0);
            blocks[1][2] = spriteSheet.getSprite(0, 0);
            blocks[2][2] = spriteSheet.getSprite(0, 0);
        }
        catch(SlickException e) {
            System.out.println(e.toString());}
	}

	@Override
	public Color getColor() {
		return Config.ZBLOCKCOLOUR;
	}
    /*
	@Override
	public Image[][] rotateClockWise() {
		if(blocks[2][1] != null) {
			return super.rotateCounterClockWise();
		}
		else {

            return super.rotateClockWise();
        }
	}

	@Override
	public Image[][] rotateCounterClockWise() {
		if(blocks[1][2] != null) {
			return super.rotateClockWise();
		}
		else return super.rotateCounterClockWise();
	}
*/
}
