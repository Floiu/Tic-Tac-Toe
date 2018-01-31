package me.floiu.main.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import me.floiu.main.AssetsLoader;
import me.floiu.main.GameController;
import me.floiu.main.Main;

public class GameplayScreen extends AbstractScreen {

    private GameController gc;
    private AssetsLoader assetsLoader;

    private ImageButton resetButton;
    private ImageButton clearOneFieldButton;

    private TextButton clearBoardButton;
    private TextButtonStyle textButtonStyle;

    private Label scoreLabel;
    private LabelStyle scoreLabelStyle;

    private TextureRegion restartButtonRegion;
    private TextureRegion clearOneFieldButtonRegion;

    private TextureRegionDrawable restartButtonDrawable;
    private TextureRegionDrawable clearOneFieldButtonDrawable;

    private Texture clearOneFieldButtonImage;
    private Texture restartButtonImage;
    private Texture boardImage;
    private Texture oImage;
    private Texture xImage;
    private Texture po_d;
    private Texture po_s;
    private Texture po_g;
    private Texture pi_l;
    private Texture pi_s;
    private Texture pi_p;
    private Texture sk_l;
    private Texture sk_p;

    private static int BOARD_SIZE = 9;
    private static int BUTTON_SIZE = 100;

    private static int BOARD_IMAGE_X = 200;
    private static int BOARD_IMAGE_Y = 250;

    private static int SCORE_LABEL_X = 325;
    private static int SCORE_LABEL_Y = 630;

    private int[] boardButtonX = {200, 310, 420, 200, 310, 420, 200, 310, 420};
    private int[] boardButtonY = {250, 250, 250, 360, 360, 360, 470, 470, 470};

    public GameplayScreen(Main game, AssetsLoader assetsLoader) {
        super(game);
        this.assetsLoader = assetsLoader;
        gc = new GameController();
        initAssets();
        initBoard();
        initResetButton();
        initScoreLabels();
        initSuperPowersButtons();
    }

