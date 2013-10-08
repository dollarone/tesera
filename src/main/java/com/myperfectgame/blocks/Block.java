package com.myperfectgame.blocks;

import org.newdawn.slick.Color;

public interface Block {

	public boolean[][] rotateClockWise();
	public boolean[][] rotateCounterClockWise();	
	public boolean[][] getBlocks();
	public Color getColor();
	public boolean getBlock(int i, int j);
	
}
