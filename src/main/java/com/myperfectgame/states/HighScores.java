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

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class HighScores extends BasicGameState{

    private int state;
    private int inputDelta;

    public HighScores(int state){
        this.state = state;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        inputDelta = 250;
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.setBackground(Color.black);
        g.drawString("High Scores",250,180);

        List<Stats> highScores = ((Game)sbg).getHighScores();
        Collections.sort(highScores, Stats.COMPARE_BY_SCORE);

        int i = 1;
        int max = 10;

        for(Stats stat : highScores) {
            g.drawString("Score:",250,200+(i*20));
            g.drawString(stat.getScore() + "",320,200+(i*20));
            i++;
            if(max==i) break;
        }

    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException{
        // display stats
        // and music

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
