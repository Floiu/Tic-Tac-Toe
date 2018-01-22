package me.floiu.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GameController {

    private char[] boardStatus = {'#', '#', '#', '#', '#', '#', '#', '#', '#'};

    private char whoIsNow = 'x';

    private float boardResetDelay = 0.75f;
    private char whereWin;
    private boolean someoneWin = false;

    private int xScore = 0;
    private int oScore = 0;

    public void makeMove(int i, InputEvent event) {
        if (boardStatus[i - 1] != '#') {
            event.cancel();
        } else {
            boardStatus[i - 1] = whoIsNow;
            checkWin();
            checkDraw();
            changePlayer();
        }
    }

    public void nextRound() {
        for (int i = 1; i <= boardStatus.length; i++) {
            boardStatus[i - 1] = '#';
        }
        whoIsNow = 'x';
        someoneWin = false;
        whereWin = '#';
    }

    public void resetGame() {
        nextRound();
        xScore = 0;
        oScore = 0;
    }

    private void checkWin() {
        if ((boardStatus[0] == whoIsNow) && (boardStatus[1] == whoIsNow) && (boardStatus[2] == whoIsNow)) {
            whereWin = '_';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        } else if ((boardStatus[3] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[5] == whoIsNow)) {
            whereWin = '-';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        } else if ((boardStatus[6] == whoIsNow) && (boardStatus[7] == whoIsNow) && (boardStatus[8] == whoIsNow)) {
            whereWin = '⁻';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        } else if ((boardStatus[0] == whoIsNow) && (boardStatus[3] == whoIsNow) && (boardStatus[6] == whoIsNow)) {
            whereWin = '|';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        } else if ((boardStatus[1] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[7] == whoIsNow)) {
            whereWin = '!';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        } else if ((boardStatus[2] == whoIsNow) && (boardStatus[5] == whoIsNow) && (boardStatus[8] == whoIsNow)) {
            whereWin = ':';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        } else if ((boardStatus[0] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[8] == whoIsNow)) {
            whereWin = '/';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        } else if ((boardStatus[2] == whoIsNow) && (boardStatus[4] == whoIsNow) && (boardStatus[6] == whoIsNow)) {
            whereWin = '\\';
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
            someoneWin = true;
            addScore(whoIsNow);
        }
    }

    private void addScore(char who) {
        if (who == 'x') {
            xScore += 1;
        } else {
            oScore += 1;
        }
    }

    private void changePlayer() {
        if (whoIsNow == 'x') {
            whoIsNow = 'o';
        } else {
            whoIsNow = 'x';
        }
    }

    private void checkDraw() {
        if ((boardStatus[0] != '#' && boardStatus[1] != '#' && boardStatus[2] != '#' && boardStatus[3] != '#' && boardStatus[4] != '#' && boardStatus[5] != '#' && boardStatus[6] != '#' && boardStatus[7] != '#' && boardStatus[8] != '#') && someoneWin == false) {
            Timer.schedule(new Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, boardResetDelay);
        }
    }

    /**
     * ---------------------
     * GETTERS AND SETTERS
     * ---------------------
     */

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

    public int getXScore() {
        return xScore;
    }

    public int getOScore() {
        return oScore;
    }
}
