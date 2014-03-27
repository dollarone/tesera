package com.myperfectgame.blocks;

import com.myperfectgame.Config;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class IBlock extends AbstractBlock<Image> implements Block<Image> {

	public IBlock() {
        //Image img; // = new Image(10,10); // empty image
        blocks = new Image[][] {
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, },
                { null, null, null, null, } };

        try {
            //Image img = new Image(10, 10);
            blocks[0][2] = new Image(10, 10);
            blocks[1][2] = new Image(10, 10);
            blocks[2][2] = new Image(10, 10);
            blocks[3][2] = new Image(10, 10);
                    //"resources/1BY4QUEST.PNG");
            //img.setFilter(Image.FILTER_NEAREST);
        }
        catch(SlickException e) {}

            //, Color.magenta



	}

	@Override
	public Color getColor() {
		return Config.IBLOCKCOLOUR;
	}
	
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

}
