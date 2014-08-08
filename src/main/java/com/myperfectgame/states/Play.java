package com.myperfectgame.states;

/**
 * Date: 08/09/13
 * Time: 17:10
 */

import com.myperfectgame.*;
import com.myperfectgame.Game;
import com.myperfectgame.blocks.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;
import java.util.Random;

public class Play extends BasicGameState {

    private int state;

    GameField gameField;
    private java.util.Random rand;
    private final static int ONE_FRAME = 16; // 1000/60 ~= 16
    private  static int FRAMES_PER_STEP = 48;//48;

    private final static int RES_WIDTH = 600;
    private final static int RES_HEIGHT = 800;
    private final int BLOCKSIZE = 30;
    private final int BLOCK_START_X = 150;
    private final int BLOCK_START_Y = 40;

    private Block<Image> block;
    private int offsetX;
    private int offsetY;

    private int pixelOffset;

    private int deltaCounter;
    private int inputDelta;

    private int frameCount;
    private int clearedLines;

    private int holdingDown;

    private LinkedList<Block<Image>> upcomingBlocks;

    private boolean updateBlocks;
    private boolean isDead = false;

    private Stats stats;

    public Play(int state){
        //super("SimpleTest");

        this.state = state;
    }

    public int getID(){
        return state;
    }

    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        resetGame(sbg);
    }


    @SuppressWarnings("static-access")
    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta)
            throws SlickException {
        if(Config.newGame) {
            this.init(container, sbg);
        }
        Config.newGame = false;

        deltaCounter -= delta;
        inputDelta -= delta;
        stats.addPlayTime(delta);

        if(upcomingBlocks.isEmpty()) {
            // generate new set of blocks
            upcomingBlocks.add(new IBlock());

            upcomingBlocks.add(rand.nextInt(2), new LBlock());
            upcomingBlocks.add(rand.nextInt(3), new JBlock());
            upcomingBlocks.add(rand.nextInt(4), new OBlock());
            upcomingBlocks.add(rand.nextInt(5), new ZBlock());
            upcomingBlocks.add(rand.nextInt(6), new TBlock());
            upcomingBlocks.add(rand.nextInt(7), new SBlock());
        }

        if(null == block) { //if new block needed

            block = upcomingBlocks.remove();

            offsetX = 3;
            offsetY = 0;

            if(gameField.checkMoveBlock(block, offsetX, offsetY) == false) {
                gameOver(sbg);
            }

        }

        try {
            Thread.currentThread().sleep(ONE_FRAME);
            frameCount = (frameCount+1) % FRAMES_PER_STEP;
            // For example, NES Tetris operates at 60 frames per second. At level 0, a piece falls one step every 48 frames, and at level 19, a piece falls one step every 2 frames. Level increments either terminate at a certain point (Game Boy Tetris tops off at level 20)
            pixelOffset = 30 * frameCount / FRAMES_PER_STEP;
            //System.out.println("frameCount: " + frameCount + " \tpixelOffset: " + pixelOffset + " \tdeltaCounter: " + deltaCounter + " \tdelta:" + delta + " \tinputDelta: " + inputDelta + " \tholdingDown: " + holdingDown);
        }
        catch(InterruptedException e) {}

        // only allow moves every so often
        if(inputDelta < 0) {
            Input input = container.getInput();


            if(input.isKeyDown(Input.KEY_PAUSE) || input.isKeyDown(Input.KEY_P)) {
                inputDelta = 250;
                stats.incPause();
                sbg.enterState(com.myperfectgame.Game.menu);
            }
            else if(input.isKeyDown(Input.KEY_X)) {
                FRAMES_PER_STEP = 5;

            }
            else if(input.isKeyDown(Input.KEY_Z)) {
                FRAMES_PER_STEP = 40;

            }
            else if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
                if(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {
                    offsetY++;
                    inputDelta = 70;
                    holdingDown++;
                    stats.incDown();
                }
            }
            else if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
                if(gameField.checkMoveBlock(block, offsetX+1, offsetY)) {
                    offsetX++;
                    inputDelta = 120;
                    //holdingDown=0;
                    stats.incRight();
                }
            }
            else if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
                if(gameField.checkMoveBlock(block, offsetX-1, offsetY)) {
                    offsetX--;
                    inputDelta = 120;
                    //holdingDown=0;
                    stats.incLeft();
                }

            }
            else if(input.isKeyDown(Input.KEY_NEXT) || input.isKeyDown(Input.KEY_R)) {
                while(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {
                    offsetY++;
                    inputDelta = 250;
                    holdingDown++;

                }
                stats.incDrop();
            }
            else if(input.isKeyDown(Input.KEY_UP)) {
                block.rotateClockWise();
                if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
                    inputDelta = 170;
                    //holdingDown=1;
                    stats.incRotate();
                }
                else {
                    block.rotateCounterClockWise();
                    block.rotateCounterClockWise();
                    if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
                        inputDelta = 170;
                        //holdingDown=1;
                        stats.incRotate();
                    }
                    else {
                        block.rotateClockWise();
                        //holdingDown=1;
                    }
                }
            }
            else if(input.isKeyDown(Input.KEY_E)) {
                block.rotateClockWise();
                if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
                    inputDelta = 170;
                    //holdingDown=1;
                    stats.incRotate();
                }
                else {
                    block.rotateCounterClockWise();
                    //holdingDown=1;
                }
            }
            else if(input.isKeyDown(Input.KEY_Q)) {
                block.rotateCounterClockWise();
                if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
                    inputDelta = 170;
                    //holdingDown=1;
                    stats.incRotate();
                }
                else {
                    block.rotateClockWise();
                    //holdingDown=1;
                }

            }
        }

        // wait one frame

        if(clearedLines>0) {

            gameField.moveDownNLines(21, clearedLines);
        }

        clearedLines = 0;

        // update as per speed (FRAMES_PER_STEP)
        if(frameCount == 0) {
            if(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {
                offsetY++;//gameField.moveBlock(block, offsetX, offsetY++);

                pixelOffset = 0;


            }
            else {
                gameField.glueBlock(block, offsetX, offsetY);

                clearedLines = gameField.clearLines(offsetY);
                switch(clearedLines) {
                    case 0: break;
                    case 1: stats.incSingles(); break;
                    case 2: stats.incDoubles(); break;
                    case 3: stats.incTriples(); break;
                    case 4: stats.incTetrises(); break;
                    default: break;
                }

                stats.addScore(gameField.score(clearedLines));
                stats.addScore(holdingDown);// count
                holdingDown = 0;

                stats.addClearedLines(clearedLines);
                stats.incBlocks();

                block = null;
            }

        }


    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics g)
            throws SlickException {

        //if(updateBlocks) {
            for(int i=0; i<gameField.getWidth(); i++) {
                for(int j=0; j<gameField.getHeight(); j++) {

                    if(j>1) {
                        if(null == gameField.getBlock(i, j)) {
                            //g.setColor(Color.gray);
                            //g.drawRect(BLOCK_START_X - 1 + i*BLOCKSIZE, BLOCK_START_Y - 1 + j*BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
                            g.setColor(Config.NOBLOCKCOLOUR);
                            g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE, BLOCK_START_Y + 1 + j*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
                        }
                        else {
                            g.drawImage(gameField.getBlock(i,j), (float)(BLOCK_START_X + (i*BLOCKSIZE)), (float)(BLOCK_START_Y + (j*BLOCKSIZE)));
                        }
                    }
                }
            }
       // }

        if(null != block) {
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(null != block.getBlock(i, j)) {
                        //blocks[i+offsetX][j+offsetY] = b.getColor();
                        //g.setColor(block.getColor());
                        //if ((BLOCK_START_Y + 1 + (j+offsetY)*BLOCKSIZE)>2) {
                        if ((offsetY+j)>1) {
                            //g.fillRect(BLOCK_START_X + 1 + (i+offsetX)*BLOCKSIZE, BLOCK_START_Y + 1 + (j+offsetY)*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);

                            // comment out the following line for smooth animation:
                            pixelOffset=30;

                            g.drawImage(block.getBlock(i,j), (float)(BLOCK_START_X + (i+offsetX)*BLOCKSIZE), (float)(BLOCK_START_Y + pixelOffset - 30 + (j+offsetY)*BLOCKSIZE));
                        }

                    }
                }
            }
        }

        // draw upcoming block:
        if(upcomingBlocks.isEmpty() == false) {
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(null != upcomingBlocks.getFirst().getBlock(i, j)) {
                        //g.setColor(upcomingBlocks.getFirst().getColor());
                        //g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE + 310, BLOCK_START_Y + 1 + (j+3)*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
                        g.drawImage(upcomingBlocks.getFirst().getBlock(i,j), (float)(BLOCK_START_X + i*BLOCKSIZE + 310), (float)(BLOCK_START_Y + (j+3)*BLOCKSIZE));

                    }
                    else {
                        //g.setColor(Color.gray);
                        //g.drawRect(BLOCK_START_X + i*BLOCKSIZE + 310, BLOCK_START_Y  + (j+3)*BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
                        g.setColor(Config.NOBLOCKCOLOUR);
                        g.fillRect(BLOCK_START_X + 1 + i*BLOCKSIZE + 310, BLOCK_START_Y + 1 + (j+3)*BLOCKSIZE, BLOCKSIZE -2, BLOCKSIZE -2);
                    }
                }
            }
        }
        g.drawString("Next:", 500, 100);
        // draw score
        g.drawString("Total score:", 20, 100);
        g.drawString(stats.getScore() + "", 70, 120);
        g.drawString("Time:", 20, 150);
        g.drawString(stats.getPlayTime()/1000 + "", 70, 170);
        g.drawString("Lines cleared:", 20, 200);
        g.drawString(stats.getClearedLines() + "", 70, 220);
        g.drawString("Blocks placed:", 20, 250);
        g.drawString(stats.getBlocks() + "", 70, 270);
        g.drawString("Singles:", 20, 300);
        g.drawString(stats.getSingles() + "", 70, 320);
        g.drawString("Doubles:", 20, 350);
        g.drawString(stats.getDoubles() + "", 70, 370);
        g.drawString("Triples:", 20, 400);
        g.drawString(stats.getTriples() + "", 70, 420);
        g.drawString("Tetrises:", 20, 450);
        g.drawString(stats.getTetrises() + "", 70, 470);
        //g.setDrawMode(Graphics.MODE_SCREEN);
    }


    private void gameOver(StateBasedGame sbg) {
        isDead = true;
        System.out.println("Game over");
        System.out.println("Lines cleared: " + stats.getClearedLines());
        System.out.println("Blocks placed: " + stats.getBlocks());
        System.out.println("Score: " + stats.getScore());
        System.out.println("Left " + stats.getLeft());

        // compare highscores?
        // or save

        // TODO: stats.setDate ( NOW );
        // TODO: stats.setName()

        ((Game)sbg).addHighScore(stats);

//        resetGame(sbg);
        Config.newGame = true;
        sbg.enterState(com.myperfectgame.Game.gameover);

    }

    private void resetGame(StateBasedGame sbg) {
        gameField = new GameField();
        rand = new Random(2);
        updateBlocks = true;
        block = null;
        offsetX = 0;
        offsetY = 0;

        holdingDown = 0;
        frameCount = 0;
        deltaCounter = 500;
        inputDelta = 0;
        upcomingBlocks = new LinkedList<Block<Image>>();

        //((Game)sbg).resetCurrentStats();
        stats = new Stats(); //((Game)sbg).getCurrentStats();

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        if (isDead) {
            resetGame(game);
            isDead = false;
        }
    }
}