    // Init super powers buttons
    private void initSuperPowersButtons() {
        clearOneFieldButtonRegion = new TextureRegion(clearOneFieldButtonImage);
        clearOneFieldButtonDrawable = new TextureRegionDrawable(clearOneFieldButtonRegion);

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        clearBoardButton = new TextButton("Clear board", textButtonStyle);
        clearBoardButton.setWidth(BUTTON_SIZE);
        clearBoardButton.setHeight(BUTTON_SIZE);
        clearBoardButton.setX(200);
        clearBoardButton.setY(50);
        clearBoardButton.setDebug(true);

        clearOneFieldButton = new ImageButton(clearOneFieldButtonDrawable);
        clearOneFieldButton.setWidth(BUTTON_SIZE);
        clearOneFieldButton.setHeight(BUTTON_SIZE);
        clearOneFieldButton.setX(420);
        clearOneFieldButton.setY(50);

        clearOneFieldButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (gc.getOF(gc.getWhoIsNow()) != gc.getWhoIsNow()) {
                    gc.setWhatToDo('f');
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        stage.addActor(clearBoardButton);
        stage.addActor(clearOneFieldButton);
    }

    // Init scores label
    private void initScoreLabels() {
        scoreLabelStyle = new LabelStyle();
        scoreLabelStyle.font = new BitmapFont();

        scoreLabel = new Label("X: " + gc.getXScore() + " | O: " + gc.getOScore(), scoreLabelStyle);
        scoreLabel.setX(SCORE_LABEL_X);
        scoreLabel.setY(SCORE_LABEL_Y);
        stage.addActor(scoreLabel);
    }

    // Init assets
    private void initAssets() {
        boardImage = assetsLoader.manager.get("board.png", Texture.class);
        oImage = assetsLoader.manager.get("o.png", Texture.class);
        xImage = assetsLoader.manager.get("x.png", Texture.class);
        po_d = assetsLoader.manager.get("po_d.png", Texture.class);
        po_s = assetsLoader.manager.get("po_s.png", Texture.class);
        po_g = assetsLoader.manager.get("po_g.png", Texture.class);
        pi_l = assetsLoader.manager.get("pi_l.png", Texture.class);
        pi_s = assetsLoader.manager.get("pi_s.png", Texture.class);
        pi_p = assetsLoader.manager.get("pi_p.png", Texture.class);
        sk_l = assetsLoader.manager.get("sk_l.png", Texture.class);
        sk_p = assetsLoader.manager.get("sk_p.png", Texture.class);
        restartButtonImage = assetsLoader.manager.get("restartButton.png", Texture.class);
        clearOneFieldButtonImage = assetsLoader.manager.get("onefield.png", Texture.class);
    }

    // Init reset game button
    private void initResetButton() {
        restartButtonRegion = new TextureRegion(restartButtonImage);
        restartButtonDrawable = new TextureRegionDrawable(restartButtonRegion);
        resetButton = new ImageButton(restartButtonDrawable);
        resetButton.setWidth(BUTTON_SIZE);
        resetButton.setHeight(BUTTON_SIZE);
        resetButton.setX(10);
        resetButton.setY(590);

        stage.addActor(resetButton);

        resetButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    gc.resetGame();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    // Init playing board
    private void initBoard() {
        for (int i=1; i<=BOARD_SIZE; i++) {
            final Button _tempbutton = new Button(new ButtonStyle());
            _tempbutton.setWidth(BUTTON_SIZE);
            _tempbutton.setHeight(BUTTON_SIZE);
            _tempbutton.setX(boardButtonX[i-1]);
            _tempbutton.setY(boardButtonY[i-1]);
            stage.addActor(_tempbutton);

            final int fieldID = i;

            _tempbutton.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (gc.isSomeoneWin() == false) {
                        if (button == Input.Buttons.LEFT) {
                            if (gc.getWhatToDo() == 'p') {
                                gc.makeMove(fieldID, event);
                            } else if (gc.getWhatToDo() == 'f') {
                                gc.oneFieldClear(fieldID, event);
                            }
                        }
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
        spriteBatch.begin();
        spriteBatch.draw(boardImage, BOARD_IMAGE_X, BOARD_IMAGE_Y); // Draw board image - 4 lines
        spriteBatch.end();
        drawMoves();
        showWinPlace();
        showPoints();
        whoIsNow();
    }

    private void whoIsNow() {
        spriteBatch.begin();
        if (gc.getWhoIsNow() == 'x') {
            spriteBatch.draw(xImage, 590, 590);
        } else {
            spriteBatch.draw(oImage, 590, 590);
        }
        spriteBatch.end();
    }

    private void showPoints() {
        scoreLabel.setText("X: " + gc.getXScore() + " | O: " + gc.getOScore());
    }

    private void showWinPlace() {
        spriteBatch.begin();
        if (gc.getWhereWin() == '_') {
            spriteBatch.draw(po_d, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        } else if (gc.getWhereWin() == '-') {
            spriteBatch.draw(po_s, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        } else if (gc.getWhereWin() == '⁻') {
            spriteBatch.draw(po_g, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        } else if (gc.getWhereWin() == '|') {
            spriteBatch.draw(pi_l, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        } else if (gc.getWhereWin() == '!') {
            spriteBatch.draw(pi_s, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        } else if (gc.getWhereWin() == ':') {
            spriteBatch.draw(pi_p, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        } else if (gc.getWhereWin() == '/') {
            spriteBatch.draw(sk_l, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        } else if (gc.getWhereWin() == '\\') {
            spriteBatch.draw(sk_p, BOARD_IMAGE_X, BOARD_IMAGE_Y);
        }
        spriteBatch.end();
    }

    private void drawMoves() {
        spriteBatch.begin();
        for (int i=1; i<=BOARD_SIZE; i++) {
            char [] _tempBoardStatus = gc.getBoardStatus();
            if (_tempBoardStatus[i-1] == 'x') {
                spriteBatch.draw(xImage, boardButtonX[i-1], boardButtonY[i-1]);
            } else  if (_tempBoardStatus[i-1] == 'o') {
                spriteBatch.draw(oImage, boardButtonX[i-1], boardButtonY[i-1]);
            }
        }
        spriteBatch.end();
    }
}
