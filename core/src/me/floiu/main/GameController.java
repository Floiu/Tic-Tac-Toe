package me.floiu.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;

public class GameController {

    private char[] boardStatus = {'#', '#', '#', '#', '#', '#', '#', '#', '#'};

    private char whoIsNow = 'x';
    private float winBoardResetDelay = 0.75f;

    public void makeMove(int i, InputEvent event) {
        if (boardStatus[i-1] != '#') {
            event.cancel();
        } else {
            boardStatus[i-1] = whoIsNow;
            checkWin();
        }
        changePlayer();
    }

    private void checkWin() {
        if (((boardStatus[0] == whoIsNow) && (boardStatus[1] == whoIsNow)&&(boardStatus[2] == whoIsNow)) ||
                ((boardStatus[3] == whoIsNow) && (boardStatus[4] == whoIsNow)&&(boardStatus[5] == whoIsNow)) ||
                ((boardStatus[6] == whoIsNow) && (boardStatus[7] == whoIsNow)&&(boardStatus[8] == whoIsNow)) ||
                ((boardStatus[0] == whoIsNow) && (boardStatus[3] == whoIsNow)&&(boardStatus[6] == whoIsNow)) ||
                ((boardStatus[1] == whoIsNow) && (boardStatus[4] == whoIsNow)&&(boardStatus[7] == whoIsNow)) ||
                ((boardStatus[2] == whoIsNow) && (boardStatus[5] == whoIsNow)&&(boardStatus[8] == whoIsNow)) ||
                ((boardStatus[0] == whoIsNow) && (boardStatus[4] == whoIsNow)&&(boardStatus[8] == whoIsNow)) ||
                ((boardStatus[2] == whoIsNow) && (boardStatus[4] == whoIsNow)&&(boardStatus[6] == whoIsNow))) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    clearBoard();
                }
            }, winBoardResetDelay);
        }
    }

    public void clearBoard() {
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
