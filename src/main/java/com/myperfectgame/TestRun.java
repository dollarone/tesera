package com.myperfectgame;

import org.newdawn.slick.*;

import java.util.LinkedList;
import java.util.Random;

public class TestRun extends BasicGame {
	
	GameField gameField;
	private java.util.Random rand;
	private final static int ONE_FRAME = 16; // 1000/60 ~= 16
	private final static int FRAMES_PER_STEP = 48;
	
	private final static int RES_WIDTH = 600;
	private final static int RES_HEIGHT = 800;
	private final int BLOCKSIZE = 30;
	private final int BLOCK_START_X = 150;
	private final int BLOCK_START_Y = 40;
	
	private Block block;
	private int offsetX;
	private int offsetY;
	
	private int deltaCounter;
    private int inputDelta;
	
	private int frameCount;
	private int clearedLines;
    private int holdingDown;

    // TODO : better stats
	private int totalClearedLines;
	private int totalBlocks;
	private int totalScore;
	
	private LinkedList <Block> upcomingBlocks;
	
	private boolean updateBlocks;
	
	public TestRun() {
		super("SimpleTest");
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		gameField = new GameField();
		rand = new Random(2);
		updateBlocks = true;


        holdingDown = 1;
		frameCount = 0;
		deltaCounter = 500;
		inputDelta = 0;
		upcomingBlocks = new LinkedList<Block>();
		
		clearedLines = 0;
		totalClearedLines = 0;
		totalBlocks = 0;
		totalScore = 0;
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		deltaCounter -= delta;
        inputDelta -= delta;
        
        if(upcomingBlocks.isEmpty()) {
        	// generate new set of blocks
        	upcomingBlocks.add(new IBlock());
        	upcomingBlocks.add(rand.nextInt(2), new LBlock());
        	upcomingBlocks.add(rand.nextInt(3), new JBlock());
        	upcomingBlocks.add(rand.nextInt(4), new OBlock());
        	upcomingBlocks.add(rand.nextInt(5), new ZBlock());
        	upcomingBlocks.add(rand.nextInt(6), new TBlock());
        	upcomingBlocks.add(rand.nextInt(7), new SBlock());
        	for(Block foo : upcomingBlocks) {
        		System.out.println(foo.getClass() + ", " );
        	}
        	System.out.println("-------------" );
        }
        
		if(null == block) { //if new block needed

			block = upcomingBlocks.remove();

			offsetX = 3;
			offsetY = 0;
			
			if(gameField.checkMoveBlock(block, offsetX, offsetY) == false) {
				//gameOver();
				System.out.println("Game over");
				System.out.println("Lines cleared: " + totalClearedLines);
				System.out.println("Blocks placed: " + totalBlocks);
				System.out.println("Score: " + totalScore);
				System.exit(1);
			}

		}
		
		// only allow moves every so often
		if(inputDelta < 0) {
			Input input = container.getInput();

            if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
                if(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {
                    gameField.moveBlock(block, offsetX, offsetY++);
                    inputDelta = 50;
                    holdingDown++;
                 }
            }
			else {
                if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
				    if(gameField.checkMoveBlock(block, offsetX+1, offsetY)) {
					    gameField.moveBlock(block, offsetX++, offsetY);
					    inputDelta = 100;
                        holdingDown=1;
                    }
				}
				else if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
                    if(gameField.checkMoveBlock(block, offsetX-1, offsetY)) {
                        gameField.moveBlock(block, offsetX--, offsetY);
                        inputDelta = 100;
                        holdingDown=1;
                    }

                }
				else if(input.isKeyDown(Input.KEY_NEXT) || input.isKeyDown(Input.KEY_R)) {
			    	while(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {
					    gameField.moveBlock(block, offsetX, offsetY++);
				    	inputDelta = 250;
                        holdingDown++;
                    }
				}
				else if(input.isKeyDown(Input.KEY_UP)) {
			    	block.rotateClockWise();
				    if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
					    gameField.moveBlock(block, offsetX, offsetY);
					    inputDelta = 150;
                        holdingDown=1;
				    }
                	else {
					    block.rotateCounterClockWise();
					    block.rotateCounterClockWise();
					    if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
						    gameField.moveBlock(block, offsetX, offsetY);
    						inputDelta = 150;
                            holdingDown=1;
	    				}
		    			else {
			    			block.rotateClockWise();
                            holdingDown=1;
				    	}
                    }
				}
				else if(input.isKeyDown(Input.KEY_E)) {
			    	block.rotateClockWise();
				    if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
					    gameField.moveBlock(block, offsetX, offsetY);
					    inputDelta = 150;
                        holdingDown=1;
				    }
				    else {
					    block.rotateCounterClockWise();
                        holdingDown=1;
				    }
			    }
			    else if(input.isKeyDown(Input.KEY_Q)) {
				    block.rotateCounterClockWise();
				    if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
					    gameField.moveBlock(block, offsetX, offsetY);
					    inputDelta = 150;
                        holdingDown=1;
				    }
				    else {
					    block.rotateClockWise();
                        holdingDown=1;
    				}
	    		}
            }
		}
		
		
		// update as per speed (FRAMES_PER_STEP)
		if(frameCount == 0) {
			if(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {				
				gameField.moveBlock(block, offsetX, offsetY++);
			}
			else {
				gameField.glueBlock(block, offsetX, offsetY);

				clearedLines = gameField.clearLines(offsetY);

				totalScore += gameField.score(clearedLines);
                totalScore += holdingDown;// count
                holdingDown = 0;

                totalClearedLines += clearedLines;
				totalBlocks++;

				block = null;
			}
		}
		
		// wait one frame
		try {
			Thread.currentThread().sleep(ONE_FRAME);
			frameCount = (frameCount+1) % FRAMES_PER_STEP;
			// For example, NES Tetris operates at 60 frames per second. At level 0, a piece falls one step every 48 frames, and at level 19, a piece falls one step every 2 frames. Level increments either terminate at a certain point (Game Boy Tetris tops off at level 20)
		}
		catch(InterruptedException e) {}
		
		if(clearedLines>0) {

            System.out.println("removing " + clearedLines);
            System.out.println("bottomest removed: " + gameField.moveDownNLines(21, clearedLines));
		}
		
		clearedLines = 0;

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		if(updateBlocks) {
			for(int i=0; i<gameField.getWidth(); i++) {			
				for(int j=0; j<gameField.getHeight(); j++) {
					g.setColor(Color.gray);
                    if(j>1) {
                        g.drawRect(BLOCK_START_X + i*BLOCKSIZE, BLOCK_START_Y + j*BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
                    }
					g.setColor(gameField.getBlock(i, j));
					if(j>1) {
					    g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE, BLOCK_START_Y + 1 + j*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
                    }
				}
			}
		}

		if(null != block) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(block.getBlock(i, j)) {					
						//blocks[i+offsetX][j+offsetY] = b.getColor();
						g.setColor(block.getColor());
                        //if ((BLOCK_START_Y + 1 + (j+offsetY)*BLOCKSIZE)>2) {
                        if ((offsetY+j)>1) {
                            g.fillRect(BLOCK_START_X + 1 + (i+offsetX)*BLOCKSIZE, BLOCK_START_Y + 1 + (j+offsetY)*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
                        }
                        System.out.println("offsetY: " + offsetY);
                        System.out.println("j: " + j);
                        //System.out.println("offsetX: " + offsetX);
                    }
				}
			}
		}
		
		// draw upcoming block:
		if(upcomingBlocks.isEmpty() == false) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(upcomingBlocks.getFirst().getBlock(i, j)) {					
						g.setColor(upcomingBlocks.getFirst().getColor());
						g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE + 310, BLOCK_START_Y + 1 + j*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);

					}
					else {
						g.setColor(Color.gray);
						g.drawRect(BLOCK_START_X + i*BLOCKSIZE + 310, BLOCK_START_Y + j*BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
						g.setColor(Color.white);
						g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE + 310, BLOCK_START_Y + 1 + j*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
					}
				}
			}
		}
        // draw score
        g.drawString("Total score:", 20, 50);
        g.drawString(totalScore + "", 50, 70);

	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new TestRun());
			app.setDisplayMode(RES_WIDTH, RES_HEIGHT, false);
			app.start();
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}

	}
}	
