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

import java.util.Collections;
import java.util.List;

public class Help extends BasicGameState{

    private int state;
    private int inputDelta;

    public Help(int state){
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
        g.drawString("Help",250,180);
        g.drawString("Fill each row completely with blocks in order to clear it",50,200);
        g.drawString("You get more points for clearing more rows at the same time",50,220);

        g.drawString("Controls: arrows, Q, W and up to rotate, P to pause",100,280);

    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException{
        // display stats
        // and music

        inputDelta -= delta;
        Input input = container.getInput();

        if (inputDelta < 0) {
            if(input.isKeyDown(Input.KEY_ENTER) | input.isKeyDown(Input.KEY_ESCAPE)) {
                inputDelta = 250;
                sbg.enterState(Game.menu);

            }
        }


    }

    public int getID(){
        return state;
    }
}
