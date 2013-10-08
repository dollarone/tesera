package com.myperfectgame.states;

/**
 * Date: 08/09/13
 * Time: 16:33
 */
import com.myperfectgame.states.transitions.CountdownTransitionOut;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState{

    private int state;
    private int inputDelta;


    public Pause(int state){
        this.state = state;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        inputDelta = 250;

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.setBackground(Color.black);
        g.drawString("Pavsa",280,300);

    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException{
        inputDelta -= delta;
        Input input = container.getInput();

        if (inputDelta < 0) {
            if(input.isKeyDown(Input.KEY_PAUSE) || input.isKeyDown(Input.KEY_P)) {
                inputDelta = 250;
                sbg.enterState(com.myperfectgame.Game.play, new CountdownTransitionOut(), null);
                //new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

            }
        }


    }

    public int getID(){
        return state;
    }
}
