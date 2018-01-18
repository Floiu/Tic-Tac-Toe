package me.floiu.main;

import com.badlogic.gdx.Game;

import me.floiu.main.screens.SplashScreen;

public class Main extends Game {

    public final static String GAME_NAME = "TicTacToe";

    public final static int WIDTH = 700;
    public final static int HEIGHT = 700;

    private boolean paused;

    @Override
    public void create () {
        this.setScreen(new SplashScreen(this));
    }

    /**
     * ---------------------
     *  GETTERS AND SETTERS
     * ---------------------
     * */

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}