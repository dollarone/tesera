package com.myperfectgame.states;

/**
 * Date: 08/09/13
 * Time: 16:33
 */
import com.myperfectgame.Game;
import com.myperfectgame.Stats;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import sun.org.mozilla.javascript.internal.ScriptRuntime;


public class GameOver extends BasicGameState{

    private int state;
    private int inputDelta;


    public GameOver(int state){
        this.state = state;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        inputDelta = 250;

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

        Stats stats = ((Game)sbg).getCurrentStats();
        g.setBackground(Color.black);
        g.drawString("Game over",250,50);

        g.drawString("Total score:", 220, 100);
        g.drawString(stats.getScore() + "", 270, 120);
        g.drawString("Time:", 220, 150);
        g.drawString(stats.getPlayTime()/1000 + "", 270, 170);
        g.drawString("Lines cleared:", 220, 200);
        g.drawString(stats.getClearedLines() + "", 270, 220);
        g.drawString("Blocks placed:", 220, 250);
        g.drawString(stats.getBlocks() + "", 270, 270);
        g.drawString("Singles:", 220, 300);
        g.drawString(stats.getSingles() + "", 270, 320);
        g.drawString("Doubles:", 220, 350);
        g.drawString(stats.getDoubles() + "", 270, 370);
        g.drawString("Triples:", 220, 400);
        g.drawString(stats.getTriples() + "", 270, 420);
        g.drawString("Tetrises:", 220, 450);
        g.drawString(stats.getTetrises() + "", 270, 470);

        g.drawString("Your rank:", 220, 550);
        g.drawString("" + ((Game)sbg).findPosition(stats) + "", 270, 570);


    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException{
        //if(something) {
        //    record_stats;
        // also display stats
        // and have end music (or different endings?)
        //}
        inputDelta -= delta;
        Input input = container.getInput();

        if (inputDelta < 0) {
            if(input.isKeyDown(Input.KEY_ENTER) | input.isKeyDown(Input.KEY_ESCAPE)) {
                inputDelta = 250;
                sbg.enterState(com.myperfectgame.Game.menu);

            }
        }


    }

    public int getID(){
        return state;
    }
}
