package com.myperfectgame.states;

/**
 * Date: 08/09/13
 * Time: 17:10
 */

import com.myperfectgame.*;
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

    // TODO : better stats
    private int totalClearedLines;
    private int totalBlocks;
    private int totalScore;

    private LinkedList<Block<Image>> upcomingBlocks;

    private boolean updateBlocks;
    private boolean isDead = false;

    public Play(int state){
        //super("SimpleTest");

        this.state = state;
    }

    public int getID(){
        return state;
    }

    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        resetGame();

    }


    @SuppressWarnings("static-access")
    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta)
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
            /*
            for(Block foo : upcomingBlocks) {
                System.out.println(foo.getClass() + ", " );
            }
            System.out.println("-------------" );
              */
        }

        if(null == block) { //if new block needed

            block = upcomingBlocks.remove();

            offsetX = 3;
            offsetY = 0;

            if(gameField.checkMoveBlock(block, offsetX, offsetY) == false) {
                gameOver(sbg);
            }

        }

        // only allow moves every so often
        if(inputDelta < 0) {
            Input input = container.getInput();


            if(input.isKeyDown(Input.KEY_PAUSE) || input.isKeyDown(Input.KEY_P)) {
                inputDelta = 250;
                sbg.enterState(com.myperfectgame.Game.pause);
            }
            else if(input.isKeyDown(Input.KEY_X)) {
                FRAMES_PER_STEP = 5;

            }
            else if(input.isKeyDown(Input.KEY_Z)) {
                FRAMES_PER_STEP = 40;

            }
            else if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
                if(gameField.checkMoveBlock(block, offsetX, offsetY+1)) {
                    offsetY++; //gameField.moveBlock(block, offsetX, offsetY++);
                    inputDelta = 70;
                    holdingDown++;
                }
            }
            else if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
                if(gameField.checkMoveBlock(block, offsetX+1, offsetY)) {
                    gameField.moveBlock(block, offsetX++, offsetY);
                    inputDelta = 120;
                    holdingDown=1;
                }
            }
            else if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
                if(gameField.checkMoveBlock(block, offsetX-1, offsetY)) {
                    gameField.moveBlock(block, offsetX--, offsetY);
                    inputDelta = 120;
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
                    inputDelta = 170;
                    holdingDown=1;
                }
                else {
                    block.rotateCounterClockWise();
                    block.rotateCounterClockWise();
                    if(gameField.checkMoveBlock(block, offsetX, offsetY)) {
                        gameField.moveBlock(block, offsetX, offsetY);
                        inputDelta = 170;
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
                    inputDelta = 170;
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
                    inputDelta = 170;
                    holdingDown=1;
                }
                else {
                    block.rotateClockWise();
                    holdingDown=1;
                }

            }
        }

        // wait one frame
        try {
            Thread.currentThread().sleep(ONE_FRAME);
            frameCount = (frameCount+1) % FRAMES_PER_STEP;
            // For example, NES Tetris operates at 60 frames per second. At level 0, a piece falls one step every 48 frames, and at level 19, a piece falls one step every 2 frames. Level increments either terminate at a certain point (Game Boy Tetris tops off at level 20)
            pixelOffset = 30 * frameCount / FRAMES_PER_STEP;
            System.out.println("frameCount: " + frameCount + " \tpixelOffset: " + pixelOffset + " \tdeltaCounter: " + deltaCounter + " \tdelta:" + delta + " \tinputDelta: " + inputDelta + " \tholdingDown: " + holdingDown);
        }
        catch(InterruptedException e) {}

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

                totalScore += gameField.score(clearedLines);
                totalScore += holdingDown;// count
                holdingDown = 0;

                totalClearedLines += clearedLines;
                totalBlocks++;

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
        g.drawString(totalScore + "", 50, 120);

        //g.setDrawMode(Graphics.MODE_SCREEN);
    }


    private void gameOver(StateBasedGame sbg) {
        isDead = true;
        System.out.println("Game over");
        System.out.println("Lines cleared: " + totalClearedLines);
        System.out.println("Blocks placed: " + totalBlocks);
        System.out.println("Score: " + totalScore);
        sbg.enterState(com.myperfectgame.Game.gameover);

    }

    private void resetGame() {
        gameField = new GameField();
        rand = new Random(2);
        updateBlocks = true;

        holdingDown = 1;
        frameCount = 0;
        deltaCounter = 500;
        inputDelta = 0;
        upcomingBlocks = new LinkedList<Block<Image>>();

        clearedLines = 0;
        totalClearedLines = 0;
        totalBlocks = 0;
        totalScore = 0;

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        if (isDead) {
            resetGame();
            isDead = false;
        }
    }
            /*
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Game());
            app.setDisplayMode(RES_WIDTH, RES_HEIGHT, false);
            app.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }

    }
    */
}
