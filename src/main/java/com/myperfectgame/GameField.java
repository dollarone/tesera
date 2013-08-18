package com.myperfectgame;

import org.newdawn.slick.Color;

import java.util.Random;

public class GameField {
	private final int GAMEFIELDHEIGHT = 22;
	private final int GAMEFIELDWIDTH = 10;
	private Color[][] blocks;

	public GameField() {
		blocks = new Color[GAMEFIELDWIDTH][GAMEFIELDHEIGHT];
		for(int i=0; i<GAMEFIELDWIDTH; i++) {
			for(int j=0; j<GAMEFIELDHEIGHT; j++) {
				blocks[i][j] = Config.NOBLOCKCOLOUR;;
			}
		}
	}

	public Color getBlock(int width, int height) {
		return blocks[width][height];
	}

	public Color[][] getBlocks() {
		return blocks;
	}
	
	public int getHeight() {
		return GAMEFIELDHEIGHT;
	}

	public int getWidth() {
		return GAMEFIELDWIDTH;
	}

	public void setBlock(int i, int j, Color col) {
		blocks[i][j] = col;
		
	}
	
	public Block addBlock() {
		Random ran = new Random();
		switch(ran.nextInt(7)) {
		case 0: return new IBlock(); 
		case 1: return new LBlock();
		case 2: return new JBlock();
		case 3: return new OBlock();
		case 4: return new ZBlock();
		case 5: return new TBlock();
		}
		return new SBlock();
		
	}
	
	public boolean checkMoveBlock(Block b, int offsetX, int offsetY) {
		boolean [][] newBlock = b.getBlocks();
		
		for(int i=0; i<newBlock.length; i++) {
			for(int j=0; j<newBlock.length; j++) {
				if(newBlock[i][j]) {
					if(i+offsetX < 0 || i+offsetX >= GAMEFIELDWIDTH ||
							j+offsetY < 0 || j+offsetY >= GAMEFIELDHEIGHT) {
						return false;
					}
							
					if(blocks[i+offsetX][j+offsetY] != Config.NOBLOCKCOLOUR) {
						return false;
					}
					//blocks[i+offsetX][j+offsetY] = b.getColor(); 
				}
			}
		}
		return true;
	}
	
	// this sucks. need to think harder how to deal with moving block VS gameField. Separate them, draw block separately until it merges.
	public void moveBlock(Block b, int offsetX, int offsetY) {
		boolean [][] newBlock = b.getBlocks();

		for(int i=0; i<newBlock.length; i++) {
			for(int j=0; j<newBlock.length; j++) {
				if(newBlock[i][j]) {
					
					if(j+offsetY-1 >= 0) {
						blocks[i+offsetX][j+offsetY-1] = Config.NOBLOCKCOLOUR;
					}
					//blocks[i+offsetX][j+offsetY] = b.getColor(); 
				}
			}
		}
	}

	public boolean glueBlock(Block b, int offsetX, int offsetY) {
		
		boolean [][] newBlock = b.getBlocks();
		
		for(int i=0; i<newBlock.length; i++) {
			for(int j=0; j<newBlock.length; j++) {
				if(newBlock[i][j]) {
					if(i+offsetX < 0 || i+offsetX >= GAMEFIELDWIDTH ||
							j+offsetY < 0 || j+offsetY >= GAMEFIELDHEIGHT) {
						return false;
					}
							
					if(blocks[i+offsetX][j+offsetY] != Config.NOBLOCKCOLOUR) {
						return false;
					}
					
					blocks[i+offsetX][j+offsetY] = b.getColor(); 
				}
			}
		}
		return true;
	}
	
	public boolean isLineFull(int lineY) {
		
		for(int x=0; x<GAMEFIELDWIDTH; x++) {
			if(blocks[x][lineY] == Config.NOBLOCKCOLOUR) {
				return false;
			}	
		}
		return true;
		
	}

    public boolean isLineEmpty(int lineY) {

        for(int x=0; x<GAMEFIELDWIDTH; x++) {
            if(blocks[x][lineY] != Config.NOBLOCKCOLOUR) {
                return false;
            }
        }
        return true;

    }

	public int clearLines(int bottomBlockY) {
		int totalClear = 0;
		for(int i=bottomBlockY; i<GAMEFIELDHEIGHT && i<=bottomBlockY+3 ; i++)
		if(isLineFull(i)) {
			totalClear++;
			for(int x=0; x<GAMEFIELDWIDTH; x++) {
				blocks[x][i] = Config.NOBLOCKCOLOUR;
				
			}
		}
		return totalClear;
	}

    public int moveDownNLines(int bottomY, int nLines) {
        int bottomLineCleared = -1;
        // find the bottom-furthest line and then shift all lines above nLines down
        for(int i=21; i>=nLines; i--)
            if(isLineEmpty(i)) {
                bottomLineCleared = i;
                for(int j=i; j>nLines; j--) {
                    for(int x=0; x<GAMEFIELDWIDTH; x++) {
                        blocks[x][j] = blocks[x][j-nLines];
                    }

                }
                return bottomLineCleared;
            }
        return bottomLineCleared;
    }

    // scoring: 1 = 100. 2 = 300. 3 = 500. 4 = 800
    public int score(int clearedLines) {
		
		switch(clearedLines) {
		case 1: return 100;
		case 2: return 300;
		case 3: return 500;
		case 4: return 800;
		}
		return 0;
	}

}
