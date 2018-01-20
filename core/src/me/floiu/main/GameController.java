package me.floiu.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;

public class GameController {

    private char[] boardStatus = {'#', '#', '#', '#', '#', '#', '#', '#', '#'};

    private char whoIsNow = 'x';

    private float winBoardResetDelay = 0.75f;
    private char whereWin;
    private boolean someoneWin = false;

    public void makeMove(int i, InputEvent event) {
        if (boardStatus[i-1] != '#') {
            event.cancel();
        } else {
            boardStatus[i-1] = whoIsNow;
            checkWin();
            changePlayer();
        }
    }

    private void checkWin() {
        if ((boardStatus[0] == whoIsNow) && (boardStatus[1] == whoIsNow) && (boardStatus[2] == whoIsNow)) {
            whereWin = '_';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        } else if ((boardStatus[3] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[5] == whoIsNow)) {
            whereWin = '-';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        } else if ((boardStatus[6] == whoIsNow) && (boardStatus[7] == whoIsNow) && (boardStatus[8] == whoIsNow)) {
            whereWin = '⁻';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        } else if ((boardStatus[0] == whoIsNow) && (boardStatus[3] == whoIsNow) && (boardStatus[6] == whoIsNow)) {
            whereWin = '|';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        } else if ((boardStatus[1] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[7] == whoIsNow)) {
            whereWin = '!';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        } else if ((boardStatus[2] == whoIsNow) && (boardStatus[5] == whoIsNow) && (boardStatus[8] == whoIsNow)) {
            whereWin = ':';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        } else if ((boardStatus[0] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[8] == whoIsNow)) {
            whereWin = '/';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        } else if ((boardStatus[2] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[6] == whoIsNow)) {
            whereWin = '\\';
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, winBoardResetDelay);
            someoneWin = true;
        }
    }

    public void nextRound() {
        for (int i=1; i<=boardStatus.length; i++) {
            boardStatus[i-1] = '#';
        }
        whoIsNow = 'x';
        someoneWin = false;
        whereWin = '#';
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

    public boolean isSomeoneWin() {
        return someoneWin;
    }

    public char getWhereWin() {
        return whereWin;
    }
}
