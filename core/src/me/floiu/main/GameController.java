package me.floiu.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class GameController {

    private char[] boardStatus = {'#', '#', '#', '#', '#', '#', '#', '#', '#'};

    private char whoIsNow = 'x';

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
