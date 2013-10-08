package com.myperfectgame.states.transitions;

/**
 * Date: 21/09/13
 * Time: 20:16
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

public class CountdownTransitionOut implements Transition
{
    private int countdown;
    private float time;

    private boolean complete;

    // Create a new mosaic transition
    public CountdownTransitionOut()
    {
        this (3);
    }

    // Create a new countdown transition
    // countdown - The number of seconds it takes for the transition to finish
    public CountdownTransitionOut(int countdown)
    {
        complete = false;
        this.countdown = countdown;
        time = 0f;
    }

    // Check if this transition has been completed
    // Return - True if the transition has been completed
    public boolean isComplete()
    {
        return complete;
    }

    // Render the transition over the existing state rendering
    // game - The game this transition is being rendered as part of
    // container - The container holding the game
    // g - The graphics context to use when rendering the transiton
    // Throws - SlickException: Indicates a failure occured during the render
    public void postRender (StateBasedGame game, GameContainer container, Graphics g) {
        g.clear();
        g.drawString("Resuming in " + countdown + " seconds",200, 300);
    }

    // Update the transition. Cause what ever happens in the transition to happen
    // game - The game this transition is being rendered as part of
    // container - The container holding the game
    // delta - The amount of time passed since last update
    // Throws - SlickException: Indicates a failure occured during the update
    public void update (StateBasedGame game, GameContainer container, int delta)
    {
        time += delta;

        if (time > 1000) {
            countdown--;
            time = 0f;
        }

        if (countdown == 0) {
            complete = true;
        }
    }

    // Render the transition before the existing state rendering
    // game - The game this transition is being rendered as part of
    // container - The container holding the game
    // delta - The amount of time passed since last update
    // Throws - SlickException: Indicates a failure occured during the update
    public void preRender (StateBasedGame game, GameContainer container, Graphics g) {
    }

    // Initialise the transition
    // firstState - The first state we're rendering (this will be rendered by the framework)
    // secondState - The second stat we're transitioning to or from (this one won't be rendered)
    public void init (GameState firstState, GameState secondState)
    {
    }
}