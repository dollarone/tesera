package com.myperfectgame;

/**
 * Date: 08/09/13
 * Time: 16:34
 */

import com.myperfectgame.states.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{

    public static final String gamename = "Tesera";
    public static final int menu = 0;
    public static final int new_game = 1;
    public static final int play = 2;
    public static final int pause = 3;
    public static final int gameover = 4;

    private final static int RES_WIDTH = 600;
    private final static int RES_HEIGHT = 800;



    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new NewGame(new_game));
        this.addState(new Play(play));
        this.addState(new Pause(pause));
        this.addState(new GameOver(gameover));
        /*
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new Newgame(new_game));
        this.addState(new Loadgame(load_game));
        this.addState(new Charselect(char_select));
        this.addState(new Itemfound(item_found));
        this.addState(new Monsterfight(monster_fight));
        this.addState(new Inventory(inventory));
        */

    }
    /*
     */
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(new_game).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(pause).init(gc, this);
        this.getState(gameover).init(gc, this);
       // this.getState(load_game).init(gc, this);

                /*
        //broken states
        this.getState(new_game).init(gc, this);
        this.getState(item_found).init(gc, this);
        this.getState(char_select).init(gc, this);
        this.getState(monster_fight).init(gc, this);
        this.getState(inventory).init(gc, this);
        //end broken states
        this.enterState(menu);
        */
        this.enterState(menu);
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer(new Game(gamename));
            //appgc.setDisplayMode(640, 360, false);
            appgc.setDisplayMode(RES_WIDTH, RES_HEIGHT, false);
            appgc.setShowFPS(false);
            appgc.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

}
