package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class IBlock extends AbstractBlock<Image> implements Block<Image> {

	public IBlock() {

        blocks = new Image[][] {
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, } };

        try {

            SpriteSheet spriteSheet = new SpriteSheet( new Image("resources/iblock.png"), 30, 30);
            blocks[0][1] = spriteSheet.getSprite(0, 0);
            blocks[1][1] = spriteSheet.getSprite(1, 0);
            blocks[2][1] = spriteSheet.getSprite(2, 0);
            blocks[3][1] = spriteSheet.getSprite(3, 0);
        }
        catch(SlickException e) {
            System.out.println(e.toString());
        }

	}

	@Override
	public Color getColor() {
		return Config.IBLOCKCOLOUR;
	}
              /*
	@Override
	public Image[][] rotateClockWise() {
		if(null != blocks[3][1]) {
			return super.rotateCounterClockWise();
		}
		else return super.rotateClockWise();
	}

	@Override
	public Image[][] rotateCounterClockWise() {
		if(null != blocks[1][3]) {
			return super.rotateClockWise();
		}
		else return super.rotateCounterClockWise();
	}
                */
}
