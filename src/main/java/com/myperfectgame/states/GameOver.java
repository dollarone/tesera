package com.myperfectgame.states;

/**
 * Date: 08/09/13
 * Time: 16:33
 */
import com.myperfectgame.states.transitions.CountdownTransitionOut;
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
        g.setBackground(Color.black);
        g.drawString("Game over",250,300);

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
