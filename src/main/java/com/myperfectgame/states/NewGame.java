package com.myperfectgame.states;

/**
 * Date: 08/09/13
 * Time: 16:33
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NewGame extends BasicGameState{

    private int state;


    public NewGame(int state){
        this.state = state;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

    }

    public int getID(){
        return state;
    }
}
