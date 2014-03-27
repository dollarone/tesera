package com.myperfectgame.blocks;

import org.newdawn.slick.Color;

public interface Block<T> {

	public T[][] rotateClockWise();
	public T[][] rotateCounterClockWise();
	public T[][] getBlocks();
	public Color getColor();
	public T getBlock(int i, int j);
	
}
