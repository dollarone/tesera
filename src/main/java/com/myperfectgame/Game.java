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

import java.io.*;
import java.util.List;
import java.util.Vector;

public class Game extends StateBasedGame{

    public static final String gamename = "Tesera";
    public static final int menu = 0;
    public static final int new_game = 1;
    public static final int play = 2;
    public static final int pause = 3;
    public static final int gameover = 4;
    public static final int highscores = 5;
    public static final int help = 6;

    private final static int RES_WIDTH = 600;
    private final static int RES_HEIGHT = 800;

    private static List<Stats> highScores;

    public Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new GameOver(gameover));
        this.addState(new HighScores(highscores));
        this.addState(new Help(help));
        this.enterState(menu);

        loadHighScores();
    }

    public void initStatesList(GameContainer gc) throws SlickException{
        /*
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(gameover).init(gc, this);
        this.getState(highscores).init(gc, this);
        // not needed as the constructor calls init
          */


    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer(new Game(gamename));
            //appgc.setDisplayMode(640, 360, false);
            appgc.setDisplayMode(RES_WIDTH, RES_HEIGHT, false);
            appgc.setShowFPS(true);
            appgc.setAlwaysRender(true);

            //appgc.setVSync(true);
            //appgc.setTargetFrameRate(60);
            appgc.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    public static List<Stats> getHighScores() {
        return highScores;
    }

    private void loadHighScores() {
        highScores = new Vector<Stats>();

        FileInputStream f_in = null;
        ObjectInputStream obj_in = null;
        try {
            f_in = new FileInputStream("1.ser");
            obj_in = new ObjectInputStream(f_in);
            while(true) {

                Object obj = obj_in.readObject();
                if(obj instanceof Stats) {
                    highScores.add((Stats) obj);
                    System.out.println(((Stats)obj).getScore());
                }
            }
        }
        catch(EOFException e) {
            System.out.println("expected");
        }
        catch(FileNotFoundException e) {}
        catch(IOException e) {}
        catch(ClassNotFoundException e) {}
        finally {
            try {
                if(obj_in != null) {
                    obj_in.close();
                }
                if(f_in != null) {
                    f_in.close();
                }
            }
            catch(IOException e) {}
        }

    }

    public void saveHighScores() {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("1.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for(Stats stats : highScores) {
                out.writeObject(stats);
            }

            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in 1.ser");
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }

    }

    public void addHighScore(Stats stats) {
        highScores.add(stats);

    }
}
