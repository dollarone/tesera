package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class IBlock extends AbstractBlock<Image> implements Block<Image> {

	public IBlock() {
        //Image img; // = new Image(10,10); // empty image
        blocks = new Image[][] {
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, } };

        try {

            SpriteSheet spriteSheet = new SpriteSheet( new Image("resources/1BY4QUEST.PNG"), 30, 30);
            blocks[2][0] = spriteSheet.getSprite(0, 0);
            blocks[2][1] = spriteSheet.getSprite(0, 1);
            blocks[2][2] = spriteSheet.getSprite(0, 2);
            blocks[2][3] = spriteSheet.getSprite(0, 3);
        }
        catch(SlickException e) {
            System.out.println(e.toString());
        }

            //, Color.magenta



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
