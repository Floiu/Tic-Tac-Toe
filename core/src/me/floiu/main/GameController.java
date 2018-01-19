package me.floiu.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import me.floiu.main.screens.GameplayScreen;

public class GameController {

    protected SpriteBatch spriteBatch;
    private GameplayScreen screen;
    private AssetsLoader assetsLoader;

    private char[] boardStatus = {'#', '#', '#', '#', '#', '#', '#', '#', '#'};

    private char whoIsNow = 'x';

    public GameController(GameplayScreen screen, SpriteBatch spriteBatch, AssetsLoader assetsLoader) {
        this.screen = screen;
        this.spriteBatch = spriteBatch;
        this.assetsLoader = assetsLoader;
    }

    public void makeMove(int i, InputEvent event) {
        if (boardStatus[i-1] != '#') {
            event.cancel();
        } else {
            boardStatus[i-1] = whoIsNow;
        }
        changePlayer();
    }

    public void resetGame() {
        for (int i=1; i<=boardStatus.length; i++) {
            boardStatus[i-1] = '#';
        }
        whoIsNow = 'x';
    }

    private void changePlayer() {
        if (whoIsNow == 'x') {
            whoIsNow = 'o';
        } else {
            whoIsNow = 'x';
        }
    }

    /**
     * ---------------------
     *  GETTERS AND SETTERS
     * ---------------------
     * */

    public char[] getBoardStatus() {
        return boardStatus;
    }

    public char getWhoIsNow() {
        return whoIsNow;
    }
}
