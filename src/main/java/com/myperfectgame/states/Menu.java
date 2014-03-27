package com.myperfectgame.states;

/**
 * Date: 08/09/13
 * Time: 16:33
 */
import com.myperfectgame.states.transitions.CountdownTransitionOut;
import java.awt.Font;


import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

    private int state;
    private int inputDelta;
    private UnicodeFont gameFont;
    private Image image;


    public Menu(int state){
        this.state = state;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        image = new Image("resources/back.png");

        inputDelta = 250;
        try {

            Font font = new Font("Verdana", Font.BOLD, 20);
            gameFont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());

        } catch (Exception e) {} ;

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.drawImage(image, 0, 0);
        g.setBackground(Color.black);
        g.setColor(Color.red);
        g.drawString("Menu", 280, 245);
        //g.setFont(gameFont);                  // not working
        g.setColor(Color.darkGray);
        g.drawString("Resume Game",250,220);
        g.setColor(Color.gray);
        g.drawString("New Game",250,300);
        g.drawString("Highscores",250,400);
        g.drawString("Quit",250,500);

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
