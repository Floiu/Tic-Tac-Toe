package me.floiu.main.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.floiu.main.AssetsLoader;
import me.floiu.main.GameController;
import me.floiu.main.Main;

public class GameplayScreen extends AbstractScreen {

    private GameController gc;
    private AssetsLoader assetsLoader;

    private TextButton resetButton;
    private TextButtonStyle resetButtonStyle;

    private Label scoreLabel;
    private LabelStyle scoreLabelStyle;
    private BitmapFont scoreLabelFont;

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
    private static int BOARD_BUTTON_SIZE = 100;

    private static int BOARD_IMAGE_X = 200;
    private static int BOARD_IMAGE_Y = 250;

    private static int SCORE_LABEL_X = 293;
    private static int SCORE_LABEL_Y = 630;
    private static int SCORE_LABEL_FONT_SIZE = 24;

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
    }

    // Init scores label
    private void initScoreLabels() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/REFSAN.ttf"));
        FreeTypeFontParameter p = new FreeTypeFontParameter();
        p.size = SCORE_LABEL_FONT_SIZE;
        scoreLabelFont = generator.generateFont(p);
        generator.dispose();

        scoreLabelStyle = new LabelStyle();
        scoreLabelStyle.font = scoreLabelFont;

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
    }

    // Init reset game button
    private void initResetButton() {
        resetButtonStyle = new TextButtonStyle();
        resetButtonStyle.font = new BitmapFont();
        resetButton = new TextButton("Restart game", resetButtonStyle);
        resetButton.setWidth(BOARD_BUTTON_SIZE);
        resetButton.setHeight(BOARD_BUTTON_SIZE);
        resetButton.setX(10);
        resetButton.setY(590);
        resetButton.setDebug(true);

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
            _tempbutton.setWidth(BOARD_BUTTON_SIZE);
            _tempbutton.setHeight(BOARD_BUTTON_SIZE);
            _tempbutton.setX(boardButtonX[i-1]);
            _tempbutton.setY(boardButtonY[i-1]);
            stage.addActor(_tempbutton);

            final int fieldID = i;

            _tempbutton.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (gc.isSomeoneWin() == false) {
                        if (button == Input.Buttons.LEFT) {
                            gc.makeMove(fieldID, event);
                        }
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
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
